package dev.farhan.service;

import dev.farhan.minhlamdc.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {
     void registerUser (User user);
     User findUserById(Integer id)throws Exception;

     User followUser(Integer userId1, Integer userId2)throws  Exception;
     User updateUser(User user,Integer userId)throws Exception;
     //xong comment code duoưới này
//     List<User> searchUser(String query);

}
