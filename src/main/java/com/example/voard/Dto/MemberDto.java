package com.example.voard.Dto;

import com.example.voard.Entity.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private  Integer role;

    public Member toEntity(){
        return Member.builder()
                     .username(username)
                     .password(password)
                     .role(role)
                     .build();
    }

    @Builder
    public MemberDto(Long id, String username, String password,  Integer role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
