package com.ssafy.ssafit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowVO {
    private List<FollowerVO> followingList;

    private List<FollowerVO> followerList;
}
