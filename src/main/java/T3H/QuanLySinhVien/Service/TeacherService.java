package T3H.QuanLySinhVien.Service;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.TeacherConverter;
import T3H.QuanLySinhVien.Entities.dao.TeacherDao;
import T3H.QuanLySinhVien.Entities.dto.AccountDto;
import T3H.QuanLySinhVien.Entities.dto.InforDto;
import T3H.QuanLySinhVien.Entities.dto.TeacherDto;
import T3H.QuanLySinhVien.Mapper.TeacherMapper;
import T3H.QuanLySinhVien.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<TeacherConverter> getAllTeachForView()
    {
        return teacherRepository.getAllTeacherForView();
    }
    public List<TeacherDto> getAllTeacher()
    {
        return teacherRepository.getAllTeacher();

    }
    public int addTeacher(TeacherDto teacherDto, AccountDto accountDto, InforDto inforDto)
    {
        return teacherRepository.addTeacher(teacherDto,accountDto,inforDto);
    }
    public void deleteTeacher(@RequestParam int id)
    {
        teacherRepository.deleteTeacherById(id);
    }
    public TeacherConverter getTeacherById(@RequestParam int id)
    {
        return  teacherRepository.getTeacherById(id);
    }
    public int updateTeacher(TeacherDto teacherDto,InforDto inforDto)
    {
        return  teacherRepository.updateTeacher(teacherDto,inforDto);
    }
    public List<TeacherConverter> searchByTeachername(@PathVariable String searchString)
    {

        return teacherRepository.searchByTeachername(searchString);
    }
}
