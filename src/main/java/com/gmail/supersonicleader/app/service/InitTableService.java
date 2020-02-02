package com.gmail.supersonicleader.app.service;

import com.gmail.supersonicleader.app.service.exception.CreateTableException;
import com.gmail.supersonicleader.app.service.exception.DeleteTableException;

public interface InitTableService {

    void deleteAndCreateTables() throws DeleteTableException, CreateTableException;
}
