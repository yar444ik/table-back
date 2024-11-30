package dev.vorstu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@ToString
public class StudentDTO {

    private Long id;

    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("group")
    private String group;
}
