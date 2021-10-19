package com.dangsan.dotjoin.modules.todaylog;


import com.dangsan.dotjoin.modules.account.model.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class TodayLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
