package com.unbabel.challenge.configuration.beans;

import org.springframework.http.HttpHeaders;

public interface UnbabelApiBuild
{
    /**
     * Builds url for Unbabel language api
     * @return url for unbabel language api
     */
    String buildLanguagueApiUrl();

    /**
     * Builds url for Unbabel translation api
     * @return url for unbabel translation api
     */
    String buildTranslationApiUrl();

    /**
     * Builds Http Header with authorization and media information to consume Unbabel api
     * @return HttpHeader with authorization and media information
     */
    HttpHeaders buildBaseHttpHeader();

    /**
     * Adds a parameter to a given url
     * @param url base url to be modified
     * @param parameter string to add to the base url
     * @return a url string with the provided url and parameter
     */
    String addParameterToUrl(final String url, final String parameter);
}
