package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.ProductDao;
import com.nju.edu.erp.dao.promotion.GiveAwayDao;
import com.nju.edu.erp.enums.sheetState.GiveAwaySheetState;
import com.nju.edu.erp.model.po.ProductPO;
import com.nju.edu.erp.model.po.promotion.GiveAwaySheetContentPO;
import com.nju.edu.erp.model.po.promotion.GiveAwaySheetPO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.promotion.GiveAwaySheetContentVO;
import com.nju.edu.erp.model.vo.promotion.GiveAwaySheetVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseOutputFormContentVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseOutputFormVO;
import com.nju.edu.erp.service.GiveAwayService;
import com.nju.edu.erp.service.WarehouseService;
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
 * @date 2022/7/9 14:51
 */
@Service
public class GiveAwayServiceImpl implements GiveAwayService {

    private final GiveAwayDao giveAwayDao;

    private final ProductDao productDao;

    private final WarehouseService warehouseService;

    @Autowired
    public GiveAwayServiceImpl(GiveAwayDao giveAwayDao, ProductDao productDao, WarehouseService warehouseService) {
        this.giveAwayDao = giveAwayDao;
        this.productDao = productDao;
        this.warehouseService = warehouseService;
    }

    @Override
    @Transactional
    public void makeSheet(UserVO userVO, GiveAwaySheetVO giveAwaySheetVO) {
        GiveAwaySheetPO toSave = new GiveAwaySheetPO();
        BeanUtils.copyProperties(giveAwaySheetVO, toSave);

        toSave.setCreateTime(new Date());
        GiveAwaySheetPO latest = giveAwayDao.getLatestSheet();
        String id = toSave.getId();
        //如果id为null说明是新建单据，否则为红冲或红冲并复制
        if (id == null) {
            id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "ZSD");
        }
        toSave.setId(id);
        toSave.setState(GiveAwaySheetState.PENDING_SALE_SHEET_APPROVAL_SUCCESS);
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<GiveAwaySheetContentPO> contentPOS = new ArrayList<>();
        for (GiveAwaySheetContentVO contentVO : giveAwaySheetVO.getContentVOList()) {
            GiveAwaySheetContentPO contentPO = new GiveAwaySheetContentPO();
            BeanUtils.copyProperties(contentVO, contentPO);
            contentPO.setGiveAwaySheetId(id);
            BigDecimal unitPrice = contentVO.getUnitPrice();
            if (unitPrice == null) {
                ProductPO productPO = productDao.findById(contentVO.getPid());
                unitPrice = productPO.getPurchasePrice();
                contentPO.setUnitPrice(unitPrice);
            }
            contentPO.setTotalPrice(unitPrice.multiply(BigDecimal.valueOf(contentPO.getQuantity())));
            contentPOS.add(contentPO);
            totalAmount = totalAmount.add(contentPO.getTotalPrice());
        }

        giveAwayDao.save(toSave);
        giveAwayDao.saveBatch(contentPOS);
    }

    @Override
    public List<GiveAwaySheetVO> getSheetByState(GiveAwaySheetState state) {
        List<GiveAwaySheetPO> all;
        if (state == null) {
            all = giveAwayDao.findAll();
        } else {
            all = giveAwayDao.findByState(state);
        }
        return POToVO(all);
    }

    @Override
    @Transactional
    public void approval(String sheetId, GiveAwaySheetState state) {
        if (state.equals(GiveAwaySheetState.FAILURE)) {
            GiveAwaySheetPO sheet = giveAwayDao.findSheetById(sheetId);
            if (sheet.getState().equals(GiveAwaySheetState.SUCCESS)) throw new RuntimeException("状态更新失败！");
            int effectLines = giveAwayDao.updateSheetState(sheetId, state);
            if (effectLines == 0) throw new RuntimeException("状态更新失败！");
        } else {
            GiveAwaySheetState prevState;
            if (state.equals(GiveAwaySheetState.SUCCESS)) {
                prevState = GiveAwaySheetState.PENDING_LEVEL_2;
            } else if (state.equals(GiveAwaySheetState.PENDING_LEVEL_2)) {
                prevState = GiveAwaySheetState.PENDING_LEVEL_1;
            } else if (state.equals(GiveAwaySheetState.PENDING_LEVEL_1)) {
                prevState = GiveAwaySheetState.PENDING_SALE_SHEET_APPROVAL_SUCCESS;
            } else {
                throw new RuntimeException("状态更新失败！");
            }

            int effectLines = giveAwayDao.updateSheetStateOnPrev(sheetId, prevState, state);
            if (effectLines == 0) throw new RuntimeException("状态更新失败！");

            if (state.equals(GiveAwaySheetState.SUCCESS)) {
                //减少库存，建立出库单
                List<GiveAwaySheetContentPO> cpos = giveAwayDao.findContentById(sheetId);
                List<WarehouseOutputFormContentVO> warehouseOutputFormContentVOS = new ArrayList<>();

                for (GiveAwaySheetContentPO cpo : cpos) {
                    WarehouseOutputFormContentVO woContentVO = new WarehouseOutputFormContentVO();
                    woContentVO.setSalePrice(cpo.getUnitPrice());
                    woContentVO.setQuantity(cpo.getQuantity());
                    woContentVO.setRemark(cpo.getRemark());
                    woContentVO.setPid(cpo.getPid());
                    warehouseOutputFormContentVOS.add(woContentVO);
                }

                WarehouseOutputFormVO warehouseOutputFormVO = new WarehouseOutputFormVO();
                warehouseOutputFormVO.setOperator(null);
                warehouseOutputFormVO.setSaleSheetId(sheetId);
                warehouseOutputFormVO.setList(warehouseOutputFormContentVOS);
                warehouseService.productOutOfWarehouse(warehouseOutputFormVO);
            }
        }
    }

    @Override
    public GiveAwaySheetPO getSheetBySaleSheetId(String saleSheetId) {
        return giveAwayDao.findSheetBySaleSheetId(saleSheetId);
    }

    private List<GiveAwaySheetVO> POToVO(List<GiveAwaySheetPO> sheetPOS) {
        List<GiveAwaySheetVO> res = new ArrayList<>();

        for (GiveAwaySheetPO po : sheetPOS) {
            GiveAwaySheetVO vo = new GiveAwaySheetVO();
            BeanUtils.copyProperties(po, vo);
            List<GiveAwaySheetContentPO> cpos = giveAwayDao.findContentById(po.getId());
            List<GiveAwaySheetContentVO> cvos = new ArrayList<>();
            for (GiveAwaySheetContentPO cpo : cpos) {
                GiveAwaySheetContentVO cvo = new GiveAwaySheetContentVO();
                BeanUtils.copyProperties(cpo, cvo);
                cvos.add(cvo);
            }
            vo.setContentVOList(cvos);
            res.add(vo);
        }

        return res;
    }
}
