package ro.trellteam.core.util.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.core.repository.domain.Type;
import ro.trellteam.core.data.dto.TypeDto;

@Mapper(componentModel = "spring")
public interface TypeMapper {
    Type dtoToDomain(TypeDto typeDto);
    TypeDto domainToDto(Type type);
}
