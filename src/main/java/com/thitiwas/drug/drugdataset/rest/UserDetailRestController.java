package com.thitiwas.drug.drugdataset.rest;

import com.thitiwas.drug.drugdataset.entity.UserDetail;
import com.thitiwas.drug.drugdataset.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserDetailRestController {
    private final UserDetailService userDetailService;

    @Autowired
    public UserDetailRestController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping("/firstNameStartWith")
    public ResponseEntity<List<UserDetail>> getFirstNameStart(@RequestParam("firstName") String firstName) {
        return ResponseEntity.ok(userDetailService.findByFistNameStartsWith(firstName));
    }

    @GetMapping("/firstNameContain")
    public ResponseEntity<List<UserDetail>> firstNameContain(@RequestParam("firstName") String firstName) {
        return ResponseEntity.ok(userDetailService.findByFistNameContains(firstName));
    }

    @GetMapping("/nameStart")
    public ResponseEntity<List<UserDetail>> nameStart(@RequestParam("fullName") String fullName) {
        return ResponseEntity.ok(userDetailService.findByNameStartCustom(fullName));
    }

    @GetMapping("/nameContain")
    public ResponseEntity<List<UserDetail>> nameContain(@RequestParam("fullName") String fullName) {
        return ResponseEntity.ok(userDetailService.findByNameContainCustom(fullName));
    }
}
