package com.slightly_techie.blog.service;

import com.slightly_techie.blog.dto.PostRequest;
import com.slightly_techie.blog.dto.PostResponse;
import com.slightly_techie.blog.entity.Post;
import com.slightly_techie.blog.repository.PostRepository;
import com.slightly_techie.blog.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    private PostResponse toDto(Post post) {
        return modelMapper.map(post, PostResponse.class);
    }

    private Post toEntity(PostRequest request) {
        return modelMapper.map(request, Post.class);
    }

    private Pageable pageable(int page, int size, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.DESC, sortBy == null ? "createdAt" : sortBy);
        return PageRequest.of(Math.max(0, page), Math.max(1, size), sort);
    }

    @Override
    public PostResponse createPost(PostRequest request) {
        Post post = toEntity(request);
        Post saved = postRepository.save(post);
        return toDto(saved);
    }

    @Override
    public PostResponse updatePost(Long id, PostRequest request) {
        Post existing = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        existing.setTitle(request.getTitle());
        existing.setContent(request.getContent());
        existing.setAuthor(request.getAuthor());
        existing.setPublished(request.isPublished());
        existing.setTags(request.getTags());
        Post saved = postRepository.save(existing);
        return toDto(saved);
    }

    @Override
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post", "id", id);
        }
        postRepository.deleteById(id);
    }

    @Override
    public PostResponse getPostById(Long id) {
        return postRepository.findById(id).map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    @Override
    public Page<PostResponse> listPosts(int page, int size, String sortBy) {
        Pageable p = pageable(page, size, sortBy);
        return postRepository.findAll(p).map(this::toDto);
    }

    @Override
    public Page<PostResponse> listPublished(int page, int size, String sortBy) {
        Pageable p = pageable(page, size, sortBy);
        return postRepository.findByPublishedTrue(p).map(this::toDto);
    }

    @Override
    public Page<PostResponse> findByAuthor(String author, int page, int size, String sortBy) {
        Pageable p = pageable(page, size, sortBy);
        return postRepository.findByAuthorIgnoreCase(author, p).map(this::toDto);
    }

    @Override
    public Page<PostResponse> findByTag(String tag, int page, int size, String sortBy) {
        Pageable p = pageable(page, size, sortBy);
        return postRepository.findByTagsContainingIgnoreCase(tag, p).map(this::toDto);
    }
}
