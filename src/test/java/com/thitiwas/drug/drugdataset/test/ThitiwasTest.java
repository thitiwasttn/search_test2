package com.thitiwas.drug.drugdataset.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thitiwas.drug.drugdataset.DrugDatasetApplication;
import com.thitiwas.drug.drugdataset.service.*;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = DrugDatasetApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
@ActiveProfiles("native")
public class ThitiwasTest {

    @Autowired
    DrugService drugService;
    @Autowired
    UtilsService utilsService;
    @Autowired
    ThaiSumService thaiSumService;
    @Autowired
    UserService userService;
    @Autowired
    PackageService packageService;
    Gson gson;
    @Autowired
    DrugServiceV2 drugServiceV2;
    @Autowired
    TagsService tagsService;

    @Before
    public void setUp() throws Exception {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @After
    public void tearDown() throws Exception {

    }

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @AfterClass
    public static void afterClass() throws Exception {

    }

    @Test
    public void testSearchAll() {
        int page = 1;
        int limit = 5;
        var test = utilsService.searchAllStartWith("para", limit, page);
        log.debug("getDrugs : {}", test.getDrugs().size());
        log.debug("user : {}", test.getUsers().size());
        log.debug("new : {}", test.getNews().size());
    }

    @Test
    public void testTest() throws Exception {
        var test = tagsService.findById(729966L);
        log.debug("test :{} ", test.getTag());
    }

    @Test
    public void searchTagDao() throws Exception {
        var test = tagsService.searchByTagStartWithDao("รัฐบาล");
        log.debug("test :{} ", test.get(0));
    }

    @Test
    public void updateTags() throws Exception {
        //var all = thaiSumService.findAllByPage(PageRequest.of(0, 50000));
        // log.debug("all : {} " , all);
        /*var all = thaiSumService.findAll();
        log.debug("all : {} " , all.size());*/
        // var all = thaiSumService.findAll();
        // var tags = tagsService.findAll();
        int size = 200;
        long loop = thaiSumService.count() / size;
        for (int i = 0; i < loop; i++) {
            log.debug("index : {} ", i);
            var all = thaiSumService.findAllByPage(PageRequest.of(i, size));
            tagsService.updateTagTemp(all, null);
        }

    }

    @Test
    public void searchThaiSum() {
        String title = "กรม";
        var test = thaiSumService.findByTitleStartWith(title);
        log.debug("test : {} ", test);
    }

    @Test
    @Transactional
    public void searchByName() throws Exception {
        String name = "Bab";
        var test = drugServiceV2.searchNameStartWith(name);
       /* Hibernate.initialize(test);
        test.stream().forEach(x -> Hibernate.initialize(x.getPackagings()));*/
        // log.debug("size : {} " , test.size());
        // test.forEach(t1Drug -> log.debug("t1 : {} " , t1Drug));
        log.debug("test : {} ", test.get(0));
    }

    @Test
    public void testFindDrug() throws Exception {
        var test = packageService.findByDrugId(25L);
        log.debug("test : {} ", gson.toJson(test));
    }

    @Test
    public void testDrug() throws Exception {
        /*var test = drugService.getT1DrugRepository();
        var a = test.findById(15L);
        var b = a.get();


        log.debug("ffff {}", b);*/

        var test = drugService.getT2PackagingRepository();
        var a = test.findById(15L);
        log.debug("a :{} ", a);
    }

    @Test
    public void testUserService() throws Exception {
        var test = userService.getUserDetailsRepository();
        var data = test.findById(1L);
        /*data.ifPresent(userDetail ->
            log.debug("userDetail : {} " , userDetail.getFirst_name());
        );*/
    }

   /* @Test
    public void testThaiSum() throws Exception {
        var test = thaiSumService.getT1ThaiSumRepository();
        var data = test.findAll();
        // log.debug("data : {} " , data.get(1).getBody());
    }*/

    @Test
    public void testReadCsv() throws Exception {
        String path = "E:\\temp\\sql_temp\\thaisum.csv";
        var test = utilsService.readCsv(path);
        log.debug("test : {} ", test.get(4944).getBody());
        // utilsService.writeSql(test);
    }

    @Test
    public void test2() {
        String a = "test'";
        var test = drugService.removeSpicalStr(a);

        String s = "Hello 'thanks' bye";
        s = s.replace("'", "\\'");
        log.debug("test : {} ", s);
    }

    @Test
    public void test() throws Exception {
        log.debug("test");
        String path = "E:\\temp\\sql_temp\\drug-ndc-0001-of-0001.json";
        var drugM = drugService.setDataFromJson(path);
        drugService.writeSQL(drugM);

        log.debug("size : {} ", drugService.getT1DrugRepository().count());

    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        var repoDrug = drugService.getT1DrugRepository();
        var repoPack = drugService.getT2PackagingRepository();
        repoPack.deleteAll(repoPack.findAll());
        repoDrug.deleteAll(repoDrug.findAll());
        log.debug("repoPack.count() : {} ", repoPack.count());
        Assert.assertEquals(0, repoPack.count());
        Assert.assertEquals(0, repoDrug.count());
    }

    @Test
    @Transactional
    public void getOneDrug() {
        /*var packagerepo = drugService.getT2PackagingRepository();
         *//*var test = packagerepo.findById(1L);
        log.debug("test : {} " , test);
        test.ifPresent(x -> {
            log.debug("x.getDrug() : {} ", x.getDrug().getGenericName());
        });*//*

        var repo = drugService.getT1DrugRepository();
        var data = repo.findById(1L);
        data.ifPresent(t1Drug -> {
            log.debug("name : {}", t1Drug.getPackagings());
        });*/
    }
}
