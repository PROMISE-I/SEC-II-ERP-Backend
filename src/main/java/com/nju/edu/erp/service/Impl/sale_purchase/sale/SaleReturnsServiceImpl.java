package com.nju.edu.erp.service.Impl.sale_purchase.sale;

import com.nju.edu.erp.dao.sale_purchase.sale.SaleReturnsSheetDao;
import com.nju.edu.erp.dao.sale_purchase.sale.SaleSheetDao;
import com.nju.edu.erp.dao.warehouse.WarehouseDao;
import com.nju.edu.erp.enums.sheet_state.sale.SaleReturnsSheetState;
import com.nju.edu.erp.model.po.sale_purchase.customer.CustomerPO;
import com.nju.edu.erp.model.po.sale.*;
import com.nju.edu.erp.model.po.sale_purchase.sale.SaleSheetContentPO;
import com.nju.edu.erp.model.po.sale_purchase.sale.SaleSheetPO;
import com.nju.edu.erp.model.po.sale_purchase.sale.io_detail.SaleIODetailFilterConditionPO;
import com.nju.edu.erp.model.po.sale_purchase.sale.io_detail.SaleIODetailPO;
import com.nju.edu.erp.model.po.sale_purchase.sale.returns.SaleReturnsSheetContentPO;
import com.nju.edu.erp.model.po.sale_purchase.sale.returns.SaleReturnsSheetPO;
import com.nju.edu.erp.model.po.warehouse.output.WarehouseOutputSheetContentPO;
import com.nju.edu.erp.model.po.warehouse.WarehousePO;
import com.nju.edu.erp.model.vo.warehouse.product.ProductInfoVO;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.model.vo.sale_purchase.sale.io_detail.SaleIODetailFilterConditionVO;
import com.nju.edu.erp.model.vo.sale_purchase.sale.returns.SaleReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.sale_purchase.sale.returns.SaleReturnsSheetVO;
import com.nju.edu.erp.service.Interface.sale_purchase.customer.CustomerService;
import com.nju.edu.erp.service.Interface.warehouse.product.ProductService;
import com.nju.edu.erp.service.Interface.sale_purchase.sale.SaleReturnsService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SaleReturnsServiceImpl implements SaleReturnsService {

    SaleReturnsSheetDao saleReturnsSheetDao;

    ProductService productService;

    SaleSheetDao saleSheetDao;

    CustomerService customerService;

    WarehouseDao warehouseDao;

    @Autowired
    public SaleReturnsServiceImpl(SaleReturnsSheetDao saleReturnsSheetDao, ProductService productService, SaleSheetDao saleSheetDao, CustomerService customerService, WarehouseDao warehouseDao) {
        this.saleReturnsSheetDao = saleReturnsSheetDao;
        this.productService = productService;
        this.saleSheetDao = saleSheetDao;
        this.customerService = customerService;
        this.warehouseDao = warehouseDao;
    }

    /**
     * 制定销售退货单
     * @param userVO 制定单据人员
     * @param saleReturnsSheetVO 销售退货单
     */
    @Override
    @Transactional
    public void makeSaleReturnsSheet(UserVO userVO, SaleReturnsSheetVO saleReturnsSheetVO) {
        SaleReturnsSheetPO saleReturnsSheetPO = new SaleReturnsSheetPO();
        BeanUtils.copyProperties(saleReturnsSheetVO, saleReturnsSheetPO);
        // 此处根据制定单据人员确定操作员
        saleReturnsSheetPO.setOperator(userVO.getName());
        saleReturnsSheetPO.setCreateTime(new Date());
        SaleReturnsSheetPO latest = saleReturnsSheetDao.getLatest();
        String id = saleReturnsSheetVO.getId();
        //如果id为null说明是新建单据，否则为红冲或红冲并复制
        if (id == null) {
            id = IdGenerator.generateSheetId(latest == null? null : latest.getId(), "XSTHD");
        }
        saleReturnsSheetPO.setId(id);
        saleReturnsSheetPO.setState(SaleReturnsSheetState.PENDING_LEVEL_1);
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<SaleSheetContentPO> saleSheetContent = saleSheetDao.findContentBySheetId(saleReturnsSheetPO.getSaleSheetId());
        Map<String, SaleSheetContentPO> map = new HashMap<>();
        for (SaleSheetContentPO item : saleSheetContent) {
            map.put(item.getPid(), item);
        }
        List<SaleReturnsSheetContentPO> sRContentPOList = new ArrayList<>();
        for (SaleReturnsSheetContentVO content : saleReturnsSheetVO.getSaleReturnsSheetContent()) {
            SaleReturnsSheetContentPO sContentPO = new SaleReturnsSheetContentPO();
            BeanUtils.copyProperties(content, sContentPO);
            sContentPO.setSaleReturnsSheetId(id);
            SaleSheetContentPO item = map.get(sContentPO.getPid());
            sContentPO.setUnitPrice(item.getUnitPrice());

            BigDecimal unitPrice = sContentPO.getUnitPrice();
            sContentPO.setTotalPrice(unitPrice.multiply(BigDecimal.valueOf(sContentPO.getQuantity())));
            sRContentPOList.add(sContentPO);
            totalAmount = totalAmount.add(sContentPO.getTotalPrice());
        }
        saleReturnsSheetDao.saveBatch(sRContentPOList);

        saleReturnsSheetPO.setRawTotalAmount(totalAmount);
        SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleReturnsSheetPO.getSaleSheetId());
        saleReturnsSheetPO.setDiscount(saleSheetPO.getDiscount());
        saleReturnsSheetPO.setVoucherAmount(saleSheetPO.getVoucherAmount()
                .multiply(saleReturnsSheetPO.getRawTotalAmount())
                .divide(saleSheetPO.getRawTotalAmount(), 2, BigDecimal.ROUND_DOWN)
        );
        saleReturnsSheetPO.setFinalAmount(saleReturnsSheetPO.getRawTotalAmount()
                .multiply(saleReturnsSheetPO.getDiscount())
                .subtract(saleReturnsSheetPO.getVoucherAmount())
        );

        saleReturnsSheetDao.save(saleReturnsSheetPO);
    }

    /**
     * 根据状态获取进货退货单[不包括content信息](state == null 则获取所有进货退货单)
     *
     * @param state 销售退货单的状态
     * @return 销售退货单
     */
    @Override
    @Transactional
    public List<SaleReturnsSheetVO> getSaleReturnsSheetByState(SaleReturnsSheetState state) {
        List<SaleReturnsSheetVO> res = new ArrayList<>();
        List<SaleReturnsSheetPO> all;
        if (state == null) {
            all = saleReturnsSheetDao.findAll();
        } else {
          all = saleReturnsSheetDao.findAllByState(state);
        }
        for (SaleReturnsSheetPO po : all) {
            SaleReturnsSheetVO vo = new SaleReturnsSheetVO();
            BeanUtils.copyProperties(po, vo);
            List<SaleReturnsSheetContentPO> alll = saleReturnsSheetDao.findContentBySaleReturnsSheetId(po.getId());
            List<SaleReturnsSheetContentVO> vos = new ArrayList<>();
            for (SaleReturnsSheetContentPO p : alll) {
                SaleReturnsSheetContentVO v = new SaleReturnsSheetContentVO();
                BeanUtils.copyProperties(p, v);
                vos.add(v);
            }
            vo.setSaleReturnsSheetContent(vos);
            res.add(vo);
        }
        return res;
    }

    /**
     * 根据销售退货单id进行审批(state == "待二级审批"/"审批完成"/"审批失败")
     * 在controller层进行权限控制
     *
     * @param saleReturnsSheetId 销售退货单id
     * @param state 销售退货单修改后的状态
     */
    @Override
    @Transactional
    public void approval(String saleReturnsSheetId, SaleReturnsSheetState state) {
        SaleReturnsSheetPO saleReturnsSheet = saleReturnsSheetDao.findOneById(saleReturnsSheetId);
        if (state.equals(SaleReturnsSheetState.FAILURE)) {
            if (saleReturnsSheet.getState() == SaleReturnsSheetState.SUCCESS) throw new RuntimeException("状态更新失败");
            int effectLines = saleReturnsSheetDao.updateState(saleReturnsSheetId, state);
            if(effectLines == 0) throw new RuntimeException("状态更新失败");
        } else {
            SaleReturnsSheetState prevState;
            if (state.equals(SaleReturnsSheetState.SUCCESS)) {
                prevState = SaleReturnsSheetState.PENDING_LEVEL_2;
            } else if (state.equals(SaleReturnsSheetState.PENDING_LEVEL_2)) {
                prevState = SaleReturnsSheetState.PENDING_LEVEL_1;
            } else {
                throw new RuntimeException("状态更新失败");
            }
            int effectLines = saleReturnsSheetDao.updateStateV2(saleReturnsSheetId, prevState, state);
            if (effectLines == 0) throw new RuntimeException("状态更新失败");
            if (state.equals(SaleReturnsSheetState.SUCCESS)) {
                // TODO 审批完成, 修改一系列状态
                // 销售退货单id， 关联的销售单id 【   销售退货单id->销售单id->出库单id->好多批次id】
                List<SaleReturnsSheetContentPO> saleReturnsSheetContentPOS = saleReturnsSheetDao.findContentBySaleReturnsSheetId(saleReturnsSheetId);

                for (SaleReturnsSheetContentPO sRSContentPO : saleReturnsSheetContentPOS) {
                    String pid = sRSContentPO.getPid();
                    Integer quantity = sRSContentPO.getQuantity();
                    List<WarehouseOutputSheetContentPO> batchIdAndQuantities = saleReturnsSheetDao.findAllWarehouseOutputSheetContentBySaleReturnsSheetIdAndPid(saleReturnsSheetId, pid);
                    for (WarehouseOutputSheetContentPO woscPO : batchIdAndQuantities) {
                        Integer batchId = woscPO.getBatchId();
                        Integer quantityInThisBatch = woscPO.getQuantity();
                        WarehousePO warehousePO = warehouseDao.findOneByPidAndBatchId(pid, batchId);
                        if(warehousePO == null) throw new RuntimeException("单据发生错误！请联系管理员！");
                        if (quantity <= quantityInThisBatch) {
                            warehousePO.setQuantity(quantity);
                            warehouseDao.addQuantity(warehousePO);
                            ProductInfoVO productInfoVO = productService.getOneProductByPid(pid);
                            productInfoVO.setQuantity(productInfoVO.getQuantity() + quantity);
                            productService.updateProduct(productInfoVO);
                            quantity = 0;
                            break;
                        } else {
                            warehousePO.setQuantity(quantityInThisBatch);
                            warehouseDao.addQuantity(warehousePO);
                            ProductInfoVO productInfoVO = productService.getOneProductByPid(pid);
                            productInfoVO.setQuantity(productInfoVO.getQuantity() + quantityInThisBatch);
                            productService.updateProduct(productInfoVO);
                            quantity -= quantityInThisBatch;
                        }
                    }
                    if (quantity > 0) throw new RuntimeException("退货商品数量超出所购！审批失败！");
                }

                // 【销售退货单id & 销售单id -> 客户receivable减去已经收了的钱】
                SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleReturnsSheet.getSaleSheetId());
                Integer supplier = saleSheetPO.getSupplier();
                CustomerPO customer = customerService.findCustomerById(supplier);

                customer.setReceivable(customer.getReceivable().subtract(saleReturnsSheet.getFinalAmount()));
                customerService.updateCustomer(customer);
            }

        }

    }

    @Override
    public List<SaleIODetailPO> getSaleReturnsDetailByCondition(SaleIODetailFilterConditionVO condition) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SaleIODetailFilterConditionPO conditionPO = new SaleIODetailFilterConditionPO();

        BeanUtils.copyProperties(condition, conditionPO);
        conditionPO.setBeginDate(dateFormat.parse(condition.getBeginDateStr()));
        conditionPO.setEndDate(dateFormat.parse(condition.getEndDateStr()));

        return saleReturnsSheetDao.getSaleReturnsDetailByCondition(conditionPO);
    }

    @Override
    public List<SaleIODetailPO> getSaleReturnsDetailByRange(String beginTimeStr, String endTimeStr) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = dateFormat.parse(beginTimeStr);
        Date endDate = dateFormat.parse(endTimeStr);

        return saleReturnsSheetDao.getSaleReturnsDetailByRange(beginDate, endDate);
    }

    @Override
    public BigDecimal getTotalSaleReturnsAmountByMonthAndYearAndSalesman(int year, int month, String salesman) {
        BigDecimal saleReturnsAmount = saleReturnsSheetDao.getTotalSaleReturnsAmountByMonthAndYearAndSalesman(year, month, salesman);
        return saleReturnsAmount == null ? BigDecimal.ZERO : saleReturnsAmount;
    }

    @Override
    public boolean isSheetExists(String sheetId) {
        return saleReturnsSheetDao.findOneById(sheetId) != null;
    }
}
