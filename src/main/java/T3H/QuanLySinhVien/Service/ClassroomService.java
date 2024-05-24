package T3H.QuanLySinhVien.Service;

import T3H.QuanLySinhVien.Converter.ClassroomConverter;
import T3H.QuanLySinhVien.Entities.dto.ClassroomDto;
import T3H.QuanLySinhVien.Repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<ClassroomConverter> getAllClassroomForView()
    {
        return classroomRepository.getAllClassroomForView();
    }
    public List<ClassroomDto> getAllClassroom()
    {
        return classroomRepository.getAllClassroom();
    }
    public ClassroomDto getClassroomById(@PathVariable int id)
    {
       return classroomRepository.getClassroomById(id);
    }
    public int addClassroom(ClassroomDto classroomDto)
    {
        return classroomRepository.addClassroom(classroomDto);
    }
    public int  updateClassroom(ClassroomDto classroomDto)
    {
        return classroomRepository.updateClassroom(classroomDto);
    }
    public void deleteClassroomById(@RequestParam int id)
    {
        classroomRepository.deleteClassroomById(id);
    }
    public List<ClassroomConverter> searchByClassroomname(@PathVariable String searchString)
    {

        return classroomRepository.searchByClassroomname(searchString);
    }

}
