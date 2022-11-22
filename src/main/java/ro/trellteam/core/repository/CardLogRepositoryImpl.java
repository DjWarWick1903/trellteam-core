package ro.trellteam.core.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.trellteam.core.domain.CardLog;
import ro.trellteam.core.exceptions.TrellGenericException;

@Component
@Slf4j
@RequiredArgsConstructor
public class CardLogRepositoryImpl {
    private final CardLogRepository cardLogRepository;

    /**
     * Method used to save a card log in the database.
     * @param cardLog
     * @return CardLog
     */
    public CardLog save(CardLog cardLog) {
        log.debug("CardLogRepositoryImpl--save--IN");
        cardLog = cardLogRepository.save(cardLog);
        log.debug("CardLogRepositoryImpl--save--cardLog: {}", cardLog);
        return cardLog;
    }

    /**
     * Method used to find a card log in the database starting from it's id.
     * @param id
     * @return CardLog
     */
    public CardLog findById(Long id) {
        log.debug("CardLogRepositoryImpl--findById--id: {}", id);

        CardLog cardLog = null;
        try {
            cardLog = cardLogRepository.findById(id).get();
        } catch(final Exception exception) {
            log.error(exception.getMessage());
            throw new TrellGenericException("CORE_ERR_3");
        }

        log.debug("CardLogRepositoryImpl--findById--cardLog: {}", cardLog);

        return cardLog;
    }
}
