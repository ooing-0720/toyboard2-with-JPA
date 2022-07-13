package com.toyboard2.web.dto;

import com.toyboard2.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "/index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "/posts-save";
    }

    @PostMapping("/posts/save")
    public String postsSave(Model model, String title, String author, String content) {
        PostsSaveRequestDto postsSaveRequestDto = new PostsSaveRequestDto(title, author, content);
        postsService.save(postsSaveRequestDto);
        model.addAttribute("msg", "글이 등록되었습니다.");
        model.addAttribute("url", "/");
        return "/message/alert";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto = postsService.findById(id);
        model.addAttribute("post", responseDto);

        return "/posts-update";
    }

    @PostMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, String title, String content, Model model) {
        PostsUpdateRequestDto requestDto = new PostsUpdateRequestDto(title, content);
        postsService.update(id, requestDto);

        model.addAttribute("msg", "수정이 완료되었습니다.");
        model.addAttribute("url", "/");
        return "/message/alert";
    }

    @RequestMapping("/posts/delete/{id}")
    public String postsDelete(@PathVariable Long id, Model model) {
        postsService.delete(id);

        model.addAttribute("msg", "삭제되었습니다.");
        model.addAttribute("url", "/");
        return "/message/alert";

    }
}

