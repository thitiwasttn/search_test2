package com.thitiwas.drug.drugdataset.repository;

import com.thitiwas.drug.drugdataset.entity.UserDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetail, Long> {
    List<UserDetail> findByFistNameStartsWith(String firstName, Pageable pageable);

    List<UserDetail> findByFistNameContains(String firstName, Pageable pageable);
}
