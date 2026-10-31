package com.landgraf.users.business.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private Long id;
    private String streetLine1;
    private String streetLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
