package com.example.deamaserver.service;

import com.example.deamaserver.entity.Emission;
import com.example.deamaserver.entity.EmissionRepository;
import com.example.deamaserver.entity.types.Category;
import com.example.deamaserver.presentation.woonildto.CategoryAndArrayResponse;
import com.example.deamaserver.presentation.woonildto.EmissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WoonilService {

    private final EmissionRepository emissionRepository;

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
