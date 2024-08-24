package ro.trellteam.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.trellteam.core.repository.domain.CardLog;

import java.util.Optional;

public interface CardLogRepo extends JpaRepository<CardLog, Long> {
    CardLog save(CardLog log);
    Optional<CardLog> findById(Long id);
}
