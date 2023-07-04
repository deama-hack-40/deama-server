package com.example.deamaserver.service;

import com.example.deamaserver.controller.dto.ListResponse;
import com.example.deamaserver.controller.dto.Response;
import com.example.deamaserver.entity.Emission;
import com.example.deamaserver.entity.EmissionRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmissionService {

    private EmissionRepository emissionRepository;


    @Transactional(readOnly = true)
    public Response getById(Long id) {
        Emission emission = emissionRepository.findById(id).orElseThrow();
        return Response.builder()
                .title(emission.getTitle())
                .content(emission.getContent())
                .category(String.valueOf(emission.getCategory())).
                build();
    }

    @Transactional(readOnly = true)
    public List<ListResponse> getAllpost(ListResponse response) {
        List<Emission> emissions = emissionRepository.findAll();
        return emissions.stream().map(
                e-> ListResponse.builder()
                        .id(e.getId())
                        .title(e.getTitle())
                        .build()
        ).collect(Collectors.toList());
    }
}
