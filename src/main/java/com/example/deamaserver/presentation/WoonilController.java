package com.example.deamaserver.presentation;

import com.example.deamaserver.entity.types.Category;
import com.example.deamaserver.presentation.woonildto.CategoryAndArrayResponse;
import com.example.deamaserver.service.WoonilService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/woon-il")
@RestController
public class WoonilController {

    private final WoonilService woonilService;

    @GetMapping("/photo")
    public CategoryAndArrayResponse photoApi(@RequestParam(value = "photo") MultipartFile file) {
        return woonilService.findByPhoto(file);
    }

    @GetMapping("/{category}")
    public CategoryAndArrayResponse findByCategory(@PathVariable Category category) {
        return woonilService.findByCategory(category);
    }
}
