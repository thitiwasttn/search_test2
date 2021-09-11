package com.thitiwas.drug.drugdataset.entity;

import java.util.List;
import java.util.Optional;

public interface TagDAO {
    List<T1Tags> searchTag(String tag);

    Optional<T1Tags> findByIdCustom(Long id);

    Optional<T1Tags> findByTagCustom(String tag);
}
