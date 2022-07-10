package com.nju.edu.erp.web.controller.sale_purchase.customer;

import com.nju.edu.erp.enums.customer.CustomerType;
import com.nju.edu.erp.model.po.sale_purchase.customer.CustomerPO;
import com.nju.edu.erp.model.vo.sale_purchase.customer.CustomerVO;
import com.nju.edu.erp.service.Interface.sale_purchase.customer.CustomerService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/findByType")
    public Response findByType(@RequestParam CustomerType type) {
        return Response.buildSuccess(customerService.getCustomersByType(type));
    }

    //EXPORT： 客户管理， 新增客户
    @PostMapping("/addCustomer")
    public Response createCustomer(@RequestBody CustomerVO customerVO){
        CustomerPO customerPO = new CustomerPO(customerVO);
        customerService.addCustomer(customerPO);
        return Response.buildSuccess();
    }

    @GetMapping("/findById")
    public Response findById(@RequestParam int id){
        CustomerPO customerPO = customerService.findCustomerById(id);
        CustomerVO customerVO = new CustomerVO(customerPO);
        return Response.buildSuccess(customerVO);
    }

    //EXPORT: 客户管理, 更新
    @PostMapping("/update")
    public Response updateCustomer(@RequestBody CustomerVO customerVO){
        CustomerPO customerPO = new CustomerPO(customerVO);
        customerService.updateCustomer(customerPO);
        return Response.buildSuccess();
    }

    //EXPORT: 客户管理, 删除
    @GetMapping("/delete")
    public Response deleteById(@RequestParam int id){
        customerService.deleteOne(id);
        return Response.buildSuccess();
    }
}
