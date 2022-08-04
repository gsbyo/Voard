package com.example.voard.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@DynamicInsert
@Table(name = "members")
public class Member{

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    Long id;

    @Column
    String username;

    @Column
    String password;

    @ColumnDefault("0")
    Integer role;

    @Builder
    Member(String username, String password,  Integer role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

}

