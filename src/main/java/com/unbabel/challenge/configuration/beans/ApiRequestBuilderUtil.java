package com.unbabel.challenge.configuration.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unbabel.challenge.configuration.UnbabelEndpointProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Slf4j
public class ApiRequestBuilderUtil implements UnbabelApiBuild
{
    private final static String AUTH_HEADER_SUFIX = "ApiKey";
    private final static String AUTH_HEADER_NAME = "Authorization";
    private static final String SLASH = "/";

    @Autowired
    UnbabelEndpointProperties endpointProperties;


    @Override
    public String buildLanguagueApiUrl(){
        return buildEndpointUrl(endpointProperties.getLanguagePairApiUrl()).toUriString();
    }

    @Override
    public String buildTranslationApiUrl(){
        return buildEndpointUrl(endpointProperties.getTranslationApiUrl()).toUriString();
    }

    @Override
    public String addParameterToUrl(final String url, final String parameter){
        return url + parameter;
    }

    @Override
    public HttpHeaders buildBaseHttpHeader(){

        HttpHeaders requestHeaders = buildAuthorizattionHttpHeader();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        return requestHeaders;
    }

    private HttpHeaders buildAuthorizattionHttpHeader(){
        String authorizationHeader = AUTH_HEADER_SUFIX + " "
                + endpointProperties.getUserNameParamValue() + ":"
                + endpointProperties.getApiKeyParamValue();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(AUTH_HEADER_NAME, authorizationHeader);

        return requestHeaders;
    }

    protected UriComponentsBuilder buildEndpointUrl(final String apiUrl){
        return UriComponentsBuilder.fromUriString(endpointProperties.getBaseUrl() + apiUrl);
    }


}
