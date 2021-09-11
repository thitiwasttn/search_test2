package com.thitiwas.drug.drugdataset.entity;

import java.util.List;

public interface ThaiSumDAO {
    List<T1ThaiSum> findByTitleStartWith(String name);

    List<T1ThaiSum> findByTitleStartWith(String name, int limit, int page);
}
