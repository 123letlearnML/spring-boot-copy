package duong.milion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Entity
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class RapChieuPhim {
    // da test xong

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer RCP_id;
    private Integer soPhongConLai;
    private Integer soPhongCoSan;
    private Integer soPhongDangDung;
    @OneToMany(mappedBy = "rapChieuPhim", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phong> phongs = new ArrayList<>();
//    private Integer viTriGheNgoi;
//    private Integer soChoDat;
//    private Integer soPhong;

    // Getter và Setter cho RCP_id
    public Integer getRCP_id() {
        return RCP_id;
    }

    public void setRCP_id(Integer RCP_id) {
        this.RCP_id = RCP_id;
    }

    // Getter và Setter cho soPhongConLai
    public Integer getSoPhongConLai() {
        return soPhongConLai;
    }

    public void setSoPhongConLai(Integer soPhongConLai) {
        this.soPhongConLai = soPhongConLai;
    }

    // Getter và Setter cho soPhongCoSan
    public Integer getSoPhongCoSan() {
        return soPhongCoSan;
    }

    public void setSoPhongCoSan(Integer soPhongCoSan) {
        this.soPhongCoSan = soPhongCoSan;
    }

    // Getter và Setter cho soPhongDangDung
    public Integer getSoPhongDangDung() {
        return soPhongDangDung;
    }

    public void setSoPhongDangDung(Integer soPhongDangDung) {
        this.soPhongDangDung = soPhongDangDung;
    }
}
