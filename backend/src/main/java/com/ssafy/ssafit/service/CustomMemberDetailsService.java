package com.ssafy.ssafit.service;

import com.ssafy.ssafit.dao.MemberMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomMemberDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    public CustomMemberDetailsService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        String storedPassword = memberMapper.findPasswordByMemberId(memberId);
        return memberMapper.findByMemberId(memberId)
                .map(member -> org.springframework.security.core.userdetails.User.builder()
                        .username(member.getMemberId())
                        .password(storedPassword)
                        .authorities("ROLE_ADMIN")
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Member not found: " + memberId));
    }
}