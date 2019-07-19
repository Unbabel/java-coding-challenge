package com.unbabel.challenge.service;

import com.unbabel.challenge.configuration.beans.ApiRequestBuilderUtil;
import com.unbabel.challenge.configuration.beans.SessionAttributes;
import com.unbabel.challenge.dto.languages.AvailableLanguagesDTO;
import com.unbabel.challenge.dto.languages.SourceLanguageDTO;
import com.unbabel.challenge.dto.languages.TargetLanguageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LanguageServiceTest
{
    @Mock
    RestTemplate restTemplate;

    @Mock
    ApiRequestBuilderUtil apiRequestBuilder;

    @Spy
    SessionAttributes sessionAttributes;

    @Spy
    AvailableLanguagesDTO availableLanguagesDTO;

    @Spy
    HashMap<String, Object> dummySessionAttributes;

    @Mock
    HttpHeaders httpHeaders;

    @Mock
    ResponseEntity<AvailableLanguagesDTO> responseEntity;

    @InjectMocks
    LanguageServiceImpl languageServiceImpl;


    @Test
    public void should_return_languages_from_session_attribute()
    {
        dummySessionAttributes.put("availableLanguages", availableLanguagesDTO);

        when(sessionAttributes.getAttributes()).thenReturn(dummySessionAttributes);
        AvailableLanguagesDTO result = languageServiceImpl.fetchAvailableLanguages();
        assertThat(result, notNullValue());
        assertThat(result, is(availableLanguagesDTO));
    }

    @Test
    public void should_return_languages_from_api_call(){
        when(apiRequestBuilder.buildLanguagueApiUrl()).thenReturn("https://sandbox.unbabel.com/tapi/v2/language_pair/");
        when(apiRequestBuilder.buildBaseHttpHeader()).thenReturn(httpHeaders);


        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(AvailableLanguagesDTO.class)))
                .thenReturn(responseEntity);
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(responseEntity.getBody()).thenReturn(availableLanguagesDTO);

        AvailableLanguagesDTO result = languageServiceImpl.fetchAvailableLanguages();
        assertThat(result, notNullValue());
        assertThat(result, is(availableLanguagesDTO));
    }

    @Test
    public void testFilterAllAvailableSourceLanguages()
    {
        List<SourceLanguageDTO> result = languageServiceImpl.filterAllAvailableSourceLanguages(new AvailableLanguagesDTO());
    }

    @Test
    public void testFindCompatibleTargetLanguages()
    {
        List<TargetLanguageDTO> result = languageServiceImpl.findCompatibleTargetLanguages(new AvailableLanguagesDTO(), "languageCode");
    }

}
