package playground.sortingpagination.ex2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilterDTO(
        @JsonProperty("firstname")
        String firstName,
        @JsonProperty("lastname")
        String lastName,
        @JsonProperty("birthYear")
        Integer birthYear,
        @JsonProperty("salary")
        Integer salary
) {
}
