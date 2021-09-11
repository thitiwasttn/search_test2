package com.thitiwas.drug.drugdataset.entity;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class T1DrugDAOImpl implements T1DrugDAO {

    private final EntityManager entityManager;

    @Autowired
    public T1DrugDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<T1Drug> searchByName(String name) {
        /*Session session = entityManager.unwrap(Session.class);
        Query<T1Drug> t1DrugQuery = session.createQuery("from T1Drug where genericName LIKE :name%", T1Drug.class);
        t1DrugQuery.setParameter("name", name);
        return t1DrugQuery.getResultList();*/
        return null;
    }
}
