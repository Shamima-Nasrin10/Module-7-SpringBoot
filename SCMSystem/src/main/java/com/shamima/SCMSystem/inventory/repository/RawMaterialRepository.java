package com.shamima.SCMSystem.inventory.repository;

import com.shamima.SCMSystem.inventory.entity.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {

    @Query("SELECT rm FROM RawMaterial rm WHERE rm.supplier.name = :suppliername")
    List<RawMaterial> findRMBySupplierName(@Param("supplierName") String suppliername);
}
