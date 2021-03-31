package com.hust.alt.dao_impl;

import com.hust.alt.dao.AccountDao;
import com.hust.alt.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    public boolean checkLogin(String username, String password) {
        return false;
    }

    public Account getObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    public List<Account> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

    public List<Account> findAll() throws SQLException {
        return null;
    }

    public Account findById(int id) throws SQLException {
        return null;
    }

    public Account insert(Account account) throws SQLException {
        return null;
    }

    public boolean update(Account account) throws SQLException {
        return false;
    }

    public boolean delete(int id) throws SQLException {
        return false;
    }
}
