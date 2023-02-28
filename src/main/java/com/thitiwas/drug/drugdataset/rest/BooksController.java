package com.thitiwas.drug.drugdataset.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thitiwas.drug.drugdataset.model.BookM;
import com.thitiwas.drug.drugdataset.model.SearchResultVM;
import com.thitiwas.drug.drugdataset.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Slf4j
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public ResponseEntity<List<BookM>> findAll() {
        List<BookM> byName = bookService.findAll();
        return ResponseEntity.ok(byName);
    }

    @GetMapping("/name")
    public ResponseEntity<List<BookM>> searchAll(@RequestParam("name") String name) {
        List<BookM> byName = bookService.findByName(name);
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return ResponseEntity.ok(byName);
    }

    @GetMapping("/name-split")
    public ResponseEntity<List<BookM>> searchNameSplit(@RequestParam("name") String name) {
        List<BookM> byName = bookService.findByNameSplit(name);
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return ResponseEntity.ok(byName);
    }
}
