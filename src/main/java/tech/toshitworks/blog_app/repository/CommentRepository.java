package tech.toshitworks.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.toshitworks.blog_app.entity.Comment;
import tech.toshitworks.blog_app.entity.Post;
import tech.toshitworks.blog_app.entity.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findByUser(User user);

    List<Comment> findByPost(Post post);

}
