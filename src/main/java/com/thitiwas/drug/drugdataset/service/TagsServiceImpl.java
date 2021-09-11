package com.thitiwas.drug.drugdataset.service;

import com.thitiwas.drug.drugdataset.entity.T1Tags;
import com.thitiwas.drug.drugdataset.entity.T1ThaiSum;
import com.thitiwas.drug.drugdataset.entity.TagDAO;
import com.thitiwas.drug.drugdataset.exception.MyResourceNotFoundException;
import com.thitiwas.drug.drugdataset.repository.T1TagsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TagsServiceImpl implements TagsService {
    private final T1TagsRepository t1TagsRepository;
    private final TagDAO tagDAO;

    @Autowired
    public TagsServiceImpl(T1TagsRepository t1TagsRepository, TagDAO tagDAO) {
        this.t1TagsRepository = t1TagsRepository;
        this.tagDAO = tagDAO;
    }


    @Override
    @Transactional
    public void updateTagTemp(List<T1ThaiSum> thaiSums, List<T1Tags> tags) throws IOException {
        File file = new File("E:\\temp\\sql_temp\\new_tag.sql");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

        // List<T1Tags> t1Tags = t1TagsRepository.findAll();
        // log.debug("ready====");

        thaiSums.forEach(t1ThaiSum -> {
            String t1ThaiSumTag = t1ThaiSum.getTag().toLowerCase().trim();
            Arrays.stream(t1ThaiSumTag.split(",")).forEach(s -> {
                Optional<T1Tags> t1Tags = t1TagsRepository.findByTag(s);
                t1Tags.ifPresent(x -> {
                    try {
                        writer.newLine();
                        writer.append("insert into t2_thaisum_tag (thaisum_id, tag_id) VALUES (")
                                .append(String.valueOf(t1ThaiSum.getId()))
                                .append(",")
                                .append(String.valueOf(x.getId()))
                                .append(");");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            });

        });


        /*tags.forEach(t1Tags1 -> {
            thaiSums.forEach(t1ThaiSum -> {
                String t1ThaiSumTag = t1ThaiSum.getTag().toLowerCase().trim();
                Arrays.stream(t1ThaiSumTag.split(",")).forEach(s -> {
                    if (s.equals(t1Tags1.getTag())) {
                        try {
                            writer.newLine();
                            writer.append("insert into t2_thaisum_tag (thaisum_id, tag_id) VALUES (")
                                    .append(String.valueOf(t1ThaiSum.getId()))
                                    .append(",")
                                    .append(String.valueOf(t1Tags1.getId()))
                                    .append(");");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            });
        });*/
        writer.close();
        // log.debug("done");
    }

    @Override
    public Optional<T1Tags> findByTag(String tag) {
        return t1TagsRepository.findByTag(tag);
    }

    @Override
    public List<T1Tags> findAll() {
        return t1TagsRepository.findAll();
    }

    @Override
    public T1Tags findByTagRest(String tag) {
        return tagDAO.findByIdCustom(t1TagsRepository
                .findByTag(tag).orElseThrow(() -> new MyResourceNotFoundException("not fond tag")).getId())
                .orElseThrow(() -> new MyResourceNotFoundException("not fond tag"));
    }

    @Override
    public List<T1Tags> searchTagStartWith(String tag) {
        return t1TagsRepository.findByTagStartsWith(tag, PageRequest.of(0, 10));
    }

    @Override
    @Transactional
    public List<T1Tags> searchByTagStartWithDao(String tag) {
        return tagDAO.searchTag(tag);
    }

    @Override
    @Transactional
    public T1Tags findById(Long id) {
        return tagDAO.findByIdCustom(id).orElseThrow(() -> new MyResourceNotFoundException("not found tag id - " + id));
    }

    public String removeSpicalStr(String s) {
        return s != null ? s.replace("'", "\\'") : s;
    }
}
