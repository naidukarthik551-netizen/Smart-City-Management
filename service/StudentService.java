package service;

import model.Student;
import org.springframework.stereotype.Service;
import repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(int studentId) {
        return studentRepository.findById(studentId);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public boolean deleteStudent(int studentId) {
        return studentRepository.deleteById(studentId);
    }
}
