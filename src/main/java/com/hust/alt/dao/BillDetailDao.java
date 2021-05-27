package com.hust.alt.dao;

import com.hust.alt.model.BillDetail;

import java.sql.SQLException;
import java.util.List;

public interface BillDetailDao extends BaseDao<BillDetail>{
    List<BillDetail> getAllProductInBill(int billId) throws SQLException;

    double totalMoney(int billId) throws SQLException;
}
