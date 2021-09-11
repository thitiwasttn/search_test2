package com.thitiwas.drug.drugdataset.repository;

import com.thitiwas.drug.drugdataset.entity.T2packaging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface T2PackagingRepository extends JpaRepository<T2packaging, Long> {
}
