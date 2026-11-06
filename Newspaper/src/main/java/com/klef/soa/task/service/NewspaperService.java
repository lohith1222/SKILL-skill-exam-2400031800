package com.klef.soa.task.service;

import java.util.List;

import com.klef.soa.task.entity.Newspaper;

public interface NewspaperService {

    Newspaper insert(Newspaper newspaper);
    Newspaper update(Long id, Newspaper newspaper);
    void delete(Long id);
    List<Newspaper> getAll();
    Newspaper getById(Long id);
    List<Newspaper> findByName(String name);
    List<Newspaper> findByType(String type);

}