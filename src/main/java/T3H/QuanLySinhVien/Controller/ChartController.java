package T3H.QuanLySinhVien.Controller;




import T3H.QuanLySinhVien.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ChartController {

    @Autowired
    private SubjectService subjectService;
    @GetMapping("/admin/showChart")
    public String GetChart(Model module){
        module.addAttribute("charts",subjectService.getAllSubject());
        return "Chart/chart";
    }

}
