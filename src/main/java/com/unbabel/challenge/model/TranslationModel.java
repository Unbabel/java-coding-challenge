package com.unbabel.challenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter @Getter
public class TranslationModel
{
    @Id
    private String uid;

    private Double orderNumber;

    private Double price;

    private String sourceLanguage;

    private String status;

    private String targetLanguage;

    private String text;

    private String textFormat;

    private String translatedText;
}



