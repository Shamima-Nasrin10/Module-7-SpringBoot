package com.shamima.SCMSystem.inventory.repository;

import com.shamima.SCMSystem.inventory.entity.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
}
