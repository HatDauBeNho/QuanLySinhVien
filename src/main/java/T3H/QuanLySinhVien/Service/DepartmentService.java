package T3H.QuanLySinhVien.Service;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Entities.dao.DepartmentDao;
import T3H.QuanLySinhVien.Entities.dto.ClassroomDto;
import T3H.QuanLySinhVien.Entities.dto.DepartmentDto;
import T3H.QuanLySinhVien.Mapper.ClassroomMapper;
import T3H.QuanLySinhVien.Mapper.DepartmentMapper;
import T3H.QuanLySinhVien.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<DepartmentConverter> getAllDepartmentForView()
    {
        return departmentRepository.getAllDepartmentForView();
    }
    public List<DepartmentDto> getAllDepartment()
    {
        return departmentRepository.getAllDepartment();
    }
    public DepartmentDto getDepartmentById(@PathVariable int id)
    {
        return departmentRepository.getDepartmentById(id);
    }
    public int addDepartment(DepartmentDto departmentDto)
    {
        return departmentRepository.addDepartment(departmentDto);
    }
    public int updateDepartment(DepartmentDto departmentDto)
    {
        return departmentRepository.updateDepartment(departmentDto);
    }
    public void deleteDepartment(@RequestParam int id)
    {
        departmentRepository.deleteDepartmentById(id);
    }
    public List<DepartmentConverter> searchByDepartmentname(@PathVariable String searchString)
    {

        return departmentRepository.searchByDepartmentname(searchString);
    }
}
