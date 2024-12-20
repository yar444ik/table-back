package com.vorstu.table.dto.mapper;

import com.vorstu.table.dto.Student;
import com.vorstu.table.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "group", source = "dto.group")
    StudentEntity dtoToEntity(Student dto);

//    @Mapping(target = "group", source = "dto.group")
//    StudentEntity dtoToEntity(Student dto, StudentEntity entity) {
//        entity.setSurname(dto.getSurname());
//        entity.setName(dto.getName());
//        entity.setGroup(dto.getGroup());
//        return entity;
//    }

    Student entityToDto(StudentEntity entity);

}
