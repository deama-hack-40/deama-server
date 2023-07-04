package com.example.deamaserver.entity;

import com.example.deamaserver.entity.types.Category;
import com.example.deamaserver.controller.dto.EmissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmissionRepository extends JpaRepository<Emission, Long> {

    @Query("select new com.example.deamaserver.controller.dto.EmissionResponse(e) " +
            "from Emission e where e.category = :category")
    List<EmissionResponse> getCategory(@Param("category") Category category);
}
