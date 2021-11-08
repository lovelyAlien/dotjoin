package com.dangsan.dotjoin.modules.toyproject.model;

import com.dangsan.dotjoin.modules.account.model.Account;
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

    @OneToMany
    @JoinTable(
            name="toy_project_requester",
            inverseJoinColumns = @JoinColumn(name="requester_id"))
    private List<Account> requesters = new ArrayList<Account>(); //참여 희망자

    @OneToMany
    @JoinTable(name="toy_project_member",
            inverseJoinColumns = @JoinColumn(name="member_id"))
    private List<Account> members= new ArrayList<Account>(); //참여자

    @OneToMany(mappedBy = "toyProject")
    private List<ToyProjectRate> projectRates = new ArrayList<ToyProjectRate>();
}
