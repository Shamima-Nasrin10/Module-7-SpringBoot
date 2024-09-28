package com.shamima.SCMSystem.production.repository;

import com.shamima.SCMSystem.production.entity.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailerRepository extends JpaRepository<Retailer, Long> {

}
