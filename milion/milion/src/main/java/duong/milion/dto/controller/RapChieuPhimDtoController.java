package duong.milion.dto.controller;

import duong.milion.dto.entity.RapChieuPhimDto;

import duong.milion.dto.entity.SeatType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/RapChieuPhimDto")
public class RapChieuPhimDtoController {



    @PostMapping("/datChoNgoiDto")
    public ResponseEntity<?> datchoRapchieu(@RequestBody RapChieuPhimDto dto ){
        if (dto.getSoChoDat() == null || dto.getViTriGheNgoi() == null || dto.getSoChoNgoiConLai() == null) {
            return ResponseEntity.badRequest().body("Thiếu thông tin đặt chỗ");
        }


        if(dto.getSoChoDat() > dto.getSoChoNgoiConLai()){
            return ResponseEntity.badRequest().body("Số chỗ đặt vượt quá số chỗ còn lại");
        }

        SeatType seatType = dto.getSeatType();
        int pricePerSeat;
        switch (seatType) {
            case VIP:
                pricePerSeat = 100;
                break;
            case THUONG:
                pricePerSeat = 50;
                break;
            case GAN_MAN_HINH:
                pricePerSeat = 30;
                break;
            default:
                return ResponseEntity.badRequest().body("Loại ghế không xác định");
        }

        int tongTien = pricePerSeat * dto.getSoChoDat();

        // Cập nhật số chỗ còn lại sau khi đặt
        dto.setSoChoNgoiConLai(dto.getSoChoNgoiConLai() - dto.getSoChoDat());
        // Đánh dấu rằng đã đặt chỗ thành công
        dto.setDaDatChoNgoiChua(true);

        // Chuẩn bị dữ liệu trả về
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Đặt chỗ thành công");
        response.put("soChoDat", dto.getSoChoDat());
        response.put("seatType", seatType);
        response.put("tongTien", tongTien);
        response.put("soChoNgoiConLai", dto.getSoChoNgoiConLai());
        return ResponseEntity.ok(response);
    }
}
