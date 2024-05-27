package com.example.myapplication.domain.service.dao.interfaces;

import com.example.myapplication.domain.model.Grammar;

import java.util.List;

public interface IGrammarDAO extends IDAO<Grammar> {
    public List<Grammar> find(int courseId);
}
