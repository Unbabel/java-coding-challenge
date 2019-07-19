package com.unbabel.challenge.facade;

import com.unbabel.challenge.facade.data.LanguageData;
import com.unbabel.challenge.facade.data.TranslateFormData;
import com.unbabel.challenge.facade.data.TranslationEntryData;

import java.util.List;

public interface UnbabelTranslationFacade
{
    /**
     * Retrieve all available source languages from service layer
     * @return List of Language Data for use in view layer
     */
    List<LanguageData> getAvailableSourceLanguages();

    /**
     * Retrieve all available target languages from service layer
     * @return List of Language Data for use in view layer
     */
    List<LanguageData> getCompatibleTargetLanguagues(final String languageCode);

    /**
     * Accepts form data submitted by view layer to the service layer in order to create a translation
     * @param formData contains the necessary data to create a translation
     * @return return information about the created translation entry
     */
    TranslationEntryData submitTranslation(final TranslateFormData formData);

    /**
     * Gets all translations made by the current user from the service layer
     * @return List of translation data, each with information about a translation made by the current user
     */
    List<TranslationEntryData> getAllTranslations();

}
