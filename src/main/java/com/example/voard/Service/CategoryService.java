package com.example.voard.Service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.voard.Dto.CategoryDto;
import com.example.voard.Entity.Category;
import com.example.voard.Repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> categoryLoad(Principal principal){
        return categoryRepository.findAllByUser(principal.getName());
    }

    public Long categorySave(CategoryDto categoryDto, Principal principal){
         categoryDto.setUser(principal.getName());

         return categoryRepository.save(categoryDto.toEntity()).getId();

    }
    
}
