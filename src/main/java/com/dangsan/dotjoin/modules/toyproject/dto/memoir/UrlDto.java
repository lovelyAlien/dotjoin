package com.dangsan.dotjoin.modules.toyproject.dto.memoir;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.Memoir;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Url;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlDto {

    private Long urlId;

    private String type;

    private String url;

    public UrlDto(Url url){
        this.urlId=url.getId();

        this.type=url.getType();

        this.url=url.getUrl();
    }


}
