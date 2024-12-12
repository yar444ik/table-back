package dev.vorstu.services;

import dev.vorstu.dto.UserDTO;
import dev.vorstu.mappers.UserMapper;
import dev.vorstu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserDTO saveUser(UserDTO userDto) {
        return userMapper.toUserShow(
                userRepository.save(userMapper.toUserEntity(userDto))
        );
    }

    public boolean checkAvailableUsername(String username) {
        return userRepository.checkAvailableUsername(username);
    }


}