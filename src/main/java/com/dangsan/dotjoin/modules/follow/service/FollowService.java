package com.dangsan.dotjoin.modules.follow.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.dangsan.dotjoin.infra.handler.ex.CustomApiException;
import com.dangsan.dotjoin.modules.follow.dto.FollowDto;
import com.dangsan.dotjoin.modules.follow.model.QFollow;
import com.dangsan.dotjoin.modules.follow.repository.FollowCustomRepositoryImpl;
import com.dangsan.dotjoin.modules.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final FollowCustomRepositoryImpl followCustomRepository;
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    @Transactional
    public void follow(long fromUserId, long toUserId) {

        if(followRepository.findFollowByFromUserIdAndToUserId(fromUserId, toUserId) != null) throw new CustomApiException("이미 팔로우 하였습니다.");

        followRepository.follow(fromUserId, toUserId);
    }

    @Transactional
    public void unFollow(long fromUserId, long toUserId) {
        followRepository.unFollow(fromUserId, toUserId);
    }

    @Transactional
    public List<FollowDto> getFollower(long profileId, long loginId) {

        QFollow follow=QFollow.follow;



        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.nickname, u.profile_img_url, ");
        sb.append("CASE WHEN EXISTS(SELECT * FROM follow WHERE from_user_id = ?1 AND to_user_id= u.id) ");
        sb.append("IS NOT null THEN true ELSE false END AS followState, ");
        sb.append("CASE WHEN ?1=u.id THEN true ELSE false END AS loginUser ");
        sb.append("FROM account u, follow f ");
        sb.append("WHERE u.id = f.from_user_id AND f.to_user_id = ?2");
        // 쿼리 완성


        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, loginId)
                .setParameter(2, profileId);

        System.out.println("sb.toString(): " + sb.toString());
        //JPA 쿼리 매핑 - DTO에 매핑
        JpaResultMapper result = new JpaResultMapper();
        List<FollowDto> followDtoList = result.list(query, FollowDto.class);
        return followDtoList;
    }

    @Transactional
    public List<FollowDto> getFollowing(long profileId, long loginId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.nickname, u.profile_img_url, ");
//        sb.append("SELECT u.id, u.nickname, case when u.profile_img_url is null then '' else u.profile_img_url end as profile_img_url , ");
        sb.append("CASE WHEN EXISTS(SELECT * FROM follow WHERE from_user_id = ?1 AND to_user_id = u.id) ");
        sb.append("IS NOT null THEN true ELSE false END AS followState, ");
        sb.append("CASE WHEN ?1 = u.id THEN true ELSE false END AS loginUser ");
        sb.append("FROM account u, follow f ");
        sb.append("WHERE u.id = f.to_user_id AND f.from_user_id = ?2");

        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, loginId)
                .setParameter(2, profileId);
//                .setParameter(3, profileId);

        System.out.println("sb.toString(): " + sb.toString());



        //JPA 쿼리 매핑 - DTO에 매핑
        JpaResultMapper result = new JpaResultMapper();
        List<FollowDto> followDtoList = result.list(query, FollowDto.class);
        return followDtoList;
    }
}
