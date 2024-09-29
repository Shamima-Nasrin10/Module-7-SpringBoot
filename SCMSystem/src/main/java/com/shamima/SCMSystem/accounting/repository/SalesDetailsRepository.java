package com.shamima.SCMSystem.accounting.repository;

import com.shamima.SCMSystem.accounting.entity.Sales;
import com.shamima.SCMSystem.accounting.entity.SalesDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesDetailsRepository extends JpaRepository<SalesDetails, Long> {

    List<SalesDetails> findBySaleId(int salesId);

    // Custom query to group SalesDetails by Sales ID
    @Query("SELECT sd FROM SalesDetails sd GROUP BY sd.sale.id")
    List<SalesDetails> findSalesDetailsGroupedBySalesId();
}