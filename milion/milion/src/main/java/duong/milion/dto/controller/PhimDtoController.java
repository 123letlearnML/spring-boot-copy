package duong.milion.dto.controller;

import duong.milion.dto.entity.PhimDto;
import duong.milion.dto.exception.ResourceNotFoundException;
import duong.milion.entity.Phim;
import duong.milion.repo.PhimRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/PhimDto")
public class PhimDtoController {
// phim nao dang chieu, sap chieu va se chieu
    @Autowired
    PhimRepo phimRepo;


    // mặc định mỗi trang là 5 phim

    @GetMapping("/phimDto")
    public ResponseEntity<Page<PhimDto>> getPhimPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {  // mặc định mỗi trang là 5 bản ghi
        Pageable pageable = PageRequest.of(page, size);
        Page<Phim> phimList = phimRepo.findAll(pageable);
        List<PhimDto> phimDTOList = phimList.stream()
                .map(phim -> new PhimDto(
                     phim.getPhimId(),
                        phim.getTenPhim(),
                        phim.getGioChieuPhim(),
                        phim.getSoNguoiDiCungNhau(),
                        phim.getGiaTienphim(),
                        phim.getVePhim()
                ))
                .collect(Collectors.toList());
        Page<PhimDto> phimDTOPage = new PageImpl<>(phimDTOList, pageable, phimList.getTotalElements());
        return new ResponseEntity<>(phimDTOPage, HttpStatus.OK);
    }

    @GetMapping("/phimDto/{id}")
    public ResponseEntity<PhimDto> getPhimByiddto(@PathVariable Integer id){
Phim phim = phimRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("ko tìm thấy phim có id la" +id));
        PhimDto phimDto = new PhimDto(
                phim.getPhimId(),
                phim.getTenPhim(),
                phim.getGioChieuPhim(),
                phim.getSoNguoiDiCungNhau(),
                phim.getGiaTienphim(),
                phim.getVePhim()
        );
        return ResponseEntity.ok(phimDto);
    }
}
