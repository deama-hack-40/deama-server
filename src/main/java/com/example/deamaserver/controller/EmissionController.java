package com.example.deamaserver.controller;

import com.example.deamaserver.controller.dto.request.EmissionRequest;
import com.example.deamaserver.controller.dto.response.ListResponse;
import com.example.deamaserver.controller.dto.response.Response;
import com.example.deamaserver.entity.types.Category;
import com.example.deamaserver.controller.dto.response.CategoryAndArrayResponse;
import com.example.deamaserver.service.EmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class EmissionController {
    private final EmissionService emissionService;

    @GetMapping("/explain/{id}")
    public Response getExplain(@PathVariable Long id) {
        return emissionService.getById(id);
    }

    @GetMapping("/list")
    public List<ListResponse> getAllPost (ListResponse response) {
        return emissionService.getAllPost(response);
    }

    @GetMapping("/photo")
    public CategoryAndArrayResponse photoApi(@RequestParam(value = "photo") MultipartFile file) {
        return emissionService.findByPhoto(file);
    }

    @GetMapping("/{category}")
    public CategoryAndArrayResponse findByCategory(@PathVariable Category category) {
        return emissionService.findByCategory(category);
    }

    @PostMapping("/save")
    public void saveEmission(@RequestBody @Valid EmissionRequest request) {
        emissionService.saveEmission(request);
    }
}
