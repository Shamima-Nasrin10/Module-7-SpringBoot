package com.emranhss.FirstSpringBoot.restcontroller;

import com.emranhss.FirstSpringBoot.entity.Department;
import com.emranhss.FirstSpringBoot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dep")
public class DepartmentRestController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/save")
    public void saveDepartment(@RequestBody Department d) {
        departmentService.saveDepartment(d);
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void deleteDepartment(@PathVariable("id") int id) {
        departmentService.deleteDepById(id);
    }

}
