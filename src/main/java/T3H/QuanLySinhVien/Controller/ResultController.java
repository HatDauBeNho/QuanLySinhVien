package T3H.QuanLySinhVien.Controller;

import T3H.QuanLySinhVien.Service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultController {
    @Autowired
    ResultService resultService;
    @GetMapping("teacher/resultForm/{id}")
    public String getResultForTeacher(@PathVariable Integer id, Model model) {
        model.addAttribute("listResult", resultService.getResultForTeacher(id));
        return "ManagerModule_subject/updateresult";
    }
    @PostMapping("teacher/updateResult")
    public String updateResult(@RequestParam int course_registration_id, @RequestParam double point)
    {
        resultService.updateResult(course_registration_id,point);
        return "redirect:/teacher/module_subject";
    }
}
