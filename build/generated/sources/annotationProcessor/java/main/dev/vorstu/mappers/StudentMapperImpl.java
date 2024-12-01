package dev.vorstu.mappers;

import dev.vorstu.dto.StudentDTO;
import dev.vorstu.entities.StudentEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-30T12:25:20+0300",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentEntity toStudentEntity(StudentDTO studentDTO) {
        if ( studentDTO == null ) {
            return null;
        }

        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setId( studentDTO.getId() );
        studentEntity.setName( studentDTO.getName() );
        studentEntity.setSurname( studentDTO.getSurname() );
        studentEntity.setGroup( studentDTO.getGroup() );

        return studentEntity;
    }

    @Override
    public StudentDTO toStudentDTO(StudentEntity studentEntity) {
        if ( studentEntity == null ) {
            return null;
        }

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId( studentEntity.getId() );
        studentDTO.setName( studentEntity.getName() );
        studentDTO.setSurname( studentEntity.getSurname() );
        studentDTO.setGroup( studentEntity.getGroup() );

        return studentDTO;
    }

    @Override
    public StudentEntity toStudentEntityExceptId(StudentEntity studentEntity, StudentDTO studentDTO) {
        if ( studentDTO == null ) {
            return studentEntity;
        }

        studentEntity.setName( studentDTO.getName() );
        studentEntity.setSurname( studentDTO.getSurname() );
        studentEntity.setGroup( studentDTO.getGroup() );

        return studentEntity;
    }
}
