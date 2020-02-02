package com.gmail.supersonicleader.app.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GeneralRepository<T> {

    T add(Connection connection, T t) throws SQLException;

    List<T> findAll(Connection connection) throws SQLException;

    void delete(Connection connection, Serializable id) throws SQLException;

}
