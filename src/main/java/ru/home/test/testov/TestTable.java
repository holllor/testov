/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.home.test.testov;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 *
 * @author oleg
 */
class TestTable {

    private long id;
    private String name;
    private String description;
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date create_date;
    private Integer place_storage;
    private boolean reserved;
    
    
//    private String column1;
//    private String column2;
//    private String column3;
//    private String column4;
//    private String column5;

    public TestTable() {
    }

//    public TestTable(long id, String column1, String column2, String column3, String column4, String column5) {
//        this.id = id;
//        this.column1 = column1;
//        this.column2 = column2;
//        this.column3 = column3;
//        this.column4 = column4;
//        this.column5 = column5;
//    }

    public TestTable(long id, String name, String description, Date create_date, Integer place_storage, boolean reserved) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.create_date = create_date;
        this.place_storage = place_storage;
        this.reserved = reserved;
    }

//    public String getColumn1() {
//        return column1;
//    }
//
//    public void setColumn1(String column1) {
//        this.column1 = column1;
//    }
//
//    public String getColumn2() {
//        return column2;
//    }
//
//    public void setColumn2(String column2) {
//        this.column2 = column2;
//    }
//
//    public String getColumn3() {
//        return column3;
//    }
//
//    public void setColumn3(String column3) {
//        this.column3 = column3;
//    }
//
//    public String getColumn4() {
//        return column4;
//    }
//
//    public void setColumn4(String column4) {
//        this.column4 = column4;
//    }
//
//    public String getColumn5() {
//        return column5;
//    }
//
//    public void setColumn5(String column5) {
//        this.column5 = column5;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Integer getPlace_storage() {
        return place_storage;
    }

    public void setPlace_storage(Integer place_storage) {
        this.place_storage = place_storage;
    }


    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "TestTable{" + "id=" + id + ", name=" + name + ", description=" + description + ", create_date=" + create_date + ", place_storage=" + place_storage + ", reserved=" + reserved + '}';
    }

  
    
}
