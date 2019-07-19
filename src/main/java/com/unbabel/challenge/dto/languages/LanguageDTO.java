package com.unbabel.challenge.dto.languages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LanguageDTO
{
    @JsonProperty("lang_pair")
    private LanguagePairDTO languagePairDTO;
}
