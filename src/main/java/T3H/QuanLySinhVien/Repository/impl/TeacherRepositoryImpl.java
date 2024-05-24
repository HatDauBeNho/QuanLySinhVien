package T3H.QuanLySinhVien.Repository.impl;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.TeacherConverter;
import T3H.QuanLySinhVien.Entities.dto.AccountDto;
import T3H.QuanLySinhVien.Entities.dto.InforDto;
import T3H.QuanLySinhVien.Entities.dto.TeacherDto;
import T3H.QuanLySinhVien.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TeacherRepositoryImpl implements TeacherRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<TeacherConverter> getAllTeacherForView() {
        String sql="SELECT t.teacher_id,i.fullname, i.date_of_birth,i.gender,i.address,i.phone_number,i.email,l.level_name " +
                "FROM teachers t " +
                "LEFT OUTER JOIN infors i ON t.infor_id=i.infor_id " +
                "LEFT OUTER JOIN accounts a ON t.account_id=a.account_id" +
                " LEFT OUTER JOIN levels l ON l.level_id=a.level_id";
        return namedParameterJdbcTemplate.query(sql,new BeanPropertyRowMapper<>(TeacherConverter.class));
    }

    @Override
    public List<TeacherDto> getAllTeacher() {
        String sql="select * from teachers";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TeacherDto.class));
    }

    @Override
    public int addTeacher(TeacherDto teacherDto, AccountDto accountDto, InforDto inforDto) {
        String infors="INSERT INTO infors( fullname, address, phone_number, email, date_of_birth, gender, created_at, updated_at)" +
                "VALUES ( :fullname, :address, :phone_number, :email, :date_of_birth, :gender, :created_at, :updated_at)";
        MapSqlParameterSource nameParameters3=new MapSqlParameterSource()
                .addValue("fullname",inforDto.getFullname())
                .addValue("address",inforDto.getAddress())
                .addValue("phone_number",inforDto.getPhone_number())
                .addValue("email",inforDto.getEmail())
                .addValue("date_of_birth",inforDto.getDate_of_birth())
                .addValue("gender",inforDto.getGender())
                .addValue("created_at", LocalDateTime.now())
                .addValue("updated_at",LocalDateTime.now());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(infors,nameParameters3,keyHolder);
        int infor_id= keyHolder.getKey().intValue();

        String accounts="INSERT INTO accounts (username, password, level_id, created_at, updated_at)" +
                "VALUES (:username, :password, :level_id,  :created_at, :updated_at)";
        MapSqlParameterSource nameParameters2=new MapSqlParameterSource()
                .addValue("username",accountDto.getUsername())
                .addValue("password",accountDto.getPassword())
                .addValue("level_id",3)
                .addValue("created_at", LocalDateTime.now())
                .addValue("updated_at",LocalDateTime.now());
        namedParameterJdbcTemplate.update(accounts,nameParameters2);
        int account_id= keyHolder.getKey().intValue();

        String sql= "INSERT INTO teachers ( account_id, infor_id, created_at, updated_at) " +
                "VALUES (:account_id, :infor_id, :created_at, :updated_at)";
        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
                .addValue("account_id",account_id)
                .addValue("infor_id",infor_id)
                .addValue("created_at", LocalDateTime.now())
                .addValue("updated_at",LocalDateTime.now());
        return namedParameterJdbcTemplate.update(sql,nameParameters);
    }

    @Override
    public int updateTeacher(TeacherDto teacherDto, InforDto inforDto) {
        String sql="UPDATE infors i " +
                "inner join teachers t on t.infor_id=i.infor_id " +
                "SET i.fullname=:fullname,i.phone_number=:phone_number,i.email=:email," +
                "i.date_of_birth=:date_of_birth,i.address=:address,i.gender=:gender,i.updated_at=:updated_at" +
                " WHERE teacher_id=:teacher_id";
        MapSqlParameterSource nameParameters1=new MapSqlParameterSource()
                .addValue("teacher_id",teacherDto.getTeacher_id())
                .addValue("infor_id",inforDto.getInfor_id())
                .addValue("fullname",inforDto.getFullname())
                .addValue("phone_number",inforDto.getPhone_number())
                .addValue("email",inforDto.getEmail())
                .addValue("date_of_birth",inforDto.getDate_of_birth())
                .addValue("address",inforDto.getAddress())
                .addValue("gender",inforDto.getGender())
                .addValue("updated_at",LocalDateTime.now    ());
        return  namedParameterJdbcTemplate.update(sql,nameParameters1);
    }

    @Override
    public TeacherConverter getTeacherById(int id) {
        String sql = "SELECT * FROM teachers " +
                "inner join infors on teachers.infor_id = infors.infor_id WHERE teacher_id =:teacher_id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("teacher_id", id);
        List<TeacherConverter> teachers = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(TeacherConverter.class));
        return teachers.get(0);
    }

    @Override
    public void deleteTeacherById(int id) {
        String sql1="DELETE FROM infors WHERE infor_id IN (SELECT infor_id FROM students WHERE teacher_id =:id)";
        String sql2="DELETE FROM accounts WHERE account_id IN (SELECT account_id FROM students WHERE teacher_id =:id)";
        String sql3="DELETE FROM teachers WHERE teacher_id =:id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(sql1,parameters);
        namedParameterJdbcTemplate.update(sql2,parameters);
        namedParameterJdbcTemplate.update(sql3,parameters);
    }

    @Override
    public List<TeacherConverter> searchByTeachername(String searchString) {

        String sql="SELECT t.teacher_id,i.fullname, i.date_of_birth,i.gender,i.address,i.phone_number,i.email,l.level_name " +
                "FROM teachers t " +
                "LEFT OUTER JOIN infors i ON t.infor_id=i.infor_id " +
                "LEFT OUTER JOIN accounts a ON t.account_id=a.account_id" +
                " LEFT OUTER JOIN levels l ON l.level_id=a.level_id" +
                " where i.fullname=:searchString";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("searchString", searchString);
        List<TeacherConverter> list =  namedParameterJdbcTemplate.query(sql,parameters,new BeanPropertyRowMapper<>(TeacherConverter.class));
        return list;
    }
}
