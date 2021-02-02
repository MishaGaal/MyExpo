package com.example.dto;

import com.example.entity.Expo;
import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class ExposDTO {
    private Page<Expo> expos;

}

