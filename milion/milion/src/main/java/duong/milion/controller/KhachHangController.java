package duong.milion.controller;

import duong.milion.entity.KhachHang;
import duong.milion.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
@Controller
@RestController
@RequestMapping("/KhachHang")
public class KhachHangController {

    // da chay duoc

@Autowired
    KhachHangService khachHangService;
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
}
