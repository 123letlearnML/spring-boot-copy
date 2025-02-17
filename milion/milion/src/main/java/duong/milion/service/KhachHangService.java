package duong.milion.service;

import duong.milion.entity.KhachHang;
import duong.milion.jwt.JwtUtil;
import duong.milion.repo.KhachHangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class KhachHangService implements AuthService {

    @Autowired
    private KhachHangRepo khachHangRepo;

    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtils;


    public KhachHangService(KhachHangRepo khachHangRepo,
                            PasswordEncoder passwordEncoder,
                            JwtUtil jwtUtils) {
        this.khachHangRepo = khachHangRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String register(KhachHang khachHang) {
        if (khachHangRepo.findBykhachHangGmail(khachHang.getKhachHangGmail()).isPresent()){
            throw new RuntimeException("Email đã tồn tại!");
        }

        // Mã hoá mật khẩu trước khi lưu

        khachHang.setKhachHangPass(passwordEncoder.encode(khachHang.getKhachHangPass()));
        KhachHang saved = khachHangRepo.save(khachHang);

        // Tạo token dựa trên email
        return jwtUtils.generateTokenFromEmail(saved.getKhachHangGmail());
    }

    @Override
    public String login(String email, String password) {
        Optional<KhachHang> khOptional = khachHangRepo.findBykhachHangGmail(email);
        if (!khOptional.isPresent()){
            throw new RuntimeException("Tài khoản không tồn tại!");
        }
        KhachHang kh = khOptional.get();

        // So sánh mật khẩu đã mã hoá
        if (!passwordEncoder.matches(password, kh.getKhachHangPass())){
            throw new RuntimeException("Mật khẩu không đúng!");
        }

        return jwtUtils.generateTokenFromEmail(kh.getKhachHangGmail());
    }
}
