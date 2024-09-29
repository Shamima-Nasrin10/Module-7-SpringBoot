package com.shamima.SCMSystem.accounting.restcontroller;
import com.shamima.SCMSystem.accounting.service.SalesDetailsService;
import com.shamima.SCMSystem.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/salesDetails")
public class SalesDetailsController {

    @Autowired
    private SalesDetailsService salesDetailsService;

    @GetMapping("/list")
    public ApiResponse getAllSalesDetails() {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            ApiResponse salesDetailsList = salesDetailsService.getAllSalesDetails();
            apiResponse.setSuccess(true);
            apiResponse.setData("salesDetails", salesDetailsList);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping("/grouped")
    public ApiResponse getGroupedSalesDetails() {
        ApiResponse apiResponse = new ApiResponse(false);
        try {
            ApiResponse groupedSalesDetails = salesDetailsService.getSalesDetailsGroupedBySalesId();
            apiResponse.setSuccess(true);
            apiResponse.setData("groupedSalesDetails", groupedSalesDetails);
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }
}
