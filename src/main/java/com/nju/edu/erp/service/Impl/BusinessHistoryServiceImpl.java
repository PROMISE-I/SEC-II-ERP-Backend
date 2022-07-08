package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.po.finance.PayMoneySheetPO;
import com.nju.edu.erp.model.po.finance.ReceiveMoneySheetPO;
import com.nju.edu.erp.model.po.finance.SalarySheetPO;
import com.nju.edu.erp.model.vo.BusinessHistoryQueryVO;
import com.nju.edu.erp.model.vo.BusinessHistorySheetVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetContentVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.service.BusinessHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessHistoryServiceImpl implements BusinessHistoryService {
    private final BusinessHistoryDao businessHistoryDao;
    private final SaleSheetDao saleSheetDao;
    private final SaleReturnsSheetDao saleReturnsSheetDao;
    private final PurchaseSheetDao purchaseSheetDao;
    private final PurchaseReturnsSheetDao purchaseReturnsSheetDao;

    @Autowired
    public BusinessHistoryServiceImpl(PurchaseReturnsSheetDao purchaseReturnsSheetDao,BusinessHistoryDao businessHistoryDao, SaleSheetDao saleSheetDao, SaleReturnsSheetDao saleReturnsSheetDao, PurchaseSheetDao purchaseSheetDao){
        this.businessHistoryDao = businessHistoryDao;
        this.saleSheetDao = saleSheetDao;
        this.saleReturnsSheetDao = saleReturnsSheetDao;
        this.purchaseSheetDao = purchaseSheetDao;
        this.purchaseReturnsSheetDao = purchaseReturnsSheetDao;
    }

    @Override
    public List<BusinessHistorySheetVO> findAll(BusinessHistoryQueryVO businessHistoryQueryVO) {
        List<BusinessHistorySheetVO> businessHistorySheetVOList = new ArrayList<>();
        String begin = businessHistoryQueryVO.getBegin();
        String end = businessHistoryQueryVO.getEnd();
        String operator = businessHistoryQueryVO.getOperator();
        Integer customer = businessHistoryQueryVO.getCustomer();
        String type = businessHistoryQueryVO.getType();
        //销售类
        if("sale".equals(type)){
            List<SaleSheetPO> lst = businessHistoryDao.findAllSaleSheetByInterval(begin, end, operator, customer);
            handleSaleSheet(businessHistorySheetVOList,lst);

        }else if("sale-returns".equals(type)){
            List<SaleReturnsSheetPO> lst = businessHistoryDao.findAllSaleReturnsSheetByInterval(begin, end, operator, customer);
            handleSaleReturns(businessHistorySheetVOList, lst);
        }
        //进货类
        else if("purchase".equals(type)){
            List<PurchaseSheetPO> lst = businessHistoryDao.findAllPurchaseSheetByInterval(begin, end, operator);
            handlePurchase(businessHistorySheetVOList, lst);
        }else if("purchase-returns".equals(type)){
            List<PurchaseReturnsSheetPO> lst = businessHistoryDao.findAllPurchaseReturnsSheetByInterval(begin, end, operator);
            handlePurchaseReturns(businessHistorySheetVOList, lst);
        }
        //财务类
        else if("pay".equals(type)){
            List<PayMoneySheetPO> lst = businessHistoryDao.findAllPayMoneySheetByInterval(begin, end, operator, customer);
            for(PayMoneySheetPO pmsp: lst){
                BusinessHistorySheetVO businessHistorySheetVO = new BusinessHistorySheetVO("pay", pmsp);
                businessHistorySheetVOList.add(businessHistorySheetVO);
            }
        }else if("receive".equals(type)){
            List<ReceiveMoneySheetPO> lst = businessHistoryDao.findAllReceiveMoneySheetByInterval(begin, end, operator, customer);
            for(ReceiveMoneySheetPO rmsp: lst){
                BusinessHistorySheetVO businessHistorySheetVO = new BusinessHistorySheetVO("receive", rmsp);
                businessHistorySheetVOList.add(businessHistorySheetVO);
            }
        }else if("salary".equals(type)){
            List<SalarySheetPO> lst = businessHistoryDao.findAllSalarySheetByInterval(begin, end);
            for(SalarySheetPO ssp: lst){
                BusinessHistorySheetVO businessHistorySheetVO = new BusinessHistorySheetVO("salary", ssp);
                businessHistorySheetVOList.add(businessHistorySheetVO);
            }
        }
        //库存类

        return businessHistorySheetVOList;
    }

    private void handleSaleSheet(List<BusinessHistorySheetVO> ans, List<SaleSheetPO> lst){
        for(SaleSheetPO ssp : lst){
            //对每个销售单，找到对应的明细列表
            SaleSheetVO ssv = new SaleSheetVO();
            ssv.setId(ssp.getId());
            ssv.setDiscount(ssp.getDiscount());
            ssv.setFinalAmount(ssp.getFinalAmount());
            ssv.setOperator(ssp.getOperator());
            ssv.setRawTotalAmount(ssp.getRawTotalAmount());
            ssv.setSupplier(ssp.getSupplier());
            ssv.setSalesman(ssp.getSalesman());
            ssv.setRemark(ssp.getRemark());
            ssv.setState(ssp.getState());
            ssv.setVoucherAmount(ssp.getVoucherAmount());
            List<SaleSheetContentPO> sscpLst = saleSheetDao.findContentBySheetId(ssp.getId());
            List<SaleSheetContentVO> sscvLst = new ArrayList<>();
            for(SaleSheetContentPO sscp : sscpLst){
                SaleSheetContentVO sscv = new SaleSheetContentVO();
                sscv.setPid(sscp.getPid());
                sscv.setRemark(sscp.getRemark());
                sscv.setQuantity(sscp.getQuantity());
                sscv.setTotalPrice(sscp.getTotalPrice());
                sscv.setUnitPrice(sscp.getUnitPrice());
                sscvLst.add(sscv);
            }
            ssv.setSaleSheetContent(sscvLst);
            BusinessHistorySheetVO businessHistorySheetVO = new BusinessHistorySheetVO("sale", ssv);
            ans.add(businessHistorySheetVO);
        }
    }

    private void handleSaleReturns(List<BusinessHistorySheetVO> ans, List<SaleReturnsSheetPO> lst){
        //对每个销售退货单，设置销售退货列表
        for(SaleReturnsSheetPO srsp : lst){
            SaleReturnsSheetVO srsv = new SaleReturnsSheetVO();
            srsv.setId(srsp.getId());
            srsv.setDiscount(srsp.getDiscount());
            srsv.setOperator(srsp.getOperator());
            srsv.setRemark(srsp.getRemark());
            srsv.setCreateTime(srsp.getCreateTime());
            srsv.setFinalAmount(srsp.getFinalAmount());
            srsv.setState(srsp.getState());
            srsv.setRawTotalAmount(srsp.getRawTotalAmount());
            srsv.setVoucherAmount(srsp.getVoucherAmount());
            srsv.setSaleSheetId(srsp.getSaleSheetId());
            List<SaleReturnsSheetContentPO> srscpLst = saleReturnsSheetDao.findContentBySaleReturnsSheetId(srsv.getId());
            List<SaleReturnsSheetContentVO> srscvLst = new ArrayList<>();
            for(SaleReturnsSheetContentPO srscp : srscpLst){
                SaleReturnsSheetContentVO srscv = new SaleReturnsSheetContentVO();
                srscv.setPid(srscp.getPid());
                srscv.setRemark(srscp.getRemark());
                srscv.setQuantity(srscp.getQuantity());
                srscv.setTotalPrice(srscp.getTotalPrice());
                srscv.setUnitPrice(srscp.getUnitPrice());
                srscvLst.add(srscv);
            }
            srsv.setSaleReturnsSheetContent(srscvLst);
            BusinessHistorySheetVO businessHistorySheetVO = new BusinessHistorySheetVO("sale-returns",srsv);
            ans.add(businessHistorySheetVO);
        }
    }

    private void handlePurchase(List<BusinessHistorySheetVO> ans, List<PurchaseSheetPO> lst){
        for(PurchaseSheetPO psp: lst){
            PurchaseSheetVO psv = new PurchaseSheetVO();
            psv.setId(psp.getId());
            psv.setOperator(psp.getOperator());
            psv.setRemark(psp.getRemark());
            psv.setState(psp.getState());
            psv.setSupplier(psp.getSupplier());
            psv.setTotalAmount(psp.getTotalAmount());
            List<PurchaseSheetContentPO> pscpLst = purchaseSheetDao.findContentByPurchaseSheetId(psv.getId());
            List<PurchaseSheetContentVO> pscvLst = new ArrayList<>();
            for(PurchaseSheetContentPO pscp: pscpLst){
                PurchaseSheetContentVO pscv = new PurchaseSheetContentVO();
                pscv.setId(pscp.getId());
                pscv.setRemark(pscp.getRemark());
                pscv.setPid(pscp.getPid());
                pscv.setQuantity(pscp.getQuantity());
                pscv.setTotalPrice(pscp.getTotalPrice());
                pscv.setUnitPrice(pscp.getUnitPrice());
                pscv.setPurchaseSheetId(pscp.getPurchaseSheetId());
                pscvLst.add(pscv);
            }
            psv.setPurchaseSheetContent(pscvLst);
            BusinessHistorySheetVO businessHistorySheetVO = new BusinessHistorySheetVO("purchase", psv);
            ans.add(businessHistorySheetVO);
        }

    }

    private void handlePurchaseReturns(List<BusinessHistorySheetVO> ans, List<PurchaseReturnsSheetPO> lst){
        for(PurchaseReturnsSheetPO prsp: lst){
            PurchaseReturnsSheetVO prsv = new PurchaseReturnsSheetVO();
            prsv.setOperator(prsp.getOperator());
            prsv.setId(prsp.getId());
            prsv.setPurchaseSheetId(prsp.getPurchaseSheetId());
            prsv.setState(prsp.getState());
            prsv.setRemark(prsp.getRemark());
            prsv.setCreateTime(prsp.getCreateTime());
            prsv.setTotalAmount(prsp.getTotalAmount());
            List<PurchaseReturnsSheetContentPO> prscpLst = purchaseReturnsSheetDao.findContentByPurchaseReturnsSheetId(prsv.getId());
            List<PurchaseReturnsSheetContentVO> prscvLst = new ArrayList<>();
            for(PurchaseReturnsSheetContentPO prscp: prscpLst){
                PurchaseReturnsSheetContentVO prscv = new PurchaseReturnsSheetContentVO();
                prscv.setId(prscp.getId());
                prscv.setPid(prscp.getPid());
                prscv.setQuantity(prscp.getQuantity());
                prscv.setRemark(prscp.getRemark());
                prscv.setTotalPrice(prscp.getTotalPrice());
                prscv.setUnitPrice(prscp.getUnitPrice());
                prscv.setPurchaseReturnsSheetId(prscp.getPurchaseReturnsSheetId());
                prscvLst.add(prscv);
            }
            prsv.setPurchaseReturnsSheetContent(prscvLst);
            BusinessHistorySheetVO businessHistorySheetVO = new BusinessHistorySheetVO("purchase-returns", prsv);
            ans.add(businessHistorySheetVO);
        }
    }

}
