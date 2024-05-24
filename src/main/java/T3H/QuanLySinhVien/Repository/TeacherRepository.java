package T3H.QuanLySinhVien.Repository;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.TeacherConverter;
import T3H.QuanLySinhVien.Entities.dao.TeacherDao;
import T3H.QuanLySinhVien.Entities.dto.AccountDto;
import T3H.QuanLySinhVien.Entities.dto.InforDto;
import T3H.QuanLySinhVien.Entities.dto.TeacherDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository

public interface TeacherRepository
{
    List<TeacherConverter> getAllTeacherForView();
    List<TeacherDto> getAllTeacher();
    int addTeacher(TeacherDto teacherDto, AccountDto accountDto, InforDto inforDto);
    int  updateTeacher(TeacherDto teacherDto, InforDto inforDto);
    TeacherConverter getTeacherById(@PathVariable int id);
    void deleteTeacherById(@RequestParam int id);
    List<TeacherConverter> searchByTeachername(String searchString);

}
