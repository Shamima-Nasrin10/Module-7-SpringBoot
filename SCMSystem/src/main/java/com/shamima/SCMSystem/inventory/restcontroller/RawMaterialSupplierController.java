package com.shamima.SCMSystem.inventory.restcontroller;


import com.shamima.SCMSystem.inventory.entity.RawMaterialSupplier;
import com.shamima.SCMSystem.inventory.service.RawMaterialService;
import com.shamima.SCMSystem.inventory.service.RawMaterialSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/supplier")
@CrossOrigin("*")
public class RawMaterialSupplierController {

    @Autowired
    private RawMaterialSupplierService rawMaterialSupplierService;

    @GetMapping("/list")
    public List<RawMaterialSupplier> getAllRawMaterialSuppliers() {
        return rawMaterialSupplierService.getAllRawMaterialSuppliers();
    }

    @PostMapping("/save")
    public void saveRawMaterialSupplier(@RequestBody RawMaterialSupplier rmSupplier) {
        rawMaterialSupplierService.saveRawMaterialSupplier(rmSupplier);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRawMaterialSupplier(@PathVariable long id) {
        rawMaterialSupplierService.deleteRawMaterialSupplier(id);
    }


    @PostMapping("/update/")
    public void updateRawMaterialSupplier(@RequestBody RawMaterialSupplier rmSupplier) {
        rawMaterialSupplierService.saveRawMaterialSupplier(rmSupplier);
    }


}
