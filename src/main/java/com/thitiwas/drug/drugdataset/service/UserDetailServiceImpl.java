package com.thitiwas.drug.drugdataset.service;

import com.thitiwas.drug.drugdataset.entity.UserDetail;
import com.thitiwas.drug.drugdataset.entity.UserDetailDAO;
import com.thitiwas.drug.drugdataset.repository.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailService {
    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailDAO userDetailDAO;

    @Autowired
    public UserDetailServiceImpl(UserDetailsRepository userDetailsRepository, UserDetailDAO userDetailDAO) {
        this.userDetailsRepository = userDetailsRepository;
        this.userDetailDAO = userDetailDAO;
    }

    @Override
    public List<UserDetail> findByFistNameStartsWith(String firstName) {
        return userDetailsRepository.findByFistNameStartsWith(firstName, PageRequest.of(0, 10));
    }

    @Override
    public List<UserDetail> findByFistNameContains(String firstName) {
        return userDetailsRepository.findByFistNameContains(firstName, PageRequest.of(0, 10));
    }

    @Override
    public List<UserDetail> findByNameStartCustom(String name) {
        return userDetailDAO.findByNameStartCustom(name);
    }

    @Override
    public List<UserDetail> findByNameContainCustom(String name) {
        return userDetailDAO.findByNameContainCustom(name);
    }

    @Override
    public List<UserDetail> findByNameStartCustom(String name, int limit, int page) {
        return userDetailDAO.findByNameStartCustom(name, limit, page);
    }

    @Override
    public List<UserDetail> findByNameContainCustom(String name, int limit, int page) {
        return userDetailDAO.findByNameContainCustom(name, limit, page);
    }
}
