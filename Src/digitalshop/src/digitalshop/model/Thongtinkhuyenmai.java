/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import digitalshop.beans.Khuyenmai;
import java.util.ArrayList;
/**
 *
 * @author NTC
 */
public class Thongtinkhuyenmai {

    public static Khuyenmai getThongtinkhuyenmai(DataManager dataManager, String ma_dongsanpham) throws SQLException{
        Khuyenmai khuyenmai = null;
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select ... from promotionalInformation" + " where productCategoryCode=" + ma_dongsanpham;
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    if (rs.next()) {
                        khuyenmai = new Khuyenmai();

                    // lay cac thuoc tinh de hien thi

                    }
                } finally {
                    stm.close();
                }
//            } catch (SQLException e) {
//                System.out.println("Could not get book: " + e.getMessage());
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return khuyenmai;

    }

    public static ArrayList getSPKhuyenMai(DataManager dataManager, String levelKH) throws SQLException{
        ArrayList<Khuyenmai> khuyenmai = new ArrayList<Khuyenmai>();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select * from laptopkhuyenmai where dieukien='" + levelKH+"'" ;
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    
                    while (rs.next()) {
                    Khuyenmai km =new Khuyenmai();
                    String st=rs.getString("productCategoryCode");
                    km.setLapKhuyenmai(dataManager,rs.getString("productCategoryCode"));
                    km.setMa_dongsanpham(rs.getString("productCategoryCode"));
                    km.setNgaybatdau(rs.getDate("ngaybatdau").toString());
                    km.setNgayketthuc(rs.getDate("ngayketthuc").toString());
                    km.setTangpham(rs.getString("tangpham"));
                    km.setTilegiamgia(rs.getDouble("tilegiamgia"));

                    khuyenmai.add(km);

                    }
                } catch(Exception e) {//finally {
                    //stm.close();
                    String r =e.getMessage();
                    r=r+"loi";
                }
//            } catch (SQLException e) {
//                System.out.println("Could not get book: " + e.getMessage());
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return khuyenmai;

    }

    public static void insertThongtinkhuyenmai(DataManager dataManager, Khuyenmai khuyenmai)  throws SQLException{
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "";
                //TODO
                try {
                    ResultSet rs = stm.executeQuery(sql);

                } finally {
                    stm.close();
                }
//            } catch (SQLException e) {
//                System.out.println("Could not get book: " + e.getMessage());
            } finally {
                dataManager.putConnection(connection);
            }
        }

    }

    public static void updateThongtinkhuyenmai(DataManager dataManager, Khuyenmai khuyenmai) {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "";
                //TODO
                try {
                    stm.executeQuery(sql);
                } finally {
                    stm.close();
                }
            } catch (SQLException e) {
                System.out.println("Could not get book: " + e.getMessage());
            } finally {
                dataManager.putConnection(connection);
            }
        }

    }

    public static void removeThongtinkhuyenmai(DataManager dataManager, String ma_dongsanpham) {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "delete ... from promotionalInformation" + " where productCategoryCode=" + ma_dongsanpham;
                //TODO
                try {
                    stm.executeQuery(sql);
                } finally {
                    stm.close();
                }
            } catch (SQLException e) {
                System.out.println("Could not get book: " + e.getMessage());
            } finally {
                dataManager.putConnection(connection);
            }
        }

    }
}
