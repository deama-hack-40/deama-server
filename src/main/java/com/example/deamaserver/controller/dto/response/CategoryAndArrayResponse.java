package com.example.deamaserver.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class CategoryAndArrayResponse {

    private String category;

    private List<EmissionResponse> emissionList;
}
