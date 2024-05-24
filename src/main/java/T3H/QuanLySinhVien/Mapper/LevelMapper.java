package T3H.QuanLySinhVien.Mapper;

import T3H.QuanLySinhVien.Entities.dao.LevelDao;
import T3H.QuanLySinhVien.Converter.LevelConverter;
import T3H.QuanLySinhVien.Entities.dto.LevelDto;

public class LevelMapper {
    public static LevelDto mapLevel(LevelDao levelDao)
    {
        return new LevelDto(
          levelDao.getLevel_id(),
          levelDao.getLevel_name(),
          levelDao.getCreated_at(),
          levelDao.getUpdated_at()
        );
    }
}
