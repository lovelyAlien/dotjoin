package com.dangsan.dotjoin.modules.hashtag.model;

import com.dangsan.dotjoin.modules.posting.model.Posting;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
public class UsedHashTag {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Posting posting;

    @ManyToOne
    private HashTag hashTag;
}

// test