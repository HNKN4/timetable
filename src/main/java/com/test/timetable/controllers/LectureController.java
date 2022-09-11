package com.test.timetable.controllers;

import com.test.timetable.entities.Classroom;
import com.test.timetable.entities.Lecture;
import com.test.timetable.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureController {
    private final LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping("/save")
    protected void save(@RequestBody Lecture lecture) {
        lectureService.saveLecture(lecture);
    }

    @GetMapping("/allLectures")
    protected List<Lecture> getAll() {
        return lectureService.getAllLectures();
    }

    @GetMapping("/lectureById")
    protected Lecture getById(@RequestParam("lectureId") Long lectureId) {
        return lectureService.getLectureById(lectureId);
    }

    @DeleteMapping("/delete")
    protected void deleteById(@RequestParam("lectureId") Long lectureId) {
        lectureService.deleteLectureById(lectureId);
    }

    @PutMapping(path = "{lectureId}")
    protected void updateById(
            @PathVariable("lectureId") Long lectureId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lecturer,
            @RequestParam(required = false) Classroom classroom
    ) {
        lectureService.updateLectureById(lectureId, name, lecturer, classroom);
    }
}