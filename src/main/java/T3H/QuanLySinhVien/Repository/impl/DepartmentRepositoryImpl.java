package T3H.QuanLySinhVien.Repository.impl;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Entities.dto.DepartmentDto;
import T3H.QuanLySinhVien.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<DepartmentConverter> getAllDepartmentForView() {
        String sql="SELECT d.department_id, d.department_name, i.fullname, d.created_at, d.updated_at" +
                " FROM departments d" +
                " LEFT OUTER JOIN teachers t ON d.teacher_id = t.teacher_id" +
                " LEFT OUTER JOIN infors i on t.infor_id = i.infor_id";
        return namedParameterJdbcTemplate.query(sql,new BeanPropertyRowMapper<>(DepartmentConverter.class));
    }

    @Override
    public List<DepartmentDto> getAllDepartment() {
        String sql="select * from departments";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DepartmentDto.class));
    }

    @Override
    public int addDepartment(DepartmentDto departmentDto) {
        String sql="INSERT INTO departments ( department_name, teacher_id, created_at, updated_at)" +
                " VALUES (:department_name,:teacher_id,:created_at,:updated_at)";
        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
                .addValue("department_name",departmentDto.getDepartment_name())
                .addValue("teacher_id",departmentDto.getTeacher_id())
                .addValue("created_at", LocalDateTime.now())
                .addValue("updated_at",LocalDateTime.now());
        return namedParameterJdbcTemplate.update(sql,nameParameters);
    }

    @Override
    public int updateDepartment(DepartmentDto departmentDto) {
        String sql="UPDATE departments "+
                "SET department_name=:department_name,teacher_id=:teacher_id,updated_at=:updated_at " +
                "WHERE department_id=:department_id";
        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
                .addValue("department_id",departmentDto.getDepartment_id())
                .addValue("department_name",departmentDto.getDepartment_name())
                .addValue("teacher_id",departmentDto.getTeacher_id())
                .addValue("updated_at", LocalDateTime.now());
        return namedParameterJdbcTemplate.update(sql,nameParameters);
    }

    @Override
    public DepartmentDto getDepartmentById(int id) {
        String sql="select * from departments where department_id=:id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        List<DepartmentDto> li= namedParameterJdbcTemplate.query(sql,parameters, new BeanPropertyRowMapper<>(DepartmentDto.class));
        return li.get(0);
    }

    @Override
    public void deleteDepartmentById(int id) {
        String sql = "DELETE FROM departments WHERE department_id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(sql,parameters);
    }

    @Override
    public List<DepartmentConverter> searchByDepartmentname(String searchString) {
        String sql="SELECT d.department_id, d.department_name, i.fullname, d.created_at, d.updated_at" +
                " FROM departments d" +
                " LEFT OUTER JOIN teachers t ON d.teacher_id = t.teacher_id" +
                " LEFT OUTER JOIN infors i on t.infor_id = i.infor_id" +
                " where d.department_name = :searchString";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("searchString", searchString);
        List<DepartmentConverter> list =  namedParameterJdbcTemplate.query(sql,parameters,new BeanPropertyRowMapper<>(DepartmentConverter.class));
        return list;
    }
}
