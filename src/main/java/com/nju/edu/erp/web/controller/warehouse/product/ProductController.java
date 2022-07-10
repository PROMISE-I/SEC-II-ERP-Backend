package com.nju.edu.erp.web.controller.warehouse.product;


import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.vo.warehouse.product.CreateProductVO;
import com.nju.edu.erp.model.vo.warehouse.product.ProductInfoVO;
import com.nju.edu.erp.service.Interface.warehouse.product.ProductService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //EXPORT: 商品管理, 新增
    @PostMapping("/create")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response createProduct(@RequestBody CreateProductVO createProductVO) {
        return Response.buildSuccess(productService.createProduct(createProductVO));
    }

    //EXPORT: 商品管理, 更新
    @PostMapping("/update")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response updateProduct(@RequestBody ProductInfoVO productInfoVO) {
        return Response.buildSuccess(productService.updateProduct(productInfoVO));
    }

    //EXPORT: 商品管理, 查询全部
    @GetMapping("/queryAll")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER, Role.SALE_STAFF, Role.SALE_MANAGER})
    public Response findAllProduct() {
        return Response.buildSuccess(productService.queryAllProduct());
    }

    //EXPORT: 商品管理, 删除
    @GetMapping("/delete")
    @Authorized(roles = {Role.ADMIN, Role.GM, Role.INVENTORY_MANAGER})
    public Response deleteProduct(@RequestParam String id) {
        productService.deleteById(id);
        return Response.buildSuccess();
    }

}
