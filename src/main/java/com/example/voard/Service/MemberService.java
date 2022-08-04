package com.example.voard.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.voard.Dto.MemberDto;
import com.example.voard.Entity.Member;
import com.example.voard.Repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService{
    private final MemberRepository memberRepository;

    @Transactional
    public Long joinUser(MemberDto member) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        member.setPassword(passwordEncoder.encode(member.getPassword()));

        return memberRepository.save(member.toEntity()).getId();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member user = memberRepository.findByUsername(username);

        if(user == null) return null;
        
        List<GrantedAuthority> authorities = new ArrayList<>();

        switch(user.getRole()){
            case 0 : 
                           authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                           break;
            case 1 : 
                           authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(user.getUsername(), user.getPassword(), authorities);
            
    }
    
}
