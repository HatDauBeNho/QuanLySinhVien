package T3H.QuanLySinhVien.Repository;
import T3H.QuanLySinhVien.Converter.ClassroomConverter;

import T3H.QuanLySinhVien.Entities.dto.ClassroomDto;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Repository
public interface ClassroomRepository
{
    List<ClassroomConverter> getAllClassroomForView();
    List<ClassroomDto> getAllClassroom();
    int addClassroom(ClassroomDto classroomDto);
    int  updateClassroom(ClassroomDto classroomDto);
    ClassroomDto getClassroomById(@PathVariable int id);
    void deleteClassroomById(@RequestParam int id);
    List<ClassroomConverter> searchByClassroomname(String searchString);

}
