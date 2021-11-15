package com.dangsan.dotjoin.modules.toyproject.repository.subproject;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.Memoir;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {

    List<Url> findAllByMemoir(Memoir memoir);
}
