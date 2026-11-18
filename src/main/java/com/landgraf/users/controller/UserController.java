package com.landgraf.users.controller;

import com.landgraf.users.business.UserService;
import com.landgraf.users.business.ZippopotamUSService;
import com.landgraf.users.business.dto.AddressDTO;
import com.landgraf.users.business.dto.PhoneNumberDTO;
import com.landgraf.users.business.dto.UserDTO;
import com.landgraf.users.infrastructure.client.ZippopotamUSDTO;
import com.landgraf.users.infrastructure.security.JwtUtil;
import com.landgraf.users.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User register, User update, User searches")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UserController {

    private final UserService userService;
    private final ZippopotamUSService zippopotamUSService;

    @PostMapping
    @Operation(summary = "Save a user", description = "Create a new user.")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.login(userDTO));
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String token){
        userService.deleteUser(token);
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

    @GetMapping("/address")
    public ResponseEntity<ZippopotamUSDTO> getAddressByZipCode(@RequestParam("zip") String zip){
        return ResponseEntity.ok(zippopotamUSService.getAddressByZipCode(zip));
    }
}
