package com.unbabel.challenge.service;

import com.unbabel.challenge.dto.languages.AvailableLanguagesDTO;
import com.unbabel.challenge.dto.languages.SourceLanguageDTO;
import com.unbabel.challenge.dto.languages.TargetLanguageDTO;

import java.util.List;

public interface LanguageService
{
    /**
     * Send a get request to the Unbabel language api to fetch available languages for translation
     * The result is stored on session variable, and always be returned if this one is in session
     * to avoid multiple calls to endpoint api
     * @return a DTO object containing available languages
     */
    AvailableLanguagesDTO fetchAvailableLanguages();

    /**
     * Accepts all available languages and return only the available source languages
     * @param availableLanguagesDTO all available languages containing source and target languages
     * @return a list of DTO of source languages
     */
    List<SourceLanguageDTO> filterAllAvailableSourceLanguages(final AvailableLanguagesDTO availableLanguagesDTO);

    /**
     * This will return the target languages compatibles with the given a source language code
     * @param availableLanguagesDTO all available languages containing source and target languages
     * @param languageCode code of the source language
     * @return a list of DTO of target languages
     */
    List<TargetLanguageDTO> findCompatibleTargetLanguages(final AvailableLanguagesDTO availableLanguagesDTO, String languageCode);
}
