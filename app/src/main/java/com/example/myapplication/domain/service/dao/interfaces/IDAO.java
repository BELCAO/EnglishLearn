package com.example.myapplication.domain.service.dao.interfaces;

import java.util.List;

public interface IDAO<T> {

    void insert(T t);
    List<T> findAll();
    int update(T t);
    int delete(T t);
}