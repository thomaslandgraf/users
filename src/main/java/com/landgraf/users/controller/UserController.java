package com.landgraf.users.controller;

import com.landgraf.users.business.UserService;
import com.landgraf.users.business.dto.AddressDTO;
import com.landgraf.users.business.dto.PhoneNumberDTO;
import com.landgraf.users.business.dto.UserDTO;
import com.landgraf.users.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(),
                        userDTO.getPassword())
        );

        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteByEmail(@RequestParam("email") String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public  ResponseEntity<UserDTO> updateUserData(@RequestBody UserDTO userDTO,
                                                   @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updateUserData(token, userDTO));
    }

    @PutMapping("/address")
    public ResponseEntity<AddressDTO> updateUserAddress(@RequestBody AddressDTO addressDTO,
                                                        @RequestParam("id") Long id){
        return ResponseEntity.ok(userService.updateUserAddress(id,addressDTO));
    }

    @PutMapping("/phone_number")
    public ResponseEntity<PhoneNumberDTO> updateUserPhoneNumber(@RequestBody PhoneNumberDTO phoneNumberDTO,
                                                        @RequestParam("id") Long id){
        return ResponseEntity.ok(userService.updateUserPhoneNumber(id,phoneNumberDTO));
    }

    @PostMapping("/address")
    public ResponseEntity<AddressDTO> registerAddress(@RequestBody AddressDTO addressDTO,
                                                      @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.registerAddress(addressDTO, token));
    }

    @PostMapping("/phone_number")
    public ResponseEntity<PhoneNumberDTO> registerPhoneNumber(@RequestBody PhoneNumberDTO phoneNumberDTO,
                                                              @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.registerPhoneNumber(phoneNumberDTO, token));
    }
}
