package T3H.QuanLySinhVien.Service;

import T3H.QuanLySinhVien.Converter.TeacherConverter;
import T3H.QuanLySinhVien.Entities.dao.AccountDao;
import T3H.QuanLySinhVien.Entities.dto.AccountDto;
import T3H.QuanLySinhVien.Entities.dto.InforDto;
import T3H.QuanLySinhVien.Mapper.AccountMapper;
import T3H.QuanLySinhVien.Converter.AccountConverter;
import T3H.QuanLySinhVien.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public AccountConverter getUpdateAccount()
    {
        return accountRepository.getUpdateAccount();
    }
    public void updateAccount(InforDto inforDto, AccountDto accountDto)
    {
        accountRepository.updateAccount(inforDto,accountDto);
    }
//    public List<Account> addAccount(Account account)
//    {
//       return accountRepository.addAccount(account);
//    }
//    public List<Account> deleteAccountByIdForAdmin(int id)
//    {
//        return accountRepository.deleteAccountByIdForAdmin(id);
//    }
//    public List<Account> updateAccountById(Account account) {
//        return accountRepository.updateAccountById(account);
//    }
}
