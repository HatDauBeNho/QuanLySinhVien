package T3H.QuanLySinhVien.Service;

import T3H.QuanLySinhVien.Entities.dao.LevelDao;
import T3H.QuanLySinhVien.Entities.dao.TeacherDao;
import T3H.QuanLySinhVien.Entities.dto.LevelDto;
import T3H.QuanLySinhVien.Entities.dto.TeacherDto;
import T3H.QuanLySinhVien.Mapper.LevelMapper;
import T3H.QuanLySinhVien.Mapper.TeacherMapper;
import T3H.QuanLySinhVien.Repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LevelService {
    @Autowired
    LevelRepository levelRepository;

    public List<LevelDto> getAllLevel()
    {
        List<LevelDto> list=new ArrayList<>();
        for (LevelDao levelDao:levelRepository.findAll())
        {
            list.add(LevelMapper.mapLevel(levelDao));
        }
        return list;

    }
}
