package com.example.voard.Repository;

import java.security.Principal;
import java.util.List;

import com.example.voard.Entity.Post;

public interface PostRepositoryCustom {
     List<Post> postLimitLoad(int page, Principal principal);

     List<Post> postGetWithCategory(Long id, int page, Principal principal);
}
