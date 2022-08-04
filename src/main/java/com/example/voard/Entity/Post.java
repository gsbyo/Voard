package com.example.voard.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "posts")
public class Post {
    
    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    Long id;

    @Column
    String url;

    @Column
    String user;

    @Column
    String date;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    @Nullable
    Category category;

    @Builder
    Post(String url,String user, String date, Category category){
        this.url = url;
        this.user = user;
        this.date = date;
        this.category = category;
    }
}
