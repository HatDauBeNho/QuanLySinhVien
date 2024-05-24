package T3H.QuanLySinhVien.Service;

import T3H.QuanLySinhVien.Converter.DepartmentConverter;
import T3H.QuanLySinhVien.Converter.Module_subjectConverter;
import T3H.QuanLySinhVien.Entities.dao.Module_subjectDao;
import T3H.QuanLySinhVien.Entities.dto.Module_subjectDto;
import T3H.QuanLySinhVien.Mapper.Module_subjectMapper;
import T3H.QuanLySinhVien.Repository.Module_subjectRepository;
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
public class Module_subjectService {
    @Autowired
    Module_subjectRepository module_subjectRepository;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Module_subjectConverter> getAllModule_subjectForView()
    {
        return  module_subjectRepository.getAllModule_subjectForView();
    }
    public List<Module_subjectDto> getAllModule_subject()
    {
        return module_subjectRepository.getAllModule_subject();
    }
    public int  addModule_subject(Module_subjectDto module_SubjectDto)
    {
        return module_subjectRepository.addModule_subject(module_SubjectDto);
    }
    public Module_subjectDto getModule_subjectById(@RequestParam int id)
    {
        return  module_subjectRepository.getModule_subjectById(id);
    }
    public void  deleteModule_subject(@RequestParam int id)
    {
        module_subjectRepository.deleteModule_subjectById(id);
    }
    public int  updateModule_subject(Module_subjectDto module_subjectDto)
    {
        return module_subjectRepository.updateModule_subject(module_subjectDto);
    }
    public List<Module_subjectConverter> searchByModule_subjectname(@PathVariable String searchString)
    {

        return module_subjectRepository.searchByModule_subjectname(searchString);
    }
    public List<Module_subjectConverter> getModulSubjectByTeacher()
    {
        return module_subjectRepository.getModulSubjectByTeacher();
    }
}
