package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;

@Service
public class StudentService {

    private final HashMap<Long, Student> studentHashMap = new HashMap<>();
    private long settingIdStudentService = 0;

    public Student createStudent(Student student) {
        student.setId(++settingIdStudentService);
        studentHashMap.put(settingIdStudentService, student);
        return student;
    }

    public Student findStudent(long id) {
        return studentHashMap.get(id);
    }

    public Student editStudent(Student student) {
        if (studentHashMap.containsKey(student.getId())) {
            studentHashMap.put(student.getId(), student);
        }
        return null;
    }

    public Student deleteStudent(long id) {
        return studentHashMap.remove(id);
    }

    public Collection<Student> getAllStudent() {
        return studentHashMap.values();
    }
}
