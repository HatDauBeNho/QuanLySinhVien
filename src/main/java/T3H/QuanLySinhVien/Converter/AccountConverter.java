package T3H.QuanLySinhVien.Converter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountConverter {
    private int account_id;
    private String fullname;
    private LocalDate date_of_birth;
    private int gender;
    private String address;
    private String phone_number;
    private String email;
    private String username;
    private String password;
    private int level_id;
    private String level_name;


}
