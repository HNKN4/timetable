package com.test.timetable.controllers;

import com.test.timetable.entities.Lecture;
import com.test.timetable.entities.Timetable;
import com.test.timetable.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/timetables")
public class TimetableController {
    private final TimetableService timetableService;

    @Autowired
    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @PostMapping("/save")
    protected void save(@RequestBody Timetable timetable) {
        timetableService.saveTimetable(timetable);
    }

    @GetMapping("/allTimetables")
    protected List<Timetable> getAll() {
        return timetableService.getAllTimetables();
    }

    @GetMapping("/timetableById")
    protected Timetable getById(@RequestParam("timetableId") Long timetableId) {
        return timetableService.getTimetableById(timetableId);
    }

    @DeleteMapping("/delete")
    protected void deleteById(@RequestParam("timetableId") Long timetableId) {
        timetableService.deleteTimetableById(timetableId);
    }

    @PutMapping(path = "{timetableId}")
    protected void updateById(
            @PathVariable("timetableId") Long timetableId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<Lecture> lectures
    ) {
        timetableService.updateTimetableById(timetableId, name, lectures);
    }

    @GetMapping("/timetableByStudentIdAndDate")
    protected Timetable getByStudentIdAndDate(@RequestParam("studentId") Long studentId,
                                              @RequestParam("date")
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return timetableService.getTimetableByStudentIdAndDate(studentId, date);
    }
}