package org.bajiepka.courseApp.controllers.HTTP;

import org.bajiepka.courseApp.domain.Student;
import org.bajiepka.courseApp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/student-community")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping(value = "/students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", service.getStudents());
        return "students";
    }

    @GetMapping(value = "/student")
    public String findStudentById(@RequestParam Long id, Model model) {

        Student student = service.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("progress", student.getProgressList());

        return "student";
    }

    @GetMapping(value = "/dismiss")
    public String dismissStudent(@RequestParam Long id) {
        service.dismissStudent(id);
        return "redirect:/student-community/students";
    }

    @GetMapping(value = "/preview-student")
    public String previewAsJSON(@RequestParam Long id, Model model) {

        Student student = service.getStudentById(id);
        String studentInJson = service.viewStudentAsJSON(id);

        model.addAttribute("student", student);
        model.addAttribute("json", studentInJson);

        return "preview-student";
    }

    @GetMapping(value = "/preview-all-students")
    public String previewAllAsJSON(@RequestParam boolean toFile, Model model) {
        model.addAttribute("toFile", toFile);
        model.addAttribute("json", service.viewAllStudentAsJSON(toFile));
        return "preview-student";
    }

    @GetMapping(value = "/register-student")
    public String signUpForUniversity(Model model) {
        model.addAttribute("student", new Student());
        return "register-student";
    }

    @GetMapping(value = "/edit-student")
    public String editStudent(@RequestParam Long id, Model model) {
        model.addAttribute("student", service.getStudentById(id));
        return "edit-student";
    }

    @PostMapping(value = "/edit-student")
    public String saveEditedStudent(@Valid Student student, BindingResult result, Model model) {
        service.updateStudent(student);
        return "redirect:/student-community/students";
    }

    @PostMapping(value = "/register-student")
    public String goToUniversity(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-student";
        }
        boolean created = service.saveStudent(student);
        return "redirect:/student-community/students";
    }

}
