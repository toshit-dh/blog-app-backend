package tech.toshitworks.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.toshitworks.blog_app.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findByTitle(String title);

    List<Category> findByTitleContaining(String title);
}
