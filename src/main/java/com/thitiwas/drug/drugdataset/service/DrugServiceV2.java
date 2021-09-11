package com.thitiwas.drug.drugdataset.service;

import com.thitiwas.drug.drugdataset.entity.T1Drug;

import java.util.List;

public interface DrugServiceV2 {

    T1Drug findById(Long id);

    List<T1Drug> searchNameStartWith(String name);

    List<T1Drug> searchNameContain(String name);

    List<T1Drug> searchNameStartWith(String name, int limit, int page);

    List<T1Drug> searchNameContain(String name, int limit, int page);

}
