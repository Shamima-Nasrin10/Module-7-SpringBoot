package com.shamima.SCMSystem.goods.repository;

import com.shamima.SCMSystem.goods.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
