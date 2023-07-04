package com.example.deamaserver.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ListResponse {

    private Long id;
    private String title;

    @Builder
    ListResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
