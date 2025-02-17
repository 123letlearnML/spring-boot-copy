package duong.milion.dto.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class KhachHangDto {
    // giao dien cua khach hang
    // khach co the dat ve, xem ve nao duoc dat,khi dat xong se gui gmail ve de thong bao
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
    private Integer khachHangId;
    private String khachHangGmail;
    private String khachHangPass;
    private String tenKhachHang;
    private Boolean daDangNhapChua;
    public Integer getKhachHangId() {
        return khachHangId;
    }
    public void setKhachHangId(Integer khachHangId) {
        this.khachHangId = khachHangId;
    }

    // Getter và Setter cho khachHangGmail
    public String getKhachHangGmail() {
        return khachHangGmail;
    }
    public void setKhachHangGmail(String khachHangGmail) {
        this.khachHangGmail = khachHangGmail;
    }

    // Getter và Setter cho khachHangPass
    public String getKhachHangPass() {
        return khachHangPass;
    }
    public void setKhachHangPass(String khachHangPass) {
        this.khachHangPass = khachHangPass;
    }

    // Getter và Setter cho tenKhachHang
    public String getTenKhachHang() {
        return tenKhachHang;
    }
    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    // Getter và Setter cho daDangNhapChua
    public Boolean getDaDangNhapChua() {
        return daDangNhapChua;
    }
    public void setDaDangNhapChua(Boolean daDangNhapChua) {
        this.daDangNhapChua = daDangNhapChua;
    }


}
