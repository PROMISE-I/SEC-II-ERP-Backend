package com.nju.edu.erp.service.Impl.promotion;

import com.nju.edu.erp.dao.promotion.strategy.TotalPricePromotionDao;
import com.nju.edu.erp.model.po.promotion.strategy.total_price.TotalPricePromotionContentPO;
import com.nju.edu.erp.model.po.promotion.strategy.total_price.TotalPricePromotionPO;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.model.vo.promotion.total_price.TotalPricePromotionContentVO;
import com.nju.edu.erp.model.vo.promotion.total_price.TotalPricePromotionVO;
import com.nju.edu.erp.service.Interface.promotion.TotalPricePromotionService;
import com.nju.edu.erp.utils.DateHelper;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WFS
 * @date 2022/7/8 19:31
 */
@Service
public class TotalPricePromotionServiceImpl implements TotalPricePromotionService {

    private final TotalPricePromotionDao totalPricePromotionDao;

    @Autowired
    public TotalPricePromotionServiceImpl(TotalPricePromotionDao totalPricePromotionDao) {
        this.totalPricePromotionDao = totalPricePromotionDao;
    }

    @Override
    @Transactional
    public void makePromotion(UserVO userVO, TotalPricePromotionVO totalPricePromotionVO) {
        TotalPricePromotionPO toSave = new TotalPricePromotionPO();
        BeanUtils.copyProperties(totalPricePromotionVO, toSave);
        Date beginDate = DateHelper.stringToDate(totalPricePromotionVO.getBeginTimeStr());
        Date endDate = DateHelper.stringToDate(totalPricePromotionVO.getEndTimeStr());

        if (beginDate.compareTo(endDate) > 0) {
            throw new RuntimeException("截至时间不能小于开始时间！");
        } else {
            toSave.setBeginTime(beginDate);
            toSave.setEndTime(endDate);
        }

        toSave.setOperator(userVO.getName());
        TotalPricePromotionPO latest = totalPricePromotionDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null? null : latest.getId(), "ZJCXCL");
        toSave.setId(id);

        List<TotalPricePromotionContentPO> cpos = new ArrayList<>();
        for (TotalPricePromotionContentVO cvo : totalPricePromotionVO.getContentList()) {
            TotalPricePromotionContentPO cpo = new TotalPricePromotionContentPO();
            BeanUtils.copyProperties(cvo, cpo);
            cpo.setTotalPricePromotionId(id);
            if (cpo.getQuantity() != null) {
                cpo.setTotalPrice(cpo.getUnitPrice().multiply(BigDecimal.valueOf(cpo.getQuantity())));
                cpos.add(cpo);
            }
        }

        totalPricePromotionDao.save(toSave);
        if (cpos.size() != 0){
            totalPricePromotionDao.saveBatch(cpos);
        }
    }

    @Override
    public List<TotalPricePromotionVO> findAll() {
        List<TotalPricePromotionPO> totalPricePromotionPOS = totalPricePromotionDao.findAll();
        List<TotalPricePromotionVO> res = new ArrayList<>();
        for (TotalPricePromotionPO po : totalPricePromotionPOS) {
            res.add(POToVO(po));
        }
        return res;
    }

    @Override
    public TotalPricePromotionVO findById(String id) {
        TotalPricePromotionPO po = totalPricePromotionDao.findById(id);
        return POToVO(po);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        totalPricePromotionDao.deleteById(id);
        totalPricePromotionDao.deleteContentById(id);
    }

    @Override
    public BigDecimal getVoucherAmountByDateAndThreshold(Date today, BigDecimal rawTotalAmount) {
        TotalPricePromotionPO promotion = totalPricePromotionDao.getVoucherAmountByDateAndThreshold(today, rawTotalAmount);
        return promotion == null? BigDecimal.ZERO : promotion.getVoucherAmount();
    }

    @Override
    public List<TotalPricePromotionContentPO> findContentByTotalPricePromotionId(String id) {
        return totalPricePromotionDao.findContentByTotalPricePromotionId(id);
    }

    @Override
    public TotalPricePromotionPO getPromotionByDateAndThreshold(Date today, BigDecimal rawTotalAmount) {
        return totalPricePromotionDao.getVoucherAmountByDateAndThreshold(today, rawTotalAmount);
    }

    private TotalPricePromotionVO POToVO(TotalPricePromotionPO po) {
        TotalPricePromotionVO vo = new TotalPricePromotionVO();
        BeanUtils.copyProperties(po, vo);
        vo.setBeginTimeStr(po.getBeginTime().toString());
        vo.setEndTimeStr(po.getEndTime().toString());

        List<TotalPricePromotionContentVO> cvos = new ArrayList<>();
        for (TotalPricePromotionContentPO cpo : totalPricePromotionDao.findContentByTotalPricePromotionId(po.getId())) {
            TotalPricePromotionContentVO cvo = new TotalPricePromotionContentVO();
            BeanUtils.copyProperties(cpo, cvo);
            cvos.add(cvo);
        }

        vo.setContentList(cvos);

        return vo;
    }
}
