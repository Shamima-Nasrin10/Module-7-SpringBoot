package com.shamima.SCMSystem.inventory.restcontroller;

import com.shamima.SCMSystem.inventory.entity.RawMaterialCategory;
import com.shamima.SCMSystem.inventory.service.RawMaterialCategoryService;
import com.shamima.SCMSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rawmaterialcategory")
@CrossOrigin("*")
public class RawMaterialCategoryController {

    @Autowired
    private RawMaterialCategoryService rawMaterialCategoryService;

    @PostMapping("/save")
    public ApiResponse save(@RequestBody RawMaterialCategory rawMaterialCategory) {
        return rawMaterialCategoryService.saveRawMaterialCategory(rawMaterialCategory);
    }

    @PutMapping("/update")
    public ApiResponse update(@RequestBody RawMaterialCategory rawMaterialCategory) {
        return rawMaterialCategoryService.updateRawMaterialCategory(rawMaterialCategory);
    }

    @GetMapping("/list")
    public ApiResponse getRawMaterialCategories() {
        return rawMaterialCategoryService.getRawMaterialCategories();
    }

    @GetMapping("/{id}")
    public ApiResponse findRawMaterialCategoryById(@PathVariable int id) {
        return rawMaterialCategoryService.findRawMaterialCategoryById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteRawMaterialCategoryById(@PathVariable long id) {
        return rawMaterialCategoryService.deleteRawMaterialCategoryById(id);
    }


}
