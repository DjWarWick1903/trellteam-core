package ro.trellteam.core.service.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.trellteam.core.domain.Board;
import ro.trellteam.core.dto.BoardDto;
import ro.trellteam.core.dto.response.ObjectResponse;
import ro.trellteam.core.mapper.BoardMapper;
import ro.trellteam.core.repository.BoardRepositoryImpl;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BoardService {
    private final BoardRepositoryImpl boardRepository;
    private final BoardMapper boardMapper;

    public ObjectResponse createBoard(BoardDto request) {
        log.debug("BoardService--createBoard--request: {}", request);
        Board board = boardMapper.dtoToDomain(request);
        board.setDateCreated(new java.sql.Date((new Date()).getTime()));
        board = boardRepository.createBoard(board);
        return new ObjectResponse(boardMapper.domainToDto(board));
    }

    public ObjectResponse listBoardsByDepartmentId(final Long departmentID) {
        log.debug("BoardService--listBoardsByDepartmentId--departmentID: {}", departmentID);
        final List<Board> boards = boardRepository.listDepartmentBoards(departmentID);
        final ObjectResponse response = new ObjectResponse(
                boards.stream()
                        .map(boardMapper::domainToDto)
                        .collect(Collectors.toList())
        );
        return new ObjectResponse(response);
    }
}
