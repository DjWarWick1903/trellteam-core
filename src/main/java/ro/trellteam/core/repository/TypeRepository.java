package ro.trellteam.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.trellteam.core.domain.Type;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findByIdOrganisation(Long idOrganisation);
    Type save(Type type);
    Optional<Type> findById(Long id);
}
