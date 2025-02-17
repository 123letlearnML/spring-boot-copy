package duong.milion.service;

import duong.milion.entity.Phong;
import duong.milion.repo.PhongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class PhongService {
    @Autowired
    PhongRepo phongRepo;

    public List<Phong>  phongGetAll()
    {

        return phongRepo.findAll();
    }

    public Phong getId(Integer id){
        return phongRepo.findById(id).orElseThrow(()->new RuntimeException("ko co phong co id la "+id));
    }


    public Phong postPhong(Phong phong){
        return  phongRepo.save(phong);
    }


    public Phong putPhong(Phong phong, Integer id){
        Phong phong1 = phongRepo.findById(id).orElseThrow(()-> new RuntimeException("PUT  ko co phong id la"+id));
        phong1.setSoChoDaDat(phong.getSoChoDaDat());
        phong1.setViTriGheNgoi(phong.getViTriGheNgoi());

        // Tính số chỗ ngồi còn lại: tổng số chỗ - số chỗ đã đặt
        phong1.setSoChoNgoiConLai(phong1.getSoChoNgoi() - phong1.getSoChoDaDat());


        // Nếu không còn chỗ, đánh dấu phòng đã được đặt
        if (phong1.getSoChoNgoiConLai() == 0 ) {
            phong1.setDaFullPhong(true);
        }
        if(phong1.getSoChoDaDat()>0 && phong1.getSoChoNgoiConLai()>0){
            phong1.setDaFullPhong(false);
            phong1.setDaDatCho(true);
        }
        return phong1;
    }


    public String phondelete(Integer id){
        phongRepo.deleteById(id);
        return "da xoa thanh cong id phong la "+id;
    }
}
