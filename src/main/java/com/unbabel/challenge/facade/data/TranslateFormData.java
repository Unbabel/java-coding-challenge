package com.unbabel.challenge.facade.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter @Getter
public class TranslateFormData
{
    @NotBlank(message = "Text may not be empty")
    @JsonProperty("text")
    String text;

    @NotBlank(message = "Source Language may not be empty")
    @JsonProperty("source_language")
    String sourceLanguage;

    @NotBlank(message = "Target Language may not be empty")
    @JsonProperty("target_language")
    String targetLanguage;
}
