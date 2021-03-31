package com.hust.alt.dao_impl;

import com.hust.alt.dao.CategoryDao;
import com.hust.alt.dao.ProductDao;
import com.hust.alt.model.Category;
import com.hust.alt.model.MyConnection;
import com.hust.alt.model.Product;

import java.util.List;

public class TestDao {

    public static void main(String[] args) {

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
            ProductDao productDao = new ProductDaoImpl();
//            Product product = new Product("IPhone 13 Pro Max",31990000,"https://cdn.tgdd.vn/Products/Images/42/213033/iphone-12-pro-max-xanh-duong-new-600x600-600x600.jpg","iPhone 12 Pro Max 128 GB siêu phẩm smartphone đang được mong chờ nhất của Apple sẽ được ra mắt và khoảng nửa cuối năm nay (2020)",false,1);
//            productDao.insert(product);
            List<Product> p = productDao.search("iphone");
            System.out.println(p);
//            for (Product o:list) {
//                System.out.println(o);
//            }


        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
