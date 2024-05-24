package T3H.QuanLySinhVien.Service;
import T3H.QuanLySinhVien.Converter.ResultConverter;
import T3H.QuanLySinhVien.Repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {
    @Autowired
    ResultRepository resultRepository;

    public List<ResultConverter> getResultForTeacher(@PathVariable  int module_subject_id)
    {
       return resultRepository.getResultForTeacher(module_subject_id);
    }
    public void addResult() {
        resultRepository.addResult();
    }
    public void updateResult(@RequestParam int course_registration_id, @RequestParam double point)
    {
        resultRepository.updateResult(course_registration_id,point);
    }
}
