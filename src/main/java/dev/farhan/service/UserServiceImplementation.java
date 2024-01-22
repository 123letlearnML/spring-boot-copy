package dev.farhan.service;

import dev.farhan.minhlamdc.User;
import dev.farhan.minhlamdc.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
// su dung dc ow nhung noi khac
public class UserServiceImplementation implements   UserService{
    @Autowired
   private UserRepository userRepository;
    @Override
    public void registerUser(User user) {
//        User newUser = new User();
//        newUser.setId(user.getId());
//        newUser.setFirstname(user.getFirstname());
//        newUser.setLastname(user.getLastname());
//        newUser.setEmail(user.getEmail());
//        newUser.setPassword(user.getPassword());
//        User saveUser = userRepository.save(newUser);
        userRepository.save(user);

    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("người dùng ko tồn tại" + userId);

    }
    @Override
    public User followUser(Integer userId1, Integer userId2)throws Exception {
        User user1 = findUserById(userId1);
//        User user2 = findUserById(userId2);
//        user2.getFolowers().add(user1.getId());
        user1.getFolowers().add(user1.getId());
//        userRepository.save(user1);
        userRepository.save(user1);
        return user1;
    }

    @Override
    public User updateUser(User user,Integer userId) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new Exception("user ko tìm thấy" + userId);
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
        User updateUser = userRepository.save(oldUser);
        return updateUser;

    }
    // xong cái này rồi lúc load lại thì nó lỗi hết, xong bỏ comment vẫn lỗi tiếp
//    @Override
//    public List<User> searchUser(String query) {
//        return userRepository.searchUser(query);
//    }
}
