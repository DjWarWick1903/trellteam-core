package ro.trellteam.core.controller.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.trellteam.core.dto.request.card.*;
import ro.trellteam.core.dto.response.ObjectResponse;
import ro.trellteam.core.exceptions.TrellGenericException;
import ro.trellteam.core.service.v1.CardService;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/card/v1", produces = APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class CardController {
    private final CardService cardService;

    @PostMapping("/main")
    public ResponseEntity<ObjectResponse> createTicket(@RequestBody @Valid CreateCardRequest payload) {
        log.debug("CardController--createTicket--IN");
        final URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/card/v1/main").toUriString());
        log.debug("CardController--createTicket--uri: {}", uri);

        final ObjectResponse response = cardService.createTicket(payload);
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/main/{id}")
    public ResponseEntity<ObjectResponse> getTicketById(@PathVariable Long id) {
        log.debug("CardController--getTicketById--IN");
        if(id == null) {
            throw new TrellGenericException("CORE_ERR_4");
        }

        final ObjectResponse response = cardService.getCard(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/main/todo")
    public ResponseEntity<ObjectResponse> updateTicketInToDo(@RequestBody @Valid UpdateCardStatusRequest payload) {
        log.debug("CardController--updateTicketInToDo--IN");
        final ObjectResponse response = cardService.updateCardStatusInToDo(payload);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/main/progress")
    public ResponseEntity<ObjectResponse> updateTicketInProgress(@RequestBody @Valid UpdateCardStatusRequest payload) {
        log.debug("CardController--updateTicketInProgress--IN");
        final ObjectResponse response = cardService.updateCardStatusInProgress(payload);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/main/done")
    public ResponseEntity<ObjectResponse> updateTicketInDone(@RequestBody @Valid UpdateCardStatusRequest payload) {
        log.debug("CardController--updateTicketInDone--IN");
        final ObjectResponse response = cardService.updateCardStatusInDone(payload);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/main")
    public ResponseEntity<ObjectResponse> updateTicket(@RequestBody @Valid UpdateCardRequest payload) {
        log.debug("CardController--updateTicket--IN");
        final ObjectResponse response = cardService.updateCard(payload);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/assign")
    public ResponseEntity<ObjectResponse> assignTicket(@RequestBody @Valid AssignCardRequest payload) {
        log.debug("CardController--assignTicket--IN");
        final ObjectResponse response = cardService.assignCard(payload);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/unassign/{id}")
    public ResponseEntity<ObjectResponse> unassignTicket(@PathVariable Long id) {
        log.debug("CardController--unassignTicket--IN");
        if(id == null) {
            throw new TrellGenericException("CORE_ERR_4");
        }

        final ObjectResponse response = cardService.unassignCard(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/comment/{idCard}")
    public ResponseEntity<ObjectResponse> getComments(@PathVariable Long idCard) {
        log.debug("CardController--getComments--IN");
        if(idCard == null) {
            throw new TrellGenericException("CORE_ERR_4");
        }

        final ObjectResponse response = new ObjectResponse(cardService.getCardComments(idCard));
        return ResponseEntity.ok().body(response);
    }

/*    @PostMapping("/comment")
    public ResponseEntity<ObjectResponse> addCardComment(@RequestBody @Valid AddCardCommentRequest payload) {
        log.debug("CardController--addCardComment--IN");

        final URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/card/v1/comment").toUriString());
        log.debug("CardController--addCardComment--uri: {}", uri);

        final CardDto card = cardService.addCommentToCard(payload);
        final ObjectResponse response = new ObjectResponse(card);
        return ResponseEntity.created(uri).body(response);
    }*/
}
