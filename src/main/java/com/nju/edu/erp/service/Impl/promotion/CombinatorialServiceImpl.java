package com.nju.edu.erp.service.Impl.promotion;

import com.nju.edu.erp.dao.promotion.strategy.CombinatorialPromotionDao;
import com.nju.edu.erp.model.po.promotion.strategy.combinatorial.CombinatorialDiscountPO;
import com.nju.edu.erp.model.vo.promotion.combinatorial.CombinatorialDiscountVO;
import com.nju.edu.erp.service.Interface.promotion.CombinatorialPromotionService;
import com.nju.edu.erp.service.Interface.warehouse.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 承载组合促销策略制定的业务逻辑
 * @author SUNLIFAN
 */
@Service
public class CombinatorialServiceImpl implements CombinatorialPromotionService {

    private final CombinatorialPromotionDao combinatorialPromotionDao;
    private final ProductService productService;

    @Autowired
    public CombinatorialServiceImpl(CombinatorialPromotionDao combinatorialPromotionDao, ProductService productService){
        this.combinatorialPromotionDao = combinatorialPromotionDao;
        this.productService = productService;
    }

    /**
     * 查找所有组合降价策略信息
     * @return 所有组合降价策略信息列表
     */
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

    /**
     * 根据商品组合来查找是否满足某个组合促销策略
     * @param productOneId
     * @param productTwoId
     * @return 参数中两个商品 id 触发的组合促销策略
     */
    @Override
    public CombinatorialDiscountVO findByPair(String productOneId, String productTwoId) {
        //根据 pair 获得 PO 对象
        String pName1 = productService.getOneProductByPid(productOneId).getName();
        String pName2 = productService.getOneProductByPid(productTwoId).getName();
        CombinatorialDiscountPO cp = combinatorialPromotionDao.findByPair(productOneId, productTwoId);
        if(cp == null)return null;
        CombinatorialDiscountVO cv = new CombinatorialDiscountVO(cp);

        //给 VO 对象补上商品名称便于前端显示
        cv.setProductOneName(pName1);
        cv.setProductTwoName(pName2);

        return cv;
    }

    /**
     * 创建组合促销策略
     * @param combinatorialDiscountVO
     * @return
     */
    @Override
    @Transactional
    public int createOne(CombinatorialDiscountVO combinatorialDiscountVO) {
        CombinatorialDiscountPO cp = new CombinatorialDiscountPO(combinatorialDiscountVO);
        int affected = combinatorialPromotionDao.createOne(cp);
        return affected;
    }

    /**
     * 更新组合促销策略信息
     * @param combinatorialDiscountVO
     * @return
     */
    @Override
    @Transactional
    public int updateOne(CombinatorialDiscountVO combinatorialDiscountVO) {
        CombinatorialDiscountPO cp = new CombinatorialDiscountPO(combinatorialDiscountVO);
        int affected = combinatorialPromotionDao.updateOne(cp);
        return affected;
    }

    /**
     * 根据 id 删除组合促销策略信息
     * @param id
     * @return
     */
    @Override
    public int deleteOne(Integer id) {
        return combinatorialPromotionDao.deleteOne(id);
    }
}
