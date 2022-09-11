package com.test.timetable.services;

import com.test.timetable.entities.Lecture;
import com.test.timetable.entities.Timetable;
import com.test.timetable.repositories.TimetableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TimetableServiceTest {
    @Mock
    private TimetableRepository timetableRepository;
    private TimetableService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TimetableService(timetableRepository);
    }

    @Test
    void canSaveTimetable() {
        //given
        List<Lecture> lectures = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Timetable timetable = new Timetable("Расписание группы ИС-1051", date, lectures);
        //when
        underTest.saveTimetable(timetable);
        //then
        ArgumentCaptor<Timetable> timetableArgumentCaptor = ArgumentCaptor.forClass(Timetable.class);
        verify(timetableRepository).save(timetableArgumentCaptor.capture());
        Timetable capturedTimetable = timetableArgumentCaptor.getValue();
        assertThat(capturedTimetable).isEqualTo(timetable);
    }

    @Test
    void canGetAllTimetables() {
        //when
        underTest.getAllTimetables();
        //then
        verify(timetableRepository).findAll();
    }

    @Test
    void canDeleteTimetableById() {
        //when
        Timetable timetable = new Timetable();
        //when
        underTest.deleteTimetableById(timetable.getId());
        //then
        verify(timetableRepository).deleteById(timetable.getId());
    }
}