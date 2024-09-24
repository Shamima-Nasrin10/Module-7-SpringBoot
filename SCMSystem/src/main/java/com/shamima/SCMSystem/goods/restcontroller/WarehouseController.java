package com.shamima.SCMSystem.goods.restcontroller;

import com.shamima.SCMSystem.goods.entity.Inventory;
import com.shamima.SCMSystem.goods.entity.Warehouse;
import com.shamima.SCMSystem.goods.service.WarehouseService;
import com.shamima.SCMSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    // Save a new warehouse
    @PostMapping("/save")
    public ApiResponse saveWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.saveWarehouse(warehouse);
    }

    // Get all warehouses
    @GetMapping("/list")
    public ApiResponse getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    // Update an existing warehouse
    @PutMapping("/update")
    public ApiResponse updateWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.updateWarehouse(warehouse);
    }

    // Find warehouse by ID and include its inventories
    @GetMapping("/{id}")
    public ApiResponse findWarehouseById(@PathVariable long id) {
        return warehouseService.findWarehouseById(id);
    }

    // Delete a warehouse by ID
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteWarehouseById(@PathVariable long id) {
        return warehouseService.deleteWarehouseById(id);
    }

    // Add an inventory to a warehouse
    @PostMapping("/{warehouseId}/inventory/add")
    public ApiResponse addInventoryToWarehouse(@PathVariable Long warehouseId, @RequestBody Inventory inventory) {
        return warehouseService.addInventoryToWarehouse(warehouseId, inventory);
    }

    // New endpoint: Get products by inventory
    @GetMapping("/inventories/{inventoryId}/products")
    public ApiResponse getProductsByInventoryId(@PathVariable Long inventoryId) {
        return warehouseService.getProductsByInventoryId(inventoryId);
    }

}