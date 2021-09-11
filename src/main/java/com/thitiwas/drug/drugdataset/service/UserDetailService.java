package com.thitiwas.drug.drugdataset.service;

import com.thitiwas.drug.drugdataset.entity.UserDetail;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDetailService {
    List<UserDetail> findByFistNameStartsWith(String firstName);

    List<UserDetail> findByFistNameContains(String firstName);

    List<UserDetail> findByNameStartCustom(String name);

    List<UserDetail> findByNameContainCustom(String name);

    List<UserDetail> findByNameStartCustom(String name, int limit, int page);

    List<UserDetail> findByNameContainCustom(String name, int limit, int page);
}
