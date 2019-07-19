package com.unbabel.challenge.controller;

import com.unbabel.challenge.facade.data.TranslateFormData;
import com.unbabel.challenge.facade.UnbabelTranslationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Main controller for home page
 */
@Controller
public class UnbabelTranslationController
{
    private static final String TRANSLATION_ENTRY = "translationEntryRow";
    private static final String FRAG_TARGET_LANGUAGE = "fragments/targetLanguages :: targetLanguagesSelector";
    private static final String FRAG_TRANSLATION_ENTRY = "fragments/newEntryRow :: translationEntry";
    private static final String TARGET_LANGUAGES = "targetLanguages";
    private static final String AVAILABLE_SOURCELANGUAGES = "availableSourcelanguagues";
    private static final String TRANSLATIONS_ENTRIES = "translationsEntries";
    private static final String INDEX_PAGE = "index";

    @Autowired
    private UnbabelTranslationFacade translationFacade;

    /**
     * Accepts get requests to the "/" url, generates random messages
     * and renders them in thymeleaf template (index.html).
     * @param model inject objects into thymeleaf template
     * @return generated html home page
     */
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute(AVAILABLE_SOURCELANGUAGES, translationFacade.getAvailableSourceLanguages());
        model.addAttribute(TRANSLATIONS_ENTRIES, translationFacade.getAllTranslations());

        return INDEX_PAGE;
    }

    /**
     * Accepts get request to the url "/targetlanguages/{language}" with the source language code to return
     * the compatible target languages
     * @param model inject objects into thymeleaf template
     * @param languageCode selected source language code
     * @return generated html fragment to display target languages
     */
    @RequestMapping(value= "/targetlanguages/{language}", method = RequestMethod.GET)
    public String getCompatibleTargetLanguages(Model model, @PathVariable("language") String languageCode){
        model.addAttribute(TARGET_LANGUAGES, translationFacade.getCompatibleTargetLanguagues(languageCode));

        return FRAG_TARGET_LANGUAGE;
    }

    /**
     * Accepts post request to the url "/translate" to create a translation into Unbabel service
     * @param model inject objects into thymeleaf template
     * @param formData data in json format with parameters to create a translation
     * @return generated html fragment with the information about the created translation
     */
    @PostMapping("/translate")
    public String processForm(Model model,@Valid @RequestBody TranslateFormData formData) {
        model.addAttribute(TRANSLATION_ENTRY, translationFacade.submitTranslation(formData));

        return FRAG_TRANSLATION_ENTRY;
    }

}
