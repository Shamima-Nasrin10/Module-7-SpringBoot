package com.shamima.SCMSystem.inventory.repository;

import com.shamima.SCMSystem.inventory.entity.RawMaterial;
import com.shamima.SCMSystem.inventory.entity.RawMaterialCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RawMaterialCategoryRepository extends JpaRepository<RawMaterialCategory, Long> {

}
