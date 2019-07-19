package com.unbabel.challenge.repositories;

import com.unbabel.challenge.model.TranslationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepository extends CrudRepository<TranslationModel, Long>
{
}
