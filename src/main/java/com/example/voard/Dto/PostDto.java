package com.example.voard.Dto;

import com.example.voard.Entity.Category;
import com.example.voard.Entity.Post;
import com.example.voard.Repository.CategoryRepository;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

   
    private String url;
    private String user;
    private String date;
    private Category category;

    public Post toEntity(){
        return Post.builder()
                   .url(url)
                   .user(user)
                   .date(date)
                   .category(category)
                   .build();
    }

    
    @Builder
    PostDto(String url, String user, String date, Category category){
        this.url = url;
        this.user = user;
        this.date = date;
        this.category = category;
    }
}
