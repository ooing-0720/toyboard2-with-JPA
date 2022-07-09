package com.toyboard2.web;

import com.toyboard2.service.posts.PostsService;
import com.toyboard2.web.dto.PostsResponseDto;
import com.toyboard2.web.dto.PostsSaveRequestDto;
import com.toyboard2.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

/*
    @GetMapping("/api/v1/posts/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("post", postsService.findById(id));
        return "/posts-update";
    }

    @PostMapping("/api/v1/posts/{id}")
    public String update(@PathVariable Long id, String title, String content, Model model){
        PostsUpdateRequestDto requestDto = new PostsUpdateRequestDto(title, content);
        postsService.update(id, requestDto);

        model.addAttribute("msg", "수정되었습니다.");
        model.addAttribute("url", "/");
        return "/message/alert";
    }
*/
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {

        return postsService.findById(id);
    }
}
