package com.test.timetable.controllers;

import com.test.timetable.entities.Classroom;
import com.test.timetable.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping("/save")
    protected void save(@RequestBody Classroom classroom) {
        classroomService.saveClassroom(classroom);
    }

    @GetMapping("/allClassrooms")
    protected List<Classroom> getAll() {
        return classroomService.getAllClassrooms();
    }

    @GetMapping("/classroomById")
    protected Classroom getById(@RequestParam("classroomId") Long classroomId) {
        return classroomService.getClassroomById(classroomId);
    }

    @DeleteMapping("/delete")
    protected void deleteById(@RequestParam("classroomId") Long classroomId) {
        classroomService.deleteClassroomById(classroomId);
    }

    @PutMapping(path = "{classroomId}")
    protected void updateById(
            @PathVariable("classroomId") Long classroomId,
            @RequestParam(required = false) String classroomNumber
    ) {
        classroomService.updateClassroomById(classroomId,classroomNumber);
    }
}