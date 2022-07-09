package com.nju.edu.erp.service.Impl.strategy.promotion.give_away;

import com.nju.edu.erp.model.po.sale.SaleSheetPO;
import com.nju.edu.erp.model.po.promotion.strategy.total_price.TotalPricePromotionContentPO;
import com.nju.edu.erp.model.po.promotion.strategy.total_price.TotalPricePromotionPO;
import com.nju.edu.erp.model.vo.promotion.grifts.GiveAwaySheetContentVO;
import com.nju.edu.erp.model.vo.promotion.grifts.GiveAwaySheetVO;
import com.nju.edu.erp.service.Interface.promotion.gifts.GiveAwayService;
import com.nju.edu.erp.service.Interface.promotion.TotalPricePromotionService;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WFS
 * @date 2022/7/9 16:24
 */
public class TotalPricePromotionGiveAwayStrategy implements GiveAwayStrategy{

    private final TotalPricePromotionService totalPricePromotionService;

    private final GiveAwayService giveAwayService;

    private final SaleSheetPO saleSheetPO;

    public TotalPricePromotionGiveAwayStrategy(TotalPricePromotionService totalPricePromotionService, GiveAwayService giveAwayService, SaleSheetPO saleSheetPO) {
        this.totalPricePromotionService = totalPricePromotionService;
        this.giveAwayService = giveAwayService;
        this.saleSheetPO = saleSheetPO;
    }

    @Override
    public void makeGiveAwaySheet() {
        Date today = new Date();
        GiveAwaySheetVO giveAwaySheetVO = new GiveAwaySheetVO();
        TotalPricePromotionPO promotion = totalPricePromotionService.getPromotionByDateAndThreshold(today, saleSheetPO.getRawTotalAmount());

        if (promotion != null) {
            List<TotalPricePromotionContentPO> cpos = totalPricePromotionService.findContentByTotalPricePromotionId(promotion.getId());

            List<GiveAwaySheetContentVO> gascvos = new ArrayList<>();
            for(TotalPricePromotionContentPO cpo : cpos) {
                GiveAwaySheetContentVO gasvo = new GiveAwaySheetContentVO();
                BeanUtils.copyProperties(cpo, gasvo);
                gasvo.setId(null);
                gascvos.add(gasvo);
            }
            giveAwaySheetVO.setContentVOList(gascvos);

            giveAwaySheetVO.setSaleSheetId(saleSheetPO.getId());

            giveAwayService.makeSheet(null, giveAwaySheetVO);
        }
    }
}
