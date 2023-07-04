package com.example.deamaserver.service;

import com.example.deamaserver.controller.dto.ListResponse;
import com.example.deamaserver.controller.dto.Response;
import com.example.deamaserver.entity.Emission;
import com.example.deamaserver.entity.EmissionRepository;
import com.example.deamaserver.entity.types.Category;
import com.example.deamaserver.controller.dto.CategoryAndArrayResponse;
import com.example.deamaserver.controller.dto.EmissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class EmissionService {

    private final EmissionRepository emissionRepository;


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

    public CategoryAndArrayResponse findByPhoto(MultipartFile file) {
        Category category = Category.PAPER;
        List<EmissionResponse> emissions = emissionRepository.getCategory(category);
        return CategoryAndArrayResponse.builder()
                .category(category.name())
                .emissionList(emissions).build();
    }

    public CategoryAndArrayResponse findByCategory(Category category) {
        List<EmissionResponse> emissions = emissionRepository.getCategory(category);
        return CategoryAndArrayResponse.builder()
                .category(category.name())
                .emissionList(emissions).build();
    }
}
