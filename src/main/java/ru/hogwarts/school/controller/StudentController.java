package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.dto.FacultyDto;
import ru.hogwarts.school.model.dto.NewStudentDto;
import ru.hogwarts.school.model.dto.StudentDto;
import ru.hogwarts.school.service.StudentService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Validated
public class StudentController {

    private final StudentService studentService;

    @GetMapping(path = "{id}")
    public ResponseEntity<StudentDto> findStudentInfo(@PathVariable long id) {
        return ResponseEntity.ok(studentService.findStudent(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid NewStudentDto student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> editStudent(@PathVariable long id,
                                                  @RequestBody @Valid NewStudentDto student) {
        return ResponseEntity.ok(studentService.editStudent(id, student));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping(params = "age")
    public ResponseEntity<Collection<StudentDto>> findStudentsByAge(@RequestParam int age) {
        return ResponseEntity.ok(studentService.findStudentsByAge(age));
    }

    @GetMapping
    public ResponseEntity<Collection<StudentDto>> findStudentsByAgeBetween(@RequestParam int from,
                                                                           @RequestParam int to) {
        return ResponseEntity.ok(studentService.findStudentByAgeBetween(from, to));
    }

    @GetMapping(params = "studentId")
    public ResponseEntity<FacultyDto> findFacultyByStudentId(@RequestParam Long studentId) {
        return ResponseEntity.ok(studentService.findFacultyByStudent(studentId));
    }

    @PatchMapping("/{id}/avatar")
    public ResponseEntity<StudentDto> patchStudentAvatar(@PathVariable long id,
                                                         @RequestParam("avatarId") long avatarId) {
        return ResponseEntity.ok(studentService.setStudentAvatar(id, avatarId));
    }

    @GetMapping(value = "/parallel", params = "letter")
    public ResponseEntity<Collection<String>> getAllStudentNamesStarting(@RequestParam("letter")
                                                                         @Pattern(regexp = "[a-zA-Z]")//message = "Enter only one starting letter"
                                                                         String letter) {
        return ResponseEntity.ok(studentService.getAllStudentNamesStarting(letter));
    }

    @GetMapping(value = "/parallel/avg-age")
    public ResponseEntity<Double> getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }
}
