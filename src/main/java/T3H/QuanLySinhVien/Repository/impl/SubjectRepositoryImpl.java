package T3H.QuanLySinhVien.Repository.impl;
import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.SubjectConverter;
import T3H.QuanLySinhVien.Entities.dto.SubjectDto;
import T3H.QuanLySinhVien.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
@Repository

public class SubjectRepositoryImpl implements SubjectRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<SubjectConverter> getAllSubjectForView() {
        String sql="SELECT s.subject_id,s.subject_name,s.credit_hour,d.department_name" +
                " FROM subjects s" +
                " LEFT OUTER JOIN departments d ON d.department_id = s.department_id";
        return namedParameterJdbcTemplate.query(sql,new BeanPropertyRowMapper<>(SubjectConverter.class));
    }

    @Override
    public List<SubjectDto> getAllSubject() {
        String sql="select * from subjects";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SubjectDto.class));
    }

    @Override
    public int addSubject(SubjectDto subjectDto) {
        String sql= "INSERT INTO subjects (subject_name, credit_hour, department_id, created_at, updated_at) " +
                "VALUES (:subject_name, :credit_hour, :department_id, :created_at, :updated_at)";
        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
                .addValue("subject_name",subjectDto.getSubject_name())
                .addValue("credit_hour",subjectDto.getCredit_hour())
                .addValue("department_id",subjectDto.getDepartment_id())
                .addValue("created_at", LocalDateTime.now())
                .addValue("updated_at",LocalDateTime.now());
        return namedParameterJdbcTemplate.update(sql,nameParameters);
    }

    @Override
    public int updateSubject(SubjectDto subjectDto) {
        String sql="UPDATE subjects " +
                "SET subject_name=:subject_name,credit_hour=:credit_hour,department_id=:department_id,updated_at=:updated_at " +
                "WHERE subject_id=:subject_id";
        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
                .addValue("subject_id",subjectDto.getSubject_id())
                .addValue("subject_name",subjectDto.getSubject_name())
                .addValue("department_id",subjectDto.getDepartment_id())
                .addValue("credit_hour",subjectDto.getCredit_hour())
                .addValue("updated_at",LocalDateTime.now());
        return namedParameterJdbcTemplate.update(sql,nameParameters);
    }

    @Override
    public SubjectDto getSubjectById(int id) {
        String sql="select * from subjects where subject_id=:id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        List<SubjectDto> li= namedParameterJdbcTemplate.query(sql,parameters, new BeanPropertyRowMapper<>(SubjectDto.class));
        return li.get(0);
    }

    @Override
    public void deleteSubjectById(int id) {
        String sql = "DELETE FROM subjects WHERE subject_id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(sql,parameters);
    }

    @Override
    public List<SubjectConverter> searchBySubjectname(String searchString) {

        String sql="SELECT s.subject_id,s.subject_name,s.credit_hour,d.department_name" +
                " FROM subjects s" +
                " LEFT OUTER JOIN departments d ON d.department_id = s.department_id" +
                " where s.subject_name=:searchString";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("searchString", searchString);
        List<SubjectConverter> list =  namedParameterJdbcTemplate.query(sql,parameters,new BeanPropertyRowMapper<>(SubjectConverter.class));
        return list;
    }
}
