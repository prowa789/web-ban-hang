package com.hust.alt.dao;

import com.hust.alt.model.Bill;
import com.hust.alt.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface BillDao extends BaseDao<Bill> {
    int getTotalBill() throws SQLException;

    List<Bill> pagingBill(int index) throws SQLException;

    boolean confirmBill(int billId) throws SQLException;
}
