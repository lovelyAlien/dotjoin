package com.dangsan.dotjoin.modules.posting.form;

import com.dangsan.dotjoin.modules.posting.model.Posting;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResponsePostingsForm {

    @NotBlank
    private List<Posting> postingList = new ArrayList<Posting>();
}
