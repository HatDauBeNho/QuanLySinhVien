package T3H.QuanLySinhVien.Repository;

import T3H.QuanLySinhVien.Converter.AccountConverter;
import T3H.QuanLySinhVien.Entities.dto.AccountDto;
import T3H.QuanLySinhVien.Entities.dto.InforDto;

public interface AccountRepository {
    AccountConverter getUpdateAccount();
    void updateAccount(InforDto inforDto, AccountDto accountDto);
}
