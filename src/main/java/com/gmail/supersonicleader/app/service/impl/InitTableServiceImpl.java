package com.gmail.supersonicleader.app.service.impl;

import com.gmail.supersonicleader.app.service.InitTableService;
import com.gmail.supersonicleader.app.service.TableService;
import com.gmail.supersonicleader.app.service.exception.CreateTableException;
import com.gmail.supersonicleader.app.service.exception.DeleteTableException;

public class InitTableServiceImpl implements InitTableService {

    private static TableService tableService;
    private static InitTableService instance;

    private InitTableServiceImpl() {
    }

    public static InitTableService getInstance() {
        if (instance == null) {
            instance = new InitTableServiceImpl();
        }
        return instance;
    }

    public void deleteAndCreateTables() throws CreateTableException, DeleteTableException {
        if (tableService == null) {
            tableService = TableServiceImpl.getInstance();
            tableService.deleteAllTables();
            tableService.createAllTables();
        }
    }

}
