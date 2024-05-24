package T3H.QuanLySinhVien.Mapper;

import T3H.QuanLySinhVien.Entities.dao.Course_registrationDao;
import T3H.QuanLySinhVien.Converter.Course_registrationConverter;
import T3H.QuanLySinhVien.Entities.dto.Course_registrationDto;

public class Course_registrationMapper {
    public static Course_registrationDto mapCourse_registration(Course_registrationDao courseRegistrationDao)
    {
        return new Course_registrationDto(
                courseRegistrationDao.getCourse_registration_id(),
                courseRegistrationDao.getStudent_id(),
                courseRegistrationDao.getModule_subject_id(),
                courseRegistrationDao.getCreated_at(),
                courseRegistrationDao.getUpdated_at()
        );
    }
}
