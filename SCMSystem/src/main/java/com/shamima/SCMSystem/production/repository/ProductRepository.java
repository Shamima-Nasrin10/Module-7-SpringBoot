package com.shamima.SCMSystem.production.repository;

import com.shamima.SCMSystem.production.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
