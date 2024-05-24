package T3H.QuanLySinhVien.Converter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResultConverter {
    private int result_id;
    private String fullname;
    private String class_name;
    private String phone_number;
    private int course_registration_id;
    private int module_subject_id;
    @Nullable
    private double point;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
