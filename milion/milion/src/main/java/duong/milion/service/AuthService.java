package duong.milion.service;

import duong.milion.entity.KhachHang;

public interface AuthService {
    String register(KhachHang khachHang);
    String login(String email, String password);
}
