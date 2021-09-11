package com.thitiwas.drug.drugdataset.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrugM {
    private String productNdc;
    private String genericName;
    private String laberName;
    private String productId;
    List<DrugPackagingM> packaging;
}
