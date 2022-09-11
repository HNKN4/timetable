package com.test.timetable.controllers;

import com.test.timetable.entities.Group;
import com.test.timetable.entities.Student;
import com.test.timetable.entities.Timetable;
import com.test.timetable.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/save")
    protected void save(@RequestBody Group group) {
        groupService.saveGroup(group);
    }

    @GetMapping("/allGroups")
    protected List<Group> getAll() {
        return groupService.getAllGroups();
    }

    @GetMapping("/groupById")
    protected Group getById(@RequestParam("groupId") Long groupId) {
        return groupService.getGroupById(groupId);
    }

    @DeleteMapping("/delete")
    protected void deleteById(@RequestParam("groupId") Long groupId) {
        groupService.deleteGroupById(groupId);
    }

    @PutMapping(path = "{groupId}")
    protected void updateById(
            @PathVariable("groupId") Long groupId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<Student> students,
            @RequestParam(required = false) List<Timetable> timetables
    ) {
        groupService.updateGroupById(groupId, name, students, timetables);
    }
}