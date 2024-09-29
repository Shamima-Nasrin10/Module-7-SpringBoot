package com.shamima.SCMSystem.accounting.entity;

import com.shamima.SCMSystem.goods.entity.Product;
import com.shamima.SCMSystem.production.entity.Retailer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "acc_sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "retailer_id")
    private Retailer  retailer;
    private Date salesdate;
    private int totalprice;

    private int quantity;
    private float discount;



    @ManyToMany
    private List<Product> product;


    @ManyToOne
    private SalesDetails salesDetails;

}
