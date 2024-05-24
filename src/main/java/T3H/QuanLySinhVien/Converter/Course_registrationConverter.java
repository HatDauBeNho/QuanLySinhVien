package T3H.QuanLySinhVien.Converter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course_registrationConverter {
    private String subject_name;
    private String fullname;
    private int module_subject_id;
    private int current_student;
    private int maximum_student;
    private LocalDate start_at;
    private LocalDate end_at;
    private LocalDateTime updated_at;


}
