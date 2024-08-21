package com.emranhss.FirstSpringBoot.restcontroller;


import com.emranhss.FirstSpringBoot.entity.Student;
import com.emranhss.FirstSpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/api")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/save")
    public void save(@RequestBody Student student) {
        studentService.saveStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
       studentService.deleteById(id);
    }

}
