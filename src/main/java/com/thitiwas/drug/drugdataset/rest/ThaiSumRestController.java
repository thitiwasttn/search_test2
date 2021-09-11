package com.thitiwas.drug.drugdataset.rest;

import com.thitiwas.drug.drugdataset.entity.T1ThaiSum;
import com.thitiwas.drug.drugdataset.service.ThaiSumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/thaisum")
@Slf4j
public class ThaiSumRestController {

    private final ThaiSumService thaiSumService;

    @Autowired
    public ThaiSumRestController(ThaiSumService thaiSumService) {
        this.thaiSumService = thaiSumService;
    }

    @GetMapping("/titleStartWith")
    public ResponseEntity<List<T1ThaiSum>> findBytitleStartWith(@RequestParam("title") String title) {
        return ResponseEntity.ok(thaiSumService.findByTitleStartWith(title));
    }

    @GetMapping("/titleContain")
    public ResponseEntity<List<T1ThaiSum>> findByTitleContain(@RequestParam("title") String title) {
        return ResponseEntity.ok(thaiSumService.findByTitleContain(title));
    }


    @GetMapping("/bodyStartWith")
    public ResponseEntity<List<T1ThaiSum>> findByBodyStartWith(@RequestParam("body") String body) {
        return ResponseEntity.ok(thaiSumService.findByBodyStartWith(body));
    }

    @GetMapping("/bodyContain")
    public ResponseEntity<List<T1ThaiSum>> findByBodyContain(@RequestParam("body") String body) {
        return ResponseEntity.ok(thaiSumService.findByBodyContain(body));
    }
}
