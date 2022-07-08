package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.promotion.CombinatorialPromotionDao;
import com.nju.edu.erp.model.po.promotion.CombinatorialDiscountPO;
import com.nju.edu.erp.model.vo.promotion.CombinatorialDiscountVO;
import com.nju.edu.erp.service.CombinatorialPromotionService;
import com.nju.edu.erp.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CombinatorialServiceImpl implements CombinatorialPromotionService {

    private final CombinatorialPromotionDao combinatorialPromotionDao;
    private final ProductService productService;

    @Autowired
    public CombinatorialServiceImpl(CombinatorialPromotionDao combinatorialPromotionDao, ProductService productService){
        this.combinatorialPromotionDao = combinatorialPromotionDao;
        this.productService = productService;
    }

    @Override
    public List<CombinatorialDiscountVO> findAll() {
        //获取所有组合降价 PO
        List<CombinatorialDiscountPO> combinatorialDiscountPOList = combinatorialPromotionDao.findAll();
        List<CombinatorialDiscountVO> combinatorialDiscountVOList = new ArrayList<>();
        //对于每个组合 PO, 补上商品名，便于前端显示
        for(CombinatorialDiscountPO cp : combinatorialDiscountPOList){
            CombinatorialDiscountVO cv = new CombinatorialDiscountVO(cp);
            String pid1 = cp.getProductOneId();
            String pid2 = cp.getProductTwoId();
            String pName1 = productService.getOneProductByPid(pid1).getName();
            String pName2 = productService.getOneProductByPid(pid2).getName();
            cv.setProductOneName(pName1);
            cv.setProductTwoName(pName2);
            combinatorialDiscountVOList.add(cv);
        }
        return combinatorialDiscountVOList;
    }

    @Override
    public CombinatorialDiscountVO findByPair(String productOneId, String productTwoId) {
        //根据 pair 获得 PO 对象
        String pName1 = productService.getOneProductByPid(productOneId).getName();
        String pName2 = productService.getOneProductByPid(productTwoId).getName();
        CombinatorialDiscountPO cp = combinatorialPromotionDao.findByPair(productOneId, productTwoId);
        CombinatorialDiscountVO cv = new CombinatorialDiscountVO(cp);
        //给 VO 对象补上商品名称便于前端显示
        cv.setProductOneName(pName1);
        cv.setProductTwoName(pName2);
        return cv;
    }

    @Override
    public int createOne(CombinatorialDiscountVO combinatorialDiscountVO) {
        CombinatorialDiscountPO cp = new CombinatorialDiscountPO(combinatorialDiscountVO);
        int affected = combinatorialPromotionDao.createOne(cp);
        return affected;
    }

    @Override
    public int updateOne(CombinatorialDiscountVO combinatorialDiscountVO) {
        CombinatorialDiscountPO cp = new CombinatorialDiscountPO(combinatorialDiscountVO);
        int affected = combinatorialPromotionDao.updateOne(cp);
        return affected;
    }

    @Override
    public int deleteOne(Integer id) {
        return combinatorialPromotionDao.deleteOne(id);
    }
}