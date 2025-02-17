package duong.milion.service;

import duong.milion.entity.Phim;
import duong.milion.repo.PhimRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
@Service
@RequestMapping("/Phim")

public class PhimService {


    @Autowired
    PhimRepo phimRepo;


    public List<Phim> getAllPhim(){
       return phimRepo.findAll();

    }


    public Phim GetPhimById(Integer id){
Phim phim = phimRepo.findById(id).orElseThrow(()-> new RuntimeException("phim ko ton tai" +id));
        return phim;
    }

    public Phim postPhim(Phim phim){
        return phimRepo.save(phim);
    }

    public Phim updatePhim(Integer id, Phim phimDetail) {
        Phim phim = phimRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Phim không tồn tại với id: " + id));

        phim.setTenPhim(phimDetail.getTenPhim());
        phim.setGioChieuPhim(phimDetail.getGioChieuPhim());
        phim.setVePhim(phimDetail.getVePhim());
        phim.setGiaTienphim(phimDetail.getGiaTienphim());

        return phimRepo.save(phim);
    }


    public void deletePhim(Integer id) {
        Phim phim = phimRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Phim không tồn tại với id: " + id));
        phimRepo.delete(phim);
    }






}
