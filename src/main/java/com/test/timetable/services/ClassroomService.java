package com.test.timetable.services;

import com.test.timetable.entities.Classroom;
import com.test.timetable.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public void saveClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    public List<Classroom> getAllClassrooms() {
        return (List<Classroom>) classroomRepository.findAll();
    }

    public Classroom getClassroomById(Long classroomId) {
        return classroomRepository.findById(classroomId).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void deleteClassroomById(Long classroomId) {
        classroomRepository.deleteById(classroomId);
    }

    @Transactional
    public void updateClassroomById(Long classroomId, String classroomNumber) {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new IllegalStateException("classroom with id " + classroomId + " does not exists"));
        if (classroomNumber != null &&
                classroomNumber.length() > 0 &&
                !Objects.equals(classroom.getClassroomNumber(), classroomNumber)) {
            classroom.setClassroomNumber(classroomNumber);
        }
    }
}