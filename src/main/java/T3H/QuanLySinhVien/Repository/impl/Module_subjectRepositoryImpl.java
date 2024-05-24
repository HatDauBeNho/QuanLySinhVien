package T3H.QuanLySinhVien.Repository.impl;

import T3H.QuanLySinhVien.Converter.Module_subjectConverter;
import T3H.QuanLySinhVien.Entities.dto.Module_subjectDto;
import T3H.QuanLySinhVien.Repository.Module_subjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class Module_subjectRepositoryImpl implements Module_subjectRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<Module_subjectConverter> getAllModule_subjectForView() {
        String sql="SELECT m.module_subject_id, s.subject_name, m.current_student,m.maximum_student,i.fullname,m.start_at,m.end_at,m.created_at\n" +
                "FROM module_subjects m\n" +
                "LEFT OUTER JOIN subjects s ON s.subject_id=m.subject_id\n" +
                "LEFT OUTER JOIN teachers t on m.teacher_id=t.teacher_id\n" +
                "LEFT OUTER JOIN infors i on t.infor_id=i.infor_id\n";
        return namedParameterJdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Module_subjectConverter.class));
    }

    @Override
    public List<Module_subjectDto> getAllModule_subject() {
        String sql="select * from module_subjects";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Module_subjectDto.class));
    }

    @Override
    public int addModule_subject(Module_subjectDto module_subjectDto) {
        String sql= "INSERT INTO module_subjects ( subject_id, current_student, maximum_student, teacher_id, start_at, end_at, created_at, updated_at) " +
                "VALUES (:subject_id, :current_student, :maximum_student, :teacher_id, :start_at, :end_at, :created_at, :updated_at)";
        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
                .addValue("subject_id",module_subjectDto.getSubject_id())
                .addValue("current_student",module_subjectDto.getCurrent_student())
                .addValue("maximum_student",module_subjectDto.getMaximum_student())
                .addValue("teacher_id",module_subjectDto.getTeacher_id())
                .addValue("start_at",module_subjectDto.getStart_at())
                .addValue("end_at",module_subjectDto.getEnd_at())
                .addValue("created_at", LocalDateTime.now())
                .addValue("updated_at",LocalDateTime.now());
        return namedParameterJdbcTemplate.update(sql,nameParameters);
    }

    @Override
    public int updateModule_subject(Module_subjectDto module_subjectDto) {
        String sql="UPDATE module_subjects " +
                "SET subject_id=:subject_id,maximum_student=:maximum_student," +
                "teacher_id=:teacher_id,start_at=:start_at,end_at=:end_at,updated_at=:updated_at " +
                "WHERE module_subject_id=:module_subject_id";
        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
                .addValue("module_subject_id",module_subjectDto.getModule_subject_id())
                .addValue("subject_id",module_subjectDto.getModule_subject_id())
                .addValue("maximum_student",module_subjectDto.getMaximum_student())
                .addValue("start_at",module_subjectDto.getStart_at())
                .addValue("end_at",module_subjectDto.getEnd_at())
                .addValue("teacher_id",module_subjectDto.getTeacher_id())
                .addValue("updated_at",LocalDateTime.now());
        return namedParameterJdbcTemplate.update(sql,nameParameters);
    }

    @Override
    public Module_subjectDto getModule_subjectById(int id) {
        String sql="select * from module_subjects where module_subject_id=:id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        List<Module_subjectDto> li= namedParameterJdbcTemplate.query(sql,parameters, new BeanPropertyRowMapper<>(Module_subjectDto.class));
        return li.get(0);
    }

    @Override
    public void deleteModule_subjectById(int id) {
        String sql = "DELETE FROM module_subjects WHERE module_subject_id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(sql,parameters);
    }

    @Override
    public List<Module_subjectConverter> searchByModule_subjectname(String searchString) {
        String sql="SELECT m.module_subject_id, s.subject_name, m.current_student,i.fullname,m.start_at,m.end_at,m.created_at\n" +
                "FROM module_subjects m\n" +
                "LEFT OUTER JOIN subjects s ON s.subject_id=m.subject_id\n" +
                "LEFT OUTER JOIN teachers t on m.teacher_id=t.teacher_id\n" +
                "LEFT OUTER JOIN infors i on t.infor_id=i.infor_id\n" +
                "where s.subject_name=:searchString";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("searchString", searchString);
        List<Module_subjectConverter> list =  namedParameterJdbcTemplate.query(sql,parameters,new BeanPropertyRowMapper<>(Module_subjectConverter.class));
        return list;
    }

    @Override
    public List<Module_subjectConverter> getModulSubjectByTeacher() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        String getId="select t.teacher_id from teachers t " +
                "inner join accounts a on t.account_id=a.account_id " +
                "where a.username=:username";
        MapSqlParameterSource nameParameter=new MapSqlParameterSource()
                .addValue("username",username);
        Integer id = namedParameterJdbcTemplate.queryForObject(getId, nameParameter, Integer.class);
        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
                .addValue("id",id);
        String sql="Select m.module_subject_id,s.subject_name,m.current_student,m.maximum_student,m.start_at,m.end_at "+
                "from module_subjects m "+
                "inner join subjects s on m.subject_id=s.subject_id where m.teacher_id=:id";
        return namedParameterJdbcTemplate.query(sql,nameParameters,new BeanPropertyRowMapper<>(Module_subjectConverter.class));
    }
}
