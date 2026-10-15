package controller;

import model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("isNew", true);
        return "student-form";
    }

    @PostMapping
    public String createStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("notice", "Student added.");
        return "redirect:/students";
    }

    @GetMapping("/{studentId}")
    public String viewStudent(@PathVariable int studentId, Model model) {
        model.addAttribute("student", requireStudent(studentId));
        return "student-view";
    }

    @GetMapping("/{studentId}/edit")
    public String showEditForm(@PathVariable int studentId, Model model) {
        model.addAttribute("student", requireStudent(studentId));
        model.addAttribute("isNew", false);
        return "student-form";
    }

    @PostMapping("/{studentId}")
    public String updateStudent(
            @PathVariable int studentId,
            @ModelAttribute Student student,
            RedirectAttributes redirectAttributes) {
        student.setStudentId(studentId);
        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("notice", "Student updated.");
        return "redirect:/students/" + studentId;
    }

    @PostMapping("/{studentId}/delete")
    public String deleteStudent(@PathVariable int studentId, RedirectAttributes redirectAttributes) {
        studentService.deleteStudent(studentId);
        redirectAttributes.addFlashAttribute("notice", "Student deleted.");
        return "redirect:/students";
    }

    private Student requireStudent(int studentId) {
        return studentService.getStudentById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
