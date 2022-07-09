package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.promotion.LevelStrategyDao;
import com.nju.edu.erp.dao.promotion.PresentInfoDao;
import com.nju.edu.erp.model.po.promotion.LevelPromotionStrategyPO;
import com.nju.edu.erp.model.po.promotion.PresentInfoPO;
import com.nju.edu.erp.model.vo.promotion.LevelPromotionStrategyVO;
import com.nju.edu.erp.service.LevelPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        for(PresentInfoPO p: presentInfoPOList){
            System.out.println(p);
        }
        return match(levelPromotionStrategyPOList, presentInfoPOList);
    }

    /**
     * 返回某个级别的促销策略
     * @param level
     * @return 某个级别的促销策略列表
     */
    @Override
    public LevelPromotionStrategyVO findByLevel(Integer level) {

        List<LevelPromotionStrategyPO> levelPromotionStrategyPOList = levelStrategyDao.findByLevel(level);

        List<PresentInfoPO> presentInfoPOList = presentInfoDao.findAll();

        return match(levelPromotionStrategyPOList, presentInfoPOList).get(0);
    }

    @Transactional
    @Override
    public void updateOne(LevelPromotionStrategyVO levelPromotionStrategyVO) {
        LevelPromotionStrategyPO levelPromotionStrategyPO = new LevelPromotionStrategyPO(levelPromotionStrategyVO);
        List<PresentInfoPO> lst = levelPromotionStrategyVO.getPresentInfoList();
        if(lst != null) {
            for (PresentInfoPO pip : lst) {
                if("".equals(pip.getPid()))continue;
                if (pip.getId() == null) presentInfoDao.insertOne(pip);
                else presentInfoDao.updateOne(pip);
            }
        }
        levelStrategyDao.updateOne(levelPromotionStrategyPO);
    }

    @Override
    public List<PresentInfoPO> findPresentInfoByLevel(Integer level) {
        return presentInfoDao.findPresentInfoByLevel(level);
    }

    private List<LevelPromotionStrategyVO> match(List<LevelPromotionStrategyPO> levelPromotionStrategyPOList, List<PresentInfoPO> presentInfoPOList){
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
            if(lst.size() > 0)lpsv.setPresentInfoList(lst);
            levelPromotionStrategyVOList.add(lpsv);
        }

        return levelPromotionStrategyVOList;
    }
}
