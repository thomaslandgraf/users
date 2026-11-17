package com.landgraf.users.business;

import com.landgraf.users.infrastructure.client.ZippopotamUSClient;
import com.landgraf.users.infrastructure.client.ZippopotamUSDTO;
import com.landgraf.users.infrastructure.exceptions.InvalidZipCodeException;
import com.landgraf.users.infrastructure.exceptions.ResourceNotFoundException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ZippopotamUSService {

    private final ZippopotamUSClient zippopotamUSClient;

    public ZippopotamUSDTO getAddressByZipCode(String zipCode){
        String zip = processZipCode(zipCode);
        try {
            return zippopotamUSClient.getAddressByZipCode(zip);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Zip code not found: " + zip);
        }
    }

    private String processZipCode(String zipCode){
        String zipCodeFormated = zipCode.replace(" ", "")
                .replace("-", "");

        if(!zipCodeFormated.matches("\\d{5}")){
            throw new InvalidZipCodeException("Zip code invalid. " + zipCode);
        }

        return zipCodeFormated;
    }

}
