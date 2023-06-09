package com.nju.edu.erp.web.controller.staff;

import com.nju.edu.erp.model.po.staff.StaffPO;
import com.nju.edu.erp.model.vo.staff.StaffVO;
import com.nju.edu.erp.service.Interface.staff.StaffService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对员工信息进行增删改查
 */
@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService){
        this.staffService = staffService;
    }

    /**
     * 创建一个员工, 会同时分配一个账号
     * @param staffVO
     * @return
     */
    //员工管理, 新增
    @PostMapping("/create")
    public Response createStaff(@RequestBody StaffVO staffVO){
        StaffPO staffPO = new StaffPO(staffVO);

        staffService.createStaff(staffPO);
        return Response.buildSuccess();
    }

    /**
     * 查询所有员工信息
     * @return 员工信息列表
     */
    @GetMapping("/findAll")
    public Response findAll(){

        return Response.buildSuccess(staffService.findAll());
    }

    /**
     * 找到除了总经理外的所有员工
     * @return 员工信息列表
     */
    @GetMapping("/findAllExceptGM")
    public Response findAllExceptGM() {
       return Response.buildSuccess(staffService.findAllExceptGM());
    }

    /**
     * 更新员工信息
     * @param staffVO
     * @return
     */
    //EXPORT: 员工管理, 更新
    @PostMapping("/update")
    public Response updateOne(@RequestBody StaffVO staffVO){
        StaffPO staffPO = new StaffPO(staffVO);

        staffService.updateOne(staffPO);

        return Response.buildSuccess();
    }

    /**
     * 根据 staffId 来删除员工
     * @param id
     * @return
     */
    //EXPORT: 员工管理, 删除
    @GetMapping("/delete")
    public Response deleteById(@RequestParam Integer id){
        staffService.deleteStaffById(id);

        return Response.buildSuccess();
    }

    /**
     * 根据 Id 来查找员工
     * @param staffId
     * @return staff 对象
     */
    @GetMapping("/findById")
    public Response findById(@RequestParam Integer staffId){
        return Response.buildSuccess(staffService.findStaffById(staffId));
    }
}
