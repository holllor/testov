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

    public void getCustomers() throws PropertyVetoException {

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
                return new TestTable(rs.getLong("id"), rs.getString("column1"),
                        rs.getString("column2"), rs.getString("column3"),
                        rs.getString("column4"), rs.getString("column5"));
            }
        });

        for (TestTable customer : results) {
            System.out.println(customer);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PropertyVetoException {
        // Retrieve all customers
        DBwork db = new DBwork();
        db.getCustomers();
    }

    @Override
    public List<TestTable> listAllVaulues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void readValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
