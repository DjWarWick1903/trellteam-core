package ro.trellteam.core.dto.request.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class UpdateCardRequest {
    @NotNull(message = "CORE_ERR_5")
    @JsonProperty("cardId")
    private Long cardId;

    @NotNull(message = "CORE_ERR_5")
    @JsonProperty("title")
    private String title;

    @NotNull(message = "CORE_ERR_5")
    @JsonProperty("typeId")
    private Long typeId;

    @NotNull(message = "CORE_ERR_5")
    @JsonProperty("urgency")
    private String urgency;

    @NotNull(message = "CORE_ERR_5")
    @JsonProperty("difficulty")
    private String difficulty;

    @NotNull(message = "CORE_ERR_5")
    @NotEmpty(message = "CORE_ERR_5")
    @JsonProperty("description")
    private String description;

    @JsonProperty("notes")
    private String notes;

    @NotNull(message = "CORE_ERR_5")
    @JsonProperty("changed")
    private String changed;

    @NotNull(message = "CORE_ERR_5")
    @NotEmpty(message = "CORE_ERR_5")
    @JsonProperty("userID")
    private Long userID;
}
