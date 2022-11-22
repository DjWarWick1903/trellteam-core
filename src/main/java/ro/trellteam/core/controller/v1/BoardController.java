package ro.trellteam.core.controller.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.trellteam.core.dto.BoardDto;
import ro.trellteam.core.dto.response.ObjectResponse;
import ro.trellteam.core.exceptions.TrellGenericException;
import ro.trellteam.core.service.v1.BoardService;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/board/v1", produces = APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @PostMapping()
    public ResponseEntity<ObjectResponse> createBoard(@RequestBody @Valid final BoardDto requestBody) {
        log.debug("BoardController--createBoard--IN");
        final URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/board/v1").toUriString());
        log.debug("BoardController--createBoard--uri: {}", uri);

        final ObjectResponse response = boardService.createBoard(requestBody);
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/department/{idDepartment}")
    public ResponseEntity<ObjectResponse> listBoardsByDepartment(@PathVariable Long idDepartment) {
        log.debug("BoardController--listBoardsByDepartment--IN");
        if(idDepartment == null) {
            throw new TrellGenericException("CORE_ERR_4");
        }

        final ObjectResponse response = boardService.listBoardsByDepartmentId(idDepartment);
        return ResponseEntity.ok().body(response);
    }
}
