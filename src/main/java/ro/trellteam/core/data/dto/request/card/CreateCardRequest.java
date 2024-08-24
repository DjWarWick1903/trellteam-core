package ro.trellteam.core.data.dto.request.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.trellteam.core.data.dto.CardDto;


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
