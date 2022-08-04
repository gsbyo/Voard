package com.example.voard.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.voard.Entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom{
    public List<Post> findAllByUser(String user);

    
}
