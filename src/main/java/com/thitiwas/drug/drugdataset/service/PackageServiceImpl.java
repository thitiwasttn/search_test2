package com.thitiwas.drug.drugdataset.service;

import com.thitiwas.drug.drugdataset.entity.T2PackageDAO;
import com.thitiwas.drug.drugdataset.entity.T2packaging;
import com.thitiwas.drug.drugdataset.repository.T2PackagingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PackageServiceImpl implements PackageService {
    private final T2PackagingRepository t2PackagingRepository;
    private final T2PackageDAO t2PackageDAO;

    @Autowired
    public PackageServiceImpl(T2PackagingRepository t2PackagingRepository, T2PackageDAO t2PackageDAO) {
        this.t2PackagingRepository = t2PackagingRepository;
        this.t2PackageDAO = t2PackageDAO;
    }

    @Override
    @Transactional
    public List<T2packaging> findByDrugId(Long drugId) {
        return t2PackageDAO.findByDrugId(drugId);
    }
}
