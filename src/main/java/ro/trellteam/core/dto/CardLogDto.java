package ro.trellteam.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardLogDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("text")
    private String text;
    @JsonProperty("date")
    private Date logDate;
    @JsonProperty("accountID")
    private Long accountID;
}
