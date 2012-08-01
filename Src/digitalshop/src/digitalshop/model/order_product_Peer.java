/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import digitalshop.beans.order_product;

/**
 *
 * @author tindt
 */
public class order_product_Peer {
    public static ArrayList getListOfPurchasedProducts(DataManager dataManager, int page) throws SQLException {
        ArrayList order_products = new ArrayList();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                int limit = 10*(page - 1);
                String sql = "select a.productCategoryCode as product_name, c.manufacturerName as manufacturer, sum(b.quantity) as quantity, sum(b.totalMoney) as total from productCategory a, cart b, manufacturer c, bill d where d.cartCode=b.cartCode and b.product=a.productCategoryCode and a.manufacturerCode=c.manufacturerCode group by a.productCategoryCode, c.manufacturerName limit " + limit + "," + 10;
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            order_product order_product = new order_product();

                            order_product.setProduct_name(rs.getString("product_name"));
                            order_product.setManufacturer(rs.getString("manufacturer"));
                            order_product.setQuantity(Integer.valueOf(rs.getString("quantity")));
                            order_product.setTotal(Double.valueOf(rs.getString("total")));

                            order_products.add(order_product); //dua vao danh sach

                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    stm.close();
                }
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return order_products;
    }

    public static int getCountPurchasedProducts(DataManager dataManager) throws SQLException {
        int count = 0;
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select count(distinct a.product) as count from cart a, bill b where a.cartCode=b.cartCode";
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        rs.next();
                        count = Integer.valueOf(rs.getString("count"));
                    } finally {
                        rs.close();
                    }
                } finally {
                    stm.close();
                }
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return count;
    }

    public static ArrayList getListOfViewedProducts(DataManager dataManager, int page) throws SQLException {
        ArrayList order_products = new ArrayList();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                int limit = 10*(page - 1);
                String sql = "select a.productCategoryCode as product_name, b.manufacturerName as manufacturer, a.viewed as viewed from productCategory a, manufacturer b where a.manufacturerCode=b.manufacturerCode order by a.viewed desc limit " + limit + "," + 10;
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            order_product order_product = new order_product();

                            order_product.setProduct_name(rs.getString("product_name"));
                            order_product.setManufacturer(rs.getString("manufacturer"));
                            order_product.setViewed(Integer.valueOf(rs.getString("viewed")));

                            order_products.add(order_product); //dua vao danh sach

                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    stm.close();
                }
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return order_products;
    }

    public static int getCountViewedProducts(DataManager dataManager) throws SQLException {
        int count = 0;
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select count(*) as count from productCategory";
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        rs.next();
                        count = Integer.valueOf(rs.getString("count"));
                    } finally {
                        rs.close();
                    }
                } finally {
                    stm.close();
                }
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return count;
    }

    public static int getSumViewed(DataManager dataManager) throws SQLException {
        int sum = 0;
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select sum(viewed) as sum from productCategory";
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        rs.next();
                        sum = Integer.valueOf(rs.getString("sum"));
                    } finally {
                        rs.close();
                    }
                } finally {
                    stm.close();
                }
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return sum;
    }

}
