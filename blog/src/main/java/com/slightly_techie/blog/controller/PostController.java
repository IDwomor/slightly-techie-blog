package com.slightly_techie.blog.controller;

import com.slightly_techie.blog.dto.PostRequest;
import com.slightly_techie.blog.dto.PostResponse;
import com.slightly_techie.blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 🔒 Only ADMIN can create posts
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostResponse> create(@Valid @RequestBody PostRequest request) {
        PostResponse created = postService.createPost(request);
        return ResponseEntity.ok(created);
    }

    // 👀 Both ADMIN and READER can read posts
    @PreAuthorize("hasAnyRole('ADMIN', 'READER')")
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // 🔒 Only ADMIN can update posts
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> update(@PathVariable Long id, @Valid @RequestBody PostRequest request) {
        return ResponseEntity.ok(postService.updatePost(id, request));
    }

    // 🔒 Only ADMIN can delete posts
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    // 👀 Both ADMIN and READER can list posts
    @PreAuthorize("hasAnyRole('ADMIN', 'READER')")
    @GetMapping
    public ResponseEntity<Page<PostResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String tag,
            @RequestParam(defaultValue = "false") boolean publishedOnly
    ) {
        Page<PostResponse> result;
        if (author != null && !author.isBlank()) {
            result = postService.findByAuthor(author, page, size, sortBy);
        } else if (tag != null && !tag.isBlank()) {
            result = postService.findByTag(tag, page, size, sortBy);
        } else if (publishedOnly) {
            result = postService.listPublished(page, size, sortBy);
        } else {
            result = postService.listPosts(page, size, sortBy);
        }
        return ResponseEntity.ok(result);
    }
}

