package T3H.QuanLySinhVien.Repository;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.SubjectConverter;
import T3H.QuanLySinhVien.Entities.dao.SubjectDao;
import T3H.QuanLySinhVien.Entities.dto.SubjectDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository

public interface SubjectRepository {
    List<SubjectConverter> getAllSubjectForView();
    List<SubjectDto> getAllSubject();
    int addSubject(SubjectDto subjectDto);
    int  updateSubject(SubjectDto subjectDto);
    SubjectDto getSubjectById(@PathVariable int id);
    void deleteSubjectById(@RequestParam int id);
    List<SubjectConverter> searchBySubjectname(String searchString);

}
