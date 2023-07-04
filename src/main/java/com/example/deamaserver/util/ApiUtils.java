package com.example.deamaserver.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class ApiUtils {

    @Value("${api.ai-server}")
    private String aiServerAddress;

    public <T> ResponseEntity<T> requestApi(T returnType, MultipartFile file) {
        URI uri = UriComponentsBuilder
                .fromUriString(aiServerAddress)
                .encode()
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        //
        return new ResponseEntity<T>(HttpStatus.OK);
    }
}
