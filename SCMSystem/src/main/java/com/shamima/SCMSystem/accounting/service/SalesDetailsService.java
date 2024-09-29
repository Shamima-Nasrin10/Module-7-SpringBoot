package com.shamima.SCMSystem.accounting.service;

import com.shamima.SCMSystem.accounting.entity.SalesDetails;
import com.shamima.SCMSystem.accounting.repository.SalesDetailsRepository;
import com.shamima.SCMSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SalesDetailsService {

    @Autowired
    private SalesDetailsRepository salesDetailRepository;

    // Get all Sales Details
    public ApiResponse getAllSalesDetails() {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            List<SalesDetails> salesDetailsList = salesDetailRepository.findAll();
            apiResponse.setSuccess(true);
            apiResponse.setData("salesDetails", salesDetailsList);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    // Get Sales Details grouped by Sales ID
    public ApiResponse getSalesDetailsGroupedBySalesId() {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            List<SalesDetails> allSalesDetails = salesDetailRepository.findAll();

            // Group by sales ID
            Map<Long, List<SalesDetails>> groupedSalesDetails = allSalesDetails.stream()
                    .collect(Collectors.groupingBy(sd -> sd.getSale().getId()));

            apiResponse.setSuccess(true);
            apiResponse.setData("groupedSalesDetails", groupedSalesDetails);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }
}
