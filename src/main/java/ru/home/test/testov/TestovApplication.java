package ru.home.test.testov;

//import com.google.gson.Gson;
import java.beans.PropertyVetoException;
//import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TestovApplication {

    private final WorkCRUD wCrud;

    public static void main(String[] args) {
        SpringApplication.run(TestovApplication.class, args);
    }

    @Autowired
    public TestovApplication(WorkCRUD wCrud) {
        this.wCrud = wCrud;
    }

//    @Autowired
//    DBwork db;

    @GetMapping("/test1")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        try {
            wCrud.listTovars();
        } catch (PropertyVetoException ex) {
            Logger.getLogger(TestovApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.format("Hello %s!", name);
    }

    /**
     *
     * @param name
     * @return
     * @throws PropertyVetoException
     */
    @GetMapping("/tovars")
    public ResponseEntity<List<TestTable>> listTovars(@RequestParam(value = "name", defaultValue = "World") String name) throws PropertyVetoException {
//        System.out.println(name);
        List<TestTable> listTable = wCrud.listTovars();
//        return new Gson().toJson(listTable);
        return listTable != null && !listTable.isEmpty()
                ? new ResponseEntity<>(listTable, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param tovar
     * @return
     */
    @PostMapping(value = "/tovars")
    //@ResponseBody
    public ResponseEntity<String> create(@RequestBody TestTable tovar) {
//        System.out.println(tovar.toString());
        wCrud.createValue(tovar);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    /**
//     *
//     * @param id
//     * @return
//     */
//    @GetMapping(value = "/tovars/{id}")
//    public ResponseEntity<TestTable> read(@PathVariable(name = "id") int id) {
//        TestTable tovar = wCrud.readValue(id);
//
//        return tovar != null
//                ? new ResponseEntity<>(tovar, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PutMapping(value = "/tovars/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody TestTable tovar) {
        boolean upd = wCrud.updateValue(tovar, id);

        return upd
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/tovars/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = wCrud.deleteValue(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
