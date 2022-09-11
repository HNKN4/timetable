package com.test.timetable.services;

import com.test.timetable.entities.Student;
import com.test.timetable.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student getStudentById(Long studentId){
        return studentRepository.findById(studentId).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudentById(Long studentId, String name, String surname) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists"));
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if(surname != null && surname.length() > 0 && !Objects.equals(student.getSurname(), surname)) {
            student.setSurname(surname);
        }
    }
}