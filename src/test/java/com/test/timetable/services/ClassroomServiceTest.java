package com.test.timetable.services;

import com.test.timetable.entities.Classroom;
import com.test.timetable.repositories.ClassroomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClassroomServiceTest {
    @Mock
    private ClassroomRepository classroomRepository;
    private ClassroomService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ClassroomService(classroomRepository);
    }

    @Test
    void canSaveClassroom() {
        //given
        Classroom classroom = new Classroom("№205");
        //when
        underTest.saveClassroom(classroom);
        //then
        ArgumentCaptor<Classroom> classroomArgumentCaptor = ArgumentCaptor.forClass(Classroom.class);
        verify(classroomRepository).save(classroomArgumentCaptor.capture());
        Classroom capturedClassroom = classroomArgumentCaptor.getValue();
        assertThat(capturedClassroom).isEqualTo(classroom);
    }

    @Test
    void canGetAllClassrooms() {
        //when
        underTest.getAllClassrooms();
        //then
        verify(classroomRepository).findAll();
    }

    @Test
    void canDeleteClassroomById() {
        //given
        Classroom classroom = new Classroom();
        //when
        underTest.deleteClassroomById(classroom.getId());
        //then
        verify(classroomRepository).deleteById(classroom.getId());
    }
}