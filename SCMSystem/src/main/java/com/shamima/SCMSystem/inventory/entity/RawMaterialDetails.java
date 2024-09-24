package com.shamima.SCMSystem.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RawMaterialDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
