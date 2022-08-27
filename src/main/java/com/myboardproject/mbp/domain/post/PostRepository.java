package com.myboardproject.mbp.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // SpringDataJPA로 해결하는 방법을 모르겠네;;;
    @Query("SELECT p FROM Post p ORDER BY p.lastModifiedDate DESC")
    List<Post> findAllPostDesc();
}
