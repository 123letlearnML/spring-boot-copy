package duong.milion.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhimDto {
    private Integer phimId;
    private String tenPhim;
    private String gioChieuPhim;
    private Integer vePhim;
    private Float giaTienphim;
    private Boolean daDatVePhimChua;


    public PhimDto(Integer phimId, String tenPhim, String gioChieuPhim) {
        
    }

    public PhimDto(Integer phimId, String tenPhim, String gioChieuPhim, Integer soNguoiDiCungNhau, Float giaTienphim) {
    }

    public PhimDto(Integer phimId, String tenPhim, String gioChieuPhim, Integer soNguoiDiCungNhau, Float giaTienphim, Integer vePhim) {
    }

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

    // Getter và Setter cho daDatVePhimChua
    public Boolean getDaDatVePhimChua() {
        return daDatVePhimChua;
    }

    public void setDaDatVePhimChua(Boolean daDatVePhimChua) {
        this.daDatVePhimChua = daDatVePhimChua;
    }
}
