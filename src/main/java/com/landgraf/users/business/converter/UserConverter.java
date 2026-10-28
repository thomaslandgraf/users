package com.landgraf.users.business.converter;

import com.landgraf.users.business.dto.AddressDTO;
import com.landgraf.users.business.dto.PhoneNumberDTO;
import com.landgraf.users.business.dto.UserDTO;
import com.landgraf.users.infrastructure.entity.Address;
import com.landgraf.users.infrastructure.entity.PhoneNumber;
import com.landgraf.users.infrastructure.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {

    public User forUser(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .addresses(forListAddresses(userDTO.getAddresses()))
                .phoneNumber(forListPhoneNumber(userDTO.getPhoneNumber()))
                .build();
    }

    public List<Address> forListAddresses(List<AddressDTO> addressDTOList){
        return addressDTOList.stream().map(this::forAddress).toList();
    }

    public Address forAddress(AddressDTO addressDTO){
        return Address.builder()
                .state(addressDTO.getState())
                .city(addressDTO.getCity())
                .zipCode(addressDTO.getZipCode())
                .country(addressDTO.getCountry())
                .streetLine1(addressDTO.getStreetLine1())
                .streetLine2(addressDTO.getStreetLine2())
                .build();
    }

    public List<PhoneNumber> forListPhoneNumber(List<PhoneNumberDTO> phoneNumberDTOList){
        return phoneNumberDTOList.stream().map(this::forPhoneNumber).toList();
    }

    public PhoneNumber forPhoneNumber(PhoneNumberDTO phoneNumberDTO){
        return PhoneNumber.builder()
                .number(phoneNumberDTO.getNumber())
                .build();
    }


    public UserDTO forUserDTO(User user){
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .addresses(forListAddressesDTO(user.getAddresses()))
                .phoneNumber(forListPhoneNumberDTO(user.getPhoneNumber()))
                .build();
    }

    public List<AddressDTO> forListAddressesDTO(List<Address> addressList){
        return addressList.stream().map(this::forAddressDTO).toList();
    }

    public AddressDTO forAddressDTO(Address address){
        return AddressDTO.builder()
                .state(address.getState())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .country(address.getCountry())
                .streetLine1(address.getStreetLine1())
                .streetLine2(address.getStreetLine2())
                .build();
    }

    public List<PhoneNumberDTO> forListPhoneNumberDTO(List<PhoneNumber> phoneNumberList){
        return phoneNumberList.stream().map(this::forPhoneNumberDTO).toList();
    }

    public PhoneNumberDTO forPhoneNumberDTO(PhoneNumber phoneNumber){
        return PhoneNumberDTO.builder()
                .number(phoneNumber.getNumber())
                .build();
    }

    public User updateUserData(User entity, UserDTO userDTO){
        return User.builder()
                .id(entity.getId())
                .name(userDTO.getName() != null ? userDTO.getName() : entity.getName())
                .password(userDTO.getPassword() != null ? userDTO.getPassword() : entity.getPassword())
                .email(userDTO.getEmail() != null ? userDTO.getEmail() : entity.getEmail())
                .addresses(entity.getAddresses())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }
}
