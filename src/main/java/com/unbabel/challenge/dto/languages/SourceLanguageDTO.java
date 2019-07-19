package com.unbabel.challenge.dto.languages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceLanguageDTO
{
    @JsonProperty("name")
    private String name;

    @JsonProperty("shortname")
    private String shortname;
}
