package com.emranhss.FirstSpringBoot.service;


import com.emranhss.FirstSpringBoot.entity.Department;
import com.emranhss.FirstSpringBoot.entity.Faculty;
import com.emranhss.FirstSpringBoot.repository.DepartmentRepository;
import com.emranhss.FirstSpringBoot.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public void saveDepartment(Department d) {
        Faculty faculty = facultyRepository.findById(d.getFaculty().getId())
                .orElseThrow(
                        () -> new RuntimeException("Faculty not found has Id:" + d.getFaculty().getId())
                );
        d.setFaculty(faculty);
        departmentRepository.save(d);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public void deleteDepById(Integer id) {
        departmentRepository.deleteById(id);
    }

    public Department findById(Integer id) {
        return departmentRepository.findById(id).get();
    }

    public void updateDepartment(Department d) {
        departmentRepository.save(d);
    }
}
