package com.shamima.SCMSystem.inventory.repository;

import com.shamima.SCMSystem.inventory.entity.RawMaterialSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawMaterialSupplierRepository extends JpaRepository<RawMaterialSupplier, Long> {
}
