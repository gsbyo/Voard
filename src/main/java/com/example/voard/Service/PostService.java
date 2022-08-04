package com.example.voard.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.voard.Dto.CategoryDto;
import com.example.voard.Dto.PostDto;
import com.example.voard.Entity.Category;
import com.example.voard.Entity.Post;
import com.example.voard.Repository.CategoryRepository;
import com.example.voard.Repository.PostRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public Long postSave(PostDto postDto, Principal principal){
        if(postDto.getCategory() != null){
            Category category = categoryRepository.findById(postDto.getCategory().getId()).get();
            postDto.setCategory(category);
        }
       
        postDto.setDate(LocalDate.now().toString());
        postDto.setUser(principal.getName());

        return postRepository.save(postDto.toEntity()).getId();
    }

    public List<Post> postLoad(int page, Principal principal){
        return postRepository.postLimitLoad(page, principal);
    }

    public boolean postDel(Long id){
        postRepository.deleteById(id);

        return true;
    }

    public List<Post> postLoadWithCategory(Long id, int page, Principal principal){
        return postRepository.postGetWithCategory(id, page, principal);
    }
    
}
