package com.thitiwas.drug.drugdataset.service;

import com.ibm.icu.text.BreakIterator;
import com.thitiwas.drug.drugdataset.model.BookM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class BookService {
    private final EntityManager entityManager;

    @Autowired
    public BookService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<BookM> findByName(String name) {
        List<BookM> bookMS = new ArrayList<>();
        Query query = entityManager.createNativeQuery(
                "select *\n" +
                        "from books\n" +
                        "where 1\n" +
                        "  AND name LIKE '%" + name + "%' ");
        List<Object[]> resultList = query.getResultList();
        for (Object[] objects : resultList) {
            bookMS.add(BookM.builder()
                    .id(Long.valueOf(String.valueOf(objects[0])))
                    .name(String.valueOf(objects[1]))
                    .build());
        }
        return bookMS;
    }

    public List<BookM> findAll() {
        List<BookM> bookMS = new ArrayList<>();
        Query query = entityManager.createNativeQuery(
                "select *\n" +
                        "from books\n" +
                        "where 1");
        List<Object[]> resultList = query.getResultList();
        for (Object[] objects : resultList) {
            bookMS.add(BookM.builder()
                    .id(Long.valueOf(String.valueOf(objects[0])))
                    .name(String.valueOf(objects[1]))
                    .build());
        }
        return bookMS;
    }

    public List<BookM> findByNameSplit(String name) {
        List<BookM> bookMS = new ArrayList<>();
        BreakIterator boundary = BreakIterator.getWordInstance(new Locale("th"));
        boundary.setText(name);
        String s = printEachForwardSQL(boundary, name);
        s = s.replace("| |", "|");
        Query query = entityManager.createNativeQuery(
                "select distinct *\n" +
                        "from (select *\n" +
                        "      from books\n" +
                        "      where 1\n" +
                        "        AND name LIKE '%" + name + "%'\n" +
                        "      UNION ALL\n" +
                        "      select *\n" +
                        "      from books\n" +
                        "      where 1\n" +
                        "        AND name REGEXP '" + s + "') as pp;\n" +
                        ";");
        List<Object[]> resultList = query.getResultList();
        for (Object[] objects : resultList) {
            bookMS.add(BookM.builder()
                    .id(Long.valueOf(String.valueOf(objects[0])))
                    .name(String.valueOf(objects[1]))
                    .build());
        }
        return bookMS;
    }

    public void printEachForward(BreakIterator boundary, String source) {

        StringBuffer strout = new StringBuffer();
        int start = boundary.first();
        for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {

            strout.append(source.substring(start, end) + "-");
        }

        log.info("strout :{}", strout);

    }

    public String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }

    public String printEachForwardSQL(BreakIterator boundary, String source) {

        StringBuffer strout = new StringBuffer();
        int start = boundary.first();
        for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {

            strout.append(source.substring(start, end)).append("|");
        }
        String s = strout.toString();
        s = removeLastChar(s);
//        log.info("strout :{}", s);
        return s;

    }

}
