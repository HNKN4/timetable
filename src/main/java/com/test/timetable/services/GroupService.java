package com.test.timetable.services;

import com.test.timetable.entities.Group;
import com.test.timetable.entities.Student;
import com.test.timetable.entities.Timetable;
import com.test.timetable.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    public List<Group> getAllGroups() {
        return (List<Group>) groupRepository.findAll();
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void deleteGroupById(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    @Transactional
    public void updateGroupById(Long groupId, String name, List<Student> students, List<Timetable> timetables) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalStateException("group with id " + groupId + " does not exists"));
        if(name != null && name.length() > 0 && !Objects.equals(group.getName(), name)) {
               group.setName(name);
        }

        if(students != null && !Objects.equals(group.getStudents(), students)) {
            group.setStudents(students);
        }

        if(timetables != null && !Objects.equals(group.getTimetables(), timetables)) {
            group.setTimetables(timetables);
        }
    }
}