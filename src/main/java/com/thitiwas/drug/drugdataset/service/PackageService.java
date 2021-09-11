package com.thitiwas.drug.drugdataset.service;

import com.thitiwas.drug.drugdataset.entity.T2packaging;

import java.util.List;

public interface PackageService {

    List<T2packaging> findByDrugId(Long drugId);
}
