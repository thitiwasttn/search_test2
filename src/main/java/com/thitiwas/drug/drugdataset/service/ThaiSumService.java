package com.thitiwas.drug.drugdataset.service;

import com.thitiwas.drug.drugdataset.entity.T1ThaiSum;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThaiSumService {

    List<T1ThaiSum> findByTitleStartWith(String name);

    List<T1ThaiSum> findByTitleStartWith(String name, int limit, int page);

    List<T1ThaiSum> findByTitleContain(String name);

    List<T1ThaiSum> findByTitleContain(String name, int limit, int page);

    List<T1ThaiSum> findByBodyStartWith(String name);

    List<T1ThaiSum> findByBodyContain(String name);

    List<T1ThaiSum> findAll();

    List<T1ThaiSum> findAllByPage(Pageable pageable);

    Long count();
}
