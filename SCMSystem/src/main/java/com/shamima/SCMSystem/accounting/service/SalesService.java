package com.shamima.SCMSystem.accounting.service;

import com.shamima.SCMSystem.accounting.entity.Sales;
import com.shamima.SCMSystem.accounting.entity.SalesDetails;
import com.shamima.SCMSystem.accounting.repository.SalesDetailsRepository;
import com.shamima.SCMSystem.accounting.repository.SalesRepository;
import com.shamima.SCMSystem.goods.entity.Product;
import com.shamima.SCMSystem.goods.repository.ProductRepository;
import com.shamima.SCMSystem.util.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SalesDetailsRepository salesDetailsRepository;

    // Get all Sales
    public ApiResponse getAllSales() {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            List<Sales> salesList = salesRepository.findAll();
            apiResponse.setSuccess(true);
            apiResponse.setData("sales", salesList);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @Transactional
    public ApiResponse saveSales(Sales sales) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            // Save the sale first to get the generated ID
            Sales savedSales = salesRepository.save(sales);

            for (Product soldProduct : savedSales.getProduct()) {
                Product product = productRepository.findById(soldProduct.getId())
                        .orElseThrow(() -> new RuntimeException("Product not found with ID " + soldProduct.getId()));

                // Update product stock
                int newStock = product.getStock() - soldProduct.getQuantity();
                if (newStock < 0) {
                    throw new RuntimeException("Not enough stock for product " + product.getName());
                }
                product.setStock(newStock);
                productRepository.save(product);

                // Create SalesDetails for each product
                SalesDetails salesDetails = new SalesDetails();
                salesDetails.setSale(savedSales);
                salesDetails.setProduct(product);
                salesDetails.setQuantity(soldProduct.getQuantity());
                salesDetails.setUnitPrice(product.getUnitPrice());
                salesDetails.setDiscount(sales.getDiscount());

                // Apply discount (if any) to calculate total price
                double discount = sales.getDiscount();  // Discount from Sales entity
                double unitPrice = product.getUnitPrice();
                long quantity = soldProduct.getQuantity();

                // Calculate total price after discount
                double totalPrice = quantity * unitPrice * (1 - discount / 100);
                salesDetails.setTotalPrice(totalPrice);

                // Save the sales details
                salesDetailsRepository.save(salesDetails);
            }

            apiResponse.setSuccess(true);
            apiResponse.setMessage("Sales saved successfully");
            apiResponse.setData("sales", savedSales);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    // Delete Sales by ID
    @Transactional
    public ApiResponse deleteSalesById(long id) {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            if (salesRepository.existsById(id)) {
                salesRepository.deleteById(id);
                apiResponse.setSuccess(true);
                apiResponse.setMessage("Sales deleted successfully");
            } else {
                apiResponse.setMessage("Sales not found with ID " + id);
            }
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

}