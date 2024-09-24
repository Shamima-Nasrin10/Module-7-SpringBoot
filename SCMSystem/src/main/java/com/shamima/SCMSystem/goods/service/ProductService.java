package com.shamima.SCMSystem.goods.service;

import com.shamima.SCMSystem.goods.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
}
