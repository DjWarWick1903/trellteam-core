package ro.trellteam.core.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.trellteam.core.domain.Card;
import ro.trellteam.core.exceptions.TrellGenericException;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CardRepositoryImpl {
    private final CardRepository cardRepository;

    /**
     * Method used to save a card in the database.
     * @param card
     * @return Card
     */
    public Card save(Card card) {
        log.debug("CardRepositoryImpl--createCard--IN");
        card = cardRepository.save(card);
        log.debug("CardRepositoryImpl--createCard--card: {}", card);
        return card;
    }

    /**
     * Method used to get a card by it's id.
     * @param id
     * @return Card
     */
    public Card findById(final Long id) {
        log.debug("CardRepositoryImpl--findById--id: {}", id);

        Card card = null;
        try {
            card = cardRepository.findById(id).get();
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("CORE_ERR_2");
        }

        log.debug("CardRepositoryImpl--findById--card: {}", card);
        log.debug("CardRepositoryImpl--findById--OUT");
        return card;
    }

    /**
     * Method used to get a list of cards that were published by an employee.
     * @param idPublisher
     * @return List
     */
    public List<Card> getAllPublisherCards(final Long idPublisher) {
        log.debug("CardRepositoryImpl--getAllPublisherCards--idPublisher: {}", idPublisher);

        List<Card> cards = null;
        try {
            cards = cardRepository.findAllByIdPublisher(idPublisher);
            if(cards == null || cards.isEmpty()) throw new Exception("No card was found for the publisher id: " + idPublisher);
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("CORE_ERR_2");
        }

        log.debug("CardRepositoryImpl--getAllPublisherCards--card count: {}", cards.size());
        log.debug("CardRepositoryImpl--getAllPublisherCards--OUT");
        return cards;
    }

    /**
     * Method used to get a list of cards on which an employee worked on.
     * @param idAssignedUser
     * @return List
     */
    public List<Card> getAllAssigneeCards(final Long idAssignedUser) {
        log.debug("CardRepositoryImpl--getAllAssigneeCards--idAssignedUser: {}", idAssignedUser);

        List<Card> cards = null;
        try {
            cards = cardRepository.findAllByIdAssignedUser(idAssignedUser);
            if(cards == null || cards.isEmpty()) throw new Exception("No card was found for the assigned user id: " + idAssignedUser);
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("CORE_ERR_2");
        }

        log.debug("CardRepositoryImpl--getAllAssigneeCards--card count: {}", cards.size());
        log.debug("CardRepositoryImpl--getAllAssigneeCards--OUT");
        return cards;
    }
}
