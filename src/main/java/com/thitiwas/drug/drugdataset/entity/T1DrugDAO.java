package com.thitiwas.drug.drugdataset.entity;

import java.util.List;

public interface T1DrugDAO {

    List<T1Drug> searchByName(String name);
}
