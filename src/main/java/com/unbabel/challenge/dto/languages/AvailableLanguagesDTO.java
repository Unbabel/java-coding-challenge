package com.unbabel.challenge.dto.languages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AvailableLanguagesDTO
{
    @JsonProperty("objects")
    private List<LanguageDTO> languages;
}
