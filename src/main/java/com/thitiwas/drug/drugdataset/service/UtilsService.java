package com.thitiwas.drug.drugdataset.service;

import com.opencsv.CSVReader;
import com.thitiwas.drug.drugdataset.model.SearchResultVM;
import com.thitiwas.drug.drugdataset.model.ThaiSumM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilsService {

    private final UserDetailService userDetailService;
    private final DrugServiceV2 drugService;
    private final ThaiSumService thaiSumService;

    @Autowired
    public UtilsService(UserDetailService userDetailService, DrugServiceV2 drugServiceV2, ThaiSumService thaiSumService) {
        this.userDetailService = userDetailService;
        this.drugService = drugServiceV2;
        this.thaiSumService = thaiSumService;
    }

    @Transactional
    public SearchResultVM searchAllStartWith(String search, int limit, int page) {
        log.debug("limit :{}, page :{}", limit, page);
        log.debug("search : {} ", search);
        SearchResultVM ret = SearchResultVM
                .builder()
                .drugs(drugService.searchNameStartWith(search, limit, page))
                .users(userDetailService.findByNameStartCustom(search, limit, page))
                .news(thaiSumService.findByTitleStartWith(search, limit, page))
                .build();
        log.debug("getDrugs : {}", ret.getDrugs().size());
        log.debug("user : {}", ret.getUsers().size());
        log.debug("new : {}", ret.getNews().size());
        return ret;
    }

    @Transactional
    public SearchResultVM searchAllContain(String search, int limit, int page) {
        log.debug("search :{} ", search);
        log.debug("limit :{}, page :{}", limit, page);
        SearchResultVM ret = SearchResultVM
                .builder()
                .drugs(drugService.searchNameContain(search, limit, page))
                .users(userDetailService.findByNameContainCustom(search, limit, page))
                .news(thaiSumService.findByTitleContain(search, limit, page))
                .build();
        log.debug("searchAllContaingetDrugs : {}", ret.getDrugs().size());
        log.debug("searchAllContainuser : {}", ret.getUsers().size());
        log.debug("searchAllContainnew : {}", ret.getNews().size());
        return ret;
    }

    public List<ThaiSumM> readCsv(String filePath) throws Exception {
        List<List<String>> records = new ArrayList<List<String>>();
        int starter = 0;
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
                if (starter == 200000) {
                    break;
                }
                starter++;
            }

        }

        records = records.stream()
                .filter(strings -> strings.get(0).length() < 1000)
                .filter(strings -> strings.get(1).length() < 4000)
                .filter(strings -> strings.get(2).length() < 1000)
                .filter(strings -> strings.get(3).length() < 1000)
                .filter(strings -> strings.get(4).length() < 1000)
                .filter(strings -> strings.get(5).length() < 1000)
                .collect(Collectors.toList());

        List<ThaiSumM> thaiSumMS = records.stream().map(record -> ThaiSumM
                .builder()
                .title(record.get(0))
                .body(record.get(1))
                .summary(record.get(2))
                .type(record.get(3))
                .tag(record.get(4))
                .url(record.get(5))
                .build()).collect(Collectors.toList());
        return thaiSumMS;
    }

    public String removeSpicalStr(String s) {
        return s != null ? s.replace("'", "\\'") : s;
    }


    public void writeSql(List<ThaiSumM> test) throws Exception {
        String path = "E:\\temp\\sql_temp\\thaisum.sql";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        int id = 1;
        for (ThaiSumM thaiSumM : test) {
            writer.newLine();
            StringBuilder sb = new StringBuilder();
            sb.append("insert into t1_thai_sum (title, body_, summary, type, tag, url)  ");
            sb.append("VALUES (");
            sb.append("'").append(removeSpicalStr(thaiSumM.getTitle())).append("',");
            sb.append("'").append(removeSpicalStr(thaiSumM.getBody())).append("',");
            sb.append("'").append(removeSpicalStr(thaiSumM.getSummary())).append("',");
            sb.append("'").append(removeSpicalStr(thaiSumM.getType())).append("',");
            sb.append("'").append(removeSpicalStr(thaiSumM.getTag())).append("',");
            sb.append("'").append(removeSpicalStr(thaiSumM.getUrl())).append("');");
            id++;

            writer.append(sb.toString());
            /*if (id == 3) {
                break;
            }*/
        }

        writer.close();
    }
}
