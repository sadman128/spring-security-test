package com.sajid.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        user.setMobile(userDto.mobile());

        user.setPassword(passwordEncoder.encode(userDto.password()));

        userRepository.save(user);
        IO.println("User added successfully");
    }

    public List<User> getUser(String username) {
        return userRepository.getUsersByUsername(username);
    }
}
