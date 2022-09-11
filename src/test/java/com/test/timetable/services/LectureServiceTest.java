package com.test.timetable.services;

import com.test.timetable.entities.Classroom;
import com.test.timetable.entities.Lecture;
import com.test.timetable.repositories.LectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {
    @Mock
    private LectureRepository lectureRepository;
    private LectureService underTest;

    @BeforeEach
    void setUp() {
        underTest = new LectureService(lectureRepository);
    }

    @Test
    void canSaveLecture() {
        //given
        Classroom classroom = new Classroom();
        Lecture lecture = new Lecture("Искусственный интеллект", "Борисов Э.В.", classroom);
        //when
        underTest.saveLecture(lecture);
        //then
        ArgumentCaptor<Lecture> lectureArgumentCaptor = ArgumentCaptor.forClass(Lecture.class);
        verify(lectureRepository).save(lectureArgumentCaptor.capture());
        Lecture capturedLecture = lectureArgumentCaptor.getValue();
        assertThat(capturedLecture).isEqualTo(lecture);
    }

    @Test
    void canGetAllLectures() {
        //when
        underTest.getAllLectures();
        //then
        verify(lectureRepository).findAll();
    }

    @Test
    void canDeleteLectureById() {
        //given
        Lecture lecture = new Lecture();
        //when
        underTest.deleteLectureById(lecture.getId());
        //then
        verify(lectureRepository).deleteById(lecture.getId());
    }
}