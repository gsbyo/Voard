package com.example.voard.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category{

    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    Long id;

    @Column
    String name;

    @Column
    String user;

    @Builder
    Category(String name, String user){
        this.name = name;
        this.user = user;
    }

}
