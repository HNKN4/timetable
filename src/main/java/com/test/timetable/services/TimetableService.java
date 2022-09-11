package com.test.timetable.services;

import com.test.timetable.entities.Lecture;
import com.test.timetable.entities.Timetable;
import com.test.timetable.repositories.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class TimetableService {
    private final TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public void saveTimetable(Timetable timetable) {
        timetableRepository.save(timetable);
    }

    public List<Timetable> getAllTimetables() {
        return (List<Timetable>) timetableRepository.findAll();
    }

    public Timetable getTimetableById(Long timetableId) {
        return timetableRepository.findById(timetableId).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void deleteTimetableById(Long timetableId) {
        timetableRepository.deleteById(timetableId);
    }

    @Transactional
    public void updateTimetableById(Long timetableId, String name, List<Lecture> lectures) {
        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new IllegalStateException("timetable with id " + timetableId + " does not exists"));
        if(name != null && name.length() > 0 && !Objects.equals(timetable.getName(), name)) {
            timetable.setName(name);
        }

        if(lectures != null && !Objects.equals(timetable.getLectures(), lectures)) {
            timetable.setLectures(lectures);
        }
    }

    public Timetable getTimetableByStudentIdAndDate(Long studentId, LocalDate date) {
        return timetableRepository.findTimetableByStudentIdAndDate(studentId, date).
                orElseThrow(RuntimeException::new);
    }
}