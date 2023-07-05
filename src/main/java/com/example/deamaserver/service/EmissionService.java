package com.example.deamaserver.service;

import com.example.deamaserver.controller.dto.request.EmissionRequest;
import com.example.deamaserver.controller.dto.response.ListResponse;
import com.example.deamaserver.controller.dto.response.Response;
import com.example.deamaserver.entity.Emission;
import com.example.deamaserver.entity.EmissionRepository;
import com.example.deamaserver.entity.types.Category;
import com.example.deamaserver.controller.dto.response.CategoryAndArrayResponse;
import com.example.deamaserver.controller.dto.response.EmissionResponse;
import com.example.deamaserver.exception.EmissionNotFoundException;
import com.example.deamaserver.util.ApiUtils;
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

    private final ApiUtils apiUtils;


    @Transactional(readOnly = true)
    public Response getById(Long id) {
        Emission emission = emissionRepository.findById(id).orElseThrow(()-> EmissionNotFoundException.EXCEPTION);
        return Response.builder()
                .id(emission.getId())
                .title(emission.getTitle())
                .content(emission.getContent())
                .category(String.valueOf(emission.getCategory())).
                build();
    }

    @Transactional(readOnly = true)
    public List<ListResponse> getAllPost(ListResponse response) {
        List<Emission> emissions = emissionRepository.findAll();
        return emissions.stream().map(
                e-> ListResponse.builder()
                        .id(e.getId())
                        .title(e.getTitle())
                        .build()
        ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryAndArrayResponse findByPhoto(MultipartFile file) {
        String categoryString = apiUtils.requestApi(file);
        Category category = Category.valueOf(categoryString);
        List<EmissionResponse> emissions = emissionRepository.getCategory(category);
        return CategoryAndArrayResponse.builder()
                .category(category.name())
                .emissionList(emissions).build();
    }

    @Transactional(readOnly = true)
    public CategoryAndArrayResponse findByCategory(Category category) {
        List<EmissionResponse> emissions = emissionRepository.getCategory(category);
        return CategoryAndArrayResponse.builder()
                .category(category.name())
                .emissionList(emissions).build();
    }

    public void saveEmission(EmissionRequest request) {
        emissionRepository.save(request.toEmission());
    }
}
