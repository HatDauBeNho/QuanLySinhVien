package T3H.QuanLySinhVien.Converter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentConverter {
    private int student_id;
    private String fullname;
    private LocalDate date_of_birth;
    private int gender;
    private String address;
    private String phone_number;
    private String email;
    private double gpa;
    private String class_name;


}
