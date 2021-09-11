package com.thitiwas.drug.drugdataset.repository;

import com.thitiwas.drug.drugdataset.entity.T1ThaiSum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface T1ThaiSumRepository extends JpaRepository<T1ThaiSum, Long> {

    List<T1ThaiSum> findByTitleStartsWithOrderByTitleAsc(String name, Pageable pageable);

    List<T1ThaiSum> findByTitleStartsWithOrderByIdDesc(String name, Pageable pageable);

    List<T1ThaiSum> findByTitleContainsOrderByIdDesc(String name, Pageable pageable);

    List<T1ThaiSum> findByBodyStartsWithOrderByIdDesc(String name, Pageable pageable);

    List<T1ThaiSum> findByBodyContainsOrderByIdDesc(String name, Pageable pageable);

    // List<T1ThaiSum> findAll(Pageable pageable);
}
