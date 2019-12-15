package com.test.vendor.mysql;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import com.jsql.model.InjectionModel;
import com.jsql.model.exception.JSqlException;
import com.jsql.view.terminal.SystemOutTerminal;

@TestInstance(Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
public class MySQLTimeTestSuite extends ConcreteMySQLTestSuite {

    @Override
    public void initialize3() throws Exception {

        InjectionModel model = new InjectionModel();
        this.injectionModel = model;

        model.addObserver(new SystemOutTerminal());

        model.parameterUtil.initQueryString("http://localhost:8080/greeting-time");
        model.parameterUtil.initRequest("");
        model.parameterUtil.setQueryString(Arrays.asList(
            new SimpleEntry<>("tenant", "mysql"),
            new SimpleEntry<>("name", "1'")
        ));

        model.connectionUtil.setMethodInjection(model.QUERY);
        model.connectionUtil.setTypeRequest("GET");
        
        model.setIsScanning(true);
        model.setStrategy(model.TIME);
        model.beginInjection();
    }
    
    @Ignore
    @Override
    @Test
    public void listDatabases() throws JSqlException {
        LOGGER.info("Ignore: too slow");
    }
    
    @Ignore
    @Override
    @Test
    public void listTables() throws JSqlException {
        LOGGER.info("Ignore: too slow");
    }
    
    @Ignore
    @Override
    @Test
    public void listColumns() throws JSqlException {
        LOGGER.info("Ignore: too slow");
    }
    
}
