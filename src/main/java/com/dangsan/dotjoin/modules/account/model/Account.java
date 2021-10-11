package com.dangsan.dotjoin.modules.account.model;

import com.dangsan.dotjoin.modules.Timestamped;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Account extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=100,nullable = false)
    private String email;

    @Column(length=100,nullable = false)
    private String password; // 로그인 패스워드

    @Column
    private String nickname; // 계정 아이디

    @Column
    private String username; // 이름

    @Column
    private String profileImgUrl;

    @Column
    private String text; // 프로필 소개

    @Column
    private String phone; // 번호

    @Column
    private String birth; //생년월일

//    @Column
//    private LocalDateTime createdDate; // 계정 생성일
//
//    @Column
//    private LocalDateTime modifiedDate; // 정보 변경일

    @Column
    private String verify;

    @Column
    private String roles; //USER, ADMIN

    @Column
    private String emailCheckToken;

    @Column
    private LocalDateTime emailCheckTokenGeneratedAt;


    public List<String> getRoleList(){
        if(this.roles.length()>0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }


    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
    }

//    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL )
//    private List<Post> post;
//
//    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
//    private List<Comment> comments;
//
//
//    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
//    private Set<Follow> followings;
//
//    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
//    private Set<Follow> followers;

}
