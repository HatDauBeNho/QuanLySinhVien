package T3H.QuanLySinhVien.Repository;

import T3H.QuanLySinhVien.Converter.ResultConverter;
import T3H.QuanLySinhVien.Entities.dao.ResultDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository

public interface ResultRepository  {
    List<ResultConverter> getResultForTeacher(@PathVariable  int module_subject_id);
    void addResult();
    void updateResult(@RequestParam int course_registration_id, @RequestParam double point);
}
