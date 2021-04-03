package com.hust.alt.dao;

import com.hust.alt.model.Account;

import java.sql.SQLException;

public interface AccountDao extends BaseDao<Account>{
    public Account checkLogin(String username, String password) throws SQLException;
}
