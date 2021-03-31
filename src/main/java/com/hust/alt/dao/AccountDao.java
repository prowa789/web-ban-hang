package com.hust.alt.dao;

import com.hust.alt.model.Account;

public interface AccountDao extends BaseDao<Account>{
    public boolean checkLogin(String username, String password);
}
