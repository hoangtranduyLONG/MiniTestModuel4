package repository;


import model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post , Long> {

    Iterable<Post> findAllByTitleContaining(String title);


}