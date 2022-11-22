package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;

@Service
public class FacultyService {

    private final HashMap<Long, Faculty> facultyHashMap = new HashMap<>();
    private long settingIdFacultyService = 0;

    public Faculty creatFaculty(Faculty faculty) {
        faculty.setId(++settingIdFacultyService);
        facultyHashMap.put(settingIdFacultyService,faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return facultyHashMap.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (facultyHashMap.containsKey(faculty.getId())) {
            facultyHashMap.put(faculty.getId(), faculty);
        }
        return null;
    }

    public Faculty delFaculty(long id) {
        return facultyHashMap.remove(id);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyHashMap.values();
    }
}
