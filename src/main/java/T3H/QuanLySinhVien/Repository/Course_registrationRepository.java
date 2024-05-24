package T3H.QuanLySinhVien.Repository;


import T3H.QuanLySinhVien.Converter.Course_registrationConverter;
import T3H.QuanLySinhVien.Entities.dto.Module_subjectDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Course_registrationRepository {
    List<Course_registrationConverter> getAllCourse_registrationForView();
    void registerCourse(int module_subject_id);
    boolean checkRegistedCourse(int module_subject_id);
}
