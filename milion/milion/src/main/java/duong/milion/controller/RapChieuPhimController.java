package duong.milion.controller;

import duong.milion.entity.RapChieuPhim;
import duong.milion.repo.RapChieuPhimRepo;
import duong.milion.service.RapChieuPhimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping("/RapChieuPhim")
public class RapChieuPhimController {
    @Autowired
    RapChieuPhimService rapChieuPhimService;

    @GetMapping("all")
    public List<RapChieuPhim> getall(){
        return  rapChieuPhimService.getAllCho();
    }

    @GetMapping("/get/{id}")
    public RapChieuPhim getById(@PathVariable Integer id){
        return rapChieuPhimService.getRapByid(id);
    }

    @PostMapping("/post")
    public  RapChieuPhim postRap(@RequestBody RapChieuPhim rapChieuPhim){
        return  rapChieuPhimService.postRap(rapChieuPhim);
    }

    @PutMapping("/put/{id}")
    public  RapChieuPhim putRap(@RequestBody RapChieuPhim rapChieuPhim, @PathVariable Integer id){
        return rapChieuPhimService.putRap(id, rapChieuPhim);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRapdi (@PathVariable Integer id){
         rapChieuPhimService.deleteRap(id);
         return "da xoa thanh cong phim co id" +id;
    }






}
