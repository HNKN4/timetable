package com.test.timetable.repositories;

import com.test.timetable.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}