package T3H.QuanLySinhVien.Repository;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Entities.dao.DepartmentDao;
import T3H.QuanLySinhVien.Entities.dto.DepartmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface DepartmentRepository {
    List<DepartmentConverter> getAllDepartmentForView();
    List<DepartmentDto> getAllDepartment();
    int addDepartment(DepartmentDto departmentDto);
    int  updateDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(@PathVariable int id);
    void deleteDepartmentById(@RequestParam int id);
    List<DepartmentConverter> searchByDepartmentname(String searchString);

}
