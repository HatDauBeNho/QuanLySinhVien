package T3H.QuanLySinhVien.Controller;

import T3H.QuanLySinhVien.Converter.ClassroomConverter;
import T3H.QuanLySinhVien.Entities.dto.ClassroomDto;
import T3H.QuanLySinhVien.Service.ClassroomService;
import T3H.QuanLySinhVien.Service.DashboardService;
import T3H.QuanLySinhVien.Service.MajorService;
import T3H.QuanLySinhVien.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassroomController {
    @Autowired
    ClassroomService classroomService;

    @Autowired
    MajorService majorService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    DashboardService dashboardService;


    @GetMapping("/admin/classroom")
    public String getAllClassroom(Model model) {
        model.addAttribute("userName",dashboardService.getUserName());
        model.addAttribute("levelName",dashboardService.getLevelName());
        model.addAttribute("classroomList", classroomService.getAllClassroomForView());
        model.addAttribute("majorList", majorService.getAllMajor());
        model.addAttribute("teacherList",teacherService.getAllTeachForView());
        return "ManagerClass/index";
    }
    @GetMapping("/admin/searchClassroom")
    public String searchByClassroomname(@RequestParam(name = "searchString", required = false) String searchString, Model model)
    {
        List<ClassroomConverter> newClassroomList = classroomService.searchByClassroomname(searchString);
        model.addAttribute("classroomList", new ArrayList<>());
        model.addAttribute("classroomList", newClassroomList);
        return "ManagerClass/index";
    }
    @PostMapping("/admin/addClassroom")
    public String addClassroom(@ModelAttribute("classroom") ClassroomDto classroomDto) {
        classroomService.addClassroom(classroomDto);
        return "redirect:/admin/classroom"; // Chuyển hướng về trang danh sách lớp học sau khi thêm mới
    }

    @PostMapping("/admin/deleteClassroom")
    public String deleteClassroom(@RequestParam int id) {
        classroomService.deleteClassroomById(id);
        return "redirect:/admin/classroom";
    }

    @GetMapping("/admin/updateClassroomForm/{id}")
    public String getUpdateClassroom(@PathVariable Integer id, Model model) {
        model.addAttribute("classroom", classroomService.getClassroomById(id));
        model.addAttribute("majorList", majorService.getAllMajor());
        model.addAttribute("teacherList", teacherService.getAllTeachForView());
        return "ManagerClass/update";
    }
    @PostMapping("/admin/updateClassroom")
    public String updateClassroom(@ModelAttribute("classroom") ClassroomDto classroomDto) {
        classroomService.updateClassroom(classroomDto);
        return "redirect:/admin/classroom";
    }
//    @PostMapping("/classroom")
//    public ResponseEntity<Integer> addClassroom(@RequestBody ClassroomDto classroomDto)
//    {
//        String sql= "INSERT INTO class_rooms (class_name, quantity, major_id, teacher_id, created_at, updated_at) " +
//                "VALUES (:class_name,:quantity,:major_id,:teacher_id,:created_at,:updated_at)";
//        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
//                .addValue("class_name",classroomDto.getClass_name())
//                .addValue("quantity",classroomDto.getQuantity())
//                .addValue("major_id",classroomDto.getMajor_id())
//                .addValue("teacher_id",classroomDto.getTeacher_id())
//                .addValue("created_at", LocalDateTime.now())
//                .addValue("updated_at",LocalDateTime.now());
//        int result = namedParameterJdbcTemplate.update(sql,nameParameters);
//        return ResponseEntity.status(HttpStatus.CREATED).body(result);
//    }
//    @PutMapping("/updateClassroom")
//    public ResponseEntity<Integer> updateClassroom(@RequestBody ClassroomDto classroomDto)
//    {
//        String sql="UPDATE class_rooms " +
//                "SET class_name=:class_name,quantity=:quantity,major_id=:major_id,teacher_id=:teacher_id,updated_at=:updated_at " +
//                "WHERE class_id=:class_id";
//        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
//                .addValue("class_id",classroomDto.getClass_id())
//                .addValue("class_name",classroomDto.getClass_name())
//                .addValue("quantity",classroomDto.getQuantity())
//                .addValue("major_id",classroomDto.getMajor_id())
//                .addValue("teacher_id",classroomDto.getTeacher_id())
//                .addValue("updated_at",LocalDateTime.now());
//        int result = namedParameterJdbcTemplate.update(sql,nameParameters);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
//    }
//    @DeleteMapping("/classroom/{id}")
//    public ResponseEntity<Integer> deleteClassroom(@PathVariable int id)
//    {
//        String sql="DELETE from class_rooms " +
//                "WHERE class_id=:class_id";
//        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
//                .addValue("class_id",id);
//        int result = namedParameterJdbcTemplate.update(sql,nameParameters);
//        return ResponseEntity.ok(result);
//    }


}
