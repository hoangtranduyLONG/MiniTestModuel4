package controller;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repository.PostRepository;
import service.IPostService;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    IPostService postService;
    @GetMapping
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("/post/list");
        modelAndView.addObject("post", postService.findAll());
        return modelAndView;

    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/post/edit");
        Post post = postService.findById(id).get();
        modelAndView.addObject("po", post);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView update(@PathVariable Long id, Post post) {
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postService.save(post);
        return modelAndView;
    }
    @PostMapping("/search")
    public ModelAndView search(@RequestParam String title){
        ModelAndView modelAndView =new ModelAndView("/post/search");
        Iterable<Post> posts =postService.findAllByTitle(title);
        modelAndView.addObject("po",posts);
        return modelAndView;

    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        postService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        return modelAndView;
    }



}