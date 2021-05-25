package com.hust.alt.dao;

import com.hust.alt.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao extends BaseDao<Comment>{

    List<Comment> findByProduct(int idProduct) throws SQLException;


}
