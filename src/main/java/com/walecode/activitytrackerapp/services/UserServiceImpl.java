package com.walecode.activitytrackerapp.services;


import com.walecode.activitytrackerapp.dto.UserDTO;
import com.walecode.activitytrackerapp.models.User;
import com.walecode.activitytrackerapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserDTO userDTO) {
         User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getPassword());
         return userRepository.save(user);
    }

    @Override
    public User findUser(UserDTO userDTO) {
        return userRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword()).orElse(null);
    }
}
