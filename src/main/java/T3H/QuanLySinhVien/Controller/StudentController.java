package T3H.QuanLySinhVien.Controller;


import T3H.QuanLySinhVien.Converter.StudentConverter;
import T3H.QuanLySinhVien.Entities.dto.*;
import T3H.QuanLySinhVien.Service.ClassroomService;
import T3H.QuanLySinhVien.Service.DashboardService;
import T3H.QuanLySinhVien.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    ClassroomService classroomService;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    DashboardService dashboardService;
    @GetMapping("/admin/student")
    public String getAllStudent(Model model)
    {
        model.addAttribute("userName",dashboardService.getUserName());
        model.addAttribute("levelName",dashboardService.getLevelName());
        model.addAttribute("classroomList",classroomService.getAllClassroom());
        model.addAttribute("studentList",studentService.getAllStudentForView());
        return "ManagerStudent/index";
    }
    @GetMapping("/admin/searchStudent")
    public String searchByStudentname(@RequestParam(name = "searchString", required = false) String searchString, Model model)
    {
        List<StudentConverter> newStudentList = studentService.searchByStudentname(searchString);
        model.addAttribute("studentList", new ArrayList<>());
        model.addAttribute("studentList", newStudentList);
        return "ManagerStudent/index";
    }
    @PostMapping("/admin/addStudent")
    public String addStudent(@ModelAttribute("student") StudentDto studentDto,
                             @ModelAttribute("account") AccountDto accountDto,
                             @ModelAttribute("infor") InforDto inforDto)
    {
        studentService.addStudent(studentDto,accountDto,inforDto);
        return "redirect:/student"; // Chuyển hướng về trang danh sách lớp học sau khi thêm mới
    }
    @PostMapping("/admin/deleteStudent")
    public String deleteStudent(@RequestParam int id)
    {
        studentService.deleteStudent(id);
        return "redirect:/student";
    }

    @GetMapping("/admin/updateStudentForm/{id}")
    public String getUpdateStudent(@PathVariable Integer id, Model model) {

        model.addAttribute("student", studentService.getStudentById(id));
        return "ManagerStudent/update";
    }
    @PostMapping("/admin/updateStudent")
    public String updateStudent(@ModelAttribute("student") StudentDto studentDto,
                                @ModelAttribute("infor") InforDto inforDto)
    {
        String sql="UPDATE infors i " +
                "inner join students s on s.infor_id=i.infor_id " +
                "SET i.fullname=:fullname,i.phone_number=:phone_number,i.email=:email," +
                "i.date_of_birth=:date_of_birth,i.address=:address,i.gender=:gender,i.updated_at=:updated_at" +
                " WHERE student_id=:student_id";
        MapSqlParameterSource nameParameters1=new MapSqlParameterSource()
                .addValue("student_id",studentDto.getStudent_id())
                .addValue("infor_id",inforDto.getInfor_id())
                .addValue("fullname",inforDto.getFullname())
                .addValue("phone_number",inforDto.getPhone_number())
                .addValue("email",inforDto.getEmail())
                .addValue("date_of_birth",inforDto.getDate_of_birth())
                .addValue("address",inforDto.getAddress())
                .addValue("gender",inforDto.getGender())
                .addValue("updated_at",LocalDateTime.now());
        namedParameterJdbcTemplate.update(sql,nameParameters1);
        return "redirect:/student";
    }
}
