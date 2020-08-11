/*
методы для работы с БД
 */
package ru.home.test.testov;

import java.beans.PropertyVetoException;
import java.util.List;

/**
 *
 * @author oleg
 */
public interface WorkCRUD {

    public void createValue(TestTable tovar);

    public TestTable readValue(int id);

    public boolean updateValue(TestTable tovar, int id);

    public boolean deleteValue(int id);

    /**
     *
     * @return
     * @throws PropertyVetoException
     */
    public List<TestTable> listTovars() throws PropertyVetoException;

}
