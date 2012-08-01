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
import digitalshop.beans.Dienthoai;
import digitalshop.beans.Khachhang;

/**
 *
 * @author NTC
 */
public class DienthoaiPeer {

    public static ArrayList search(DataManager dataManager, String keyword) {
        ArrayList<Dienthoai> dienthoais = new ArrayList<Dienthoai>();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select  ... from productCategory" + " where title like '%" + keyword.trim() + "%'" + " or author like '%" + keyword.trim() + "%'";
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            Dienthoai dienthoai = new Dienthoai();

                            // lay cac thuoc tinh de hien thi

                            dienthoais.add(dienthoai); //dua vao danh sach
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    stm.close();
                }
            } catch (SQLException e) {
                System.out.println("Could not search for books:" + e.getMessage());
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return dienthoais;
    }

    public static ArrayList getDienthoaiByNhasanxuat(DataManager dataManager, String ma_nhasx) {
        ArrayList<Dienthoai> dienthoais = new ArrayList<Dienthoai>();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select .... from productCategory" + " where manufacturerCode=" + ma_nhasx;
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            Dienthoai dienthoai = new Dienthoai();

                            // lay cac thuoc tinh de hien thi

                            dienthoais.add(dienthoai); //dua vao danh sach
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    stm.close();
                }
            } catch (SQLException e) {
                System.out.println("Could not get books: " + e.getMessage());
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return dienthoais;
    }

    public static Dienthoai getDienthoaiByID(DataManager dataManager, String ma_dongsanpham) {
        Dienthoai dienthoai = null;
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select ... from productCategory" + " where productCategoryCode=" + ma_dongsanpham;
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    if (rs.next()) {
                        dienthoai = new Dienthoai();

                    // lay cac thuoc tinh de hien thi

                    }
                } finally {
                    stm.close();
                }
            } catch (SQLException e) {
                System.out.println("Could not get book: " + e.getMessage());
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return dienthoai;
    }

    public static void insert(DataManager dataManager, Dienthoai dienthoai) {
    }

    public static void update(DataManager dataManager, Dienthoai dienthoai) {
    }

    public static void remove(DataManager dataManager, String ma_dongsanpham) {
    }


////------------Khach hang-----------
    
    public static void insertKhachhang(DataManager dataManager, Khachhang khachhang)
    {
        
    }
}
