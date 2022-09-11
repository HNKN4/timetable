package com.test.timetable.repositories;

import com.test.timetable.entities.Timetable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TimetableRepository extends CrudRepository<Timetable, Long> {
    @Query(value = "select t.* from groups g " +
            "left join groups_students gs ON g.id = gs.group_id " +
            "left join groups_timetable gt on g.id = gt.group_id " +
            "left join timetables t on gt.timetable_id = t.id " +
            "where gs.students_id = :studentId and t.date = :date",
    nativeQuery = true)
    Optional<Timetable> findTimetableByStudentIdAndDate(@Param("studentId") Long studentId,
                                                        @Param("date") LocalDate date);
}