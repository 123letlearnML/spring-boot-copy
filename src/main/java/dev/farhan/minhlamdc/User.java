package dev.farhan.minhlamdc;

import dev.farhan.service.UserService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Entity
//cái này dùng để ánh xạ  đến cơ sở dữ liệu, nói thẳng ra là
// muốn cái nào liên quan đến dữ liệu thì dùng nó
// java sẽ tự động tạo 1 bảng là User trong mysql
@Table(name ="users")
///tạo 1 bảng(giống User nhưng thủ công) , đặt tên nó là bangthunhat
// ở đây @entity tạo 1 bảng và đặt tên nó là User, và @table có thể tùy chỉnh lại tên bảng,
//thiết lập cái bảng đó luôn, có rất nhiều cái có thể thiết lập và tên chỉ là 1 ví dụ

public class User {
    @Id          //khóa chính sẽ là interger id ở dưới
    // hàm @GeneratedValue: sẽ tạo tự đọng cho khóa chính luôn, tự tìm hiểu
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   //thay đổi tên cột từ first_name thành tên thứ nhất thôi, ko có gì cả
    //có thể làm tương tự với các thuộc tính kia
    private String firstname;
    private String lastname;
    private String email;
    private  String  password;
    private  List<Integer> folowers;

    private  List<Integer> folowings;
    private  String genter;

    public  User(){}
    //hay xóa cái generate id kia đi bạn nó ở useraays

    public User(Integer id, String firstname, String lastname, String email, String password, List<Integer> folowers, List<Integer> folowings, String genter) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.folowers = folowers;
        this.folowings = folowings;
        this.genter = genter;
    }

    public User(Integer id, String firstname, String lastname, String email, String password) {
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password +
                "}";
    }
    public List<Integer> getFolowers() {
        return folowers;
    }

    public void setFolowers(List<Integer> folowers) {
        this.folowers = folowers;
    }

    public List<Integer> getFolowings() {
        return folowings;
    }

    public void setFolowings(List<Integer> folowings) {
        this.folowings = folowings;
    }

    public String getGenter() {
        return genter;
    }

    public void setGenter(String genter) {
        this.genter = genter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
