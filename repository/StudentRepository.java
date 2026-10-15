package repository;

import model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class StudentRepository {
    private final Map<Integer, Student> students = new LinkedHashMap<>();

    public Student save(Student student) {
        students.put(student.getStudentId(), student);
        return student;
    }

    public Optional<Student> findById(int studentId) {
        return Optional.ofNullable(students.get(studentId));
    }

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    public boolean deleteById(int studentId) {
        return students.remove(studentId) != null;
    }
}
