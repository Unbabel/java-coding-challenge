package com.unbabel.challenge.configuration;

import com.unbabel.challenge.configuration.beans.ApiRequestBuilderUtil;
import com.unbabel.challenge.configuration.beans.SessionAttributes;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class UnbabelBeansConfig
{

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ApiRequestBuilderUtil getEndpointUrlBuilderUtil(){return new ApiRequestBuilderUtil();}

    @Bean
    public ModelMapper modelMapper() {return new ModelMapper();}

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SessionAttributes SessionAttributes() {
        return new SessionAttributes();
    }


}
