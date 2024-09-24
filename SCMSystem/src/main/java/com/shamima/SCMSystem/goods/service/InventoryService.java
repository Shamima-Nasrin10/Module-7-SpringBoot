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
    private ProductRepository productRepository;  // Add ProductRepository for product-related operations

    // Save Inventory
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

    // Get all Inventories
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

    // Find Inventory by ID
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

    // Update Inventory
    @Transactional
    public ApiResponse updateInventory(Inventory updatedInventory, Long warehouseId) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Inventory existingInventory = inventoryRepository.findById(updatedInventory.getId()).orElse(null);
            if (existingInventory == null) {
                apiResponse.setMessage("Inventory not found");
                return apiResponse;
            }

            Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
            if (warehouse == null) {
                apiResponse.setMessage("Warehouse not found");
                return apiResponse;
            }

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

    // Delete Inventory
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

    // New Method: Get Products by Inventory ID
    public ApiResponse getProductsByInventoryId(Long inventoryId) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Inventory inventory = inventoryRepository.findById(inventoryId)
                    .orElseThrow(() -> new RuntimeException("Inventory not found"));
            List<Product> products = inventory.getProducts(); // Assuming a getter for products exists in Inventory entity
            apiResponse.setSuccess(true);
            apiResponse.setData("products", products);
        } catch (Exception e) {
            apiResponse.setMessage("Failed to fetch products: " + e.getMessage());
        }
        return apiResponse;
    }

}
