package com.thitiwas.drug.drugdataset.entity;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailDAOImpl implements UserDetailDAO {
    private final EntityManager entityManager;

    @Autowired
    public UserDetailDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<UserDetail> findByNameStartCustom(String name) {
        Session currentSession = entityManager.unwrap(Session.class);
        String sql = "select *\n" +
                "from user_details\n" +
                "where CONCAT_WS(' ',first_name,last_name) LIKE '" + name + "%' LIMIT 10";
        // log.debug("sql :{} ", sql);
        Query query = currentSession.createNativeQuery(sql, UserDetail.class);

        // query.setParameter("name", name);
        List<UserDetail> userDetails = query.getResultList();
        //log.debug("userDetails size : {} with :{} ", userDetails, name);
        return userDetails;
    }

    @Override
    @Transactional
    public List<UserDetail> findByNameContainCustom(String name) {
        Session currentSession = entityManager.unwrap(Session.class);
        String sql = "select *\n" +
                "from user_details\n" +
                "where CONCAT_WS(' ',first_name,last_name) LIKE '%" + name + "%' LIMIT 10";
        Query query = currentSession.createNativeQuery(sql, UserDetail.class);

        // query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<UserDetail> findByNameStartCustom(String name, int limit, int page) {
        Session currentSession = entityManager.unwrap(Session.class);
        String sql = "select *\n" +
                "from user_details\n" +
                "where CONCAT_WS(' ',first_name,last_name) LIKE '" + name + "%' ";
        Query query = currentSession.createNativeQuery(sql, UserDetail.class);
        query.setFirstResult((page) * limit);
        query.setMaxResults(limit);
        // query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<UserDetail> findByNameContainCustom(String name, int limit, int page) {
        Session currentSession = entityManager.unwrap(Session.class);
        String sql = "select *\n" +
                "from user_details\n" +
                "where CONCAT_WS(' ',first_name,last_name) LIKE '%" + name + "%' ";
        Query query = currentSession.createNativeQuery(sql, UserDetail.class);
        query.setFirstResult(page * limit);
        query.setMaxResults(limit);
        // query.setParameter("name", name);
        return query.getResultList();
    }
}
