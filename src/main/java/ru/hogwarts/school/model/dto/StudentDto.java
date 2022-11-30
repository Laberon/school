package ru.hogwarts.school.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto implements Serializable {
    private Long id;
    @NotBlank(message = "Name can't be blank")
    private String name;
    private int age;
    private FacultyDto faculty;
    private AvatarDto avatar;
}
