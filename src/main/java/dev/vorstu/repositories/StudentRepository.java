package dev.vorstu.repositories;

import dev.vorstu.entities.StudentEntity;
import org.springframework.data.jpa.repository.*;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
//    @Query("from Student")
//    Page<Student> findAllByPageRequest(Pageable pageable);
//
//    @Query("SELECT s FROM Student s WHERE " +
//            "LOWER(s.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
//            "LOWER(s.surname) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
//            "LOWER(s.group) LIKE LOWER(CONCAT('%', :filter, '%'))")
//    Page<Student> findByFilter(@Param("filter") String filter, Pageable pageable);
}
