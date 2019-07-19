package com.unbabel.challenge.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("unbabel.endpoint")
@Setter @Getter
public class UnbabelEndpointProperties
{
    private String baseUrl;
    private String userNameParamKey;
    private String apiKeyParamKey;
    private String userNameParamValue;
    private String apiKeyParamValue;
    private String languagePairApiUrl;
    private String translationApiUrl;

}
