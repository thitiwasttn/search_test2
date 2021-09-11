package com.thitiwas.drug.drugdataset.repository;

import com.thitiwas.drug.drugdataset.entity.T1Drug;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface T1DrugRepository extends JpaRepository<T1Drug, Long> {

    // List<T1Drug> findByGenericNameStartingWith(String name);

    List<T1Drug> findByGenericNameContainsOrderByIdDesc(String name,Pageable pageable);

    List<T1Drug> findByGenericNameStartsWith(String name, Pageable pageable);

    List<T1Drug> findByGenericNameStartsWithOrderByIdDesc(String name, Pageable pageable);
}
