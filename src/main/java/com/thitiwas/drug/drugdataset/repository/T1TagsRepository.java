package com.thitiwas.drug.drugdataset.repository;

import com.thitiwas.drug.drugdataset.entity.T1Tags;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface T1TagsRepository extends JpaRepository<T1Tags, Long> {
    Optional<T1Tags> findByTag(String tag);

    List<T1Tags> findByTagStartsWith(String tag, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from t1_tags where id=:id limit 10")
    Optional<T1Tags> findByIdCustom(@Param("id") Long id);
}
