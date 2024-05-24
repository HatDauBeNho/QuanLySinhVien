package T3H.QuanLySinhVien.Repository.impl;


import T3H.QuanLySinhVien.Converter.Module_subjectConverter;
import T3H.QuanLySinhVien.Converter.ResultConverter;
import T3H.QuanLySinhVien.Repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ResultRepositoryImpl implements ResultRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ResultConverter> getResultForTeacher(int module_subject_id) {
        String sql="Select c.course_registration_id,i.fullname,k.class_name,i.phone_number,r.point " +
                "from course_registrations c " +
                "left outer join students s on c.student_id=s.student_id " +
                "left outer join class_rooms k on s.class_id=k.class_id " +
                "left outer join module_subjects m on m.module_subject_id=c.module_subject_id " +
                "left outer join infors i on s.infor_id=i.infor_id " +
                "left outer join results r on c.course_registration_id=r.course_registration_id " +
                "where m.module_subject_id=:id;";
        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
                .addValue("id",module_subject_id);
        List<ResultConverter> list= namedParameterJdbcTemplate.query(sql,nameParameters,new BeanPropertyRowMapper<>(ResultConverter.class));
        return list;
    }

    @Override
    public void addResult() {
        String sql3="select c.course_registration_id from course_registrations c ORDER BY c.course_registration_id DESC LIMIT 1";
        Integer lastCourse_registration_id = jdbcTemplate.queryForObject(sql3,Integer.class);
        String sql4="insert into results (course_registration_id,point,created_at,updated_at) " +
                "values (:course_registration_id,0,:created_at,:updated_at)";
        MapSqlParameterSource nameParameter3=new MapSqlParameterSource()
                .addValue("created_at", LocalDateTime.now())
                .addValue("updated_at", LocalDateTime.now())
                .addValue("course_registration_id",lastCourse_registration_id);
        namedParameterJdbcTemplate.update(sql4,nameParameter3);
    }

    @Override
    public void updateResult(int course_registration_id,double point) {
        String sql="update results " +
                "set point=:newPoint where course_registration_id=:id";
        MapSqlParameterSource nameParameter=new MapSqlParameterSource()
                .addValue("newPoint",point)
                .addValue("id",course_registration_id);
        namedParameterJdbcTemplate.update(sql,nameParameter);
    }
}
