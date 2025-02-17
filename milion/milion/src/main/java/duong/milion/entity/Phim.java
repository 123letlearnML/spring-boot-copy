package duong.milion.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Phim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer phimId;
    private String tenPhim;
    private String gioChieuPhim;
    private Integer vePhim;
    private Float giaTienphim;
    private Boolean daDatVePhimChua;

    private Integer soNguoiDiCungNhau;

    private String anhPhim;

    public String getAnhPhim() {
        return anhPhim;
    }

    public void setAnhPhim(String anhPhim) {
        this.anhPhim = anhPhim;
    }


    public Boolean getDaDatVePhimChua() {
        return daDatVePhimChua;
    }

    public void setDaDatVePhimChua(Boolean daDatVePhimChua) {
        this.daDatVePhimChua = daDatVePhimChua;
    }

    public Integer getSoNguoiDiCungNhau() {
        return soNguoiDiCungNhau;
    }

    public void setSoNguoiDiCungNhau(Integer soNguoiDiCungNhau) {
        this.soNguoiDiCungNhau = soNguoiDiCungNhau;
    }



    // Getter và Setter cho phimId
    public Integer getPhimId() {
        return phimId;
    }

    public void setPhimId(Integer phimId) {
        this.phimId = phimId;
    }

    // Getter và Setter cho tenPhim
    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    // Getter và Setter cho gioChieuPhim
    public String getGioChieuPhim() {
        return gioChieuPhim;
    }

    public void setGioChieuPhim(String gioChieuPhim) {
        this.gioChieuPhim = gioChieuPhim;
    }

    // Getter và Setter cho vePhim
    public Integer getVePhim() {
        return vePhim;
    }

    public void setVePhim(Integer vePhim) {
        this.vePhim = vePhim;
    }

    // Getter và Setter cho giaTienphim
    public Float getGiaTienphim() {
        return giaTienphim;
    }

    public void setGiaTienphim(Float giaTienphim) {
        this.giaTienphim = giaTienphim;
    }

}
