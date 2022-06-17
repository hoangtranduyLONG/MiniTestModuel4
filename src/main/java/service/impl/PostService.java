package service.impl;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import repository.PostRepository;
import service.IPostService;

import java.util.Optional;

public class PostService implements IPostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public Iterable findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional findById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void remove(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findAllByTitle(String title) {
        return postRepository.findAllByTitleContaining(title);
    }
}