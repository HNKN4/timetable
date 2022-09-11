package com.test.timetable.controllers;

import com.test.timetable.entities.Student;
import com.test.timetable.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    protected void save(@RequestBody Student student) {
        studentService.saveStudent(student);
    }

    @GetMapping("/allStudents")
    protected List<Student> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/studentById")
    protected Student getById(@RequestParam("studentId") Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @DeleteMapping("/delete")
    protected void deleteById(@RequestParam("studentId") Long studentId) {
        studentService.deleteStudentById(studentId);
    }

    @PutMapping(path = "{studentId}")
    protected void updateById(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname
            ) {
        studentService.updateStudentById(studentId, name, surname);
    }
}