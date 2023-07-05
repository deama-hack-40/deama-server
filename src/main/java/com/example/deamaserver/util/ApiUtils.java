package com.example.deamaserver.util;

import com.example.deamaserver.controller.dto.AIRequestDto;
import com.example.deamaserver.util.s3.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Component
public class ApiUtils {

    @Value("${api.ai-server}")
    private String aiServerAddress;

    private final S3Util s3Util;

    public String requestApi(MultipartFile file) {
        String filePath = s3Util.uploadImage(file);
        System.out.println(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("image", file);

        URI uri = UriComponentsBuilder
                .fromUriString(aiServerAddress)
                .path("/upload_photo/")
                .encode()
//                .queryParam("image", filePath)
                .build()
                .toUri();
//        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        HttpEntity<AIRequestDto> request = new HttpEntity<>(new AIRequestDto(filePath), headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> type = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
//        ResponseEntity<String> type = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
        if (type.getStatusCode().is2xxSuccessful()) {
            System.out.println(type.getBody());
        } else {
            System.out.println("error");
        }
        return type.getBody();
    }
}
