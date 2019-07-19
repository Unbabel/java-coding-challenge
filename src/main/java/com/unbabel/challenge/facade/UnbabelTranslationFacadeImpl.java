package com.unbabel.challenge.facade;

import com.unbabel.challenge.dto.translation.TranslationResponseDTO;
import com.unbabel.challenge.facade.data.LanguageData;
import com.unbabel.challenge.facade.data.TranslateFormData;
import com.unbabel.challenge.dto.languages.AvailableLanguagesDTO;
import com.unbabel.challenge.dto.languages.SourceLanguageDTO;
import com.unbabel.challenge.dto.languages.TargetLanguageDTO;
import com.unbabel.challenge.facade.data.TranslationEntryData;
import com.unbabel.challenge.service.LanguageService;
import com.unbabel.challenge.service.TranslationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class UnbabelTranslationFacadeImpl implements UnbabelTranslationFacade
{

    @Autowired
    LanguageService languageService;

    @Autowired
    TranslationService translationService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<LanguageData> getAvailableSourceLanguages(){
        AvailableLanguagesDTO availableLanguagesDTO = languageService.fetchAvailableLanguages();
        List<SourceLanguageDTO> sourceLanguageDTOS =
                languageService.filterAllAvailableSourceLanguages(availableLanguagesDTO).stream()
                .filter(distinctByKey(SourceLanguageDTO::getName))
                .collect(Collectors.toList());
        Type listType = new TypeToken<List<LanguageData>>() {}.getType();

        return modelMapper.map(sourceLanguageDTOS, listType);
    }

    @Override
    public List<LanguageData> getCompatibleTargetLanguagues(final String languageCode)
    {
        AvailableLanguagesDTO availableLanguagesDTO = languageService.fetchAvailableLanguages();
        List<TargetLanguageDTO> targetLanguageDTOS = languageService.findCompatibleTargetLanguages(availableLanguagesDTO, languageCode);
        Type listType = new TypeToken<List<LanguageData>>() {}.getType();

        return modelMapper.map(targetLanguageDTOS, listType);
    }

    @Override
    public TranslationEntryData submitTranslation(final TranslateFormData formData){
        Optional<TranslationResponseDTO> translationResponseDTO = translationService.createTranslation(formData);
        if(translationResponseDTO.isPresent()){
            return modelMapper.map(translationResponseDTO.get(), TranslationEntryData.class);
        }else{
            return new TranslationEntryData();
        }
    }

    @Override
    public List<TranslationEntryData> getAllTranslations(){
        Type listType = new TypeToken<List<TranslationEntryData>>() {}.getType();
        return modelMapper.map(translationService.retrieveAllTranslations(), listType);
    }

    protected static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
