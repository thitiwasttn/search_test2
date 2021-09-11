package com.thitiwas.drug.drugdataset.entity;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TagDAOImpl implements TagDAO {
    private final EntityManager entityManager;

    @Autowired
    public TagDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<T1Tags> searchTag(String tag) {
        List<T1Tags> ret = new ArrayList<>();
        Query query = entityManager.createNativeQuery("select * from t1_tags where tag LIKE '" + tag + "%' LIMIT 10");
        List<Object[]> objs = query.getResultList();
        for (Object[] obj : objs) {
            ret.add(T1Tags
                    .builder()
                    .id(Long.valueOf(String.valueOf(obj[0])))
                    .tag(String.valueOf(obj[1]))
                    .build());
        }
        return ret;
    }

    @Override
    @Transactional
    public Optional<T1Tags> findByIdCustom(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createNativeQuery("select * from t1_tags where id = " + id + " LIMIT 10");
        List<Object[]> objs = query.getResultList();
        T1Tags ret = null;
        if (objs.size() > 0) {
            ret = T1Tags
                    .builder()
                    .id(Long.valueOf(String.valueOf(objs.get(0)[0])))
                    .tag(String.valueOf(objs.get(0)[1]))
                    .news(setNewsLimitV2(id))
                    .build();
        }
        return Optional.ofNullable(ret);
    }

    @Override
    public Optional<T1Tags> findByTagCustom(String tag) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createNativeQuery("select * from t1_tags where tag like ':tag'", T1Tags.class);
        List<T1Tags> tags = query.getResultList();
        if (tags.size() > 0) {
            return Optional.ofNullable(T1Tags
                    .builder()
                    .id(tags.get(0).getId())
                    .tag(tags.get(0).getTag())
                    .build());
        }
        return Optional.empty();
    }

    @Transactional
    List<T1ThaiSum> setNewsLimit(Long tagId) {
        List<T1ThaiSum> ret = new ArrayList<>();

        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createNativeQuery("select t1_thai_sum_v2.*\n" +
                "from t1_thai_sum_v2\n" +
                "         join t2_thaisum_tag t2tt on t1_thai_sum_v2.id = t2tt.thaisum_id\n" +
                "where t2tt.tag_id =:tagId order by t1_thai_sum_v2.id desc LIMIT 10;", T1ThaiSum.class);
        query.setParameter("tagId", tagId);
        List<T1ThaiSum> objs = query.getResultList();
        for (T1ThaiSum obj : objs) {
            ret.add(T1ThaiSum
                    .builder()
                    .id(obj.getId())
                    .title(obj.getTitle())
                    .body(obj.getBody())
                    .summary(obj.getSummary())
                    .type(obj.getType())
                    .tag(obj.getType())
                    .url(obj.getUrl())
                    .build());
        }

        return ret;
    }

    @Transactional
    List<T1ThaiSum> setNewsLimitV2(Long tagId) {
        List<T1ThaiSum> ret;
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createNativeQuery("select tt2.*\n" +
                "from t1_thai_sum_v2 tt2\n" +
                "join (select thaisum_id\n" +
                "             from t2_thaisum_tag\n" +
                "             where tag_id = :tagId\n" +
                "             order by thaisum_id desc\n" +
                "             limit 10) as v on tt2.id = v.thaisum_id", T1ThaiSum.class);
        query.setParameter("tagId", tagId);
        List<T1ThaiSum> objs = query.getResultList();
        ret = objs.stream().map(obj -> T1ThaiSum
                        .builder()
                        .id(obj.getId())
                        .title(obj.getTitle())
                        .body(obj.getBody())
                        .summary(obj.getSummary())
                        .type(obj.getType())
                        .tag(obj.getType())
                        .url(obj.getUrl())
                        .build())
                .collect(Collectors.toList());

        return ret;
    }
}
