package com.dangsan.dotjoin.modules.toyproject.model;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ToyProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String shotDescription;

    @Column
    private String fullDescription;

    @Column
    private String imagePath;

    @Column
    private LocalDate projectStart;

    @Column
    private LocalDate projectEnd;

    @OneToMany(mappedBy = "toyProject")
    private List<SubProject> subProjects = new ArrayList<SubProject>();

    @OneToMany(mappedBy = "toyProject")
    private List<ToyProjectAccount> requesters = new ArrayList<ToyProjectAccount>();

    @OneToMany(mappedBy = "toyProject")
    private List<ToyProjectRate> projectRates = new ArrayList<ToyProjectRate>();
}
