package duong.milion.repo;

import duong.milion.entity.RapChieuPhim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RapChieuPhimRepo extends JpaRepository<RapChieuPhim, Integer> {


}
