package com.test.timetable.services;

import com.test.timetable.entities.Classroom;
import com.test.timetable.entities.Lecture;
import com.test.timetable.repositories.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public void saveLecture(Lecture lecture) {
        lectureRepository.save(lecture);
    }

    public List<Lecture> getAllLectures() {
        return (List<Lecture>) lectureRepository.findAll();
    }

    public Lecture getLectureById(Long lectureId) {
        return lectureRepository.findById(lectureId).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void deleteLectureById(Long lectureId) {
        lectureRepository.deleteById(lectureId);
    }

    @Transactional
    public void updateLectureById(Long lectureId, String name, String lecturer, Classroom classroom) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalStateException("lecture with id " + lectureId + " does not exists"));
        if(name != null && name.length() > 0 && !Objects.equals(lecture.getName(), name)) {
            lecture.setName(name);
        }

        if(lecturer != null && lecturer.length() > 0 && !Objects.equals(lecture.getLecturer(), lecturer)) {
            lecture.setLecturer(lecturer);
        }

        if(classroom != null && !Objects.equals(lecture.getClassroom(), classroom)) {
            lecture.setClassroom(classroom);
        }
    }
}