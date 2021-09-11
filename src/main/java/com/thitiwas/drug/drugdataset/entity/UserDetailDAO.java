package com.thitiwas.drug.drugdataset.entity;

import com.thitiwas.drug.drugdataset.entity.UserDetail;

import java.util.List;

public interface UserDetailDAO {
    List<UserDetail> findByNameStartCustom(String name);

    List<UserDetail> findByNameContainCustom(String name);


    List<UserDetail> findByNameStartCustom(String name, int limit, int page);

    List<UserDetail> findByNameContainCustom(String name, int limit, int page);
}
