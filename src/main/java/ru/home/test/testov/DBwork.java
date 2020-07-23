/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.home.test.testov;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
//import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 *
 * @author oleg
 */
public class DBwork {

    public static void getCustomers() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.hsqldb.jdbc.JDBCDriver.class);
        dataSource.setUsername("SA");
        dataSource.setUrl("jdbc:hsqldb:hsql://localhost:9001/");
        dataSource.setPassword("");
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
    public static void main(String[] args) {
        // Retrieve all customers
        getCustomers();
    }
}
