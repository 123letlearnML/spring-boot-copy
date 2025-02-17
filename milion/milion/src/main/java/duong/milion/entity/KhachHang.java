package duong.milion.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer khachHangId;
    private String khachHangGmail;
    private String khachHangPass;
    private String tenKhachHang;


    // Getter và Setter cho khachHangId
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


}
