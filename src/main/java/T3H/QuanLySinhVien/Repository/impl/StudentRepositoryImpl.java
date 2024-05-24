package T3H.QuanLySinhVien.Repository.impl;
import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.StudentConverter;
import T3H.QuanLySinhVien.Entities.dto.AccountDto;
import T3H.QuanLySinhVien.Entities.dto.InforDto;
import T3H.QuanLySinhVien.Entities.dto.StudentDto;
import T3H.QuanLySinhVien.Repository.StudentRepository;
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

public class StudentRepositoryImpl implements StudentRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<StudentConverter> getAllStudentForView() {
        String sql="SELECT s.student_id,i.fullname,i.date_of_birth,i.gender,i.address,i.phone_number,i.email,s.gpa,c.class_name\n" +
                "FROM students s\n" +
                "LEFT OUTER JOIN infors i ON s.infor_id=i.infor_id\n" +
                "left outer join class_rooms c on s.class_id=c.class_id" +
                " ORDER BY s.student_id";
        return namedParameterJdbcTemplate.query(sql,new BeanPropertyRowMapper<>(StudentConverter.class));
    }

    @Override
    public List<StudentDto> getAllStudent() {
        String sql="select * from students";
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StudentDto.class));
    }

    @Override
    public int addStudent(StudentDto studentDto, AccountDto accountDto, InforDto inforDto) {
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
                .addValue("level_id",4)
                .addValue("created_at", LocalDateTime.now())
                .addValue("updated_at",LocalDateTime.now());
        namedParameterJdbcTemplate.update(accounts,nameParameters2);
        int account_id= keyHolder.getKey().intValue();

        String sql= "INSERT INTO students ( account_id, infor_id, class_id, created_at, updated_at) " +
                "VALUES (:account_id, :infor_id, :class_id, :created_at, :updated_at)";

        MapSqlParameterSource nameParameters1=new MapSqlParameterSource()
                .addValue("account_id", account_id)
                .addValue("infor_id", infor_id)
                .addValue("class_id", studentDto.getClass_id())
                .addValue("created_at", LocalDateTime.now())
                .addValue("updated_at",LocalDateTime.now());
        return namedParameterJdbcTemplate.update(sql,nameParameters1);
    }

    @Override
    public int updateStudent(StudentDto studentDto, InforDto inforDto) {
        String sql="UPDATE infors i " +
                "inner join students s on s.infor_id=i.infor_id " +
                "SET i.fullname=:fullname,i.phone_number=:phone_number,i.email=:email," +
                "i.date_of_birth=:date_of_birth,i.address=:address,i.gender=:gender,i.updated_at=:updated_at" +
                " WHERE student_id=:student_id";
        MapSqlParameterSource nameParameters1=new MapSqlParameterSource()
                .addValue("student_id",studentDto.getStudent_id())
                .addValue("infor_id",inforDto.getInfor_id())
                .addValue("fullname",inforDto.getFullname())
                .addValue("phone_number",inforDto.getPhone_number())
                .addValue("email",inforDto.getEmail())
                .addValue("date_of_birth",inforDto.getDate_of_birth())
                .addValue("address",inforDto.getAddress())
                .addValue("gender",inforDto.getGender())
                .addValue("updated_at",LocalDateTime.now());

        return  namedParameterJdbcTemplate.update(sql,nameParameters1);
    }

    @Override
    public StudentConverter getStudentById(int id) {
        String sql = "SELECT * FROM students inner join infors on students.infor_id = infors.infor_id WHERE student_id =:student_id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("student_id", id);
        List<StudentConverter> students = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(StudentConverter.class));
        return students.get(0) ;
    }

    @Override
    public void deleteStudentById(int id) {

        String sql1="DELETE FROM infors WHERE infor_id IN (SELECT infor_id FROM students WHERE student_id =:id)";
        String sql2="DELETE FROM accounts WHERE account_id IN (SELECT account_id FROM students WHERE student_id =:id)";
        String sql3="DELETE FROM students WHERE student_id =:id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update(sql1,parameters);
        namedParameterJdbcTemplate.update(sql2,parameters);
        namedParameterJdbcTemplate.update(sql3,parameters);
    }

    @Override
    public List<StudentConverter> searchByStudentname(String searchString) {

        String sql="SELECT s.student_id,i.fullname,i.date_of_birth,i.gender,i.address,i.phone_number,i.email,s.gpa,c.class_name\n" +
                "FROM students s\n" +
                "LEFT OUTER JOIN infors i ON s.infor_id=i.infor_id\n" +
                "left outer join class_rooms c on s.class_id=c.class_id" +
                " ORDER BY s.student_id" +
                " where i.fullname=:searchString";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("searchString", searchString);
        List<StudentConverter> list =  namedParameterJdbcTemplate.query(sql,parameters,new BeanPropertyRowMapper<>(StudentConverter.class));
        return list;
    }
}
