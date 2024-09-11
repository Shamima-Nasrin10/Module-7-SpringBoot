package com.shamima.SCMSystem.inventory.service;


import com.shamima.SCMSystem.inventory.entity.RawMaterial;
import com.shamima.SCMSystem.inventory.repository.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawMaterialService {

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    public List<RawMaterial> getAllRawMaterials() {

        return rawMaterialRepository.findAll();

    }

    public void saveRawMaterial(RawMaterial rm) {
        rawMaterialRepository.save(rm);
    }

    public void deleteRawMaterialById(long id) {
        rawMaterialRepository.deleteById(id);
    }

    private RawMaterial getRawMaterialById(long id) {

            return rawMaterialRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("RawMaterial with id"+ id +"not found"));
    }
}
