package duong.milion.dto.controller;


import duong.milion.dto.entity.KhachHangDto;
import duong.milion.dto.entity.PhimDto;
import duong.milion.dto.exception.ResourceNotFoundException;
import duong.milion.entity.KhachHang;
import duong.milion.jwt.JwtUtil;
import duong.milion.repo.KhachHangRepo;
import duong.milion.service.KhachHangService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("KhachHangDto")
public class KhachHangDtoController{

    // da chay duoc



    // doi mat khau , gmail

    @Autowired
    KhachHangService khachHangService;
    @Autowired
    KhachHangRepo khachHangRepo;
    @Autowired
JwtUtil jwtUtil;

@GetMapping("/all")
public ResponseEntity<List<KhachHangDto>> dsdto(){
    List<KhachHang> khachHangList = khachHangRepo.findAll();
    List<KhachHangDto> dtos = khachHangList.stream().map(khachHang -> {
KhachHangDto dto = new KhachHangDto();
        dto.setKhachHangId(khachHang.getKhachHangId());
        dto.setKhachHangGmail(khachHang.getKhachHangGmail());
        // Không trả về mật khẩu để bảo mật
        dto.setKhachHangPass(null);
        dto.setTenKhachHang(khachHang.getTenKhachHang());
        // Nếu cần, có thể set thêm các trường khác như trạng thái đăng nhập
        dto.setDaDangNhapChua(jwtUtil.daDangNhapChua(khachHang.getKhachHangGmail()));
        return dto;
    }).collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
}



    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody KhachHang khachHang) {
        String token = khachHangService.register(khachHang);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        String token = khachHangService.login(email, password);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @PutMapping("/changeDto/{id}")
    public ResponseEntity<?> thayDoiGmailVaMatKhau(@RequestBody KhachHangDto khachHangDto,
                                                              @PathVariable Integer id) {


        // Lấy khách hàng hiện có từ database, nếu không tìm thấy sẽ ném exception
        KhachHang existingKhachHang = khachHangRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với id: " + id));

        if (!jwtUtil.daDangNhapChua(existingKhachHang.getKhachHangGmail())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Hãy đăng nhập để sử dụng tính năng này"));
        }

        // Cập nhật email và mật khẩu (bạn có thể cập nhật các trường khác nếu cần)
        existingKhachHang.setKhachHangGmail(khachHangDto.getKhachHangGmail());
        existingKhachHang.setKhachHangPass(khachHangDto.getKhachHangPass());

        // Lưu lại thông tin cập nhật
        KhachHang updatedKhachHang = khachHangRepo.save(existingKhachHang);

        // Chuyển đổi entity thành DTO để trả về (có thể không trả về mật khẩu để bảo mật)
        KhachHangDto updatedDto = new KhachHangDto();
        updatedDto.setKhachHangId(updatedKhachHang.getKhachHangId());
        updatedDto.setKhachHangGmail(updatedKhachHang.getKhachHangGmail());
        // Nếu không muốn trả về mật khẩu cho phía client, có thể set là null:
        updatedDto.setKhachHangPass(null);
        updatedDto.setTenKhachHang(updatedKhachHang.getTenKhachHang());

        return ResponseEntity.ok(updatedDto);
    }


}
