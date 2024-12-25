package com.vorstu.table.service;

import com.vorstu.table.dto.Student;
import com.vorstu.table.dto.mapper.StudentMapper;
import com.vorstu.table.entity.StudentEntity;
import com.vorstu.table.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper mapper;

//    public StudentService(@Autowired StudentRepository studentRepository, @Qualifier("studentMapper") @Autowired StudentMapper mapper) {
//        this.studentRepository = studentRepository;
//        this.mapper = mapper;
//    }

    public Student create(Student student) {
        return mapper.entityToDto(
                studentRepository.save(mapper.dtoToEntity(student))
        );
    }

    public Student update(Student student, Long id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        return mapper.entityToDto(studentRepository.save(dtoToEntity(student, studentEntity)));
    }

    public Long delete(Long id) {
        studentRepository.deleteById(id);
        return id;
    }

    public Page<Student> findAll(int page, int size, String sortField, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<StudentEntity> studentEntities = studentRepository.findAll(pageable);
        return studentEntities.map(mapper::entityToDto);
    }

    StudentEntity dtoToEntity(Student dto, StudentEntity entity) {
        entity.setSurname(dto.getSurname());
        entity.setName(dto.getName());
        entity.setGroup(dto.getGroup());
        return entity;
    }

//    public String register(RegistrationRequest request) {
//        AppUser appUser = new AppUser(
//                request.getFirstname(),request.getLastname(),request.getEmail(),request.getPassword(),AppUserRole.USER
//        );
//        String token = appUserService.signUpUser(appUser);
//        String confirmationLink = "http://localhost:9090/registration/confirmation?token=" + token;
//        // todo: send the email.
//
//        return token;
//    }

//    public  Page<Student> findByFilter(String filter, Pageable pageable) {
//        Page<StudentEntity> studentEntities = studentRepository.findByFilter(filter, pageable);
//        return studentEntities.map(mapper::entityToDto);
//    }
}
