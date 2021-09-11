package com.thitiwas.drug.drugdataset.model;

import com.thitiwas.drug.drugdataset.entity.T1Drug;
import com.thitiwas.drug.drugdataset.entity.T1ThaiSum;
import com.thitiwas.drug.drugdataset.entity.UserDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultVM {
    List<UserDetail> users = new ArrayList<>();
    List<T1Drug> drugs = new ArrayList<>();
    List<T1ThaiSum> news = new ArrayList<>();
}
