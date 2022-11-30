package ru.hogwarts.school.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hogwarts.school.exception.EntryNotFoundException;
import ru.hogwarts.school.mapper.Mapper;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.dto.FacultyDto;
import ru.hogwarts.school.model.dto.StudentDto;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final Mapper mapper;

    public FacultyDto creatFaculty(FacultyDto faculty) {
        faculty.setId(null);
        Faculty facultyToAdd = mapper.toEntity(faculty);
        return mapper.toDto(facultyRepository.save(facultyToAdd));
    }

    public void deleteFaculty(long id) {
        findFacultyById(id);
        facultyRepository.deleteById(id);
    }

    public FacultyDto updateFaculty(long id, FacultyDto faculty) {
        Faculty foundFaculty = findFacultyById(id);
        foundFaculty.setName(faculty.getName());
        foundFaculty.setColor(faculty.getColor());
        return mapper.toDto(facultyRepository.save(foundFaculty));
    }

    public Collection<FacultyDto> findFacultiesByColor(String color) {
        log.info("Get all faculties with color={}", color);
        return facultyRepository.
                findFacultiesByColor(color)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public Collection<FacultyDto> findFacultiesByName(String name) {
        log.info("Get all faculties with name={}", name);
        return facultyRepository
                .findFacultiesByName(name)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional
    public Collection<StudentDto> findFacultyStudents(String facultyName) {
        log.info("Get all students from facultyName={}", facultyName);
        return facultyRepository
                .findByFacultyName(facultyName)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
    public Collection<Faculty> findAllFaculty() {
        return facultyRepository.findAll();
    }
    public FacultyDto findFaculty(Long id) {
        log.info("Get faculty with id={}", id);
        return mapper.toDto(findFacultyById(id));
    }

    protected Faculty findFacultyById(Long id) {
        return facultyRepository
                .findById(id)
                .orElseThrow(() -> new EntryNotFoundException("Faculty with id=" + id + " doesn't exist",
                        "The specified faculty not found"));
    }

    public String findLongestName() {
        log.info("Get the longest faculty name");
        return facultyRepository.findLongestName();
    }
}
