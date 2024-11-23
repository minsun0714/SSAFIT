package com.ssafy.ssafit.dao;

import com.ssafy.ssafit.domain.Member;

import java.util.Optional;

public interface MemberMapper {
    void insertMember(Member member);

    String findPasswordByMemberId(String memberId);

    Optional<Member> findByMemberId(String memberId);

    void updateMember(Member member);

    void updateWeight(Member member);

    void deleteByMemberId(String memberId);

    boolean existsByMemberId(String memberId);
}
