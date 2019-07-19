package com.unbabel.challenge.dto.translation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TranslationResponseDTO
{
    @JsonProperty("order_number")
    private Double orderNumber;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("source_language")
    private String sourceLanguage;

    @JsonProperty("status")
    private String status;

    @JsonProperty("target_language")
    private String targetLanguage;

    @JsonProperty("text")
    private String text;

    @JsonProperty("text_format")
    private String textFormat;

    @JsonProperty("translatedText")
    private String translatedText;

    @JsonProperty("uid")
    private String uid;
}
