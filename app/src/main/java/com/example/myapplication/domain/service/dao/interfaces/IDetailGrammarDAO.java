package com.example.myapplication.domain.service.dao.interfaces;

import com.example.myapplication.domain.model.DetailGrammar;

import java.util.List;

public interface IDetailGrammarDAO extends IDAO<DetailGrammar> {
    public List<DetailGrammar> find(int grammarId);
}
