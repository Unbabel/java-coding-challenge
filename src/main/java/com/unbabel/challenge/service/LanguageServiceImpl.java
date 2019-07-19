package com.unbabel.challenge.service;

import com.unbabel.challenge.configuration.beans.ApiRequestBuilderUtil;
import com.unbabel.challenge.configuration.beans.SessionAttributes;
import com.unbabel.challenge.dto.languages.AvailableLanguagesDTO;
import com.unbabel.challenge.dto.languages.SourceLanguageDTO;
import com.unbabel.challenge.dto.languages.TargetLanguageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LanguageServiceImpl implements LanguageService
{

    private final static String LANG_KEY_ATTRIBUTE = "availableLanguages";
    private final static String ERROR_MSG_LANGUAGE_API = "Could not retrieve available Languages";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ApiRequestBuilderUtil apiRequestBuilder;

    @Autowired
    SessionAttributes sessionAttributes;

    @Override
    public AvailableLanguagesDTO fetchAvailableLanguages(){

        if(sessionAttributes.getAttributes().containsKey(LANG_KEY_ATTRIBUTE)){
            return (AvailableLanguagesDTO) sessionAttributes.getAttributes().get(LANG_KEY_ATTRIBUTE);
        }else{

            try {
            HttpEntity requestEntity = new HttpEntity<>(apiRequestBuilder.buildBaseHttpHeader());

            ResponseEntity<AvailableLanguagesDTO> responseEntity = restTemplate.exchange(
                    apiRequestBuilder.buildLanguagueApiUrl(),
                    HttpMethod.GET,
                    requestEntity,
                    AvailableLanguagesDTO.class
            );

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                sessionAttributes.getAttributes().put(LANG_KEY_ATTRIBUTE, responseEntity.getBody());
                return responseEntity.getBody();
            }

            } catch (HttpClientErrorException httpException) {
                log.error(ERROR_MSG_LANGUAGE_API,httpException.getStatusCode().toString());
            } catch(Exception generalException) {
                log.error(generalException.toString());
            }
        }

        return new AvailableLanguagesDTO();
    }

    @Override
    public List<SourceLanguageDTO> filterAllAvailableSourceLanguages(final AvailableLanguagesDTO availableLanguagesDTO){
        if(!CollectionUtils.isEmpty(availableLanguagesDTO.getLanguages())){
            return availableLanguagesDTO.getLanguages().stream()
                    .map(languagePair -> languagePair.getLanguagePairDTO().getSourceLanguage())
                    .collect(Collectors.toList());
        }else{
            return Collections.emptyList();
        }

    }

    @Override
    public List<TargetLanguageDTO> findCompatibleTargetLanguages(final AvailableLanguagesDTO availableLanguagesDTO, String languageCode){
        if(!CollectionUtils.isEmpty(availableLanguagesDTO.getLanguages())){
            return availableLanguagesDTO.getLanguages().stream()
                    .filter(languagePair -> languageCode.equals(languagePair.getLanguagePairDTO().getSourceLanguage().getShortname()))
                    .map(languagePair -> languagePair.getLanguagePairDTO().getTargetLanguage())
                    .collect(Collectors.toList());
        }else{
            return Collections.emptyList();
        }

    }

}
