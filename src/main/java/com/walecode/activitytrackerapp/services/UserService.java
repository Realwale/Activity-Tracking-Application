package com.walecode.activitytrackerapp.services;


import com.walecode.activitytrackerapp.dto.UserDTO;
import com.walecode.activitytrackerapp.models.User;

public interface UserService {

    User save(UserDTO userDTO);

    User findUser(UserDTO userDTO);
}
