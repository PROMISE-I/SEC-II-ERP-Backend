package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.BusinessHistoryDao;
import com.nju.edu.erp.model.po.PurchaseReturnsSheetPO;
import com.nju.edu.erp.model.po.PurchaseSheetPO;
import com.nju.edu.erp.model.po.SaleReturnsSheetPO;
import com.nju.edu.erp.model.po.SaleSheetPO;
import com.nju.edu.erp.model.po.finance.PayMoneySheetPO;
import com.nju.edu.erp.model.po.finance.ReceiveMoneySheetPO;
import com.nju.edu.erp.model.po.finance.SalarySheetPO;
import com.nju.edu.erp.model.vo.BusinessHistorySheetVO;
import com.nju.edu.erp.service.BusinessHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessHistoryServiceImpl implements BusinessHistoryService {
    private final BusinessHistoryDao businessHistoryDao;

    @Autowired
    public BusinessHistoryServiceImpl(BusinessHistoryDao businessHistoryDao){
        this.businessHistoryDao = businessHistoryDao;
    }

    @Override
    public List<BusinessHistorySheetVO> findAll(String begin, String end) {
        List<BusinessHistorySheetVO> businessHistorySheetVOList = new ArrayList<>();

        //销售类
        List<SaleSheetPO> saleSheetPOList = businessHistoryDao.findAllSaleSheetByInterval(begin, end);
        List<SaleReturnsSheetPO> saleReturnsSheetPOList = businessHistoryDao.findAllSaleReturnsSheetByInterval(begin, end);
        copy(businessHistorySheetVOList, saleSheetPOList, "sale");
        copy(businessHistorySheetVOList, saleReturnsSheetPOList, "sale-returns");

        //进货类
        List<PurchaseSheetPO> purchaseSheetPOList = businessHistoryDao.findAllPurchaseSheetByInterval(begin, end);
        List<PurchaseReturnsSheetPO> purchaseReturnsSheetPOList = businessHistoryDao.findAllPurchaseReturnsSheetByInterval(begin, end);
        copy(businessHistorySheetVOList, purchaseSheetPOList, "purchase");
        copy(businessHistorySheetVOList, purchaseReturnsSheetPOList, "purchase-returns");

        //财务类
        List<PayMoneySheetPO> payMoneySheetPOList = businessHistoryDao.findAllPayMoneySheetByInterval(begin, end);
        List<ReceiveMoneySheetPO> receiveMoneySheetPOList = businessHistoryDao.findAllReceiveMoneySheetByInterval(begin, end);
        List<SalarySheetPO> salarySheetPOList = businessHistoryDao.findAllSalarySheetByInterval(begin, end);
        copy(businessHistorySheetVOList, payMoneySheetPOList, "pay-money");
        copy(businessHistorySheetVOList, receiveMoneySheetPOList, "receive-money");
        copy(businessHistorySheetVOList, salarySheetPOList, "salary");

        //库存类

        return businessHistorySheetVOList;
    }

    private void copy(List<BusinessHistorySheetVO> dst, List<? extends Object> src, String type){
        if(src == null || src.isEmpty())return;
        for(Object o : src){
            BusinessHistorySheetVO businessHistorySheetVO = new BusinessHistorySheetVO(type, o);
            dst.add(businessHistorySheetVO);
        }
    }
}
