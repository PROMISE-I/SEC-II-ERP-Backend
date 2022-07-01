package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.enums.CustomerType;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.service.CustomerService;
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

    @PostMapping("/update")
    public Response updateCustomer(@RequestBody CustomerVO customerVO){
        CustomerPO customerPO = new CustomerPO(customerVO);
        customerService.updateCustomer(customerPO);
        return Response.buildSuccess();
    }

    @GetMapping("/delete")
    public Response deleteById(@RequestParam int id){
        customerService.deleteOne(id);
        return Response.buildSuccess();
    }
}
