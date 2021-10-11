package com.dangsan.dotjoin.modules.posting.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
public class PostingLike {

    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private LocalDateTime likeAt;

    // ManyToOne
    // 회원

    @ManyToOne
    private Posting posting;
}
