package com.thitiwas.drug.drugdataset.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThaiSumM {
    private String title;
    private String body;
    private String summary;
    private String type;
    private String tag;
    private String url;
}
