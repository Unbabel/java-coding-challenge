package com.unbabel.challenge.dto.languages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LanguagePairDTO
{
    @JsonProperty("source_language")
    private SourceLanguageDTO sourceLanguage;

    @JsonProperty("target_language")
    private TargetLanguageDTO targetLanguage;
}

