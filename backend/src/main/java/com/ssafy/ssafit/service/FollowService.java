package com.ssafy.ssafit.service;

import com.ssafy.ssafit.dao.FollowMapper;
import com.ssafy.ssafit.domain.Follow;
import com.ssafy.ssafit.dto.response.FollowVO;
import com.ssafy.ssafit.dto.response.FollowerVO;
import com.ssafy.ssafit.exception.MemberNotAuthenticatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ssafy.ssafit.utils.DTOMapper.toFollowVOMapper;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowMapper followMapper;

    @Transactional
    public void createFollow(String followerId) {
        String memberId = getAuthenticatedMemberId();

        Follow follow = Follow.builder()
                .memberId(memberId)
                .followerId(followerId)
                .build();

        followMapper.insertFollow(follow);
    }

    public FollowVO getFollowRelationship() {
        String memberId = getAuthenticatedMemberId();

        List<FollowerVO> followers = followMapper.selectFollowers(memberId);
        List<FollowerVO> followings = followMapper.selectFollowings(memberId);

        System.out.println("followers" + followers);
        System.out.println("followings" + followings);
        return toFollowVOMapper(followers, followings);
    }

    public FollowVO getOthersFollowRelationship(String memberId) {

        List<FollowerVO> followers = followMapper.selectFollowers(memberId);
        List<FollowerVO> followings = followMapper.selectFollowings(memberId);

        return toFollowVOMapper(followers, followings);
    }

    public void unfollow(String followerId) {
        String memberId = getAuthenticatedMemberId();

        // 팔로우 관계가 존재하는지 확인
        boolean exists = followMapper.existsFollow(memberId, followerId);
        if (!exists) {
            throw new IllegalArgumentException("팔로우 관계가 존재하지 않습니다.");
        }

        // 팔로우 관계 삭제
        Follow follow = Follow.builder()
                .memberId(memberId)
                .followerId(followerId)
                .build();

        followMapper.deleteFollow(follow);
    }

    public String getAuthenticatedMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof String)) {
            throw new MemberNotAuthenticatedException("Member not authenticated");
        }
        return (String) authentication.getPrincipal();
    }
}
