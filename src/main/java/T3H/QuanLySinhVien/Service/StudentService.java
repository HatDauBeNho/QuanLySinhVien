package T3H.QuanLySinhVien.Service;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.StudentConverter;
import T3H.QuanLySinhVien.Entities.dao.StudentDao;
import T3H.QuanLySinhVien.Entities.dto.AccountDto;
import T3H.QuanLySinhVien.Entities.dto.InforDto;
import T3H.QuanLySinhVien.Entities.dto.StudentDto;
import T3H.QuanLySinhVien.Mapper.StudentMapper;
import T3H.QuanLySinhVien.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<StudentConverter> getAllStudentForView()
    {
        return studentRepository.getAllStudentForView();
    }
    public List<StudentDto> getAllStudent()
    {
       return studentRepository.getAllStudent();
    }
    public int  addStudent(StudentDto studentDto, AccountDto accountDto, InforDto inforDto)
    {
        return studentRepository.addStudent(studentDto,accountDto,inforDto);
    }
    public void deleteStudent(@RequestParam int id)
    {
        studentRepository.deleteStudentById(id);
    }
    public StudentConverter getStudentById(@RequestParam int id)
    {
        return studentRepository.getStudentById(id);
    }
    public int updateStudent(StudentDto studentDto,InforDto inforDto)
    {
       return studentRepository.updateStudent(studentDto,inforDto);
    }
    public List<StudentConverter> searchByStudentname(@PathVariable String searchString)
    {

        return studentRepository.searchByStudentname(searchString);
    }
}
