package com.hust.alt.dao_impl;

import com.hust.alt.dao.OrderDao;
import com.hust.alt.model.MyConnection;
import com.hust.alt.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

    private MyConnection myConnection = new MyConnection();
    @Override
    public Order getObject(ResultSet resultSet) throws SQLException {
        Order order = null;
        order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setName(resultSet.getString("name"));
        order.setPhoneNumber(resultSet.getString("phone_number"));
        order.setEmail(resultSet.getString("email"));
        order.setAddress(resultSet.getString("address"));
        order.setpQuantity(resultSet.getInt("quantity"));
        order.setTotalMoney(resultSet.getDouble("total_money"));
        order.setIdProduct(resultSet.getInt("product_id"));
        return order;
    }

    @Override
    public List<Order> findAll() throws SQLException {
        return null;
    }

    @Override
    public Order findById(int id) throws SQLException {
        Order order = null;
        String sql = "select * from order where id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            order = getObject(resultSet);
        }
        return order;
    }

    @Override
    public Order insert(Order order) throws SQLException {
        Order order1 = null;
        String sql = "insert into order (name,phone_number,email,address,quantity,total_money,product_id) values(?,?,?,?,?,?,?)";
        PreparedStatement statement = myConnection.prepareUpdate(sql);
        statement.setString(1,order.getName());
        statement.setString(2,order.getPhoneNumber());
        statement.setString(3,order.getEmail());
        statement.setString(4,order.getAddress());
        statement.setInt(5,order.getpQuantity());
        statement.setDouble(6,order.getTotalMoney());
        statement.setInt(7,order.getIdProduct());
        int rs = statement.executeUpdate();
        if (rs > 0){
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                order1 = findById((int) resultSet.getLong(1));
            }
        }
        return order1;
    }

    @Override
    public boolean update(Order order) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
