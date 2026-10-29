package com.landgraf.users.business;

import com.landgraf.users.business.converter.UserConverter;
import com.landgraf.users.business.dto.AddressDTO;
import com.landgraf.users.business.dto.PhoneNumberDTO;
import com.landgraf.users.business.dto.UserDTO;
import com.landgraf.users.infrastructure.entity.Address;
import com.landgraf.users.infrastructure.entity.PhoneNumber;
import com.landgraf.users.infrastructure.entity.User;
import com.landgraf.users.infrastructure.exceptions.ConflictException;
import com.landgraf.users.infrastructure.exceptions.ResourceNotFoundException;
import com.landgraf.users.infrastructure.repository.AddressRepository;
import com.landgraf.users.infrastructure.repository.PhoneNumberRepository;
import com.landgraf.users.infrastructure.repository.UserRepository;
import com.landgraf.users.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserDTO saveUser(UserDTO userDTO){
        emailExist(userDTO.getEmail());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userConverter.forUser(userDTO);
        return userConverter.forUserDTO(userRepository.save(user));
    }

    public void emailExist(String email){
        try{
            boolean emailExist = verifyEmailExists(email);
            if(emailExist){
                throw new ConflictException("Email already exists. " + email);
            }
        } catch (ConflictException e){
            throw new ConflictException("Email already exists.", e.getCause());
        }
    }

    public boolean verifyEmailExists(String email){
        return userRepository.existsByEmail(email);
    }

    public UserDTO findUserByEmail(String email){
        try {
            return userConverter.forUserDTO(userRepository.findByEmail(email).orElseThrow(
                    () -> new ResourceNotFoundException("Email not found.")));
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Email not found.");
        }

    }

    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }

    public UserDTO updateUserData(String token, UserDTO userDTO){
        String email = jwtUtil.extractUsername(token.substring(7));
        userDTO.setPassword(userDTO.getPassword() != null ? passwordEncoder.encode(userDTO.getPassword()) : null);
        User userEntity = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email not Found."));

        User user = userConverter.updateUserData(userEntity, userDTO);
        return userConverter.forUserDTO(userRepository.save(user));
    }

    public AddressDTO updateUserAddress(Long addressId, AddressDTO addressDTO){
        Address entity = addressRepository.findById(addressId).orElseThrow(
                () -> new ResourceNotFoundException("Address not found with id: " + addressId)
        );

        Address address = userConverter.updateUserAddress(entity, addressDTO);
        return userConverter.forAddressDTO(addressRepository.save(address));
    }

    public PhoneNumberDTO updateUserPhoneNumber(Long phoneNumberId, PhoneNumberDTO phoneNumberDTO){
        PhoneNumber entity = phoneNumberRepository.findById(phoneNumberId).orElseThrow(
                () -> new ResourceNotFoundException("Phone number not found with id: " + phoneNumberId)
        );

        PhoneNumber phoneNumber = userConverter.updateUserPhoneNumber(entity, phoneNumberDTO);
        return userConverter.forPhoneNumberDTO(phoneNumberRepository.save(phoneNumber));
    }
}

