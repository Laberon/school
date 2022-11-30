package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.dto.FacultyDto;
import ru.hogwarts.school.model.dto.StudentDto;
import ru.hogwarts.school.service.FacultyService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@RestController
@RequestMapping("/faculty")
@RequiredArgsConstructor
@Validated
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping(path = "{id}")
    public ResponseEntity<FacultyDto> getFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.findFaculty(id));
    }

    @PostMapping
    public ResponseEntity<FacultyDto> createFaculty(@RequestBody FacultyDto faculty) {
        return ResponseEntity.ok(facultyService.creatFaculty(faculty));
    }

    @PutMapping
    public ResponseEntity<FacultyDto> editFaculty(@PathVariable long id,
                                                  @RequestBody @Valid FacultyDto faculty) {
        return ResponseEntity.ok(facultyService.updateFaculty(id, faculty));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }

    @GetMapping(params = "color")
    public ResponseEntity<Collection<FacultyDto>> getFacultiesByColor(@RequestParam @NotBlank String color) {
        return ResponseEntity.ok(facultyService.findFacultiesByColor(color));
    }

    @GetMapping(params = "name")
    public ResponseEntity<Collection<FacultyDto>> findFacultiesByName(@RequestParam @NotBlank String name) {
        return ResponseEntity.ok(facultyService.findFacultiesByName(name));
    }

    @GetMapping(params = "facultyName")
    public ResponseEntity<Collection<StudentDto>> findFacultyStudents(@RequestParam @NotBlank String facultyName) {
        return ResponseEntity.ok(facultyService.findFacultyStudents(facultyName));
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.findAllFaculty());
    }

    @GetMapping("/parallel/longest-name")
    public ResponseEntity<String> findLongestName() {
        return ResponseEntity.ok(facultyService.findLongestName());
    }
}
