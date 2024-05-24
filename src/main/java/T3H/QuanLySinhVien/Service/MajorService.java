package T3H.QuanLySinhVien.Service;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.MajorConverter;
import T3H.QuanLySinhVien.Entities.dao.MajorDao;
import T3H.QuanLySinhVien.Entities.dto.MajorDto;
import T3H.QuanLySinhVien.Mapper.DepartmentMapper;
import T3H.QuanLySinhVien.Mapper.MajorMapper;
import T3H.QuanLySinhVien.Repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MajorService {
    @Autowired
    MajorRepository majorRepository;

    @Autowired
    NamedParameterJdbcTemplate  namedParameterJdbcTemplate;
    public List<MajorConverter> getAllMajorForView()
    {
        return majorRepository.getAllMajorForView();
    }
    public List<MajorDto> getAllMajor()
    {
        return majorRepository.getAllMajor();
    }
    public int addMajor(MajorDto majorDto)
    {
        return majorRepository.addMajor(majorDto);
    }
    public MajorDto getMajorById(@PathVariable int id)
    {
        return majorRepository.getMajorById(id);
    }
    public int updateMajor(MajorDto majorDto)
    {
        return majorRepository.updateMajor(majorDto);
    }
    public void deleteMajor(@PathVariable int id)
    {
        majorRepository.deleteMajorById(id);
    }
    public List<MajorConverter> searchByMajorname(@PathVariable String searchString)
    {

        return majorRepository.searchByMajorname(searchString);
    }
}
