package T3H.QuanLySinhVien.Service;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.SubjectConverter;
import T3H.QuanLySinhVien.Entities.dao.SubjectDao;
import T3H.QuanLySinhVien.Entities.dto.SubjectDto;
import T3H.QuanLySinhVien.Mapper.SubjectMapper;
import T3H.QuanLySinhVien.Repository.SubjectRepository;
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
public class SubjectService {
    @Autowired
    SubjectRepository  subjectRepository;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<SubjectConverter> getAllSubjectForView()
    {
        return subjectRepository.getAllSubjectForView();
    }
    public List<SubjectDto> getAllSubject()
    {
        return subjectRepository.getAllSubject();
    }
    public int addSubject(SubjectDto subjectDto)
    {
        return subjectRepository.addSubject(subjectDto);
    }
    public SubjectDto getSubjectById(@RequestParam int id)
    {
        return subjectRepository.getSubjectById(id);
    }
    public void deleteSubject(@RequestParam int id)
    {
        subjectRepository.deleteSubjectById(id);
    }
    public int updateSubject(SubjectDto subjectDto)
    {
        return subjectRepository.updateSubject(subjectDto);
    }
    public List<SubjectConverter> searchBySubjectname(@PathVariable String searchString)
    {

        return subjectRepository.searchBySubjectname(searchString);
    }
}
