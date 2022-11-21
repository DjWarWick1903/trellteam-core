package ro.trellteam.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.trellteam.core.enums.CardStatusEnum;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Long id;

    @NotNull(message = "CORE_ERR_5")
    private String title;

    private String difficulty;

    @NotNull(message = "CORE_ERR_5")
    private String description;

    private String notes;

    private CardStatusEnum status;

    @NotNull(message = "CORE_ERR_5")
    private String urgency;

    @NotNull(message = "CORE_ERR_5")
    private Long idPublisher;
    private Long idAssignedUser;

    private TypeDto type;

    private Set<CommentDto> comments;
    private Set<CardLogDto> logs;
}
