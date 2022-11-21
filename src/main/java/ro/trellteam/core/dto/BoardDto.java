package ro.trellteam.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    @NotNull(message = "CORE_ERR_5")
    private String title;

    @JsonProperty("dateCreated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateCreated;

    @JsonProperty("departmentId")
    @NotNull(message = "CORE_ERR_5")
    private Long idDep;

    @JsonProperty("version")
    @NotNull(message = "CORE_ERR_5")
    private String version;

    @JsonProperty("releaseDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "CORE_ERR_5")
    private Date release;

    @JsonProperty("cards")
    private List<CardDto> cards;
}
