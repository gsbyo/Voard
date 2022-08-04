package com.example.voard.Dto;

import com.example.voard.Entity.Category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    String name;
    String user;

    public Category toEntity(){
       return Category.builder()
                      .name(name)
                      .user(user)
                      .build();
    }

    @Builder
    CategoryDto(String name, String user){
        this.name = name;
        this.user = user;
    }

}
