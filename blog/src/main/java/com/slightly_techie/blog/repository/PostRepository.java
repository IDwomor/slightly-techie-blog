package com.slightly_techie.blog.repository;

import com.slightly_techie.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByAuthorIgnoreCase(String author, Pageable pageable);
    Page<Post> findByPublishedTrue(Pageable pageable);
    // simple tag search (contains)
    Page<Post> findByTagsContainingIgnoreCase(String tag, Pageable pageable);
}
