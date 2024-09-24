package com.shamima.SCMSystem.goods.restcontroller;

import com.shamima.SCMSystem.goods.entity.Product;
import com.shamima.SCMSystem.goods.service.ProductService;
import com.shamima.SCMSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/save")
    public ApiResponse saveProduct(@RequestBody Product product, @RequestParam Long inventoryId) {
        return productService.saveProduct(product, inventoryId);
    }


    @PutMapping("/update")
    public ApiResponse updateProduct(@RequestBody Product product, @RequestParam Long inventoryId) {
        return productService.updateProduct(product, inventoryId);
    }

    @GetMapping("/list")
    public ApiResponse getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ApiResponse findProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
}
