package com.clament.czx.service;
import org.springframework.util.DigestUtils;
import com.clament.czx.DAO.UserRepository;
import com.clament.czx.DataEntity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user=userRepository.findByUsernameAndPassword(username,DigestUtils.md5DigestAsHex(password.getBytes()));
        return user;
    }
}
