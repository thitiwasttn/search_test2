package com.thitiwas.drug.drugdataset.rest;

import com.thitiwas.drug.drugdataset.entity.T1Drug;
import com.thitiwas.drug.drugdataset.service.DrugServiceV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/drug")
@Slf4j
public class DrugRestController {

    private final DrugServiceV2 drugServiceV2;

    @Autowired
    public DrugRestController(DrugServiceV2 drugServiceV2) {
        this.drugServiceV2 = drugServiceV2;
    }

    @GetMapping("/nameStartWith")
    public ResponseEntity<List<T1Drug>> nameStartWith(@RequestParam("name") String name) {
        return ResponseEntity.ok(drugServiceV2.searchNameStartWith(name));
    }

    @GetMapping("/nameContain")
    public ResponseEntity<List<T1Drug>> nameContain(@RequestParam("name") String name) {
        return ResponseEntity.ok(drugServiceV2.searchNameContain(name));
    }
}
