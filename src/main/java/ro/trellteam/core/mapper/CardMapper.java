package ro.trellteam.core.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.core.domain.Card;
import ro.trellteam.core.dto.CardDto;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardDto domainToDto(Card card);
    Card dtoToDomain(CardDto cardDto);
}
