package com.hust.alt.dao_impl;

import com.hust.alt.dao.AccountDao;
import com.hust.alt.model.Account;
import com.hust.alt.model.MyConnection;

import javax.xml.registry.infomodel.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl extends  BaseDaoImpl<Account> implements AccountDao{

    private MyConnection myConnection = new MyConnection();

    public Account checkLogin(String username, String password) throws SQLException {

        List<Account> accounts = findAll();
        for(Account account : accounts) {
            if(account.getUsername().equalsIgnoreCase(username)) {
                if (account.getPassword().equalsIgnoreCase(password)) {
                    return account;
                }
                else return null;
            }
        }
        return null;
    }

    public Account getObject(ResultSet resultSet) throws SQLException {
        Account account = null;
        account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setUsername(resultSet.getString("username"));
        account.setPassword(resultSet.getString("password"));
        account.setEmail(resultSet.getString("email"));
        account.setSdt(resultSet.getString("sdt"));
        account.setDeleted(resultSet.getBoolean("deleted"));
        return null;
    }


    public List<Account> findAll() throws SQLException {
        String sql = "select * from account where deleted = false ";
        PreparedStatement statement = myConnection.prepare(sql);
        return getList(statement.executeQuery());
    }

    public Account findById(int id) throws SQLException {
        Account account = null;
        String sql ="select * from account where deleted = false and id = ?" ;
        PreparedStatement statement = myConnection.prepare(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) account = getObject(resultSet);
        return account  ;
    }

    public Account insert(Account account) throws SQLException {
        Account account1 = null;
        String sql = "insert into account(username, password, email, sdt , deleted) values(?,?,?,?,?)";
        PreparedStatement statement = myConnection.prepareUpdate(sql);
        statement.setString(1, account.getUsername());
        statement.setString(2, account.getPassword());
        statement.setString(3, account.getEmail());
        statement.setString(4, account.getSdt());
        statement.setBoolean(5, account.isDeleted());

        int rs = statement.executeUpdate();
        if (rs > 0){
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                account1 = findById((int) resultSet.getLong(1));
            }
        }
        return account1;
    }

    public boolean update(Account account) throws SQLException {
        boolean result = false;
        String sql = "update account set password = ?, email = ?, sdt = ? where id = ?";
        PreparedStatement statement = myConnection.prepareUpdate(sql);
        statement.setString(1, account.getPassword());
        statement.setString(2, account.getEmail());
        statement.setString(3, account.getSdt());
        statement.setInt(4, account.getId());
        int rs = statement.executeUpdate();
        if (rs > 0) result = true;

        return result;
    }

    public boolean delete(int id) throws SQLException {
        boolean result = false;
        String sql = "update account set deleted = true where id = ? ";
        PreparedStatement statement = myConnection.prepareUpdate(sql);
        statement.setInt(1,id);
        int rs = statement.executeUpdate();
        if (rs > 0) result = true;

        return result;
    }
}
