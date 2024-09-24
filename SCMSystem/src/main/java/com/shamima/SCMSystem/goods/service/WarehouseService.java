package com.shamima.SCMSystem.goods.service;

import com.shamima.SCMSystem.goods.entity.Inventory;
import com.shamima.SCMSystem.goods.entity.Warehouse;
import com.shamima.SCMSystem.goods.repository.InventoryRepository;
import com.shamima.SCMSystem.goods.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public Warehouse createWarehouse(Warehouse warehouse) {

        return warehouseRepository.save(warehouse);
    }

    public List<Inventory> getInventoriesByWarehouse(Long warehouseId) {
        return inventoryRepository.findByWarehouseId(warehouseId);
    }

    public Warehouse addInventoryToWarehouse(Long warehouseId, Inventory inventory) {
        try {
            // Try to find the warehouse by ID
            Warehouse warehouse = warehouseRepository.findById(warehouseId)
                    .orElseThrow(() -> new RuntimeException("Warehouse not found with ID: " + warehouseId));

            // Set the warehouse for the inventory
            inventory.setWarehouse(warehouse);

            // Save the inventory
            inventoryRepository.save(inventory);

            return warehouse;
        } catch (RuntimeException e) {
            // Handle any exceptions, including when the warehouse is not found
            throw new RuntimeException("Failed to add inventory to warehouse: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
        }
    }
}
