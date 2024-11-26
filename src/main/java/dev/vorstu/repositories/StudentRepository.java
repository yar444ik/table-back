package dev.vorstu.repositories;

import dev.vorstu.dto.Student;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {
//    @Query("from Student")
//    Page<Student> findAllByPageRequest(Pageable pageable);
//
//    @Query("SELECT s FROM Student s WHERE " +
//            "LOWER(s.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
//            "LOWER(s.surname) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
//            "LOWER(s.group) LIKE LOWER(CONCAT('%', :filter, '%'))")
//    Page<Student> findByFilter(@Param("filter") String filter, Pageable pageable);
}
