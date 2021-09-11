package com.thitiwas.drug.drugdataset.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrugPackagingM {
    private String packageNdc;
    private String description;
    private Date marketingStartDate;
}
