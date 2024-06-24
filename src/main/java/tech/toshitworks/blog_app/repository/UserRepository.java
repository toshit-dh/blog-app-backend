package tech.toshitworks.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.toshitworks.blog_app.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

}
