/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.home.test.testov;

import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author oleg
 */
@Repository
public class DBwork implements WorkCRUD {

    private final SimpleDriverDataSource dataSource;
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public  DataSource getDataSource() {
//       return DataSourceBuilder.create().build();
//    }

    public DBwork() {
        dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.hsqldb.jdbc.JDBCDriver.class);
        dataSource.setUsername("SA");
        dataSource.setUrl("jdbc:hsqldb:hsql://localhost:9001/test");
//         dataSource.setUrl("jdbc:hsqldb:hsql://localhost:9001");
        dataSource.setPassword("");
    }

    /**
     * вывод всего списка
     *
     * @return @throws PropertyVetoException
     */
    @Override
    public List<TestTable> listTovars() throws PropertyVetoException {

//        DataSource dataSource = getDataSource();
//         Connection conn = null;
//        String db = "jdbc:hsqldb:hsql://localhost:9001/";
//        String user = "SA";
//        String password = "";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<TestTable> results = jdbcTemplate.query(
                "select * from public.tovar ", new Object[]{},
                new RowMapper<TestTable>() {
            @Override
            public TestTable mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TestTable(rs.getLong("id"), rs.getString("name"),
                        rs.getString("description"), rs.getDate("create_date"),
                        rs.getInt("place_storage"), rs.getBoolean("reserved"));
            }
        });

        return results;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PropertyVetoException {
        DBwork db = new DBwork();
        List<TestTable> results = db.listTovars();
        for (TestTable customer : results) {
            System.out.println(customer);
        }
    }

    /**
     *
     * @param tovar
     */
    @Override
    @Transactional
    public void createValue(TestTable tovar) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(
                "INSERT INTO public.tovar (NAME, DESCRIPTION, CREATE_DATE, PLACE_STORAGE, RESERVED) VALUES (?, ?,?,?,?)",
                tovar.getName(), tovar.getDescription(), tovar.getCreate_date(), tovar.getPlace_storage(), tovar.isReserved());
        System.out.println("create in HSQLDB " + tovar.toString());

    }

    @Override
    public TestTable readValue(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateValue(TestTable tovar, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteValue(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
