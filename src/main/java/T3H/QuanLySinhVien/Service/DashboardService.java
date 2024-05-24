package T3H.QuanLySinhVien.Service;

import T3H.QuanLySinhVien.Repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    DashboardRepository dashboardRepository;
    public String getUserName()
    {

        return dashboardRepository.getUserName();
    }
    public String getLevelName()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = new ArrayList<>(authentication.getAuthorities());
        GrantedAuthority firstAuthority = authorities.get(0);
        return firstAuthority.getAuthority();

    }
    public int countDeparment()
    {
        return dashboardRepository.countDepartment();
    }
    public int countTeacher()
    {
        return dashboardRepository.countTeacher();
    }
    public int countStudent()
    {
        return dashboardRepository.countStudent();
    }
    public int countSubject()
    {
        return dashboardRepository.countSubject();
    }
    public int countMajor()
    {
        return dashboardRepository.countMajor();
    }
    public double gpa()
    {
        return dashboardRepository.gpa();
    }
    public int countRegistedCourse()
    {
        return dashboardRepository.countRegistedCourse();
    }

}
