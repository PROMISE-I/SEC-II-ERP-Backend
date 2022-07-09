package com.nju.edu.erp.web.controller.promotion.gifts;

import com.nju.edu.erp.enums.sheet_state.gifts.GiveAwaySheetState;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.model.vo.promotion.grifts.GiveAwaySheetVO;
import com.nju.edu.erp.service.Interface.promotion.gifts.GiveAwayService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author WFS
 * @date 2022/7/9 14:42
 */
@RestController
@RequestMapping(path = "/give-away")
public class GiveAwayController {
    private final GiveAwayService giveAwayService;

    @Autowired
    public GiveAwayController(GiveAwayService giveAwayService) {
        this.giveAwayService = giveAwayService;
    }

    @PostMapping(value = "/sheet-make")
    public Response makeSheet(UserVO userVO, @RequestBody GiveAwaySheetVO giveAwaySheetVO) {
        giveAwayService.makeSheet(userVO, giveAwaySheetVO);
        return Response.buildSuccess();
    }

    @GetMapping(value = "/sheet-show")
    public Response showSheetByState(@RequestParam(value = "state")GiveAwaySheetState state) {
        return Response.buildSuccess(giveAwayService.getSheetByState(state));
    }

    @GetMapping(value = "/first-approval")
    public Response firstApproval(@RequestParam("sheetId") String sheetId,
                                  @RequestParam("state") GiveAwaySheetState state) {
        if (state.equals(GiveAwaySheetState.FAILURE) || state.equals(GiveAwaySheetState.PENDING_LEVEL_2)) {
            giveAwayService.approval(sheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","操作失败");
        }
    }

    @GetMapping(value = "/second-approval")
    public Response secondApproval(@RequestParam("sheetId") String sheetId,
                                   @RequestParam("state") GiveAwaySheetState state)  {
        if(state.equals(GiveAwaySheetState.FAILURE) || state.equals(GiveAwaySheetState.SUCCESS)) {
            giveAwayService.approval(sheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","操作失败"); // code可能得改一个其他的
        }
    }

    @GetMapping(value = "/find-all")
    public Response findAll() {
        return Response.buildSuccess(giveAwayService.findAll());
    }
}
