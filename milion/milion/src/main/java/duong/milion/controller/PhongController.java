package duong.milion.controller;

import duong.milion.entity.Phong;
import duong.milion.service.PhongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping("/Phong")
public class PhongController {

@Autowired
    PhongService phongService;

@GetMapping("/all")
    public List<Phong> phongList(){
    return phongService.phongGetAll();
}

@GetMapping("/getId/{id}")
    public Phong phong (@PathVariable Integer id){
    return phongService.getId(id);


}

@PostMapping("/post")
    public Phong phong (@RequestBody Phong phong){
    return phongService.postPhong(phong);
}


@PutMapping("/put/{id}")
    public Phong phongPut(@RequestBody Phong phong, @PathVariable Integer id ){
    return phongService.putPhong(phong, id);
}

@DeleteMapping("/delete/{id}")
    public  String phongdelete (@PathVariable Integer id){
   return phongService.phondelete(id);
}



}
