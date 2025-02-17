package duong.milion.service;



import duong.milion.entity.Phong;
import duong.milion.entity.RapChieuPhim;
import duong.milion.repo.RapChieuPhimRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class RapChieuPhimService {
    @Autowired
    RapChieuPhimRepo rapChieuPhimRepo;

    public List<RapChieuPhim> getAllCho() {
        return rapChieuPhimRepo.findAll();
    }

    public RapChieuPhim getRapByid(Integer id)
    {
        RapChieuPhim rapChieuPhim = rapChieuPhimRepo.findById(id).orElseThrow(()-> new RuntimeException("ko co cho ngoi nao co id la "+id));
        return rapChieuPhim;
    }

    public RapChieuPhim postRap(RapChieuPhim rapChieuPhim){
        return  rapChieuPhimRepo.save(rapChieuPhim);
    }

    public RapChieuPhim putRap(Integer id, RapChieuPhim rapChieuPhim){
        RapChieuPhim rapChieuPhim1 = rapChieuPhimRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("rạp chiếu phim không tồn tại với id: " + id));

//        private Integer RCP_id;
//        private Integer soPhongConLai;
//        private Integer soPhongCoSan;
//        private Integer soPhongDangDung;

        rapChieuPhim1.setSoPhongConLai(0);
    return rapChieuPhimRepo.save(rapChieuPhim1);

    }

    public void deleteRap(Integer id) {
        RapChieuPhim phim = rapChieuPhimRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rap chieu phim không tồn tại với id: " + id));
        rapChieuPhimRepo.delete(phim);
    }




}
