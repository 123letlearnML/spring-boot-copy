package duong.milion.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RapChieuPhimDto {
    // xem cho ngoi nao da duoc dat, khi dat xong se gui gmail cho khach hang nhu ben phim


    private Integer RCP_id;
    private Integer soChoNgoiConLai;
    private Integer viTriGheNgoi;
    private Integer soChoDat;
    private Boolean daDatChoNgoiChua;

    public SeatType getSeatType() {
        if (viTriGheNgoi >= 1 && viTriGheNgoi <= 30) {
            return SeatType.VIP;
        } else if (viTriGheNgoi >= 31 && viTriGheNgoi <= 60) {
            return SeatType.THUONG;
        } else if (viTriGheNgoi >= 61 && viTriGheNgoi <= 90) {
            return SeatType.GAN_MAN_HINH;
        } else {
            throw new IllegalArgumentException("Vị trí ghế không hợp lệ: " + viTriGheNgoi);
        }
    }

    public Integer getRCP_id() {
        return RCP_id;
    }

    public void setRCP_id(Integer RCP_id) {
        this.RCP_id = RCP_id;
    }

    // Getter và Setter cho soChoNgoiConLai
    public Integer getSoChoNgoiConLai() {
        return soChoNgoiConLai;
    }

    public void setSoChoNgoiConLai(Integer soChoNgoiConLai) {
        this.soChoNgoiConLai = soChoNgoiConLai;
    }

    // Getter và Setter cho viTriGheNgoi
    public Integer getViTriGheNgoi() {
        return viTriGheNgoi;
    }

    public void setViTriGheNgoi(Integer viTriGheNgoi) {
        this.viTriGheNgoi = viTriGheNgoi;
    }

    // Getter và Setter cho soChoDat
    public Integer getSoChoDat() {
        return soChoDat;
    }

    public void setSoChoDat(Integer soChoDat) {
        this.soChoDat = soChoDat;
    }

    // Getter và Setter cho daDatChoNgoiChua
    public Boolean getDaDatChoNgoiChua() {
        return daDatChoNgoiChua;
    }

    public void setDaDatChoNgoiChua(Boolean daDatChoNgoiChua) {
        this.daDatChoNgoiChua = daDatChoNgoiChua;
    }
}
