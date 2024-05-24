package T3H.QuanLySinhVien.Controller;

import T3H.QuanLySinhVien.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DashboardController
{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DashboardService dashboardService;

    @GetMapping("/admin/dashboard")
    public String getDashboardForAdmin(Model model)
    {
        model.addAttribute("userName",dashboardService.getUserName());
        model.addAttribute("levelName",dashboardService.getLevelName());
        model.addAttribute("countDepartment",dashboardService.countDeparment() );
        model.addAttribute("countMajor",dashboardService.countMajor() );
        model.addAttribute("countTeacher",dashboardService.countTeacher() );
        model.addAttribute("countStudent",dashboardService.countStudent() );
            model.addAttribute("countSubject",dashboardService.countSubject() );
        return "Dashboard/index";
    }
    @GetMapping("/student/dashboard")
    public String getDashboardForStudent(Model model)
    {
        model.addAttribute("userName",dashboardService.getUserName());
        model.addAttribute("levelName",dashboardService.getLevelName());
        model.addAttribute("countRegistedCourse",dashboardService.countRegistedCourse());
        model.addAttribute("gpa",dashboardService.gpa());
        return "Dashboard/indexforstudent";
    }



}
