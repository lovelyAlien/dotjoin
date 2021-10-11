package com.dangsan.dotjoin.modules.posting.repository;

import com.dangsan.dotjoin.modules.posting.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {
    List<Posting> findAll();
}
