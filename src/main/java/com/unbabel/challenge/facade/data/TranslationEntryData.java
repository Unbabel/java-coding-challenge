package com.unbabel.challenge.facade.data;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TranslationEntryData
{
    private String sourceLanguage;

    private String status;

    private String targetLanguage;

    private String text;

    private String translatedText;
}
