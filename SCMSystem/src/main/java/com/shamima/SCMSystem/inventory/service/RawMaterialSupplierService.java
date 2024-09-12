package com.shamima.SCMSystem.inventory.service;


import com.shamima.SCMSystem.inventory.entity.RawMaterial;
import com.shamima.SCMSystem.inventory.entity.RawMaterialSupplier;
import com.shamima.SCMSystem.inventory.repository.RawMaterialRepository;
import com.shamima.SCMSystem.inventory.repository.RawMaterialSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawMaterialSupplierService {

    @Autowired
    private RawMaterialSupplierRepository rawMaterialSupplierRepository;

    public List<RawMaterialSupplier> getAllRawMaterialSuppliers() {
        return rawMaterialSupplierRepository.findAll();
    }

    public void saveRawMaterialSupplier(RawMaterialSupplier rmSupplier) {
        rawMaterialSupplierRepository.save(rmSupplier);
    }

    public void deleteRawMaterialSupplier(Long id) {
        rawMaterialSupplierRepository.deleteById(id);
    }

    public RawMaterialSupplier getRawMaterialSupplierById(Long id) {
        return rawMaterialSupplierRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Supplier with id " + id + " not found"));
    }

    public RawMaterialSupplier updateRawMaterialSupplier(RawMaterialSupplier rmSupplier) {
       RawMaterialSupplier old = getRawMaterialSupplierById(rmSupplier.getId());
       old.setCompanyName(rmSupplier.getCompanyName());
       old.setContactPerson(rmSupplier.getContactPerson());
       old.setEmail(rmSupplier.getEmail());
       old.setCellNo(rmSupplier.getCellNo());
       old.setAddress(rmSupplier.getAddress());

      return rawMaterialSupplierRepository.save(old);
    }
}
