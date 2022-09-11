package com.test.timetable.services;

import com.test.timetable.entities.Group;
import com.test.timetable.entities.Student;
import com.test.timetable.entities.Timetable;
import com.test.timetable.repositories.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {
    @Mock
    private GroupRepository groupRepository;
    private GroupService underTest;

    @BeforeEach
    void setUp() {
        underTest = new GroupService(groupRepository);
    }

    @Test
    void canSaveGroup() {
        //given
        List<Student> students = new ArrayList<>();
        List<Timetable> timetables = new ArrayList<>();
        Group group = new Group("ИС-1051", students, timetables);
        //when
        underTest.saveGroup(group);
        //then
        ArgumentCaptor<Group> groupArgumentCaptor = ArgumentCaptor.forClass(Group.class);
        verify(groupRepository).save(groupArgumentCaptor.capture());
        Group capturedGroup = groupArgumentCaptor.getValue();
        assertThat(capturedGroup).isEqualTo(group);
    }

    @Test
    void canGetAllGroups() {
        //when
        underTest.getAllGroups();
        //then
        verify(groupRepository).findAll();
    }

    @Test
    void canDeleteGroupById() {
        //given
        Group group = new Group();
        //when
        underTest.deleteGroupById(group.getId());
        //then
        verify(groupRepository).deleteById(group.getId());
    }
}