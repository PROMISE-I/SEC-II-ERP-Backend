package com.nju.edu.erp.service.Impl.sale;

import com.nju.edu.erp.dao.customer.CustomerDao;
import com.nju.edu.erp.dao.warehouse.product.ProductDao;
import com.nju.edu.erp.dao.sale.SaleSheetDao;
import com.nju.edu.erp.enums.sheet_state.gifts.GiveAwaySheetState;
import com.nju.edu.erp.enums.sheet_state.sale.SaleSheetState;
import com.nju.edu.erp.model.po.customer.CustomerPO;
import com.nju.edu.erp.model.po.customer.CustomerPurchaseAmountPO;
import com.nju.edu.erp.model.po.promotion.gifts.GiveAwaySheetPO;
import com.nju.edu.erp.model.po.sale.io_detail.SaleIODetailFilterConditionPO;
import com.nju.edu.erp.model.po.sale.io_detail.SaleIODetailPO;
import com.nju.edu.erp.model.po.sale.SaleSheetContentPO;
import com.nju.edu.erp.model.po.sale.SaleSheetPO;
import com.nju.edu.erp.model.po.warehouse.product.ProductPO;
import com.nju.edu.erp.model.vo.warehouse.product.ProductInfoVO;
import com.nju.edu.erp.model.vo.promotion.level.LevelPromotionStrategyVO;
import com.nju.edu.erp.model.vo.sale.io_detail.SaleIODetailFilterConditionVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.model.vo.warehouse.output.WarehouseOutputFormContentVO;
import com.nju.edu.erp.model.vo.warehouse.output.WarehouseOutputFormVO;
import com.nju.edu.erp.service.Impl.strategy.promotion.discount.DiscountStrategy;
import com.nju.edu.erp.service.Impl.strategy.promotion.discount.LevelPromotionDiscountStrategy;
import com.nju.edu.erp.service.Impl.strategy.promotion.give_away.GiveAwayStrategy;
import com.nju.edu.erp.service.Impl.strategy.promotion.give_away.LevelPromotionGiveAwayStrategy;
import com.nju.edu.erp.service.Impl.strategy.promotion.give_away.TotalPricePromotionGiveAwayStrategy;
import com.nju.edu.erp.service.Impl.strategy.promotion.voucher.CombinatorialPromotionVoucherStrategy;
import com.nju.edu.erp.service.Impl.strategy.promotion.voucher.LevelPromotionVoucherStrategy;
import com.nju.edu.erp.service.Impl.strategy.promotion.voucher.TotalPricePromotionVoucherStrategy;
import com.nju.edu.erp.service.Impl.strategy.promotion.voucher.VoucherStrategy;
import com.nju.edu.erp.service.Interface.customer.CustomerService;
import com.nju.edu.erp.service.Interface.promotion.gifts.GiveAwayService;
import com.nju.edu.erp.service.Interface.promotion.CombinatorialPromotionService;
import com.nju.edu.erp.service.Interface.promotion.LevelPromotionService;
import com.nju.edu.erp.service.Interface.promotion.TotalPricePromotionService;
import com.nju.edu.erp.service.Interface.sale.SaleService;
import com.nju.edu.erp.service.Interface.warehouse.WarehouseService;
import com.nju.edu.erp.service.Interface.warehouse.product.ProductService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleSheetDao saleSheetDao;

    private final ProductDao productDao;

    private final CustomerDao customerDao;

    private final ProductService productService;

    private final CustomerService customerService;

    private final WarehouseService warehouseService;

    private final TotalPricePromotionService totalPricePromotionService;

    private final GiveAwayService giveAwayService;

    private final LevelPromotionService levelPromotionService;

    private final CombinatorialPromotionService combinatorialPromotionService;




    @Autowired
    public SaleServiceImpl(CombinatorialPromotionService combinatorialPromotionService, SaleSheetDao saleSheetDao, ProductDao productDao, CustomerDao customerDao, ProductService productService, CustomerService customerService, WarehouseService warehouseService, TotalPricePromotionService totalPricePromotionService, GiveAwayService giveAwayService, LevelPromotionService levelPromotionService) {
        this.saleSheetDao = saleSheetDao;
        this.productDao = productDao;
        this.customerDao = customerDao;
        this.productService = productService;
        this.customerService = customerService;
        this.warehouseService = warehouseService;
        this.totalPricePromotionService = totalPricePromotionService;
        this.giveAwayService = giveAwayService;
        this.levelPromotionService = levelPromotionService;
        this.combinatorialPromotionService = combinatorialPromotionService;
    }

    @Override
    @Transactional
    public void makeSaleSheet(UserVO userVO, SaleSheetVO saleSheetVO) {
        // 需要持久化销售单（SaleSheet）和销售单content（SaleSheetContent），其中总价或者折后价格的计算需要在后端进行
        // 需要的service和dao层相关方法均已提供，可以不用自己再实现一遍
        SaleSheetPO saleSheetPO = new SaleSheetPO();
        BeanUtils.copyProperties(saleSheetVO, saleSheetPO);

        saleSheetPO.setOperator(userVO.getName());
        saleSheetPO.setCreateTime(new Date());
        SaleSheetPO latest = saleSheetDao.getLatestSheet();
        String id = saleSheetVO.getId();
        //如果id为null说明是新建单据，否则为红冲或红冲并复制
        if (id == null) {
            id = IdGenerator.generateSheetId(latest == null? null : latest.getId(), "XSD");
        }
        saleSheetPO.setId(id);
        saleSheetPO.setState(SaleSheetState.PENDING_LEVEL_1);
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<SaleSheetContentPO> saleSheetContentPOList = new ArrayList<>();
        for (SaleSheetContentVO saleSheetContentVO : saleSheetVO.getSaleSheetContent()) {
            SaleSheetContentPO saleSheetContentPO = new SaleSheetContentPO();
            BeanUtils.copyProperties(saleSheetContentVO, saleSheetContentPO);
            saleSheetContentPO.setSaleSheetId(id);
            BigDecimal unitPrice = saleSheetContentVO.getUnitPrice();
            if (unitPrice == null) {
                ProductPO productPO = productDao.findById(saleSheetContentVO.getPid());
                unitPrice = productPO.getPurchasePrice();
                saleSheetContentPO.setUnitPrice(unitPrice);
            }
            saleSheetContentPO.setTotalPrice(unitPrice.multiply(BigDecimal.valueOf(saleSheetContentPO.getQuantity())));
            saleSheetContentPOList.add(saleSheetContentPO);
            totalAmount = totalAmount.add(saleSheetContentPO.getTotalPrice());
        }

        saleSheetDao.saveBatchSheetContent(saleSheetContentPOList);
        saleSheetPO.setRawTotalAmount(totalAmount);

        //TODO 促销策略的折扣和优惠券作用地方
        Integer customer = saleSheetPO.getSupplier();
        Integer level = customerService.findCustomerById(customer).getLevel();
        saleSheetPO.setDiscount(getDiscount(level));
        saleSheetPO.setVoucherAmount(getVoucherAmount(saleSheetPO));
        makeGiveAway(saleSheetPO);

        BigDecimal finalAmount = totalAmount.multiply(saleSheetPO.getDiscount()).subtract(saleSheetPO.getVoucherAmount());
        saleSheetPO.setFinalAmount(finalAmount);
        saleSheetDao.saveSheet(saleSheetPO);
    }

    @Override
    @Transactional
    public List<SaleSheetVO> getSaleSheetByState(SaleSheetState state) {
        // 根据单据状态获取销售单（注意：VO包含SaleSheetContent）
        // 依赖的dao层部分方法未提供，需要自己实现
        List<SaleSheetVO> res = new ArrayList<>();
        List<SaleSheetPO> all;
        if (state == null) {
            all = saleSheetDao.findAllSheet();
        } else {
            all = saleSheetDao.findAllByState(state);
        }
        for (SaleSheetPO saleSheetPO : all) {
            SaleSheetVO saleSheetVO = new SaleSheetVO();
            BeanUtils.copyProperties(saleSheetPO, saleSheetVO);
            List<SaleSheetContentPO> saleSheetContentPOList = saleSheetDao.findContentBySheetId(saleSheetPO.getId());
            List<SaleSheetContentVO> saleSheetContentVOList = new ArrayList<>();
            for (SaleSheetContentPO po : saleSheetContentPOList) {
                SaleSheetContentVO vo = new SaleSheetContentVO();
                BeanUtils.copyProperties(po, vo);
                saleSheetContentVOList.add(vo);
            }
            saleSheetVO.setSaleSheetContent(saleSheetContentVOList);
            res.add(saleSheetVO);
        }
        return res;
    }

    /**
     * 根据销售单id进行审批(state == "待二级审批"/"审批完成"/"审批失败")
     * 在controller层进行权限控制
     *
     * @param saleSheetId 销售单id
     * @param state       销售单要达到的状态
     */
    @Override
    @Transactional
    public void approval(String saleSheetId, SaleSheetState state) {
        // 需要的service和dao层相关方法均已提供，可以不用自己再实现一遍
        /* 一些注意点：
            1. 二级审批成功之后需要进行
                 1. 修改单据状态
                 2. 更新商品表
                 3. 更新客户表
                 4. 新建出库草稿
            2. 一级审批状态不能直接到审批完成状态； 二级审批状态不能回到一级审批状态
         */
        if (state.equals(SaleSheetState.FAILURE)) {
            SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleSheetId);
            if (saleSheetPO.getState() == SaleSheetState.SUCCESS) throw new RuntimeException("状态更新失败");
            int effectLines = saleSheetDao.updateSheetState(saleSheetId, state);
            if (effectLines == 0) throw new RuntimeException("状态更新失败");
        } else {
            SaleSheetState prevState;
            if (state.equals(SaleSheetState.SUCCESS)) {
                prevState = SaleSheetState.PENDING_LEVEL_2;
            } else if (state.equals(SaleSheetState.PENDING_LEVEL_2)) {
                prevState = SaleSheetState.PENDING_LEVEL_1;
            } else {
                throw new RuntimeException("状态更新失败");
            }
            int effectLines = saleSheetDao.updateSheetStateOnPrev(saleSheetId, prevState, state);
            if (effectLines == 0) throw new RuntimeException("状态更新失败");
            if (state.equals(SaleSheetState.SUCCESS)) {
                //审批成功
                //1. 更新商品表(recentRp)
                //2. 更新客户表(receivable)
                //3. 新建出库草稿
                List<SaleSheetContentPO> saleSheetContentPOS = saleSheetDao.findContentBySheetId(saleSheetId);
                List<WarehouseOutputFormContentVO> warehouseOutputFormContentVOS = new ArrayList<>();

                for (SaleSheetContentPO saleSheetContentPO : saleSheetContentPOS) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    productInfoVO.setId(saleSheetContentPO.getPid());
                    productInfoVO.setRecentRp(saleSheetContentPO.getUnitPrice());
                    productService.updateProduct(productInfoVO);

                    WarehouseOutputFormContentVO woContentVO = new WarehouseOutputFormContentVO();
                    woContentVO.setSalePrice(saleSheetContentPO.getUnitPrice());
                    woContentVO.setQuantity(saleSheetContentPO.getQuantity());
                    woContentVO.setRemark(saleSheetContentPO.getRemark());
                    woContentVO.setPid(saleSheetContentPO.getPid());
                    warehouseOutputFormContentVOS.add(woContentVO);
                }

                //更新客户列表(receivable)
                SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleSheetId);
                CustomerPO customer = customerDao.findOneById(saleSheetPO.getSupplier());
                customer.setReceivable(customer.getReceivable().add(saleSheetPO.getFinalAmount()));
                customerService.updateCustomer(customer);

                //新建出库草稿
                WarehouseOutputFormVO warehouseOutputFormVO = new WarehouseOutputFormVO();
                warehouseOutputFormVO.setOperator(null);// 暂时不填操作人(确认草稿单的时候填写)
                warehouseOutputFormVO.setSaleSheetId(saleSheetId);
                warehouseOutputFormVO.setList(warehouseOutputFormContentVOS);
                warehouseService.productOutOfWarehouse(warehouseOutputFormVO);

                //将对应赠送单的状态由PENDING_SALE_SHEET_APPROVAL_SUCCESS变成PENDING_LEVEL_1
                GiveAwaySheetPO giveAwaySheet = giveAwayService.getSheetBySaleSheetId(saleSheetId);
                if (giveAwaySheet != null) {
                    if (giveAwaySheet.getState().equals(GiveAwaySheetState.PENDING_SALE_SHEET_APPROVAL_SUCCESS)) {
                        giveAwayService.approval(giveAwaySheet.getId(), GiveAwaySheetState.PENDING_LEVEL_1);
                    } else {
                        throw new RuntimeException("赠送单状态异常！销售单审批失败！请联系管理员!");
                    }
                }
            }
        }
    }

    /**
     * 获取某个销售人员某段时间内消费总金额最大的客户(不考虑退货情况,销售单不需要审批通过,如果这样的客户有多个，仅保留一个)
     * @param salesman 销售人员的名字
     * @param beginDateStr 开始时间字符串
     * @param endDateStr 结束时间字符串
     * @return
     */
    public CustomerPurchaseAmountPO getMaxAmountCustomerOfSalesmanByTime(String salesman, String beginDateStr, String endDateStr){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Date beginTime =dateFormat.parse(beginDateStr);
            Date endTime=dateFormat.parse(endDateStr);
            if(beginTime.compareTo(endTime)>0){
                return null;
            }else{
                return saleSheetDao.getMaxAmountCustomerOfSalesmanByTime(salesman,beginTime,endTime);
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据销售单Id搜索销售单信息
     * @param saleSheetId 销售单Id
     * @return 销售单全部信息
     */
    @Override
    public SaleSheetVO getSaleSheetById(String saleSheetId) {
        SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleSheetId);
        if(saleSheetPO == null) return null;
        List<SaleSheetContentPO> contentPO = saleSheetDao.findContentBySheetId(saleSheetId);
        SaleSheetVO sVO = new SaleSheetVO();
        BeanUtils.copyProperties(saleSheetPO, sVO);
        List<SaleSheetContentVO> saleSheetContentVOList = new ArrayList<>();
        for (SaleSheetContentPO content:
                contentPO) {
            SaleSheetContentVO sContentVO = new SaleSheetContentVO();
            BeanUtils.copyProperties(content, sContentVO);
            saleSheetContentVOList.add(sContentVO);
        }
        sVO.setSaleSheetContent(saleSheetContentVOList);
        return sVO;
    }

    @Override
    public List<SaleIODetailPO> getSaleDetailByCondition(SaleIODetailFilterConditionVO condition) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SaleIODetailFilterConditionPO conditionPO = new SaleIODetailFilterConditionPO();

        BeanUtils.copyProperties(condition, conditionPO);
        conditionPO.setBeginDate(dateFormat.parse(condition.getBeginDateStr()));
        conditionPO.setEndDate(dateFormat.parse(condition.getEndDateStr()));

        return saleSheetDao.getSaleDetailByCondition(conditionPO);
    }

    @Override
    public List<SaleIODetailPO> getSaleDetailByRange(String beginTimeStr, String endTimeStr) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = dateFormat.parse(beginTimeStr);
        Date endDate = dateFormat.parse(endTimeStr);

        return saleSheetDao.getSaleDetailByRange(beginDate, endDate);
    }

    @Override
    public BigDecimal getTotalSaleAmountByMonthAndYearAndSalesman(Integer year, Integer month, String salesman) {
        BigDecimal totalSaleAmount =  saleSheetDao.getTotalSaleAmountByMonthAndYearAndSalesman(year, month, salesman);
        return totalSaleAmount == null ? BigDecimal.ZERO : totalSaleAmount;
    }

    @Override
    public boolean isSheetExists(String sheetId) {
        return saleSheetDao.findSheetById(sheetId) != null;
    }

    private BigDecimal getDiscount(Integer level) {
        BigDecimal totalDiscount = BigDecimal.ONE;
        List<DiscountStrategy> strategies = new ArrayList<>();
        //TODO 定义自己的构造函数并在这边修改，如果需要额外参数可以在这个函数的参数列表中加
        LevelPromotionStrategyVO levelPromotionStrategyVO = levelPromotionService.findByLevel(level);
        LevelPromotionDiscountStrategy levelPromotionDiscountStrategy = new LevelPromotionDiscountStrategy(levelPromotionStrategyVO.getDiscount());
        strategies.add(levelPromotionDiscountStrategy);

        for (DiscountStrategy strategy : strategies) {
            totalDiscount = totalDiscount.multiply(strategy.getDiscount());
        }

        return totalDiscount;
    }

    private BigDecimal getVoucherAmount(SaleSheetPO saleSheetPO) {
        BigDecimal totalVoucherAmount  = BigDecimal.ZERO;
        List<VoucherStrategy> strategies = new ArrayList<>();

        TotalPricePromotionVoucherStrategy totalPricePromotionVoucherStrategy = new TotalPricePromotionVoucherStrategy(totalPricePromotionService, saleSheetPO);
        strategies.add(totalPricePromotionVoucherStrategy);
        //TODO 定义自己的构造函数并在这边修改，如果需要额外参数可以在这个函数的参数列表中加
        Integer customer = saleSheetPO.getSupplier();
        Integer level = customerService.findCustomerById(customer).getLevel();
        BigDecimal voucher = levelPromotionService.findByLevel(level).getCoupon();
        LevelPromotionVoucherStrategy levelPromotionVoucherStrategy = new LevelPromotionVoucherStrategy(voucher);
        strategies.add(levelPromotionVoucherStrategy);
        //TODO 定义自己的构造函数并在这边修改，如果需要额外参数可以在这个函数的参数列表中加
        String saleSheetId = saleSheetPO.getId();
        List<SaleSheetContentPO> saleSheetContentPOList = saleSheetDao.findContentBySheetId(saleSheetId);
        BigDecimal voucherAmount = BigDecimal.ZERO;
        for(int i = 0; i < saleSheetContentPOList.size(); i ++){
            for(int j = i + 1; j < saleSheetContentPOList.size(); j ++){
                String productOneId = saleSheetContentPOList.get(i).getPid();
                String productTwoId = saleSheetContentPOList.get(j).getPid();
                BigDecimal temp = combinatorialPromotionService.findByPair(productOneId, productTwoId).getDiscountAmount();
                if(temp.compareTo(voucherAmount) > 0)voucherAmount = temp;
            }
        }
        CombinatorialPromotionVoucherStrategy combinatorialPromotionVoucherStrategy = new CombinatorialPromotionVoucherStrategy(voucherAmount);
        strategies.add(combinatorialPromotionVoucherStrategy);

        for (VoucherStrategy strategy : strategies) {
            totalVoucherAmount = totalVoucherAmount.add(strategy.getVoucherAmount());
        }
        return totalVoucherAmount;
    }

    private void makeGiveAway(SaleSheetPO saleSheetPO) {
        List<GiveAwayStrategy> strategies = new ArrayList<>();

        TotalPricePromotionGiveAwayStrategy totalPricePromotionGiveAwayStrategy = new TotalPricePromotionGiveAwayStrategy(totalPricePromotionService, giveAwayService, saleSheetPO);
        strategies.add(totalPricePromotionGiveAwayStrategy);
        //TODO 定义自己的构造函数并在这边修改，如果需要额外参数可以在这个函数的参数列表中加
        Integer customer = saleSheetPO.getSupplier();
        Integer level = customerService.findCustomerById(customer).getLevel();
        LevelPromotionGiveAwayStrategy levelPromotionGiveAwayStrategy = new LevelPromotionGiveAwayStrategy(giveAwayService, levelPromotionService.findPresentInfoByLevel(level), saleSheetPO.getId(), productService);
        strategies.add(levelPromotionGiveAwayStrategy);

        for (GiveAwayStrategy strategy : strategies) {
            strategy.makeGiveAwaySheet();
        }
    }
}
