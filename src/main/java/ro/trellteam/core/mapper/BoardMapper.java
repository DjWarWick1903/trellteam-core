package ro.trellteam.core.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.core.domain.Board;
import ro.trellteam.core.dto.BoardDto;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    BoardDto domainToDto(Board board);
    Board dtoToDomain(BoardDto boardDto);
}
