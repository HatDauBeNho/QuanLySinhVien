package T3H.QuanLySinhVien.Repository;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.MajorConverter;
import T3H.QuanLySinhVien.Entities.dao.MajorDao;
import T3H.QuanLySinhVien.Entities.dto.MajorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository

public interface MajorRepository  {
    List<MajorConverter> getAllMajorForView();
    List<MajorDto> getAllMajor();
    int addMajor(MajorDto majorDto);
    int  updateMajor(MajorDto majorDto);
    MajorDto getMajorById(@PathVariable int id);
    void deleteMajorById(@PathVariable int id);
    List<MajorConverter> searchByMajorname(String searchString);

}
