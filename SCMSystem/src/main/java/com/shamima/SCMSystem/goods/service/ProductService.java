package com.shamima.SCMSystem.goods.service;

import com.shamima.SCMSystem.goods.entity.Inventory;
import com.shamima.SCMSystem.goods.entity.Product;
import com.shamima.SCMSystem.goods.repository.InventoryRepository;
import com.shamima.SCMSystem.goods.repository.ProductRepository;
import com.shamima.SCMSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public ApiResponse saveProduct(Product product, Long inventoryId) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Inventory inventory = inventoryRepository.findById(inventoryId)
                    .orElseThrow(() -> new RuntimeException("Inventory not found with ID: " + inventoryId));

            product.setInventory(inventory);
            productRepository.save(product);

            apiResponse.setSuccess(true);
            apiResponse.setMessage("Product saved successfully.");
        } catch (Exception e) {
            apiResponse.setMessage("Error while saving product: " + e.getMessage());
        }
        return apiResponse;
    }


    @Transactional
    public ApiResponse updateProduct(Product updatedProduct, Long inventoryId) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Product existingProduct = productRepository.findById(updatedProduct.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + updatedProduct.getId()));

            Inventory inventory = inventoryRepository.findById(inventoryId)
                    .orElseThrow(() -> new RuntimeException("Inventory not found with ID: " + inventoryId));

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setUnitPrice(updatedProduct.getUnitPrice());
            existingProduct.setStock(updatedProduct.getStock());
            existingProduct.setUnit(updatedProduct.getUnit());
            existingProduct.setInventory(inventory);

            productRepository.save(existingProduct);

            apiResponse.setSuccess(true);
            apiResponse.setMessage("Product updated successfully.");
        } catch (Exception e) {
            apiResponse.setMessage("Error while updating product: " + e.getMessage());
        }
        return apiResponse;
    }


    public ApiResponse getAllProducts() {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            List<Product> products = productRepository.findAll();
            apiResponse.setSuccess(true);
            apiResponse.setData("products", products);
        } catch (Exception e) {
            apiResponse.setMessage("Error fetching products: " + e.getMessage());
        }
        return apiResponse;
    }


    public ApiResponse findProductById(long id) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
            apiResponse.setSuccess(true);
            apiResponse.setData("product", product);
        } catch (Exception e) {
            apiResponse.setMessage("Error fetching product: " + e.getMessage());
        }
        return apiResponse;
    }


    @Transactional
    public ApiResponse deleteProductById(long id) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
            productRepository.deleteById(id);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Product deleted successfully.");
        } catch (Exception e) {
            apiResponse.setMessage("Error deleting product: " + e.getMessage());
        }
        return apiResponse;
    }


    public ApiResponse getProductsByInventoryId(Long inventoryId) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            Inventory inventory = inventoryRepository.findById(inventoryId)
                    .orElse(null);
            if (inventory == null) {
                apiResponse.setMessage("Inventory not found");
                return apiResponse;
            }

            List<Product> products = productRepository.findAllByInventoryId(inventoryId).orElse(new ArrayList<>());
            apiResponse.setSuccess(true);
            apiResponse.setData("products", products);
        } catch (Exception e) {
            apiResponse.setMessage("Failed to fetch products: " + e.getMessage());
        }
        return apiResponse;
    }
}
