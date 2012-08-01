/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.model;
import digitalshop.beans.Phukien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author Nguyen Duc Canh
 */
public class PhuKienPeer {
 public static Phukien getPhuKienByName(DataManager dataManager, String name) throws SQLException{// search gan dung theo ma dong
        Phukien phukien = new Phukien();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select * from accessories where product='" + name+"'";
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        if(rs.next()) {
                            
                            phukien.setBaohanh(rs.getString("warranty"));
                            phukien.setGiaban(rs.getDouble("price"));
                            phukien.setKind(rs.getString("kind"));
                            phukien.setNhasanxuat(rs.getString("manufacturer"));
                            phukien.setSanpham(rs.getString("product"));
                            phukien.setThongtin(rs.getString("information"));
                            phukien.setUrl(rs.getString("url"));

                           

                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    stm.close();
                }
//            } catch (SQLException e) {
//                System.out.println("Could not search for laptop:" + e.getMessage());
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return phukien;
    }
}
