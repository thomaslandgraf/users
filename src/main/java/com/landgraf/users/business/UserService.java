package com.landgraf.users.business;

import com.landgraf.users.business.converter.UserConverter;
import com.landgraf.users.business.dto.UserDTO;
import com.landgraf.users.infrastructure.entity.User;
import com.landgraf.users.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDTO saveUser(UserDTO userDTO){
        User user = userConverter.forUser(userDTO);
        return userConverter.forUserDTO(userRepository.save(user));
    }
}
