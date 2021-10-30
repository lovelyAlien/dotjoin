package com.dangsan.dotjoin.modules.toyproject.model;

import com.dangsan.dotjoin.modules.account.model.Account;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class ToyProjectAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @ManyToOne
    private ToyProject toyProject;

    @Column
    @ManyToOne
    private Account participate;

    @Column
    @CreationTimestamp
    private LocalDateTime participateTime;
}
