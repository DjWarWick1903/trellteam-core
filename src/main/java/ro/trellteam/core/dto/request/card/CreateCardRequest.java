package ro.trellteam.core.dto.request.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.trellteam.core.dto.CardDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CreateCardRequest {
    @NotNull(message = "CORE_ERR_5")
    @JsonProperty("boardId")
    private Long boardId;

    @NotNull(message = "CORE_ERR_5")
    @JsonProperty("typeId")
    private Long typeId;

    @Valid
    @NotNull(message = "CORE_ERR_5")
    @JsonProperty("card")
    private CardDto card;
}
