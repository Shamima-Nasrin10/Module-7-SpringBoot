package com.shamima.SCMSystem.production.entity;

import com.shamima.SCMSystem.inventory.entity.RawMaterialSupplier;
import com.shamima.SCMSystem.security.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "prod_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private Size size;

    public enum Size {
        XS,
        S,
        M,
        L,
        XL,
        XXL,
        XXXL
    }
    
}
