package ro.trellteam.core.util.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.core.repository.domain.Comment;
import ro.trellteam.core.data.dto.CommentDto;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment dtoToDomain(CommentDto commentDto);
    CommentDto domainToDto(Comment comment);
}
