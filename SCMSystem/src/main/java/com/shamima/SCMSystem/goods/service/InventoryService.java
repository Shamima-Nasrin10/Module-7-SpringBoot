package com.shamima.SCMSystem.goods.service;

import com.shamima.SCMSystem.goods.entity.Inventory;
import com.shamima.SCMSystem.goods.entity.Product;
import com.shamima.SCMSystem.goods.entity.Warehouse;
import com.shamima.SCMSystem.goods.repository.InventoryRepository;
import com.shamima.SCMSystem.goods.repository.ProductRepository;
import com.shamima.SCMSystem.goods.repository.WarehouseRepository;
import com.shamima.SCMSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;


    @Transactional
    public ApiResponse saveInventory(Inventory inventory, Long warehouseId) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
            if (warehouse == null) {
                apiResponse.setMessage("Warehouse not found with ID: " + warehouseId);
                return apiResponse;
            }

            inventory.setWarehouse(warehouse);
            inventoryRepository.save(inventory);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Inventory saved successfully");
        } catch (Exception e) {
            apiResponse.setMessage("Failed to save inventory: " + e.getMessage());
        }
        return apiResponse;
    }


    public ApiResponse getAllInventories() {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            List<Inventory> inventories = inventoryRepository.findAll();
            apiResponse.setSuccess(true);
            apiResponse.setData("inventories", inventories);
        } catch (Exception e) {
            apiResponse.setMessage("Failed to fetch inventories: " + e.getMessage());
        }
        return apiResponse;
    }


    public ApiResponse findInventoryById(long id) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Inventory inventory = inventoryRepository.findById(id).orElse(null);
            if (inventory == null) {
                apiResponse.setMessage("Inventory not found");
                return apiResponse;
            }
            apiResponse.setSuccess(true);
            apiResponse.setData("inventory", inventory);
        } catch (Exception e) {
            apiResponse.setMessage("Failed to find inventory: " + e.getMessage());
        }
        return apiResponse;
    }

    @Transactional
    public ApiResponse updateInventory(Inventory updatedInventory, Long warehouseId) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Inventory existingInventory = inventoryRepository.findById(updatedInventory.getId())
                    .orElseThrow(() -> new RuntimeException("Inventory not found with ID: " + updatedInventory.getId()));

            Warehouse warehouse = warehouseRepository.findById(warehouseId)
                    .orElseThrow(() -> new RuntimeException("Warehouse not found with ID: " + warehouseId));

            existingInventory.setName(updatedInventory.getName());
            existingInventory.setCapacity(updatedInventory.getCapacity());
            existingInventory.setWarehouse(warehouse);
            existingInventory.setCategory(updatedInventory.getCategory());

            inventoryRepository.save(existingInventory);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Inventory updated successfully");
        } catch (Exception e) {
            apiResponse.setMessage("Failed to update inventory: " + e.getMessage());
        }
        return apiResponse;
    }

    @Transactional
    public ApiResponse deleteInventoryById(long id) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Inventory inventory = inventoryRepository.findById(id).orElse(null);
            if (inventory == null) {
                apiResponse.setMessage("Inventory not found");
                return apiResponse;
            }

            inventoryRepository.deleteById(id);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Inventory deleted successfully");
        } catch (Exception e) {
            apiResponse.setMessage("Failed to delete inventory: " + e.getMessage());
        }
        return apiResponse;
    }

    public ApiResponse getProductsByInventoryId(Long inventoryId) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Inventory inventory = inventoryRepository.findById(inventoryId)
                    .orElseThrow(() -> new RuntimeException("Inventory not found"));
            List<Product> products = inventory.getProducts();
            apiResponse.setSuccess(true);
            apiResponse.setData("products", products);
        } catch (Exception e) {
            apiResponse.setMessage("Failed to fetch products: " + e.getMessage());
        }
        return apiResponse;
    }

}
