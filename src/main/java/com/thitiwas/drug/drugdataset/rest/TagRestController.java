package com.thitiwas.drug.drugdataset.rest;

import com.thitiwas.drug.drugdataset.entity.T1Tags;
import com.thitiwas.drug.drugdataset.service.TagsService;
import com.thitiwas.drug.drugdataset.service.ThaiSumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@Slf4j
public class TagRestController {
    private final ThaiSumService thaiSumService;
    private final TagsService tagsService;

    @Autowired
    public TagRestController(ThaiSumService thaiSumService, TagsService tagsService) {
        this.thaiSumService = thaiSumService;
        this.tagsService = tagsService;
    }

    @GetMapping(value = "/name")
    public ResponseEntity<T1Tags> findByTag(@RequestParam("tag") String tag) {
        return ResponseEntity.ok(tagsService.findByTagRest(tag));
    }

    @GetMapping(value = "/nameStartWith")
    public ResponseEntity<List<T1Tags>> searchTagStartWith(@RequestParam("tag") String tag) {
        return ResponseEntity.ok(tagsService.searchByTagStartWithDao(tag));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<T1Tags> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tagsService.findById(id));
    }
}
