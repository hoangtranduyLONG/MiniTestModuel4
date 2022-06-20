package service;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.IPostRepository;

import java.util.Optional;

@Service
public class PostService implements IPostService{
    @Autowired
    private IPostRepository postRepository;


    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    public Iterable<Post> findByTitle(String title) {
        return postRepository.findAllByTitleContains(title);
    }

    public Iterable<Post> showListPostAsc() {
        return postRepository.findAllByCreateAtAsc();
    }


    public Page<Post> findAll2(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}