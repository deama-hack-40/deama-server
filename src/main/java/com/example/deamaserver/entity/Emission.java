package com.example.deamaserver.entity;

import com.example.deamaserver.entity.types.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "content TEXT")
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder
    public Emission(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
