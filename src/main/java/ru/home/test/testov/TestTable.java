/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.home.test.testov;

/**
 *
 * @author oleg
 */
class TestTable {

    private long id;
    private String column1;
    private String column2;
    private String column3;
    private String column4;
    private String column5;

    public TestTable() {
    }

    public TestTable(long id, String column1, String column2, String column3, String column4, String column5) {
        this.id = id;
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
        this.column5 = column5;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public String getColumn5() {
        return column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }
    
     @Override
    public String toString() {
        return String.format(
                "TestTable[id=%d, column1='%s', column2='%s', column3='%s', column4='%s', column5='%s']",
                id, column1, column2, column3, column4, column5);
    }
}
