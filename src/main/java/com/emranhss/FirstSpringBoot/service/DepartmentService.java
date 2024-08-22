package com.emranhss.FirstSpringBoot.service;


import com.emranhss.FirstSpringBoot.entity.Department;
import com.emranhss.FirstSpringBoot.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public void saveDepartment(Department d) {
        departmentRepository.save(d);
    }
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }
    public Department findById(Integer id) {
        return departmentRepository.findById(id).get();
    }

    public void updateDepartment(Department d) {
        departmentRepository.save(d);
    }
}
