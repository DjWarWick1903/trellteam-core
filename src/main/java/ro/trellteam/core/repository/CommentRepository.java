package ro.trellteam.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.trellteam.core.domain.Comment;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
}
