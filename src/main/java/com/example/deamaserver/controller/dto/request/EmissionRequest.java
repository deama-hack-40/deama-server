package com.example.deamaserver.controller.dto.request;

import com.example.deamaserver.entity.Emission;
import com.example.deamaserver.entity.types.Category;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class EmissionRequest {

    @NotBlank(message = "please init any value in title")
    private String title;

    @NotBlank(message = "please init any value in content")
    private String content;

    @NotNull(message = "please inti any value in category")
    private Category category;

    public Emission toEmission() {
        return Emission.builder()
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .build();
    }
}
