package T3H.QuanLySinhVien.Repository;

import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository {
    String getUserName();

    int countDepartment();
    int countMajor();
    int countTeacher();
    int countStudent();
    int countSubject();
    double gpa();
    int countRegistedCourse();
    
}
