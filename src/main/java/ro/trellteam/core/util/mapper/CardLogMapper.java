package ro.trellteam.core.util.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.core.repository.domain.CardLog;
import ro.trellteam.core.data.dto.CardLogDto;

@Mapper(componentModel = "spring")
public interface CardLogMapper {
    CardLog dtoToDomain(CardLogDto cardLogDto);
    CardLogDto domainToDto(CardLog cardLog);
}
