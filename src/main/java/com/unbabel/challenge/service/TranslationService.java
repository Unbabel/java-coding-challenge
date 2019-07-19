package com.unbabel.challenge.service;

import com.unbabel.challenge.dto.translation.TranslationResponseDTO;
import com.unbabel.challenge.facade.data.TranslateFormData;

import java.util.List;
import java.util.Optional;

public interface TranslationService
{
    /**
     * Sends a post request to Unbabel translation api to create a new translation and caches it to the database
     * @param formData contains the necessary data to create a translation request
     * @return Optional object containing a DTO with the information of the created translation
     */
    Optional<TranslationResponseDTO> createTranslation(final TranslateFormData formData);

    /**
     * Gets the cached translations and updates its information from get request from the unbabel translation api
     * @return a List of DTO with the updated translations made by current user
     */
    List<TranslationResponseDTO> retrieveAllTranslations();
}
