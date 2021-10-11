package com.dangsan.dotjoin.modules.follow.model;

import com.dangsan.dotjoin.modules.account.model.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "from_user_id")
    @ManyToOne
    private Account fromUser;

    @JoinColumn(name = "to_user_id")
    @ManyToOne
    private Account toUser;

    @Builder
    public Follow(Account fromUser, Account toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
    }
}