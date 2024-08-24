package ro.trellteam.core.data.dto.request.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AssignCardRequest {
    @NotNull(message = "CORE_ERR_5")
    @JsonProperty("id")
    private Long cardId;

    @NotNull(message = "CORE_ERR_5")
    @JsonProperty("userID")
    private Long userID;
}
