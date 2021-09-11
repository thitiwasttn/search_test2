package com.thitiwas.drug.drugdataset.rest;

import com.thitiwas.drug.drugdataset.model.SearchResultVM;
import com.thitiwas.drug.drugdataset.service.UtilsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
@Slf4j
public class SearchRestController {
    private final UtilsService utilsService;

    @Autowired
    public SearchRestController(UtilsService utilsService) {
        this.utilsService = utilsService;
    }

    @GetMapping("/startWith")
    public ResponseEntity<SearchResultVM> searchAll(@RequestParam("search") String search,
                                                    @RequestParam(value = "limit", required = false, defaultValue = "10")
                                                            int limit,
                                                    @RequestParam(value = "page", required = false, defaultValue = "0")
                                                            int page) {
        return ResponseEntity.ok(utilsService.searchAllStartWith(search, limit, page));
    }

    @GetMapping("/contain")
    public ResponseEntity<SearchResultVM> searchAllContain(@RequestParam("search") String search,
                                                           @RequestParam(value = "limit", required = false, defaultValue = "10")
                                                                   int limit,
                                                           @RequestParam(value = "page", required = false, defaultValue = "0")
                                                                   int page) {
        return ResponseEntity.ok(utilsService.searchAllContain(search, limit, page));
    }
}
