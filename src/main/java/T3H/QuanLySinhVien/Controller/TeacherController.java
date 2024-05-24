package T3H.QuanLySinhVien.Controller;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.LevelConverter;
import T3H.QuanLySinhVien.Converter.TeacherConverter;
import T3H.QuanLySinhVien.Entities.dto.*;
import T3H.QuanLySinhVien.Service.DashboardService;
import T3H.QuanLySinhVien.Service.LevelService;
import T3H.QuanLySinhVien.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    LevelService levelService;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    DashboardService dashboardService;
    @GetMapping("/admin/teacher")
    public String getAllTeacher(Model model)
    {
        model.addAttribute("userName",dashboardService.getUserName());
        model.addAttribute("levelName",dashboardService.getLevelName());
        model.addAttribute("levelList",levelService.getAllLevel());
        model.addAttribute("teacherList",teacherService.getAllTeachForView());
        return "ManagerTeacher/index";
    }
    @GetMapping("/admin/searchTeacher")
    public String searchByTeachername(@RequestParam(name = "searchString", required = false) String searchString, Model model)
    {
        List<TeacherConverter> newTeacherList = teacherService.searchByTeachername(searchString);
        model.addAttribute("teacherList", new ArrayList<>());
        model.addAttribute("teacherList", newTeacherList);
        return "ManagerTeacher/index";
    }
    @PostMapping("/admin/addTeacher")
    public String addTeacher(@ModelAttribute("teacher") TeacherDto teacherDto,
                             @ModelAttribute("account") AccountDto accountDto,
                             @ModelAttribute("infor") InforDto inforDto)
    {
        teacherService.addTeacher(teacherDto,accountDto,inforDto);
        return "redirect:/teacher"; // Chuyển hướng về trang danh sách lớp học sau khi thêm mới
    }
    @PostMapping("/admin/deleteTeacher")
    public String deleteTeacher(@RequestParam int id)
    {
        teacherService.deleteTeacher(id);
        return "redirect:/teacher";
    }
    @GetMapping("/admin/updateTeacherForm/{id}")
    public String getUpdateTeacher(@PathVariable Integer id, Model model)
    {
        model.addAttribute("teacher",teacherService.getTeacherById(id));
        return "ManagerTeacher/update";
    }

    @PostMapping("/admin/updateTeacher")
    public String updateStudent(@ModelAttribute("teacher") TeacherDto teacherDto,
                                @ModelAttribute("infor") InforDto inforDto)
    {
        teacherService.updateTeacher(teacherDto,inforDto);
        return "redirect:/teacher";
    }
}
