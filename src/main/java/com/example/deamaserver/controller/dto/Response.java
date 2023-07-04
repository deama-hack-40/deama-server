package com.example.deamaserver.controller.dto;

import com.example.deamaserver.entity.types.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Response {

    private final Long id;
    private final String title;
    private final String content;
    private final Category category;

    @Builder
    public Response(Long id, String title, String content, String category){
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = Category.valueOf(category);
    }
}
