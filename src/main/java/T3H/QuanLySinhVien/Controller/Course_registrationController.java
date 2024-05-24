package T3H.QuanLySinhVien.Controller;


import T3H.QuanLySinhVien.Service.Course_registrationService;
import T3H.QuanLySinhVien.Service.DashboardService;
import T3H.QuanLySinhVien.Service.Module_subjectService;
import T3H.QuanLySinhVien.Service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class Course_registrationController {

    @Autowired
    Course_registrationService course_registrationService;
    @Autowired
    Module_subjectService module_subjectService;

    @Autowired
    DashboardService dashboardService;
    @Autowired
    ResultService resultService;

    @GetMapping("/student/course_registration")
    public String getAllCourse_registration(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();
        model.addAttribute("userName",loggedInUser);
        model.addAttribute("levelName",dashboardService.getLevelName());
        model.addAttribute("course_registrationList",course_registrationService.getAllCourse_registrationForView());
        return "Registered_course/index";
    }
    @GetMapping("/student/register_course")
    public String getAllModule_subject(Model model) {
        model.addAttribute("module_subjectList",module_subjectService.getAllModule_subjectForView());
        return "Register_course/index";
    }
    @PostMapping("/registerCourse")
    public String registerCourse(@RequestParam Integer id,Model model)
    {
        ArrayList<Integer> list=new ArrayList<Integer>();
        list.add(id);
        if (course_registrationService.checkRegistedCourse(id)) course_registrationService.registerCourse(list.get(list.size()-1));
        model.addAttribute("checkRegistedCourse",course_registrationService.checkRegistedCourse(id));
        resultService.addResult();
        return "redirect:/student/register_course";
    }


//    @PostMapping("/course_registration")
//    public ResponseEntity<List<Course_registration>> addCourse_registration(@RequestBody Course_registration course_registration) {
//        List<Course_registration> result = course_registrationService.addCourse_registration(course_registration);
//        return ResponseEntity.status(HttpStatus.CREATED).body(result);
//    }
//
//    @DeleteMapping("/course_registration/{id}")
//    public ResponseEntity<List<Course_registration>> deleteCourse_registration(@PathVariable int id) {
//        List<Course_registration> result = course_registrationService.deleteCourse_registration(id);
//        return ResponseEntity.ok(result);
//    }
}