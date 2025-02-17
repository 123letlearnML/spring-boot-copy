package duong.milion.controller;

import duong.milion.entity.Phim;
import duong.milion.repo.PhimRepo;
import duong.milion.service.PhimService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Phim")
public class PhimController {


    @Autowired
    PhimService phimService;

    @Autowired
    PhimRepo phimRepo;
    private final String imageBasePath = "D:/image_movie/";


    @PutMapping("/{id}")
    public Phim updatePhim(@PathVariable Integer id, @RequestBody Phim phimDetail) {
        return phimService.updatePhim(id, phimDetail);
    }


    @DeleteMapping("/{id}")
    public String deletePhim(@PathVariable Integer id) {

        phimService.deletePhim(id);
        return "Đã xóa phim có id " + id;
    }

    @GetMapping("/all")
    public List<Phim> getAllPhim(){
        return  phimService.getAllPhim();

    }

    @GetMapping("/Phim/{id}")
    public Phim getById(@PathVariable Integer id){
        return phimService.GetPhimById(id);
    }

    @PostMapping("/post")
    public Phim PostPhim(@RequestBody Phim phim){
        return phimService.postPhim(phim);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Phim> getPhimById(@PathVariable Integer id) {
        Optional<Phim> phimOpt = phimRepo.findById(id);
        return phimOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping("/image/{id}")
    public ResponseEntity<?> getPhimImage(@PathVariable Integer id) throws IOException {
        // Tìm phim theo id
        Optional<Phim> phimOpt = phimRepo.findById(id);
        if (!phimOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy phim với id: " + id);
        }

        Phim phim = phimOpt.get();

        // Kiểm tra nếu trường ảnh chưa được set hoặc rỗng
        if (phim.getAnhPhim() == null || phim.getAnhPhim().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ảnh phim chưa được thiết lập cho phim với id: " + id);
        }

        // Ghép đường dẫn cơ sở với tên file ảnh
        Path imagePath = Paths.get(imageBasePath, phim.getAnhPhim());
        File imageFile = imagePath.toFile();

        // Kiểm tra nếu file ảnh không tồn tại hoặc không phải là file
        if (!imageFile.exists() || !imageFile.isFile()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("File ảnh không tồn tại hoặc không hợp lệ: " + phim.getAnhPhim());
        }

        // Đọc nội dung file ảnh thành mảng byte
        byte[] imageBytes = Files.readAllBytes(imagePath);
        MediaType mediaType = getMediaTypeForFileName(imageFile.getName());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imageFile.getName() + "\"")
                .contentType(mediaType)
                .body(imageBytes);
    }

    // Phương thức helper để xác định MediaType dựa vào phần mở rộng của file
    private MediaType getMediaTypeForFileName(String fileName) {
        String lowerFileName = fileName.toLowerCase();
        if (lowerFileName.endsWith(".jpg") || lowerFileName.endsWith(".jpeg")) {
            return MediaType.IMAGE_JPEG;
        } else if (lowerFileName.endsWith(".png")) {
            return MediaType.IMAGE_PNG;
        } else if (lowerFileName.endsWith(".gif")) {
            return MediaType.IMAGE_GIF;
        } else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }



    @PostMapping("/image/{id}")
    public ResponseEntity<String> uploadPhimImage(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        Optional<Phim> phimOpt = phimRepo.findById(id);
        if (!phimOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy phim với id: " + id);
        }

        Phim phim = phimOpt.get();

        // Lấy tên file gốc, làm sạch tên file
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // Xây dựng đường dẫn lưu file: D:/image_movie/{fileName}
        Path targetPath = Paths.get(imageBasePath, fileName);
        try {
            // Lưu file vào hệ thống file, ghi đè nếu file đã tồn tại
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi lưu file: " + fileName);
        }

        // Cập nhật trường anhPhim của entity Phim với tên file (hoặc có thể lưu đường dẫn)
        phim.setAnhPhim(fileName);
        phimRepo.save(phim);

        return ResponseEntity.ok("Upload ảnh thành công cho phim với id: " + id);
    }
}
