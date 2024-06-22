package tech.toshitworks.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.toshitworks.blog_app.entity.Category;
import tech.toshitworks.blog_app.entity.Post;
import tech.toshitworks.blog_app.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {

    Optional<Post> findByTitle(String title);

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

}
