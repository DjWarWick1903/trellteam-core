package ro.trellteam.core.util.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.core.repository.domain.Board;
import ro.trellteam.core.data.dto.BoardDto;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    BoardDto domainToDto(Board board);
    Board dtoToDomain(BoardDto boardDto);
}
