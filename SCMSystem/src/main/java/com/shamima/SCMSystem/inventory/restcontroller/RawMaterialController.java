package com.shamima.SCMSystem.inventory.restcontroller;


import com.shamima.SCMSystem.inventory.entity.RawMaterial;
import com.shamima.SCMSystem.inventory.service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/rawmaterial")
@CrossOrigin("*")
public class RawMaterialController {

    @Autowired
    private RawMaterialService rawMaterialService;

    @PostMapping("/save")
    public ResponseEntity<RawMaterial> save(@RequestPart RawMaterial rawMaterial,
                                            @RequestPart(required = false) MultipartFile imageFile) throws Exception {

        rawMaterialService.saveRawMaterial(rawMaterial, imageFile);
        return new ResponseEntity<>(rawMaterial, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<RawMaterial>> getRawMaterials() {
        List<RawMaterial> rawMaterials = rawMaterialService.getAllRawMaterials();
        return ResponseEntity.ok(rawMaterials);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RawMaterial> updateRawMaterial(
            @RequestBody RawMaterial rm,
            @RequestParam(value = "image") MultipartFile imageFile,
            @PathVariable long id
    ) throws Exception {
        rawMaterialService.updateRawMaterial(id, rm, imageFile);
        return ResponseEntity.ok(rm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawMaterial> findRoomById(@PathVariable int id) {
        try {
            RawMaterial rm = rawMaterialService.findRawMaterialById(id);
            return ResponseEntity.ok(rm);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/r/searchrawmaterial")
    public ResponseEntity<List<RawMaterial>> findRoomBySupplierName(@RequestParam("supplierName") String supplierlName) {
        List<RawMaterial> rm = rawMaterialService.findRawMaterialsBySupplierName(supplierlName);
        return ResponseEntity.ok(rm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRawMaterialById(@PathVariable long id) {
        rawMaterialService.deleteRawMaterialById(id);
        return ResponseEntity.noContent().build();
    }


}
