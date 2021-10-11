package com.dangsan.dotjoin.modules.hashtag.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
public class HashTag {

    @Id @GeneratedValue
    private Long id;

    private String tagName;

    @OneToMany
    private List<UsedHashTag> usedHashTags = new ArrayList<UsedHashTag>();
}
