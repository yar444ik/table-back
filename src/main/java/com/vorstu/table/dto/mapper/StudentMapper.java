package com.vorstu.table.dto.mapper;

import com.vorstu.table.dto.Student;
import com.vorstu.table.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "group", source = "dto.group")
    StudentEntity dtoToEntity(Student dto);

    Student entityToDto(StudentEntity entity);

}
