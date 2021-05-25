package com.hust.alt.dao;

import com.hust.alt.model.ImageProduct;

import java.sql.SQLException;
import java.util.List;

public interface ImageProductDao extends BaseDao<ImageProduct>{
    List<ImageProduct> findByProductId(int productId) throws SQLException;
}
