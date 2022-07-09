package com.nju.edu.erp.web.controller.stub;

import com.nju.edu.erp.enums.sheet_state.gifts.GiveAwaySheetState;
import com.nju.edu.erp.model.vo.promotion.grifts.GiveAwaySheetVO;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class GiveAwayStub {
    @PostMapping(value = "/sheet-make")
    public Response makeSheet(UserVO userVO, @RequestBody GiveAwaySheetVO giveAwaySheetVO) {

        return Response.buildSuccess();
    }

    @GetMapping(value = "/sheet-show")
    public Response showSheetByState(@RequestParam(value = "state") GiveAwaySheetState state) {
        List<GiveAwaySheetVO> lst = new ArrayList<>();
        GiveAwaySheetVO giveAwaySheetVO = GiveAwaySheetVO.builder()
                .id("ZSD-20220709-00000")
                .saleSheetId("XSD-20220709-00000")
                .state(state)
                .build();
        lst.add(giveAwaySheetVO);

        return Response.buildSuccess();
    }

    @GetMapping(value = "/first-approval")
    public Response firstApproval(@RequestParam("sheetId") String sheetId,
                                  @RequestParam("state") GiveAwaySheetState state) {
        return Response.buildSuccess();
    }

    @GetMapping(value = "/second-approval")
    public Response secondApproval(@RequestParam("sheetId") String sheetId,
                                   @RequestParam("state") GiveAwaySheetState state)  {
        return Response.buildSuccess();
    }

    @GetMapping(value = "/find-all")
    public Response findAll() {
        List<GiveAwaySheetVO> lst = new ArrayList<>();
        GiveAwaySheetVO giveAwaySheetVO = GiveAwaySheetVO.builder()
                .id("ZSD-20220709-00000")
                .saleSheetId("XSD-20220709-00000")
                .state(GiveAwaySheetState.SUCCESS)
                .build();
        lst.add(giveAwaySheetVO);

        return Response.buildSuccess();
    }
}
