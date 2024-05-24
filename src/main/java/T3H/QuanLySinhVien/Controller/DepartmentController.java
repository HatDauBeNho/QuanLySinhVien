package T3H.QuanLySinhVien.Controller;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.TeacherConverter;
import T3H.QuanLySinhVien.Entities.dto.DepartmentDto;
import T3H.QuanLySinhVien.Service.DashboardService;
import T3H.QuanLySinhVien.Service.DepartmentService;
import T3H.QuanLySinhVien.Service.TeacherService;
import org.apache.logging.log4j.message.Message;
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
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    DashboardService dashboardService;

    @GetMapping("/admin/department")
    public String getAllDepartment(Model model)
    {
        model.addAttribute("departmentList",departmentService.getAllDepartmentForView());
        model.addAttribute("userName",dashboardService.getUserName());
        model.addAttribute("levelName",dashboardService.getLevelName());
        model.addAttribute("teacherList",teacherService.getAllTeachForView());
        return "ManagerDepartment/index";
    }
    @GetMapping("/admin/searchDepartment")
    public String searchByDepartmentname(@RequestParam(name = "searchString", required = false) String searchString, Model model)
    {
        List<DepartmentConverter> newDepartmentList = departmentService.searchByDepartmentname(searchString);
        model.addAttribute("departmentList", new ArrayList<>());
        model.addAttribute("departmentList", newDepartmentList);
        return "ManagerDepartment/index";
    }
    @PostMapping("/admin/addDepartment")
    public String addDepartment(@ModelAttribute("department") DepartmentDto departmentDto)
    {
        departmentService.addDepartment(departmentDto);
        return "redirect:/admin/department";
    }

    @PostMapping("/admin/deleteDepartment")
    public String deleteDepartment(@RequestParam int id)
    {
        departmentService.deleteDepartment(id);
        return "redirect:/admin/department";
    }
    @GetMapping("/admin/updateDepartmentForm/{id}")
    public String getUpdateDepartment(@PathVariable Integer id, Model model)
    {

        model.addAttribute("department",departmentService.getDepartmentById(id));
        model.addAttribute("teacherList",teacherService.getAllTeachForView());
        return "ManagerDepartment/update";
    }
    @PostMapping("/admin/updateDepartment")
    public String updateClassroom(@ModelAttribute("department") DepartmentDto departmentDto)
    {
        departmentService.updateDepartment(departmentDto);
        return "redirect:/admin/department";
    }

//    @PutMapping("/deparment")
//    public ResponseEntity<Integer> updateDepartment(@RequestBody DepartmentDto departmentDto)
//    {
//        String sql="UPDATE departments" +
//                " SET department_name=:deparment_name,teacher_id=:teacher_id,updated_at=:updated_at" +
//                " WHERE department_id=:department_id";
//        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
//                .addValue("department_id",departmentDto.getDepartment_id())
//                .addValue("deparment_name",departmentDto.getDepartment_name())
//                .addValue("teacher_id",)
//    }

}
