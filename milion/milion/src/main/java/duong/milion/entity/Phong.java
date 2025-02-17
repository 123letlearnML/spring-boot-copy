package duong.milion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Slf4j
@AllArgsConstructor
@NoArgsConstructor

public class Phong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // co them thuoc tinh la thoi gian dat phong
    private Integer PhongId;

    // Ví dụ: vị trí ghế có thể là "A1", "A2", "B1",...
    @ElementCollection
    private List<String> viTriGheNgoi;

    // Cờ đánh dấu ghế đã được đặt hay chưa
    private Boolean daFullPhong = false;
    private Boolean daDatCho = false;
    private Integer soChoNgoi;
    private Integer soChoNgoiConLai;
    private Integer soChoDaDat;



    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rap_id")
    private RapChieuPhim rapChieuPhim;

    // Getters và Setters



    public List<String> getViTriGheNgoi() {
        return viTriGheNgoi;
    }

    public void setViTriGheNgoi(List<String> viTriGheNgoi) {
        this.viTriGheNgoi = viTriGheNgoi;
    }

    public Integer getPhongId() {
        return PhongId;
    }

    public void setPhongId(Integer phongId) {
        this.PhongId = phongId;
    }



    public Boolean getDaFullPhong() {
        return daFullPhong;
    }

    public void setDaFullPhong(Boolean daFullPhong) {
        this.daFullPhong = daFullPhong;
    }

    public Boolean getDaDatCho() {
        return daDatCho;
    }

    public void setDaDatCho(Boolean daDatCho) {
        this.daDatCho = daDatCho;
    }


    public Integer getSoChoNgoiConLai() {
        return soChoNgoiConLai;
    }

    public void setSoChoNgoiConLai(Integer soChoNgoiConLai) {
        this.soChoNgoiConLai = soChoNgoiConLai;
    }

    public Integer getSoChoDaDat() {
        return soChoDaDat;
    }

    public void setSoChoDaDat(Integer soChoDaDat) {
        this.soChoDaDat = soChoDaDat;
    }

    public RapChieuPhim getRapChieuPhim() {
        return rapChieuPhim;
    }

    public void setRapChieuPhim(RapChieuPhim rapChieuPhim) {
        this.rapChieuPhim = rapChieuPhim;
    }
    public Integer getSoChoNgoi() {
        return soChoNgoi;
    }

    public void setSoChoNgoi(Integer soChoNgoi) {
        this.soChoNgoi = soChoNgoi;
    }
}
