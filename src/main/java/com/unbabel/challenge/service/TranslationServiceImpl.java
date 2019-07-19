package com.unbabel.challenge.service;

import com.unbabel.challenge.facade.data.TranslateFormData;
import com.unbabel.challenge.configuration.beans.ApiRequestBuilderUtil;
import com.unbabel.challenge.dto.translation.TranslationResponseDTO;
import com.unbabel.challenge.model.TranslationModel;
import com.unbabel.challenge.repositories.TranslationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class TranslationServiceImpl implements TranslationService
{
    private static final String ERROR_MSG_TRANSLATION_API = "Call to Translation API returned: ";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ApiRequestBuilderUtil apiRequestBuilder;

    @Autowired
    TranslationRepository translationRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Optional<TranslationResponseDTO> createTranslation(final TranslateFormData formData){

        try {
            HttpEntity<TranslateFormData> requestEntity = new HttpEntity<>(formData,
                    apiRequestBuilder.buildBaseHttpHeader());

            ResponseEntity<TranslationResponseDTO> responseEntity = restTemplate.exchange(
                    apiRequestBuilder.buildTranslationApiUrl(),
                    HttpMethod.POST,
                    requestEntity,
                    TranslationResponseDTO.class
            );

            if(responseEntity.getStatusCode() == HttpStatus.CREATED){
                TranslationResponseDTO responseDTO = responseEntity.getBody();
                translationRepository.save(modelMapper.map(responseDTO, TranslationModel.class));

                return Optional.of(responseDTO);
            }

        } catch (HttpClientErrorException httpException) {
            log.error(ERROR_MSG_TRANSLATION_API,httpException.getStatusCode().toString());
        } catch(Exception generalException) {
            log.error(generalException.toString());
        }

            return Optional.empty();
    }

    @Override
    public List<TranslationResponseDTO> retrieveAllTranslations(){

            List<TranslationResponseDTO> allTranslations = Collections.synchronizedList(new ArrayList());
            Iterable<TranslationModel> translations = translationRepository.findAll();
            Stream<TranslationModel> translationStream = StreamSupport.stream(translations.spliterator(), Boolean.TRUE);

            translationStream.forEach(translationModel -> {
                    ResponseEntity<TranslationResponseDTO> responseEntity = sendGETRequestForTranslation(translationModel);

                    if(responseEntity.getStatusCode() == HttpStatus.OK){
                        updateTranslationModel(responseEntity);
                        addTranslationEntry(allTranslations, responseEntity.getBody());
                    }
            });

            return allTranslations;
    }

    private ResponseEntity<TranslationResponseDTO> sendGETRequestForTranslation(final TranslationModel translationModel){
        try {

            HttpEntity requestEntity = new HttpEntity<>(apiRequestBuilder.buildBaseHttpHeader());

            return restTemplate.exchange(
                    apiRequestBuilder.addParameterToUrl(apiRequestBuilder.buildTranslationApiUrl(), translationModel.getUid()),
                    HttpMethod.GET,
                    requestEntity,
                    TranslationResponseDTO.class
            );

        } catch (HttpClientErrorException httpException) {
            log.error(ERROR_MSG_TRANSLATION_API,httpException.getStatusCode().toString());
        } catch(Exception generalException) {
            log.error(generalException.toString());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private void updateTranslationModel(ResponseEntity<TranslationResponseDTO> responseEntity){
            TranslationResponseDTO responseDTO = responseEntity.getBody();
            TranslationModel translationModel = modelMapper.map(responseDTO, TranslationModel.class);
            translationRepository.save(translationModel);
    }

    synchronized void addTranslationEntry(List<TranslationResponseDTO> allTranslations, TranslationResponseDTO responseDTO){
        allTranslations.add(responseDTO);
    }

}
