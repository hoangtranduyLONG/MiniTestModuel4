package repository;


import model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPostRepository extends JpaRepository<Post, Long> {
    Iterable<Post> findAllByTitleContains(String title);

    @Query(value = "select * from post order by createAt asc", nativeQuery = true)
    Iterable<Post> findAllByCreateAtAsc();


}