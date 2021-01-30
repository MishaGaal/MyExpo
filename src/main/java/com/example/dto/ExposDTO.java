package com.example.dto;

import com.example.entity.Expo;
import org.springframework.data.domain.Page;

public class ExposDTO {

    private Page<Expo> expos;

    public ExposDTO() {
    }

    public ExposDTO(Page<Expo> expos) {
        this.expos = expos;
    }

    public Page<Expo> getExpos() {
        return expos;
    }

    public void setExpos(Page<Expo> expos) {
        this.expos = expos;
    }
}

