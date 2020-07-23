package ru.home.test.testov;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TestovApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestovApplication.class, args);
    }
    @Autowired
    JdbcTemplate jdbcTemplate;
//public static void main(String[] args) {
//SpringApplication.run(DemoApplication.class, args);
//}

    @GetMapping("/test1")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/tovars")
    public String hello2(@RequestParam(value = "name", defaultValue = "World") String name) {
        List<TestTable> listTable = new ArrayList<>();
        TestTable table1 = new TestTable();
        table1.setColumn1("1");
        table1.setColumn2("2");
        table1.setColumn3("33");
        table1.setColumn4("4");
        table1.setColumn5("5");
        TestTable table2 = new TestTable();
        table2.setColumn1("89");
        table2.setColumn2("2");
        table2.setColumn3("33");
        table2.setColumn4("4");
        table2.setColumn5("5");
        listTable.add(table1);
        listTable.add(table1);
        listTable.add(table1);
        listTable.add(table1);
        listTable.add(table1);
        listTable.add(table2);

        return new Gson().toJson(listTable);
    }
}
