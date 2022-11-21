package ro.trellteam.core.service.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.trellteam.core.domain.*;
import ro.trellteam.core.dto.CardDto;
import ro.trellteam.core.dto.request.card.*;
import ro.trellteam.core.dto.response.ObjectResponse;
import ro.trellteam.core.enums.CardStatusEnum;
import ro.trellteam.core.exceptions.TrellGenericException;
import ro.trellteam.core.helper.GeneralHelper;
import ro.trellteam.core.mapper.CardMapper;
import ro.trellteam.core.repository.*;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CardService {

    // Repositories
    private final TypeRepositoryImpl typeRepository;
    private final BoardRepositoryImpl boardRepository;
    private final CardLogRepositoryImpl cardLogRepository;
    private final CardRepositoryImpl cardRepository;
    private final CommentRepositoryImpl commentRepository;

    // Mappers
    private final CardMapper cardMapper;

    @Transactional
    public ObjectResponse createTicket(final CreateCardRequest request) {
        log.debug("CardService::createTicket: {}", request);
        final Type type = typeRepository.findById(request.getTypeId());

        Card card = cardMapper.dtoToDomain(request.getCard());
        card.setType(type);
        card.setStatus(CardStatusEnum.TO_DO);

        CardLog cardLog = new CardLog();
        cardLog.setAccountID(card.getIdPublisher());
        cardLog.setLogDate(new Date());
        cardLog.setText("Ticket created");

        try {
            cardLog = cardLogRepository.save(cardLog);
            card.addLog(cardLog);

            card = cardRepository.save(card);
            log.debug("TransactionalOperations--createCard--card: {}", card);

            Board board = boardRepository.getBoardById(request.getBoardId());
            board.addCard(card);
            boardRepository.createBoard(board);
        } catch(final Exception exception) {
            log.error(exception.getMessage());
            throw new TrellGenericException("CORE_ERR_6");
        }

        final ObjectResponse response = new ObjectResponse(cardMapper.domainToDto(card));
        return response;
    }

    @Transactional
    public ObjectResponse updateCardStatusInToDo(final UpdateCardStatusRequest request) {
        log.debug("CardService::updateCardStatusInToDo: {}", request);

        Card card = cardRepository.findById(request.getCardId());

        CardLog cardLog = new CardLog();
        cardLog.setLogDate(new Date());
        cardLog.setAccountID(request.getUserID());
        cardLog.setText("Status: " + card.getStatus() + " ---> TO DO");

        try {
            cardLog = cardLogRepository.save(cardLog);

            card.setStatus(CardStatusEnum.TO_DO);
            card.addLog(cardLog);
            card = cardRepository.save(card);
        } catch(final Exception exception) {
            log.error(exception.getMessage());
            throw new TrellGenericException("CORE_ERR_7");
        }

        return new ObjectResponse(cardMapper.domainToDto(card));
    }

    @Transactional
    public ObjectResponse updateCardStatusInProgress(final UpdateCardStatusRequest request) {
        log.debug("CardService::updateCardStatusInProgress: {}", request);

        Card card = cardRepository.findById(request.getCardId());

        CardLog cardLog = new CardLog();
        cardLog.setLogDate(new Date());
        cardLog.setAccountID(request.getUserID());
        cardLog.setText("Status: " + card.getStatus() + " ---> IN PROGRESS");

        try {
            cardLog = cardLogRepository.save(cardLog);

            card.setStatus(CardStatusEnum.IN_PROGRESS);
            card.addLog(cardLog);
            card = cardRepository.save(card);
        } catch(final Exception exception) {
            log.error(exception.getMessage());
            throw new TrellGenericException("CORE_ERR_7");
        }

        return new ObjectResponse(cardMapper.domainToDto(card));
    }

    @Transactional
    public ObjectResponse updateCardStatusInDone(final UpdateCardStatusRequest request) {
        log.debug("CardService::updateCardStatusInDone: {}", request);

        Card card = cardRepository.findById(request.getCardId());

        CardLog cardLog = new CardLog();
        cardLog.setLogDate(new Date());
        cardLog.setAccountID(request.getUserID());
        cardLog.setText("Status: " + card.getStatus() + " ---> DONE");

        try {
            cardLog = cardLogRepository.save(cardLog);

            card.setStatus(CardStatusEnum.DONE);
            card.addLog(cardLog);
            card = cardRepository.save(card);
        } catch(final Exception exception) {
            log.error(exception.getMessage());
            throw new TrellGenericException("CORE_ERR_7");
        }

        return new ObjectResponse(cardMapper.domainToDto(card));
    }

    @Transactional
    public ObjectResponse updateCard(final UpdateCardRequest request) {
        log.debug("CardService::updateCard: {}", request);

        Card oldCard = cardRepository.findById(request.getCardId());

        // new object with which we compare the old one and get the log
        Card newCard = new Card();
        newCard.setTitle(request.getTitle());
        final Type type = typeRepository.findById(request.getTypeId());

        newCard.setType(type);
        newCard.setUrgency(request.getUrgency());
        newCard.setDifficulty(request.getDifficulty());
        newCard.setDescription(request.getDescription());
        newCard.setNotes(request.getNotes());

        CardLog cardLog = new CardLog();
        cardLog.setLogDate(new Date());
        cardLog.setAccountID(request.getUserID());
        cardLog.setText(GeneralHelper.getCardLogText(oldCard, newCard, request.getChanged()));

        try {
            cardLog = cardLogRepository.save(cardLog);
            oldCard.addLog(cardLog);
            oldCard = cardRepository.save(oldCard);
        } catch(final Exception exception) {
            log.error(exception.getMessage());
            throw new TrellGenericException("CORE_ERR_8");
        }

        return new ObjectResponse(cardMapper.domainToDto(oldCard));
    }

    @Transactional
    public ObjectResponse assignCard(final AssignCardRequest request) {
        log.debug("CardService::assignCard: {}", request);

        Card card = cardRepository.findById(request.getCardId());

        final Long oldAsigneeID = card.getIdAssignedUser();
        //TODO: call to security to get assigned account username and last account username
        //final String pastUsername = oldAsigneeID == null ? "Undefined" : oldAsignee.getUsername();

        CardLog cardLog = new CardLog();
        cardLog.setLogDate(new Date());
        cardLog.setAccountID(request.getUserID());
        //cardLog.setText("Assigned: " + pastUsername + " ---> " + request.getUsername());

        card.setIdAssignedUser(request.getUserID());

        try {
            cardLog = cardLogRepository.save(cardLog);
            card.addLog(cardLog);
            card = cardRepository.save(card);
        } catch(final Exception exception) {
            log.error(exception.getMessage());
            throw new TrellGenericException("CORE_ERR_9");
        }

        return new ObjectResponse(cardMapper.domainToDto(card));
    }

    @Transactional
    public ObjectResponse unassignCard(final Long id) {
        log.debug("CardService::unassignCard: {}", id);

        Card card = cardRepository.findById(id);

        CardLog cardLog = new CardLog();
        cardLog.setLogDate(new Date());
        cardLog.setAccountID(card.getIdAssignedUser());
        //TODO: get account user
        //cardLog.setText("Assigned: " + card.getAssigned().getUsername() + " ---> Undefined");

        card.setIdAssignedUser(null);

        try {
            cardLog = cardLogRepository.save(cardLog);
            card.addLog(cardLog);
            card = cardRepository.save(card);
        } catch(final Exception exception) {
            log.error(exception.getMessage());
            throw new TrellGenericException("CORE_ERR_10");
        }

        return new ObjectResponse(cardMapper.domainToDto(card));
    }

    public ObjectResponse getCardComments(final Long idCard) {
        final CardDto card = cardMapper.domainToDto(cardRepository.findById(idCard));
        return new ObjectResponse(card.getComments());
    }

    public ObjectResponse getCard(final Long id) {
        final CardDto card = cardMapper.domainToDto(cardRepository.findById(id));
        return new ObjectResponse(card);
    }

    /*@Transactional
    public CardDto addCommentToCard(final AddCardCommentRequest request) {
        log.debug("CardService::addCommentToCard: {}", request);

        //TODO: get account from security with username
        final Account user = accountService.getAccount(request.getUsername());
        final Card card = findById(request.getCardId());

        Comment comment = new Comment();
        comment.setAccountID(user);
        comment.setCommentDate(new Date());
        comment.setText(request.getComment());

        *//*CardLog cardLog = new CardLog();
        cardLog.setUser(user);
        cardLog.setLogDate(new Date());
        cardLog.setText("User " + user.getUsername() + " added comment: " + request.getComment());*//*

        comment = commentRepository.save(comment);
        cardLog = cardLogRepository.save(cardLog);

        card.addComment(comment);
        card.addLog(cardLog);

        card = cardRepository.save(card);

        return cardMapper.domainToDto(card);
    }*/
}
