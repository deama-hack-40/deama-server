package com.example.deamaserver.controller;

import com.example.deamaserver.controller.dto.ListResponse;
import com.example.deamaserver.controller.dto.Response;
import com.example.deamaserver.entity.types.Category;
import com.example.deamaserver.controller.dto.CategoryAndArrayResponse;
import com.example.deamaserver.service.EmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EmissionController {
    private final EmissionService emissionService;

    @GetMapping("/explain/{id}")
    public Response getexplain (@PathVariable Long id) {
        return emissionService.getById(id);
    }

    @GetMapping("list")
    public List<ListResponse> getAllPost (ListResponse response) {
        return emissionService.getAllpost(response);
    }

    @GetMapping("/photo")
    public CategoryAndArrayResponse photoApi(@RequestParam(value = "photo") MultipartFile file) {
        return emissionService.findByPhoto(file);
    }

    @GetMapping("/{category}")
    public CategoryAndArrayResponse findByCategory(@PathVariable Category category) {
        return emissionService.findByCategory(category);
    }
}
