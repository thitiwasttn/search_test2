package com.thitiwas.drug.drugdataset.entity;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class T2PackageDAOImpl implements T2PackageDAO {

    private final EntityManager entityManager;

    @Autowired
    public T2PackageDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<T2packaging> findByDrugId(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Query<T2packaging> ret = session.createQuery("from T2packaging where drug.id=:id", T2packaging.class);
        ret.setParameter("id", id);
        return ret.getResultList();
    }
}
