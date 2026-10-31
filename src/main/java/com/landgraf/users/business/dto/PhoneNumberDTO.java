package com.landgraf.users.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumberDTO {

    private Long id;
    private String number;
}
