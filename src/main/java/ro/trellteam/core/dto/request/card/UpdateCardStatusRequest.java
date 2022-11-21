package ro.trellteam.core.dto.request.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class UpdateCardStatusRequest {
    @NotNull(message = "CORE_ERR_5")
    @JsonProperty("id")
    private Long cardId;

    @NotNull(message = "CORE_ERR_5")
    @JsonProperty("userID")
    private Long userID;
}
