package ro.trellteam.core.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeDto {
    @JsonProperty("id")
    private Long id;
    @NotNull(message = "TRELL_ERR_8")
    @JsonProperty("name")
    private String name;
    @NotNull(message = "TRELL_ERR_8")
    @JsonProperty("idOrganisation")
    private Long idOrganisation;
}
