package duong.milion.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import duong.milion.entity.KhachHang;

@Repository
public interface KhachHangRepo extends JpaRepository<KhachHang, Integer> {
    Optional<KhachHang> findBykhachHangGmail(String email);
}
