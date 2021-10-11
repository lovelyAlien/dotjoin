package com.dangsan.dotjoin.modules.follow.repository;

import com.dangsan.dotjoin.modules.follow.dto.FollowDto;
import com.dangsan.dotjoin.modules.follow.model.Follow;

import java.util.List;

public interface FollowCustomRepository {

    Follow findFollowByFromUserIdAndToUserId(Long from_user_id, Long to_user_id);

    List<FollowDto> getFollower(long profileId, long loginId);

    List<FollowDto> getFollowing(long profileId, long loginId);


}
