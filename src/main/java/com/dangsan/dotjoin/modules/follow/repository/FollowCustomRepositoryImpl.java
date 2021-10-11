package com.dangsan.dotjoin.modules.follow.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.dangsan.dotjoin.modules.account.model.QAccount;
import com.dangsan.dotjoin.modules.follow.dto.FollowDto;
import com.dangsan.dotjoin.modules.follow.model.Follow;
import com.dangsan.dotjoin.modules.follow.model.QFollow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class FollowCustomRepositoryImpl implements FollowCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Follow findFollowByFromUserIdAndToUserId(Long from_user_id, Long to_user_id){
        QFollow follow=QFollow.follow;

        Follow f= jpaQueryFactory
                    .selectFrom(follow)
                    .where(follow.fromUser.id.eq(from_user_id).and(follow.toUser.id.eq(to_user_id)))
                    .fetchOne();

        System.out.println("Querydsl test="+ f);

        return f;
    }

    @Override
    public List<FollowDto> getFollower(long profileId, long loginId){
        QFollow follow=QFollow.follow;
        QAccount account=QAccount.account;

        List<FollowDto> follwers= jpaQueryFactory
                .select(Projections.fields(FollowDto.class,
                        account.id, account.nickname, account.profileImgUrl,
                        ExpressionUtils.as(JPAExpressions.selectFrom(follow)
                                .where(follow.fromUser.id.eq(loginId).and(follow.toUser.id.eq(account.id))).exists(), "followState"),
                        new CaseBuilder().when(account.id.eq(loginId)).then(true).otherwise(false).as("loginUser")))
                .from(account)
                .leftJoin(follow).on(account.eq(follow.fromUser))
                .where(follow.toUser.id.eq(profileId))
                .fetch();
//       "CASE WHEN EXISTS(SELECT * FROM follow WHERE from_user_id = ?1 AND to_user_id= u.id) "
//       "IS NOT null THEN true ELSE false END AS followState, "
//       "CASE WHEN ?1=u.id THEN true ELSE false END AS loginUser "


        return follwers;

    }

    @Override
    public List<FollowDto> getFollowing(long profileId, long loginId){
        QFollow follow=QFollow.follow;
        QAccount account=QAccount.account;

        List<FollowDto> followings= jpaQueryFactory
                .select(Projections.fields(FollowDto.class,
                        account.id, account.nickname, account.profileImgUrl,
                        ExpressionUtils.as(JPAExpressions.selectFrom(follow)
                                .where(follow.fromUser.id.eq(loginId).and(follow.toUser.id.eq(account.id))).exists(), "followState"),
                        new CaseBuilder().when(account.id.eq(loginId)).then(true).otherwise(false).as("loginUser")))
                .from(account)
                .leftJoin(follow).on(account.eq(follow.toUser))
                .where(follow.fromUser.id.eq(profileId))
                .fetch();
//        "CASE WHEN EXISTS(SELECT * FROM follow WHERE from_user_id = ?1 AND to_user_id = u.id "
//        "IS NOT null THEN true ELSE false END AS followState, "
//        "CASE WHEN ?1 = u.id THEN true ELSE false END AS loginUser "
        return followings;
    }

    public Boolean exist(Long loginId, NumberPath<Long> profileId) {
        QFollow follow=QFollow.follow;
        Integer fetchOne= jpaQueryFactory
                .selectOne()
                .from(follow)
                .where(follow.fromUser.id.eq(loginId).and(follow.toUser.id.eq(profileId)))
                .fetchFirst();

        return fetchOne!=null;
    }
}
