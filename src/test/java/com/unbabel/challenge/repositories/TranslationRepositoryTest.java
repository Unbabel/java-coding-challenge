package com.unbabel.challenge.repositories;

import com.unbabel.challenge.model.TranslationModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TranslationRepositoryTest
{
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TranslationRepository translationRepository;

    TranslationModel translation1, translation2, translation3;

    @Before
    public void setTestData(){
        translation1 = new TranslationModel();
        translation1.setUid("1234");
        translation1.setSourceLanguage("en");
        translation1.setTargetLanguage("fr");
        translation1.setText("Hello World");

        translation2 = new TranslationModel();
        translation2.setUid("12345");
        translation2.setSourceLanguage("en");
        translation2.setTargetLanguage("fr");
        translation2.setText("Hello World");

        translation3 = new TranslationModel();
        translation3.setUid("12346");
        translation3.setSourceLanguage("en");
        translation3.setTargetLanguage("fr");
        translation3.setText("Hello World");
    }

    @Test
    public void should_find_no_customers_if_repository_is_empty(){
        Iterable<TranslationModel> translations = translationRepository.findAll();

        assertThat(translations).isEmpty();
    }

    @Test
    public void should_store_a_translation() {
       translationRepository.save(translation1);

        assertThat(translation1).hasFieldOrPropertyWithValue("uid", "1234");
        assertThat(translation1).hasFieldOrPropertyWithValue("sourceLanguage", "en");
        assertThat(translation1).hasFieldOrPropertyWithValue("targetLanguage", "fr");
        assertThat(translation1).hasFieldOrPropertyWithValue("text", "Hello World");
    }

    @Test
    public void should_find_all_translations() {
        entityManager.persist(translation1);
        entityManager.persist(translation2);
        entityManager.persist(translation3);

        Iterable<TranslationModel> customers = translationRepository.findAll();

        assertThat(customers).hasSize(3).contains(translation1, translation2, translation3);
    }


}