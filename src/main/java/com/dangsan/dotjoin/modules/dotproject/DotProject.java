package com.dangsan.dotjoin.modules.dotproject;


import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.tag.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class DotProject {

    @Id
    @GeneratedValue
    private Long id;

//    @ManyToMany
//    private Set<Account> managers = new HashSet<>();
//
//    @ManyToMany
//    private Set<Account> members = new HashSet<>();

    @Column(unique = true)
    private String path;

    private String title;

    private String shortDescription;

    private Long score;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String fullDescription;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String image;


//    @ManyToMany
//   private Set<Tag> tags = new HashSet<>();

    private LocalDateTime publishedDateTime;

    private LocalDateTime closedDateTime;

    private LocalDateTime recruitingUpdatedDateTime;

    private boolean recruiting;

    private boolean published;

    private boolean closed;

    private boolean useBanner;

    private int memberCount;
}
