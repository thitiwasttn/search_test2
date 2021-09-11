package com.thitiwas.drug.drugdataset.service;

import com.thitiwas.drug.drugdataset.entity.T1ThaiSum;
import com.thitiwas.drug.drugdataset.entity.ThaiSumDAO;
import com.thitiwas.drug.drugdataset.repository.T1ThaiSumRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ThaiSumServiceImpl implements ThaiSumService {
    private final T1ThaiSumRepository t1ThaiSumRepository;
    private final ThaiSumDAO thaiSumDAO;

    @Autowired
    public ThaiSumServiceImpl(T1ThaiSumRepository t1ThaiSumRepository, ThaiSumDAO thaiSumDAO) {
        this.t1ThaiSumRepository = t1ThaiSumRepository;
        this.thaiSumDAO = thaiSumDAO;
    }


    @Override
    public List<T1ThaiSum> findByTitleStartWith(String name) {
        return thaiSumDAO.findByTitleStartWith(name);
    }

    @Override
    public List<T1ThaiSum> findByTitleStartWith(String name, int limit, int page) {
        return thaiSumDAO.findByTitleStartWith(name, limit, page);
    }

    @Override
    public List<T1ThaiSum> findByTitleContain(String name) {
        return t1ThaiSumRepository.findByTitleContainsOrderByIdDesc(name, PageRequest.of(0, 10));
    }

    @Override
    public List<T1ThaiSum> findByTitleContain(String name, int limit, int page) {
        return t1ThaiSumRepository.findByTitleContainsOrderByIdDesc(name, PageRequest.of(page, limit));
    }

    @Override
    public List<T1ThaiSum> findByBodyStartWith(String name) {
        return t1ThaiSumRepository.findByBodyStartsWithOrderByIdDesc(name, PageRequest.of(0, 10));
    }

    @Override
    public List<T1ThaiSum> findByBodyContain(String name) {
        return t1ThaiSumRepository.findByBodyContainsOrderByIdDesc(name, PageRequest.of(0, 10));
    }

    @Override
    public List<T1ThaiSum> findAll() {
        return t1ThaiSumRepository.findAll();
    }

    @Override
    public List<T1ThaiSum> findAllByPage(Pageable pageable) {
        return t1ThaiSumRepository.findAll(pageable).getContent();
    }

    @Override
    public Long count() {
        return t1ThaiSumRepository.count();
    }
}
