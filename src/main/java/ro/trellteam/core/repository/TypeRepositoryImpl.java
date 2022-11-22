package ro.trellteam.core.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.trellteam.core.domain.Type;
import ro.trellteam.core.exceptions.TrellGenericException;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class TypeRepositoryImpl {
    private TypeRepository typeRepository;

    /**
     * Method used to save a type inside the database.
     * @param type
     * @return Type
     */
    public Type save(Type type) {
        log.debug("TypeRepositoryImpl--save--IN");
        type = typeRepository.save(type);
        log.debug("TypeRepositoryImpl--save--type: {}", type.toString());
        return type;
    }

    /**
     * Method used to get a list of types of an organisation.
     * @param idOrganisation
     * @return List
     */
    public List<Type> listTypesByOrganisation(final Long idOrganisation) {
        log.debug("TypeRepositoryImpl--listTypesByOrganisation--IN");

        List<Type> types = null;
        try {
            types = typeRepository.findByIdOrganisation(idOrganisation);
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("TRELL_ERR_1");
        }

        log.debug("TypeRepositoryImpl--listTypesByOrganisation--type count: {}", types.size());

        return types;
    }

    /**
     * Method used to get a type from the database using it's id.
     * @param id
     * @return Type
     */
    public Type findById(final Long id) {
        log.debug("TypeRepositoryImpl--findById--IN");

        Type type = null;
        try {
            type = typeRepository.findById(id).get();
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("TRELL_ERR_1");
        }

        log.debug("TypeRepositoryImpl--findById--type: {}", type.toString());

        return type;
    }
}
