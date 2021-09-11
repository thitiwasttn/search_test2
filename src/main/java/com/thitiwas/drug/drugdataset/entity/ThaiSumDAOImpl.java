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

@Service
@Slf4j
public class ThaiSumDAOImpl implements ThaiSumDAO {
    private final EntityManager entityManager;

    @Autowired
    public ThaiSumDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<T1ThaiSum> findByTitleStartWith(String name) {
        Session currentSession = entityManager.unwrap(Session.class);
        String sql = "select *\n" +
                "from t1_thai_sum_v2\n" +
                "where title LIKE '" + name + "%' order by id desc LIMIT 10;";
        Query query = currentSession.createNativeQuery(sql, T1ThaiSum.class);
        List<T1ThaiSum> thaiSums = query.getResultList();
        // thaiSums.forEach(t1ThaiSum -> t1ThaiSum.setTags(new ArrayList<>()));
        return thaiSums;
    }

    @Override
    @Transactional
    public List<T1ThaiSum> findByTitleStartWith(String name, int limit, int page) {
        log.debug("findByTitleStartWith");
        Session currentSession = entityManager.unwrap(Session.class);
        String sql = "select *\n" +
                "from t1_thai_sum_v2\n" +
                "where title LIKE '" + name + "%' order by id desc ";
        Query query = currentSession.createNativeQuery(sql, T1ThaiSum.class);
        query.setFirstResult(page * limit);
        query.setMaxResults(limit);
        List<T1ThaiSum> thaiSums = query.getResultList();
        // thaiSums.forEach(t1ThaiSum -> t1ThaiSum.setTags(new ArrayList<>()));
        return thaiSums;
    }
}
