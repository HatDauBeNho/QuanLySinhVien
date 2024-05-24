package T3H.QuanLySinhVien.Repository;

import T3H.QuanLySinhVien.Entities.dao.InforDao;
import T3H.QuanLySinhVien.Entities.dao.LevelDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<LevelDao,Integer>
{
}
