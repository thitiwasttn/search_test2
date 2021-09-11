package com.thitiwas.drug.drugdataset.service;

import com.thitiwas.drug.drugdataset.entity.T1Drug;
import com.thitiwas.drug.drugdataset.entity.T2packaging;
import com.thitiwas.drug.drugdataset.model.DrugM;
import com.thitiwas.drug.drugdataset.model.DrugPackagingM;
import com.thitiwas.drug.drugdataset.repository.T1DrugRepository;
import com.thitiwas.drug.drugdataset.repository.T2PackagingRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DrugService {


    private final T1DrugRepository t1DrugRepository;
    private final T2PackagingRepository t2PackagingRepository;

    public T1Drug getDrugObj() {
        return new T1Drug();
    }

    public T2packaging getPackagingObj() {
        return new T2packaging();
    }

    @Autowired
    public DrugService(T1DrugRepository t1DrugRepository, T2PackagingRepository t2PackagingRepository) {
        this.t1DrugRepository = t1DrugRepository;
        this.t2PackagingRepository = t2PackagingRepository;
    }

    public T1DrugRepository getT1DrugRepository() {
        return t1DrugRepository;
    }

    public T2PackagingRepository getT2PackagingRepository() {
        return t2PackagingRepository;
    }

    public List<DrugM> setDataFromJson(String path) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(path));
        JSONObject jsonObject = (JSONObject) object;
        JSONArray jsonArray = (JSONArray) jsonObject.get("results");
        log.debug("size : {} ", jsonArray.size());

        List<DrugM> drugMList = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject data = (JSONObject) o;
            List<DrugPackagingM> packagingMS = new ArrayList<>();
            JSONArray packagings = (JSONArray) data.get("packaging");
            for (Object packaging : packagings) {
                JSONObject packageObj = (JSONObject) packaging;
                packagingMS.add(DrugPackagingM
                        .builder()
                        .packageNdc((String) packageObj.get("package_ndc"))
                        .description((String) packageObj.get("description"))
                        .build());
            }
            drugMList.add(DrugM
                    .builder()
                    .genericName((String) data.get("generic_name"))
                    .productNdc((String) data.get("product_ndc"))
                    .laberName((String) data.get("labeler_name"))
                    .productId((String) data.get("product_id"))
                    .packaging(packagingMS)
                    .build());
        }

        return drugMList;
    }

   /* @Transactional
    public void saveToDB(List<DrugM> drugMList) {
        List<T1Drug> all = new ArrayList<>();
        List<T2packaging> packags = new ArrayList<>();
        for (int i = 0, drugMListSize = drugMList.size(); i < drugMListSize; i++) {
            DrugM drugM = drugMList.get(i);
            T1Drug drug = getDrugObj();
            drug.setGenericName(drugM.getGenericName());
            drug.setLabelerName(drugM.getLaberName());
            drug.setProductNdc(drugM.getProductNdc());
            drug.setProductId(drugM.getProductId());
            t1DrugRepository.save(drug);
            for (DrugPackagingM drugPackagingM : drugM.getPackaging()) {
                T2packaging packaging = getPackagingObj()
                        .setDrug(drug)
                        .(drugPackagingM.getDescription())
                        .setPackageNdc(drugPackagingM.getPackageNdc());
                packags.add(packaging);
                t2PackagingRepository.save(packaging);
            }
            all.add(drug);
        }
    }*/

    public void writeSQL(List<DrugM> drugMList) throws IOException {
        File file = new File("E:\\temp\\sql_temp\\test.sql");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        int id = 1;
        int id2 = 1;
        for (DrugM drugM : drugMList) {
            writer.newLine();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO t1_drug (id, product_ndc, generic_name, labeler_name, product_id) ");
            sb.append("VALUES (");
            sb.append(id).append(",");
            sb.append("'").append(removeSpicalStr(drugM.getProductNdc())).append("',");
            sb.append("'").append(removeSpicalStr(drugM.getGenericName())).append("',");
            sb.append("'").append(removeSpicalStr(drugM.getLaberName())).append("',");
            sb.append("'").append(removeSpicalStr(drugM.getProductId())).append("'");
            sb.append(");");
            writer.append(sb.toString());
            List<DrugPackagingM> packaging = drugM.getPackaging();
            for (DrugPackagingM packagingM : packaging) {
                writer.newLine();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("INSERT INTO t2_packaging(id, package_ndc, description, t1_drug_id) VALUES (");
                sb2.append(id2).append(",");
                sb2.append("'").append(removeSpicalStr(packagingM.getPackageNdc())).append("',");
                sb2.append("'").append(removeSpicalStr(packagingM.getDescription())).append("',");
                sb2.append("").append(id).append("");
                sb2.append(");");
                writer.append(sb2.toString());
                id2++;
            }

            id++;
        }
        writer.close();
    }

    public String removeSpicalStr(String s) {
        return s != null ? s.replace("'", "\\'") : s;
    }
}
