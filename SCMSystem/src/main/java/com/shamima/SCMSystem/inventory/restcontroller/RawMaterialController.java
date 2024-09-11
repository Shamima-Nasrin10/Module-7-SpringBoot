package com.shamima.SCMSystem.inventory.restcontroller;


import com.shamima.SCMSystem.inventory.service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rawmaterial")
@CrossOrigin("*")
public class RawMaterialController {

    @Autowired
    private RawMaterialService rawMaterialService;


}
