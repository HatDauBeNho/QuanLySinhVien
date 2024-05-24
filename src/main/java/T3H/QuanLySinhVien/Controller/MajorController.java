package T3H.QuanLySinhVien.Controller;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.MajorConverter;
import T3H.QuanLySinhVien.Converter.TeacherConverter;
import T3H.QuanLySinhVien.Entities.dto.MajorDto;
import T3H.QuanLySinhVien.Service.DashboardService;
import T3H.QuanLySinhVien.Service.DepartmentService;
import T3H.QuanLySinhVien.Service.MajorService;
import T3H.QuanLySinhVien.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MajorController {
    @Autowired
    MajorService majorService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    DashboardService dashboardService;
    @GetMapping("/admin/major")
    public String getAllMajor(Model model)
    {
        model.addAttribute("majorList",majorService.getAllMajorForView());
        model.addAttribute("userName",dashboardService.getUserName());
        model.addAttribute("levelName",dashboardService.getLevelName());
        model.addAttribute("teacherList",teacherService.getAllTeachForView());
        model.addAttribute("departmentList",departmentService.getAllDepartment());
        return "ManagerMajor/index";
    }
    @GetMapping("/admin/searchMajor")
    public String searchByMajorname(@RequestParam(name = "searchString", required = false) String searchString, Model model)
    {
        List<MajorConverter> newMajorList = majorService.searchByMajorname(searchString);
        model.addAttribute("majorList", new ArrayList<>());
        model.addAttribute("majorList", newMajorList);
        return "ManagerMajor/index";
    }
    @PostMapping("/admin/addMajor")
    public String addMajor(@ModelAttribute("major") MajorDto majorDto) {

        majorService.addMajor(majorDto);
        return "redirect:/major"; // Chuyển hướng về trang danh sách lớp học sau khi thêm mới
    }

    @PostMapping("/admin/deleteMajor")
    public String deleteMajor(@RequestParam int id)
    {
        majorService.deleteMajor(id);
        return "redirect:/major";
    }

    @GetMapping("/admin/updateMajorForm/{id}")
    public String getUpdateMajor(@PathVariable Integer id, Model model)
    {
        model.addAttribute("major", majorService.getMajorById(id));
        model.addAttribute("departmentList",departmentService.getAllDepartment());
        model.addAttribute("teacherList",teacherService.getAllTeachForView());
        return "ManagerMajor/update";
    }
    @PostMapping("/admin/updateMajor")
    public String updateMajor(@ModelAttribute("major") MajorDto majorDto)
    {
        majorService.updateMajor(majorDto);
        return "redirect:/major";
    }
}
