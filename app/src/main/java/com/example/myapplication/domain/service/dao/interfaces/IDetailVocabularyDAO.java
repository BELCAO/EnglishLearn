package com.example.myapplication.domain.service.dao.interfaces;

import com.example.myapplication.domain.model.DetailVocabulary;

import java.util.List;

public interface IDetailVocabularyDAO extends IDAO<DetailVocabulary> {
    List<DetailVocabulary> find(int vocabularyId);
}
