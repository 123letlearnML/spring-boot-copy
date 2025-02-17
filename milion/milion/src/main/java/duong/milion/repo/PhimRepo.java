package duong.milion.repo;

import duong.milion.entity.Phim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface PhimRepo extends JpaRepository<Phim, Integer> {

}
