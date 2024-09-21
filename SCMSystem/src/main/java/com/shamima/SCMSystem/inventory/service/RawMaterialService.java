package com.shamima.SCMSystem.inventory.service;

import com.shamima.SCMSystem.inventory.entity.RawMaterial;
import com.shamima.SCMSystem.inventory.entity.RawMaterialSupplier;
import com.shamima.SCMSystem.inventory.repository.RawMaterialRepository;
import com.shamima.SCMSystem.inventory.repository.RawMaterialSupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class RawMaterialService {

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @Autowired
    private RawMaterialSupplierRepository rawMaterialSupplierRepository;

    @Value("src/main/resources/static/images")
    private String uploadDir;

    public List<RawMaterial> getAllRawMaterials() {

        return rawMaterialRepository.findAll();

    }

    @Transactional
    public void saveRawMaterial(RawMaterial rm, MultipartFile imageFile) throws Exception {

        RawMaterialSupplier rawMaterialSupplier = rawMaterialSupplierRepository.findById(rm.getSupplier().getId())
                .orElseThrow(() -> new RuntimeException("Supplier with thid ID not found"));

        System.out.println(rawMaterialSupplier.toString());

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageFileName = saveImage(imageFile, rm);
            rm.setImage(imageFileName);
        }
        rm.setSupplier(rawMaterialSupplier);
        rawMaterialRepository.save(rm);

    }

    public void deleteRawMaterialById(long id) {
        rawMaterialRepository.deleteById(id);
    }

    public RawMaterial findRawMaterialById(long id) {
        return rawMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RawMaterial with id" + id + "not found"));
    }

    private String saveImage(MultipartFile file, RawMaterial rm) throws IOException {
        Path uploadPath = Paths.get(uploadDir + "/rawmaterial");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate a unique filename
        String filename = rm.getName() + "_" + UUID.randomUUID().toString();
        Path filePath = uploadPath.resolve(filename);

        // Save the file
        Files.copy(file.getInputStream(), filePath);

        return filename; // Return the filename for storing in the database
    }

    @Transactional
    public void updateRawMaterial(long id, RawMaterial updatedRM, MultipartFile imageFile) throws Exception {
        RawMaterial existingRM = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RawMaterial with id" + id + "not found"));

//Update Raw MAterial details
        existingRM.setName(updatedRM.getName());
        existingRM.setPrice(updatedRM.getPrice());
        existingRM.setQuantity(updatedRM.getQuantity());
        existingRM.setUnit(updatedRM.getUnit());

// Update Supplier
        RawMaterialSupplier rmSupplier = rawMaterialSupplierRepository.findById(updatedRM.getSupplier().getId())
                .orElseThrow(() -> new RuntimeException("Supplier with this ID not found"));
        existingRM.setSupplier(rmSupplier);

        // Update image if provided
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageFilename = saveImage(imageFile, existingRM);
            existingRM.setImage(imageFilename);
        }
        rawMaterialRepository.save(existingRM);
    }

    public List<RawMaterial> findRawMaterialsBySupplierName(String supplierName) {
        return rawMaterialRepository.findRMBySupplierName(supplierName);
    }
}
