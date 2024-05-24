package T3H.QuanLySinhVien.Repository;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.StudentConverter;
import T3H.QuanLySinhVien.Entities.dao.StudentDao;
import T3H.QuanLySinhVien.Entities.dto.AccountDto;
import T3H.QuanLySinhVien.Entities.dto.InforDto;
import T3H.QuanLySinhVien.Entities.dto.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository

public interface StudentRepository  {
    List<StudentConverter> getAllStudentForView();
    List<StudentDto> getAllStudent();
    int addStudent(StudentDto studentDto, AccountDto accountDto, InforDto inforDto);
    int  updateStudent(StudentDto studentDto, InforDto inforDto);
    StudentConverter getStudentById(@PathVariable int id);
    void deleteStudentById(@RequestParam int id);
    List<StudentConverter> searchByStudentname(String searchString);

}
