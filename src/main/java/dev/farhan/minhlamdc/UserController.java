package dev.farhan.minhlamdc;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.farhan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        //        List<User> users = userRepository.findAll();
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }
@GetMapping("/data")
public List<User> getdata(){
        return  userRepository.findAll();
}



    @GetMapping("/users/{userId}")//userId ở đây chỉ là tên biến thôi, đại diện cho id thôi
    public User getUserById(@PathVariable("userId") int id) throws Exception {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return user;
        }
        throw new Exception("ko tim dc " );
    }
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @PostMapping("/users")
    public User user(@RequestBody User user){
        //User user = new User( user.getId(),user.getFirstname(),user.getLastname(),user.getEmail(),user.getPassword());
        userRepository.save(user);
        return user;

    }
@GetMapping("/search")
   public List<String> findPasswordsStartingWith(@Param("kytu") String kytu){
        List<String> listPassword = userRepository.findPasswordsStartingWith(kytu);
        return listPassword;
    }


    @PutMapping("/users/{userId}")
    public String updateUser(@RequestBody User user, @PathVariable("userId") Integer userId) throws Exception {
                Optional<User> optionalUser = userRepository.findById(userId);

                if (optionalUser.isEmpty()) {
//                    throw new Exception("user ko tìm thấy" + userId);
                    return "user is empty id: " +userId  ;
                }
                User oldUser = optionalUser.get();

                if (user.getFirstname() != null) {
                    oldUser.setFirstname(user.getFirstname());
                }
                if (user.getLastname() != null) {
                    oldUser.setLastname(user.getLastname());
                }
                if (user.getEmail() != null) {
                    oldUser.setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    oldUser.setPassword(user.getPassword());
                }
                if(user.getGenter()!= null){
                    oldUser.setGenter(user.getGenter());
                }
                User updateUser = userRepository.save(oldUser);
        ObjectMapper objectMapper = new ObjectMapper();

                return objectMapper.writeValueAsString(oldUser);
    }
    @PutMapping("/users/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1,@PathVariable Integer userId2) throws Exception {
        User user = userService.followUser(userId1,userId2);
        return user;
    }
        @DeleteMapping("users/{userId}")
        public String deleteUser(@PathVariable Integer userId) throws Exception {
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) {
                throw new Exception("ko cos nguoi dung voi" + userId);
            }
            userRepository.delete(user.get());
            return "user duoc xoa thganh cong" + userId;
        }

}

