package com.example.voard.Repository;

import java.security.Principal;
import java.util.List;

import com.example.voard.Entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.example.voard.Entity.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final int PAGING_NUM = 4;

    @Override
    public List<Post> postLimitLoad(int page, Principal principal){
       System.out.println(page);

       return jpaQueryFactory.selectFrom(post)
                             .where(post.user.eq(principal.getName()))
                             .offset(page * PAGING_NUM)
                             .limit(PAGING_NUM)
                             .fetch();
    }

    @Override
    public List<Post> postGetWithCategory(Long id, int page, Principal principal) {
        return jpaQueryFactory.selectFrom(post)
                              .where(post.category().id.eq(id), post.user.eq(principal.getName()))
                              .offset(PAGING_NUM * page)
                              .limit(PAGING_NUM)
                              .fetch();
    }

     
    
}
