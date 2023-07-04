package com.example.deamaserver.controller.dto;

import com.example.deamaserver.entity.types.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Response {

    private Long id;
    private String title;
    private String content;
    private Category category;

    @Builder
    Response(String title, String content, String category){
        this.title = title;
        this.content = content;
        this.category = Category.valueOf(category);
    }
}
