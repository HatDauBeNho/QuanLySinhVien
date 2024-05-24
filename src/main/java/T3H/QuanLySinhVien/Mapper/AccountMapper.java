package T3H.QuanLySinhVien.Mapper;

import T3H.QuanLySinhVien.Entities.dao.AccountDao;
import T3H.QuanLySinhVien.Converter.AccountConverter;
import T3H.QuanLySinhVien.Entities.dto.AccountDto;

public class AccountMapper {
    public static AccountDto mapAccount(AccountDao accountDao)
    {
        return new AccountDto(
                accountDao.getAccount_id(),
                accountDao.getUsername(),
                accountDao.getPassword(),
                accountDao.getLevel_id(),
                accountDao.getRemember_token(),
                accountDao.getCreated_at(),
                accountDao.getUpdated_at()
        );
    }
}
