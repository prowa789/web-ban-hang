package com.hust.alt.dao;

import com.hust.alt.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends BaseDao<Product> {
    List<Product> sortBy(String field, boolean isASC) throws SQLException;

    List<Product> findByCategory(int idCategory) throws SQLException;

    Product getLastProduct() throws SQLException;

    List<Product> search(String txtSearch) throws SQLException;

}
