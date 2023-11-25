package com.project.todotodo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "category create res")
public class categoryCreateRes {
    @Schema(description = "카테고리 이름", example = "전공")
    private String name;
}