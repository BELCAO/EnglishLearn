package com.example.myapplication.domain.service.dao.interfaces;

import com.example.myapplication.domain.model.Vocabulary;

import java.util.List;

public interface IVocabularyDAO extends IDAO<Vocabulary> {

    List<Vocabulary> find(int courseId);
}
