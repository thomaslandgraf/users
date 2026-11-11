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

    public User toUser(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .addresses(userDTO.getAddresses() != null ? toListAddresses(userDTO.getAddresses()) : null)
                .phoneNumber(userDTO.getPhoneNumber() != null ? toListPhoneNumber(userDTO.getPhoneNumber()) : null)
                .build();
    }

    public List<Address> toListAddresses(List<AddressDTO> addressDTOList){
        return addressDTOList.stream().map(this::toAddress).toList();
    }

    public Address toAddress(AddressDTO addressDTO){
        return Address.builder()
                .state(addressDTO.getState())
                .city(addressDTO.getCity())
                .zipCode(addressDTO.getZipCode())
                .country(addressDTO.getCountry())
                .streetLine1(addressDTO.getStreetLine1())
                .streetLine2(addressDTO.getStreetLine2())
                .build();
    }

    public List<PhoneNumber> toListPhoneNumber(List<PhoneNumberDTO> phoneNumberDTOList){
        return phoneNumberDTOList.stream().map(this::toPhoneNumber).toList();
    }

    public PhoneNumber toPhoneNumber(PhoneNumberDTO phoneNumberDTO){
        return PhoneNumber.builder()
                .number(phoneNumberDTO.getNumber())
                .build();
    }


    public UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .addresses(user.getAddresses() != null ? toListAddressesDTO(user.getAddresses()) : null)
                .phoneNumber(user.getPhoneNumber() != null ? toListPhoneNumberDTO(user.getPhoneNumber()) : null)
                .build();
    }

    public List<AddressDTO> toListAddressesDTO(List<Address> addressList){
        return addressList.stream().map(this::toAddressDTO).toList();
    }

    public AddressDTO toAddressDTO(Address address){
        return AddressDTO.builder()
                .id(address.getId())
                .state(address.getState())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .country(address.getCountry())
                .streetLine1(address.getStreetLine1())
                .streetLine2(address.getStreetLine2())
                .build();
    }

    public List<PhoneNumberDTO> toListPhoneNumberDTO(List<PhoneNumber> phoneNumberList){
        return phoneNumberList.stream().map(this::toPhoneNumberDTO).toList();
    }

    public PhoneNumberDTO toPhoneNumberDTO(PhoneNumber phoneNumber){
        return PhoneNumberDTO.builder()
                .id(phoneNumber.getId())
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

    public Address updateUserAddress(Address entity, AddressDTO addressDTO){
        return Address.builder()
                .id(entity.getId())
                .state(addressDTO.getState() != null ? addressDTO.getState() : entity.getState())
                .city(addressDTO.getCity() != null ? addressDTO.getCity() : entity.getCity())
                .zipCode(addressDTO.getZipCode() != null ? addressDTO.getZipCode() : entity.getZipCode())
                .country(addressDTO.getCountry() != null ? addressDTO.getCountry() : entity.getCountry())
                .streetLine1(addressDTO.getStreetLine1() != null ? addressDTO.getStreetLine1() : entity.getStreetLine1())
                .streetLine2(addressDTO.getStreetLine2() != null ? addressDTO.getStreetLine2() : entity.getStreetLine2())
                .userId(addressDTO.getId() != null ? addressDTO.getId() : entity.getUserId())
                .build();
    }

    public PhoneNumber updateUserPhoneNumber(PhoneNumber entity, PhoneNumberDTO phoneNumberDTO){
        return PhoneNumber.builder()
                .id(entity.getId())
                .number(phoneNumberDTO.getNumber() != null ? phoneNumberDTO.getNumber() : entity.getNumber())
                .userId(phoneNumberDTO.getId() != null ? phoneNumberDTO.getId() : entity.getUserId())
                .build();
    }

    public Address toAddressEntity(AddressDTO addressDTO, Long userId){
        return Address.builder()
                .state(addressDTO.getState())
                .city(addressDTO.getCity())
                .zipCode(addressDTO.getZipCode())
                .country(addressDTO.getCountry())
                .streetLine1(addressDTO.getStreetLine1())
                .streetLine2(addressDTO.getStreetLine2())
                .userId(userId)
                .build();
    }

    public PhoneNumber toPhoneNumberEntity(PhoneNumberDTO phoneNumberDTO, Long userId){
        return PhoneNumber.builder()
                .number(phoneNumberDTO.getNumber())
                .userId(userId)
                .build();
    }
}
