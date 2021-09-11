package com.thitiwas.drug.drugdataset.service;

import com.thitiwas.drug.drugdataset.entity.T1Drug;
import com.thitiwas.drug.drugdataset.entity.T1DrugDAO;
import com.thitiwas.drug.drugdataset.exception.MyResourceNotFoundException;
import com.thitiwas.drug.drugdataset.repository.T1DrugRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DrugServiceImpl implements DrugServiceV2 {

    private final T1DrugRepository t1DrugRepository;
    private final T1DrugDAO t1DrugDAO;

    @Autowired
    public DrugServiceImpl(T1DrugRepository t1DrugRepository, T1DrugDAO t1DrugDAO) {
        this.t1DrugRepository = t1DrugRepository;
        this.t1DrugDAO = t1DrugDAO;
    }

    @Override
    @Transactional
    public T1Drug findById(Long id) {
        Optional<T1Drug> t1Drug = t1DrugRepository.findById(id);
        T1Drug ret;
        if (t1Drug.isPresent()) {
            ret = t1Drug.get();
        } else {
            throw new MyResourceNotFoundException("Did not find drug id - " + id);
        }
        return ret;
    }

    @Override
    @Transactional
    public List<T1Drug> searchNameStartWith(String name) {
        return t1DrugRepository.findByGenericNameStartsWithOrderByIdDesc(name, PageRequest.of(0, 5));
    }

    @Override
    public List<T1Drug> searchNameContain(String name) {
        return t1DrugRepository.findByGenericNameContainsOrderByIdDesc(name, PageRequest.of(0, 5));
    }

    @Override
    public List<T1Drug> searchNameStartWith(String name, int limit, int page) {
        return t1DrugRepository.findByGenericNameStartsWithOrderByIdDesc(name, PageRequest.of(page, limit));
    }

    @Override
    public List<T1Drug> searchNameContain(String name, int limit, int page) {
        return t1DrugRepository.findByGenericNameContainsOrderByIdDesc(name, PageRequest.of(page, limit));
    }
}
