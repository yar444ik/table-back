package com.vorstu.table.controllers;

import com.vorstu.table.dto.Student;
import com.vorstu.table.service.StudentService;
import com.vorstu.table.service.auth.JwtUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/base/students")
@AllArgsConstructor
public class BaseController {

    private final StudentService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student newStudent) {
        return service.create(newStudent);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student changeStudent(@RequestBody Student changingStudent, @PathVariable Long id) {
        return service.update(changingStudent, id);
    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long deleteStudent(@PathVariable("id") Long id) {
        return service.delete(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Student> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        return service.findAll(page, size, sortField, sortDirection);
    }

//    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Page<Student> searchStudents(
//            @RequestParam("filter") String filter,
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "5") int size,
//            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
//            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection)
//    {
//        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
//        Pageable pageable = PageRequest.of(page, size, sort);
//        return service.findByFilter(filter, pageable);
//    }
}
