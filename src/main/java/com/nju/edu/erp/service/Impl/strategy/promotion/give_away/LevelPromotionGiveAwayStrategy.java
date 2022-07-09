package com.nju.edu.erp.service.Impl.strategy.promotion.give_away;

import com.nju.edu.erp.model.po.promotion.gifts.PresentInfoPO;
import com.nju.edu.erp.model.vo.promotion.grifts.GiveAwaySheetContentVO;
import com.nju.edu.erp.model.vo.promotion.grifts.GiveAwaySheetVO;
import com.nju.edu.erp.service.Interface.promotion.gifts.GiveAwayService;
import com.nju.edu.erp.service.Interface.warehouse.product.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WFS
 * @date 2022/7/9 16:25
 */
//TODO 根据促销策略内容制定赠送单
public class LevelPromotionGiveAwayStrategy implements GiveAwayStrategy{

    private GiveAwayService giveAwayService;
    private List<PresentInfoPO> presentInfoPOList;
    private String saleSheetId;
    private ProductService productService;

    public LevelPromotionGiveAwayStrategy(GiveAwayService giveAwayService, List<PresentInfoPO> presentInfoPOList, String id, ProductService productService){
        this.giveAwayService = giveAwayService;
        this.presentInfoPOList = presentInfoPOList;
        this.saleSheetId = id;
        this.productService = productService;
    }
    @Override
    public void makeGiveAwaySheet() {
        String saleSheetId = this.saleSheetId;
        String remark = saleSheetId + " 对应的赠品单.";
        List<GiveAwaySheetContentVO> giveAwaySheetContentVOList = new ArrayList<>();
        for(PresentInfoPO presentInfo: presentInfoPOList){
            GiveAwaySheetContentVO giveAwaySheetContentVO = new GiveAwaySheetContentVO();
            giveAwaySheetContentVO.setPid(presentInfo.getPid());
            giveAwaySheetContentVO.setQuantity(presentInfo.getQuantity());
            giveAwaySheetContentVO.setUnitPrice(productService.getOneProductByPid(presentInfo.getPid()).getRetailPrice());
            giveAwaySheetContentVO.setTotalPrice(giveAwaySheetContentVO.getUnitPrice().multiply(BigDecimal.valueOf(giveAwaySheetContentVO.getQuantity())));
            giveAwaySheetContentVOList.add(giveAwaySheetContentVO);
        }
        GiveAwaySheetVO giveAwaySheetVO = new GiveAwaySheetVO();
        giveAwaySheetVO.setSaleSheetId(saleSheetId);
        giveAwaySheetVO.setRemark(remark);
        giveAwaySheetVO.setContentVOList(giveAwaySheetContentVOList);
        giveAwayService.makeSheet(null, giveAwaySheetVO);
    }
}
