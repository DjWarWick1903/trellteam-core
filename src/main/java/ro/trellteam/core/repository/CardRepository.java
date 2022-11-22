package ro.trellteam.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.trellteam.core.domain.Card;
import ro.trellteam.core.domain.Type;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

    Card save(Card card);
    List<Card> findAllByIdAssignedUser(Long idAssignedUser);
    List<Card> findAllByIdPublisher(Long idPublisher);
    List<Card> findAllByType(Type type);
    Optional<Card> findById(Long id);

}
