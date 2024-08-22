package com.emranhss.FirstSpringBoot.restcontroller;

import com.emranhss.FirstSpringBoot.entity.Faculty;
import com.emranhss.FirstSpringBoot.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyRestController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/list")
    public List<Faculty> getAllFaculty() {
        return facultyService.getAllFaculty();
    }


    @PostMapping("/save")
    public void saveFaculty(@RequestBody Faculty f) {
        facultyService.saveFaculty(f);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFaculty(@PathVariable("id") int id) {
        facultyService.deleteFacultyById(id);
    }

    @PutMapping("/update")
    public void updateFaculty(@RequestBody Faculty f) {
        facultyService.updateFaculty(f);
    }
}
