package com.thitiwas.drug.drugdataset.entity;

import java.util.List;

public interface T2PackageDAO {
    List<T2packaging> findByDrugId(Long id);
}
