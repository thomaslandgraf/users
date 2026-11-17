package com.landgraf.users.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zippopotamUS", url = "${zippopotamUS.url}")
public interface ZippopotamUSClient {

    @GetMapping("/{zip}")
    ZippopotamUSDTO getAddressByZipCode(@PathVariable("zip") String zip);
}
