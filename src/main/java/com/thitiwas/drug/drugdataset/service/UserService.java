package com.thitiwas.drug.drugdataset.service;

import com.thitiwas.drug.drugdataset.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDetailsRepository userDetailsRepository;


    @Autowired
    public UserService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public UserDetailsRepository getUserDetailsRepository() {
        return userDetailsRepository;
    }
}
