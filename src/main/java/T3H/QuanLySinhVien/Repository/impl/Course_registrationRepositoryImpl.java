package T3H.QuanLySinhVien.Repository.impl;
import T3H.QuanLySinhVien.Converter.Course_registrationConverter;
import T3H.QuanLySinhVien.Repository.Course_registrationRepository;
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
public class Course_registrationRepositoryImpl implements Course_registrationRepository {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<Course_registrationConverter> getAllCourse_registrationForView() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        String getId="select s.student_id from students s " +
                "inner join accounts a on s.account_id=a.account_id " +
                "where a.username=:username";
        String sql="select c.module_subject_id,s.subject_name,i.fullname,m.current_student,m.maximum_student,m.start_at,m.end_at,c.updated_at" +
                " from course_registrations c" +
                " left outer join module_subjects m on c.module_subject_id=m.module_subject_id" +
                " left outer join subjects s on m.subject_id=s.subject_id" +
                " left outer join teachers t on t.teacher_id=m.teacher_id" +
                " left outer join infors i on t.infor_id=i.infor_id " +
                "where c.student_id=:id";
        MapSqlParameterSource nameParameter=new MapSqlParameterSource()
                .addValue("username",username);
        Integer id = namedParameterJdbcTemplate.queryForObject(getId, nameParameter, Integer.class);
        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
                .addValue("id",id);
        return namedParameterJdbcTemplate.query(sql, nameParameters, new BeanPropertyRowMapper<>(Course_registrationConverter.class));
    }

    @Override
    public void registerCourse(int module_subject_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        String getId="select s.student_id from students s " +
                "inner join accounts a on s.account_id=a.account_id " +
                "where a.username=:username";
        MapSqlParameterSource nameParameter1=new MapSqlParameterSource()
                .addValue("username",username)
                .addValue("module_subject_id",module_subject_id);
        Integer student_id = namedParameterJdbcTemplate.queryForObject(getId, nameParameter1, Integer.class);
        String sql1="insert into course_registrations(student_id,module_subject_id,created_at,updated_at) " +
                "values (:student_id,:module_subject_id,:created_at,:updated_at)";
        String sql2="UPDATE module_subjects " +
                "SET current_student=:current_student,updated_at=:updated_at " +
                "WHERE module_subject_id=:module_subject_id";
        String getCurrent_student="select m.current_student " +
                "from module_subjects m " +
                "where module_subject_id=:module_subject_id";
        Integer current_student = namedParameterJdbcTemplate.queryForObject(getCurrent_student, nameParameter1, Integer.class);

        MapSqlParameterSource nameParameter2=new MapSqlParameterSource()
                .addValue("student_id",student_id)
                .addValue("current_student",current_student+1)
                .addValue("updated_at", LocalDateTime.now())
                .addValue("module_subject_id",module_subject_id)
                .addValue("created_at", LocalDateTime.now());
        namedParameterJdbcTemplate.update(sql1,nameParameter2);
        namedParameterJdbcTemplate.update(sql2,nameParameter2);


    }

    @Override
    public boolean checkRegistedCourse(int module_subject_id) {
        for (Course_registrationConverter item:
             getAllCourse_registrationForView()) {
            if (item.getModule_subject_id()==module_subject_id){
                return false;
            }
        }
        return true;
    }
}
