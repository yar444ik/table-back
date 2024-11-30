package dev.vorstu.mappers;

import dev.vorstu.dto.StudentDTO;
import dev.vorstu.entities.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {

    StudentEntity toStudentEntity(StudentDTO studentDTO);
    StudentDTO toStudentDTO(StudentEntity studentEntity);

    @Mapping(target = "id", ignore = true)
    StudentEntity toStudentEntityExceptId(@MappingTarget StudentEntity studentEntity, StudentDTO studentDTO);

}
