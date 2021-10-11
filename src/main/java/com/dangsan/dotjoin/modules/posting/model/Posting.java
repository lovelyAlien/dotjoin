package com.dangsan.dotjoin.modules.posting.model;

import com.dangsan.dotjoin.modules.hashtag.model.UsedHashTag;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
public class Posting {

    @Id @GeneratedValue
    private Long id;

    // OneToMany(mapped by = "posting")
    // 회원 writing;

    @CreationTimestamp
    private LocalDateTime postingAt;

    private String contents;

    // 사진 (todo : 추후 업데이트 예정)

    // 태그
    @OneToMany
    List<UsedHashTag> usedHashTags = new ArrayList<UsedHashTag>();

    // 좋아요
    @OneToMany(mappedBy = "posting")
    List<PostingLike> postingLikes = new ArrayList<PostingLike>();

    // 댓글
    @OneToMany(mappedBy = "posting")
    List<PostingComment> postingComments = new ArrayList<PostingComment>();
}
