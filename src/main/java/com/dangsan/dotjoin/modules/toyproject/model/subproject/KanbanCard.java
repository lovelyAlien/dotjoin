package com.dangsan.dotjoin.modules.toyproject.model.subproject;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class KanbanCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private KanbanList kanbanList;


    @Column
    private String title;


    @Column
    private String Detail;


}
