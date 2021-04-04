package com.hust.alt.dao_impl;

import com.hust.alt.dao.AccountDao;
import com.hust.alt.dao.CategoryDao;
import com.hust.alt.dao.ProductDao;
import com.hust.alt.model.Account;
import com.hust.alt.model.Category;
import com.hust.alt.model.MyConnection;
import com.hust.alt.model.Product;

import java.util.List;

public class TestDao {

    public static void main(String[] args) {
        AccountDao accountDao = new AccountDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();
        try {
            MyConnection myConnection = new MyConnection();
            myConnection.connectDB();
//            Category category = new Category("Huawei",false);
//            CategoryDao categoryDao = new CategoryDaoImpl();
//            categoryDao.insert(category);
////            categoryDao.update(category);
//            List<Category> list = categoryDao.findAll();
//            for (Category o:list) {
//                System.out.println(o);
//            }
          ;
          productDao.delete(7);
           List<Product> list = productDao.findAll();
//
            for (Product o:list) {
                System.out.println(o);
            }


        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
