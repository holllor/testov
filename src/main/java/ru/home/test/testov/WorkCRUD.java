/*
методы для работы с БД
 */
package ru.home.test.testov;

import java.util.List;

/**
 *
 * @author oleg
 */
public interface WorkCRUD {
    
    /**
     *
     * @return
     */
    public  List<TestTable> listAllVaulues();
    
    public void createValue();
    public void readValue();
    public void updateValue();
    public void deleteValue();
    
    
    
}
