package com.myboardproject.mbp.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // SpringDataJPA로 해결하는 방법을 모르겠네;;;
    @Query("SELECT p FROM Post p ORDER BY p.lastModifiedDate DESC")
    List<Post> findAllPostDesc();

    Page<Post> findAll(Pageable pageable);

    @Query("SELECT p FROM Post p where p.category = :category")
    Page<Post> findAllByCategory(Pageable pageable, @Param("category") PostCategory category);
}
