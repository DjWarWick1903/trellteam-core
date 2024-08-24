package ro.trellteam.core.util.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.core.repository.domain.Card;
import ro.trellteam.core.data.dto.CardDto;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardDto domainToDto(Card card);
    Card dtoToDomain(CardDto cardDto);
}
