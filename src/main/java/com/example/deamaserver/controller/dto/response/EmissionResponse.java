package com.example.deamaserver.controller.dto.response;

import com.example.deamaserver.entity.Emission;
import lombok.Getter;

@Getter
public class EmissionResponse {

    private Long id;

    private String title;

    public EmissionResponse(Emission e) {
        this.id = e.getId();
        this.title = e.getTitle();
    }

}
