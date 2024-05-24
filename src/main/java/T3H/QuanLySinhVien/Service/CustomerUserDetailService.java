package T3H.QuanLySinhVien.Service;

import T3H.QuanLySinhVien.Converter.AccountConverter;
import T3H.QuanLySinhVien.Converter.ClassroomConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql="select * from accounts a" +
                " inner join levels l on a.level_id=l.level_id where a.username=:username ";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", username);
        List<AccountConverter> list= namedParameterJdbcTemplate.query(sql,params, new BeanPropertyRowMapper<>(AccountConverter.class));
        if (list.size()>0)
        {
            AccountConverter accountConverter=list.get(0);
            List<GrantedAuthority> grantList=new ArrayList<>();
            GrantedAuthority authority=new SimpleGrantedAuthority(accountConverter.getLevel_name());
            grantList.add(authority);
            UserDetails userDetails=new User(accountConverter.getUsername(),accountConverter.getPassword(),grantList);
            return userDetails;
        }else
        {
           throw  new UsernameNotFoundException("Login fail!");
        }
    }
}
