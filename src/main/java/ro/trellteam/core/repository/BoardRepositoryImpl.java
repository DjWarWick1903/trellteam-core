package ro.trellteam.core.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.trellteam.core.domain.Board;
import ro.trellteam.core.domain.Card;
import ro.trellteam.core.exceptions.TrellGenericException;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BoardRepositoryImpl {
    private final BoardRepository boardRepository;

    /**
     * Method to get a list of boards starting from a department id.
     * @param idDepartment
     * @return List
     */
    public List<Board> listDepartmentBoards(Long idDepartment) {
        log.debug("BoardRepositoryImpl--listDepartmentBoards--IN");
        log.debug("BoardRepositoryImpl--listDepartmentBoards--idDepartment: {}", idDepartment);

        List<Board> boards;
        try {
            boards = boardRepository.findBoardsByIdDep(idDepartment);
        } catch(final Exception exception) {
            throw new TrellGenericException("CORE_ERR_1");
        }

        log.debug("BoardRepositoryImpl--listDepartmentBoards--boards: {}", boards.toString());
        log.debug("BoardRepositoryImpl--listDepartmentBoards--OUT");

        return boards;
    }

    /**
     * Method used to create a board.
     * @param board
     * @return Board
     */
    public Board createBoard(Board board) {
        log.debug("BoardRepositoryImpl--createBoard--IN");

        board = boardRepository.save(board);

        log.debug("BoardRepositoryImpl--createBoard--board: {}", board.toString());
        log.debug("BoardRepositoryImpl--createBoard--OUT");

        return board;
    }

    /**
     * Method used to delete a board by it's title.
     * @param title
     */
    public void deleteBoardByTitle(String title) {
        log.debug("BoardRepositoryImpl--deleteBoardByTitle--IN");
        log.debug("BoardRepositoryImpl--deleteBoardByTitle--title: {}", title);

        boardRepository.deleteByTitle(title);

        log.debug("BoardRepositoryImpl--deleteBoardByTitle--OUT");
    }

    /**
     * Method used to delete a board by it's id.
     * @param idDepartment
     */
    public void deleteBoardById(Long idDepartment) {
        log.debug("BoardRepositoryImpl--deleteBoardById--IN");
        log.debug("BoardRepositoryImpl--deleteBoardById--idDepartment: {}", idDepartment);

        boardRepository.deleteById(idDepartment);

        log.debug("BoardRepositoryImpl--deleteBoardById--OUT");
    }

    public Board getBoardById(Long id) {
        log.debug("BoardRepositoryImpl--getBoardById--IN");

        Board board = null;
        try {
            board = boardRepository.findById(id).get();
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("CORE_ERR_1");
        }

        log.debug("BoardRepositoryImpl--getBoardById--board: {}", board.toString());
        log.debug("BoardRepositoryImpl--getBoardById--OUT");
        return board;
    }
}
