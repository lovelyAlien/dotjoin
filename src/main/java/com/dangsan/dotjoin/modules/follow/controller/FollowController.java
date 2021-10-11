package com.dangsan.dotjoin.modules.follow.controller;

import com.dangsan.dotjoin.modules.account.model.UserAccount;
import com.dangsan.dotjoin.modules.follow.repository.FollowCustomRepositoryImpl;
import com.dangsan.dotjoin.modules.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FollowController {
    private final FollowService followService;
    private final FollowCustomRepositoryImpl followCustomRepository;


    
    @PostMapping("/follow/{toUserId}")
    public ResponseEntity<?> followUser(@PathVariable Long toUserId,  @AuthenticationPrincipal UserAccount userAccount ){
        followService.follow(userAccount.getId(), toUserId);
        return ResponseEntity.ok("Success follow");
    }

    @DeleteMapping("/follow/{toUserId}")
    public ResponseEntity<?> unFollowUser(@PathVariable Long toUserId, @AuthenticationPrincipal UserAccount userAccount){
        followService.unFollow(userAccount.getId(), toUserId);
        return ResponseEntity.ok("Success unfollow");
    }

    @GetMapping("/user/{profileId}/follower")
    public ResponseEntity<?> getFollower(@PathVariable Long profileId, @AuthenticationPrincipal UserAccount userAccount) {
        return new ResponseEntity<>(followCustomRepository.getFollower(profileId, userAccount.getId()), HttpStatus.OK);

    }

    @GetMapping("/user/{profileId}/following")
    public ResponseEntity<?> getFollowing(@PathVariable Long profileId, @AuthenticationPrincipal UserAccount userAccount) {
        return new ResponseEntity<>(followCustomRepository.getFollowing(profileId, userAccount.getId()), HttpStatus.OK);
    }


}
