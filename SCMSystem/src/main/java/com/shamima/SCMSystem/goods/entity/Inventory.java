package com.shamima.SCMSystem.goods.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long capacity;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonBackReference
    @Column(name = "warehouse_id")
    private Warehouse warehouse;

    private InventoryCategory category;

    public enum InventoryCategory {
        PRODUCT,
        RAW_MATERIAL
    }
}
