package com.vorstu.table.repositories;

import com.vorstu.table.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.*;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query("from StudentEntity")
    Page<StudentEntity> findAll(Pageable pageable);

//    @Query("SELECT s FROM StudentEntity s WHERE " +
//            "LOWER(s.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
//            "LOWER(s.surname) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
//            "LOWER(s.group) LIKE LOWER(CONCAT('%', :filter, '%'))")
//    Page<StudentEntity> findByFilter(String filter, Pageable pageable);
}
