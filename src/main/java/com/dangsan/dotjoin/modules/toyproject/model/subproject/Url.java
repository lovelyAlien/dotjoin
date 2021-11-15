package com.dangsan.dotjoin.modules.toyproject.model.subproject;


import com.dangsan.dotjoin.modules.toyproject.dto.memoir.RegisterUrlDto;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.UpdateTargetUrlDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type; //GIT or REF

    @Column
    private String url;

    @ManyToOne
    @JsonIgnore
    private Memoir memoir;


    public Url(Memoir memoir, RegisterUrlDto registerUrlDto){
        this.memoir=memoir;
        this.type=registerUrlDto.getType();
        this.url=registerUrlDto.getUrl();
    }

    public void update(UpdateTargetUrlDto updateTargetUrlDto){

        this.type=updateTargetUrlDto.getType();
        this.url=updateTargetUrlDto.getUrl();

    }
}
