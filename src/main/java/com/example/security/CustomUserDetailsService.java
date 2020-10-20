package com.example.security;

import com.example.mapper.Member1Mapper;
import com.example.repository.Member1Repository;
import com.example.vo.Member1Vo;
import com.example.vo.Member2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    Member1Repository member1Repository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username을 넘겨서 일치하는 사용자 1명의 정보 받기
//        Member1Vo member1Vo = member1Mapper.memberLogin(username);
        Member2Vo member1Vo  = member1Repository.findByUsername(username);

        if (member1Vo == null) {
            return null;
        }

        String[] strRoles = { member1Vo.getRole() }; // 가져온 권한정보를 문자열 배열로 만들기
                    //     = {"ADMIN", "MANAGER", "GENERAL"};

        // 문자열 배열을 Collection<GrantedAuthority>타입으로 바꾸기
        Collection<GrantedAuthority> roles = AuthorityUtils.createAuthorityList(strRoles);

        // 사용자 객체 넘기기 (아이디, 암호, 권한들)
        return new SecurityUser(member1Vo.getUsername(), member1Vo.getPassword(), member1Vo.getName(), roles);

        //세션처리까지 완료됨
    }
}