package controller;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.PostService;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/create-post")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("post" , new Post());
        return modelAndView;
    }

    @PostMapping("/create-post")
    public ModelAndView savePost(@ModelAttribute("post") Post post) {
        post.setCreateAt(LocalDateTime.now());
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("post", new Post());
        modelAndView.addObject("message", "Create successfully");
        return modelAndView;
    }

    @GetMapping("/posts1")
    public ModelAndView listPosts(@PageableDefault(value = 5) Pageable pageable) {
        Page<Post> posts = postService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @GetMapping("/edit-province/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Post> post = postService.findById(id);
        if (post.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("post", post.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-province")
    public ModelAndView updateProvince(@ModelAttribute("post") Post post) {
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("post", post);
        modelAndView.addObject("message", "Post updated successfully");
        return modelAndView;
    }

    @PostMapping("/find")
    public ModelAndView findTitle(@RequestParam String post) {
        Iterable<Post> posts = postService.findByTitle(post);
        ModelAndView modelAndView = new ModelAndView("/findByTitle");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @PostMapping("/post-asc")
    public ModelAndView newPost() {
        Iterable<Post> posts = postService.showListPostAsc();
        ModelAndView modelAndView = new ModelAndView("/postListAsc");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }
    @GetMapping("/posts2")
    public ModelAndView listCustomers(Pageable pageable){
        Page<Post> posts = postService.findAll2(pageable);
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }
    }