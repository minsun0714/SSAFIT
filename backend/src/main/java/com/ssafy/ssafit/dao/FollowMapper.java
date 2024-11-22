package com.ssafy.ssafit.dao;

import com.ssafy.ssafit.domain.Follow;
import com.ssafy.ssafit.domain.Member;
import com.ssafy.ssafit.dto.response.FollowerVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowMapper {
    void insertFollow(Follow follow);

    List<FollowerVO> selectFollowers(String memberId);

    List<FollowerVO> selectFollowings(String memberId);

    boolean existsFollow(@Param("memberId") String memberId, @Param("followerId") String followerId);

    void deleteFollow(Follow follow);
}
