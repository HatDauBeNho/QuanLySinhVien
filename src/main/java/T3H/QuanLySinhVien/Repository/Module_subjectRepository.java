package T3H.QuanLySinhVien.Repository;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.Module_subjectConverter;
import T3H.QuanLySinhVien.Entities.dao.Module_subjectDao;
import T3H.QuanLySinhVien.Entities.dto.Module_subjectDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository

public interface Module_subjectRepository  {
    List<Module_subjectConverter> getAllModule_subjectForView();
    List<Module_subjectDto> getAllModule_subject();
    int addModule_subject(Module_subjectDto module_subjectDto);
    int  updateModule_subject(Module_subjectDto module_subjectDto);
    Module_subjectDto getModule_subjectById(@PathVariable int id);
    void deleteModule_subjectById(@RequestParam int id);
    List<Module_subjectConverter> searchByModule_subjectname(String searchString);
    List<Module_subjectConverter> getModulSubjectByTeacher();

}
