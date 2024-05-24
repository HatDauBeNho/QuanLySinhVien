package T3H.QuanLySinhVien.Repository.impl;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.MajorConverter;
import T3H.QuanLySinhVien.Entities.dto.MajorDto;
import T3H.QuanLySinhVien.Repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MajorRepositoryImpl implements MajorRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<MajorConverter> getAllMajorForView() {
        String sql="SELECT m.major_id,m.major_name,d.department_name,i.fullname,m.created_at,m.updated_at" +
                " FROM majors m" +
                " LEFT OUTER JOIN departments d ON d.department_id = m.department_id" +
                " LEFT OUTER JOIN teachers t ON m.teacher_id = t.teacher_id" +
                " LEFT OUTER JOIN infors i on t.infor_id = i.infor_id";
        return namedParameterJdbcTemplate.query(sql,new BeanPropertyRowMapper<>(MajorConverter.class));
    }

    @Override
    public List<MajorDto> getAllMajor() {
        String sql="select * from majors";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MajorDto.class));
    }

    @Override
    public int addMajor(MajorDto majorDto) {
        String sql= "INSERT INTO majors (major_name,department_id, teacher_id, created_at, updated_at) " +
                "VALUES (:major_name,:department_id,:teacher_id,:created_at,:updated_at)";
        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
                .addValue("major_name",majorDto.getMajor_name())
                .addValue("department_id",majorDto.getMajor_id())
                .addValue("teacher_id",majorDto.getTeacher_id())
                .addValue("created_at", LocalDateTime.now())
                .addValue("updated_at",LocalDateTime.now());

        return namedParameterJdbcTemplate.update(sql,nameParameters);
    }

    @Override
    public int updateMajor(MajorDto majorDto) {
        String sql="UPDATE majors " +
                "SET major_name=:major_name,department_id=:department_id,teacher_id=:teacher_id,updated_at=:updated_at " +
                "WHERE major_id=:major_id";
        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
                .addValue("major_id",majorDto.getMajor_id())
                .addValue("department_id",majorDto.getDepartment_id())
                .addValue("major_name",majorDto.getMajor_name())
                .addValue("teacher_id",majorDto.getTeacher_id())
                .addValue("updated_at",LocalDateTime.now());
        return namedParameterJdbcTemplate.update(sql,nameParameters);
    }

    @Override
    public MajorDto getMajorById(int id) {
        String sql="select * from majors where major_id=:id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        List<MajorDto> li= namedParameterJdbcTemplate.query(sql,parameters, new BeanPropertyRowMapper<>(MajorDto.class));
        return li.get(0);
    }

    @Override
    public void deleteMajorById(int id) {
        String sql = "DELETE FROM majors WHERE major_id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(sql,parameters);
    }

    @Override
    public List<MajorConverter> searchByMajorname(String searchString) {

        String sql="SELECT m.major_id,m.major_name,d.department_name,i.fullname,m.created_at,m.updated_at" +
                " FROM majors m" +
                " LEFT OUTER JOIN departments d ON d.department_id = m.department_id" +
                " LEFT OUTER JOIN teachers t ON m.teacher_id = t.teacher_id" +
                " LEFT OUTER JOIN infors i on t.infor_id = i.infor_id" +
                " WHERE m.major_name=:searchString";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("searchString", searchString);
        List<MajorConverter> list =  namedParameterJdbcTemplate.query(sql,parameters,new BeanPropertyRowMapper<>(MajorConverter.class));
        return list;
    }
}
