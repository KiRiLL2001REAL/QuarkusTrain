package edu.my.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieDTO {
    @Schema(title = "Movie id", required = true)
    private Long id;
    @NotBlank
    @Schema(title = "Movie name", required = true)
    private String name;
    @Schema(title = "Movie description", required = true)
    private String description;
    @Schema(title = "The reasons why it is worth watching this movie", required = true)
    private String reasonsToView;
    @Schema(title = "Interesting facts about this movie", required = true)
    private String facts;
    @Min(value = 60, message = "The value must be more than 59 (at least 1 minute)")
    @Max(value = 21600, message = "The value must be less than 21601 (at most 6 hours)")
    @Schema(title = "The duration of the movie in seconds", required = true)
    private Integer durationInSeconds;
    @Schema(title = "Movie distributor", required = true)
    private String distributor;
    @NotBlank
    @Schema(title = "The country in which the film was shot", required = true)
    private String country;
    @Min(value = 1895, message = "The value must be more than 1894")
    @Max(value = 2100, message = "The value must be less than 2101")
    @Schema(title = "The year of the film's release between 1985 and 2100", required = true)
    private Integer releaseYear;

    @JsonIgnore
    @Schema(title = "Link to the movie's tags", required = false, hidden = true)
    private Set<MovieHasTagDTO> movieHasTagSet;
}
