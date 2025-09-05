package com.slightly_techie.blog.service;

import com.slightly_techie.blog.dto.PostRequest;
import com.slightly_techie.blog.dto.PostResponse;
import org.springframework.data.domain.Page;

public interface PostService {

    PostResponse createPost(PostRequest request);
    PostResponse updatePost(Long id, PostRequest request);
    void deletePost(Long id);
    PostResponse getPostById(Long id);
    Page<PostResponse> listPosts(int page, int size, String sortBy);
    Page<PostResponse> listPublished(int page, int size, String sortBy);
    Page<PostResponse> findByAuthor(String author, int page, int size, String sortBy);
    Page<PostResponse> findByTag(String tag, int page, int size, String sortBy);
}
