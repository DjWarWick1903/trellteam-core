package ro.trellteam.core.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.trellteam.core.domain.Comment;
import ro.trellteam.core.exceptions.TrellGenericException;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommentRepositoryImpl {
    private CommentRepository commentRepository;

    /**
     * Method used to save a comment in the database.
     * @param comment
     * @return Comment
     */
    public Comment save(Comment comment) {
        log.debug("CommentRepositoryImpl--save--IN");
        comment = commentRepository.save(comment);
        log.debug("CommentRepositoryImpl--save--comment: {}", comment);
        return comment;
    }

    /**
     * Method used to find a comment in the database starting from it's id.
     * @param id
     * @return Comment
     */
    public Comment findById(Long id) {
        log.debug("CommentRepositoryImpl--findById--id: {}", id);

        try {

        } catch(final Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("CORE_ERR_11");
        }
        final Comment comment = commentRepository.findById(id).get();

        log.debug("CommentRepositoryImpl--findById--comment: {}", comment);

        return comment;
    }
}
