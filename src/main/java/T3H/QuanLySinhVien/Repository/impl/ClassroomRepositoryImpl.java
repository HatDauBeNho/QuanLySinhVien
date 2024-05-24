package T3H.QuanLySinhVien.Repository.impl;
import T3H.QuanLySinhVien.Converter.ClassroomConverter;
import T3H.QuanLySinhVien.Converter.ClassroomConverter;
import T3H.QuanLySinhVien.Entities.dto.ClassroomDto;
import T3H.QuanLySinhVien.Repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public class ClassroomRepositoryImpl implements ClassroomRepository {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<ClassroomConverter> getAllClassroomForView() {
        String sql = "SELECT "
                + " c.class_id,"
                + " c.class_name,"
                + " c.quantity,"
                + " m.major_name,"
                + " d.department_name,"
                + " i.fullname,"
                + " i.phone_number"
                + " FROM class_rooms c"
                + " LEFT OUTER JOIN teachers t ON c.teacher_id = t.teacher_id"
                + " LEFT OUTER JOIN infors i ON t.infor_id = i.infor_id"
                + " LEFT OUTER JOIN majors m ON c.major_id = m.major_id"
                + " LEFT OUTER JOIN departments d ON m.department_id=d.department_id";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClassroomConverter.class));
    }

    @Override
    public List<ClassroomDto> getAllClassroom() {
        String sql="select * from class_rooms";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClassroomDto.class));
    }

    @Override
    public int addClassroom(ClassroomDto classroomDto) {
        String sql = "INSERT INTO class_rooms (class_name, quantity, major_id, teacher_id, created_at, updated_at) " +
                "VALUES (:class_name,:quantity,:major_id,:teacher_id,:created_at,:updated_at)";
        MapSqlParameterSource nameParameters = new MapSqlParameterSource()
                .addValue("class_name", classroomDto.getClass_name())
                .addValue("quantity", classroomDto.getQuantity())
                .addValue("major_id", classroomDto.getMajor_id())
                .addValue("teacher_id", classroomDto.getTeacher_id())
                .addValue("created_at", LocalDateTime.now())
                .addValue("updated_at", LocalDateTime.now());
        return namedParameterJdbcTemplate.update(sql, nameParameters);
    }
    @Override
    public int updateClassroom(ClassroomDto classroomDto) {
        String sql = "UPDATE class_rooms " +
                "SET class_name=:class_name,quantity=:quantity,major_id=:major_id,teacher_id=:teacher_id,updated_at=:updated_at " +
                "WHERE class_id=:class_id";
        MapSqlParameterSource nameParameters = new MapSqlParameterSource()
                .addValue("class_id", classroomDto.getClass_id())
                .addValue("class_name", classroomDto.getClass_name())
                .addValue("quantity", classroomDto.getQuantity())
                .addValue("major_id", classroomDto.getMajor_id())
                .addValue("teacher_id", classroomDto.getTeacher_id())
                .addValue("updated_at", LocalDateTime.now());
        return namedParameterJdbcTemplate.update(sql, nameParameters);
    }

    @Override
    public ClassroomDto getClassroomById(@PathVariable int id) {
        String sql="select * from class_rooms where class_id=:id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        List<ClassroomDto> li= namedParameterJdbcTemplate.query(sql,parameters, new BeanPropertyRowMapper<>(ClassroomDto.class));
        return li.get(0);
    }

    @Override
    public void deleteClassroomById(@PathVariable int id) {
        String sql = "DELETE FROM class_rooms WHERE class_id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(sql,parameters);
    }
    @Override
    public List<ClassroomConverter> searchByClassroomname(String searchString) {
        String sql = "SELECT "
                + " c.class_id,"
                + " c.class_name,"
                + " c.quantity,"
                + " m.major_name,"
                + " d.department_name,"
                + " i.fullname,"
                + " i.phone_number"
                + " FROM class_rooms c"
                + " LEFT OUTER JOIN teachers t ON c.teacher_id = t.teacher_id"
                + " LEFT OUTER JOIN infors i ON t.infor_id = i.infor_id"
                + " LEFT OUTER JOIN majors m ON c.major_id = m.major_id"
                + " LEFT OUTER JOIN departments d ON m.department_id=d.department_id" +
                " where c.class_name=:searchString";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("searchString", searchString);
        List<ClassroomConverter> list =  namedParameterJdbcTemplate.query(sql,parameters,new BeanPropertyRowMapper<>(ClassroomConverter.class));
        return list;
    }
}
