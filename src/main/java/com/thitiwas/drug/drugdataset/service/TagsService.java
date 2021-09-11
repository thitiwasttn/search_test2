package com.thitiwas.drug.drugdataset.service;

import com.thitiwas.drug.drugdataset.entity.T1Tags;
import com.thitiwas.drug.drugdataset.entity.T1ThaiSum;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TagsService {

    void updateTagTemp(List<T1ThaiSum> thaiSums, List<T1Tags> tags) throws IOException;

    Optional<T1Tags> findByTag(String tag);

    List<T1Tags> findAll();

    T1Tags findByTagRest(String tag);

    List<T1Tags> searchTagStartWith(String tag);

    List<T1Tags> searchByTagStartWithDao(String tag);

    T1Tags findById(Long id);
}
