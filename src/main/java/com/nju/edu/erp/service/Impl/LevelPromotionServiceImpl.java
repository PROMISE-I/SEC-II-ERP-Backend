package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.promotion.LevelStrategyDao;
import com.nju.edu.erp.dao.promotion.PresentInfoDao;
import com.nju.edu.erp.model.po.promotion.LevelPromotionStrategyPO;
import com.nju.edu.erp.model.po.promotion.PresentInfoPO;
import com.nju.edu.erp.model.vo.promotion.LevelPromotionStrategyVO;
import com.nju.edu.erp.service.LevelPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LevelPromotionServiceImpl implements LevelPromotionService {
    private final PresentInfoDao presentInfoDao;
    private final LevelStrategyDao levelStrategyDao;

    @Autowired
    public LevelPromotionServiceImpl(PresentInfoDao presentInfoDao, LevelStrategyDao levelStrategyDao){
        this.presentInfoDao = presentInfoDao;
        this.levelStrategyDao = levelStrategyDao;
    }

    /**
     * 返回所有不同级别的促销策略
     * @return 促销策略的列表
     */
    @Override
    public List<LevelPromotionStrategyVO> findAll() {
        List<LevelPromotionStrategyPO> levelPromotionStrategyPOList = levelStrategyDao.findAll();
        List<PresentInfoPO> presentInfoPOList = presentInfoDao.findAll();
        List<LevelPromotionStrategyVO> levelPromotionStrategyVOList = new ArrayList<>();
        //遍历每个级别的促销策略
        for (LevelPromotionStrategyPO lpsp : levelPromotionStrategyPOList){
            LevelPromotionStrategyVO lpsv = new LevelPromotionStrategyVO(lpsp);
            List<PresentInfoPO> lst = new ArrayList<>();
            //设置对应级别的赠品信息
            for (PresentInfoPO pip : presentInfoPOList){
                if(pip.getLevel() == lpsv.getLevel()){
                    lst.add(pip);
                }
            }
            lpsv.setPresentInfoList(lst);
            levelPromotionStrategyVOList.add(lpsv);
        }

        return levelPromotionStrategyVOList;
    }
}
