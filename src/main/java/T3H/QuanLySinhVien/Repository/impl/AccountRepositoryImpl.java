package T3H.QuanLySinhVien.Repository.impl;


import T3H.QuanLySinhVien.Converter.AccountConverter;
import T3H.QuanLySinhVien.Converter.TeacherConverter;
import T3H.QuanLySinhVien.Entities.dto.AccountDto;
import T3H.QuanLySinhVien.Entities.dto.InforDto;
import T3H.QuanLySinhVien.Repository.AccountRepository;
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
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public AccountConverter getUpdateAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String author= authentication.getAuthorities().toString();
        String sql1 = "SELECT t.account_id,t.infor_id, a.username, a.password, " +
                "a.level_id, i.fullname, i.address, i.phone_number, i.email, i.date_of_birth, i.gender " +
                "FROM teachers t " +
                "left outer join accounts a on t.account_id=a.account_id " +
                "left outer join infors i on t.infor_id = i.infor_id WHERE a.username=:loggedInUser";
        String sql2 = "SELECT s.account_id,s.infor_id, a.username, a.password, " +
                "a.level_id, i.fullname, i.address, i.phone_number, i.email, i.date_of_birth, i.gender " +
                "FROM students s " +
                "left outer join accounts a on s.account_id=a.account_id " +
                "left outer join infors i on s.infor_id = i.infor_id WHERE a.username=:loggedInUser";
        MapSqlParameterSource nameParameters=new MapSqlParameterSource()
                .addValue("loggedInUser",authentication.getName());
        String sql="";
        if (author.equals("[Student]")) sql=sql2;
        else sql=sql1;
        List<AccountConverter> li= namedParameterJdbcTemplate.query(sql, nameParameters, new BeanPropertyRowMapper<>(AccountConverter.class));
        return  li.get(0);
    }

    @Override
    public void updateAccount(InforDto inforDto, AccountDto accountDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String author= authentication.getAuthorities().toString();
        String sql1="UPDATE infors i " +
                "inner join teachers t on t.infor_id=i.infor_id " +
                "inner join accounts a on t.account_id=a.account_id " +
                "SET i.fullname=:fullname,i.phone_number=:phone_number,i.email=:email," +
                "i.date_of_birth=:date_of_birth,i.address=:address,i.gender=:gender,i.updated_at=:updated_at" +
                " WHERE a.username=:loggedInUser";
        String sql2="UPDATE infors i " +
                "inner join students s on s.infor_id=i.infor_id " +
                "inner join accounts a on s.account_id=a.account_id " +
                "SET i.fullname=:fullname,i.phone_number=:phone_number,i.email=:email," +
                "i.date_of_birth=:date_of_birth,i.address=:address,i.gender=:gender,i.updated_at=:updated_at" +
                " WHERE a.username=:loggedInUser";
        MapSqlParameterSource nameParameters1=new MapSqlParameterSource()
                .addValue("loggedInUser",authentication.getName())
                .addValue("infor_id",inforDto.getInfor_id())
                .addValue("fullname",inforDto.getFullname())
                .addValue("phone_number",inforDto.getPhone_number())
                .addValue("email",inforDto.getEmail())
                .addValue("date_of_birth",inforDto.getDate_of_birth())
                .addValue("address",inforDto.getAddress())
                .addValue("gender",inforDto.getGender())
                .addValue("password",accountDto.getPassword())
                .addValue("updated_at", LocalDateTime.now());
        String sql="";
        if (author.equals("[Student]")) sql=sql2;
        else sql=sql1;
        namedParameterJdbcTemplate.update(sql,nameParameters1);
    }
}
