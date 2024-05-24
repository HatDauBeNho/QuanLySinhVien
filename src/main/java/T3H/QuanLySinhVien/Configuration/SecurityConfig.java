package T3H.QuanLySinhVien.Configuration;

import T3H.QuanLySinhVien.Service.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.util.Set;


@Configuration
@EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    CustomerUserDetailService customerUserDetailService;
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**","/fonts/**","/images/**","/js/**","/vendor/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("Admin")
                .antMatchers("/student/**").hasAuthority("Student")
                .antMatchers("/teacher/**").hasAuthority("Teacher")
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login")
                .successHandler(authenticationSuccessHandler()).permitAll()
                .failureUrl("/login?success=fail")
                .loginProcessingUrl("/j_spring_security_check");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailService).passwordEncoder(passwordEncoder());

    }
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            if (roles.contains("Admin")) {
                response.sendRedirect("/admin/dashboard");
            } else if (roles.contains("Student")) {
                response.sendRedirect("/student/dashboard");
            } else {
                // Default redirect for other roles
                response.sendRedirect("/teacher/module_subject");
            }
        };
    }
}
