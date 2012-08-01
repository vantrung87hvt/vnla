/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.model;

import digitalshop.beans.Tiente;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ndcanh
 */
public class TientePeer {

    public static Tiente getTiente(DataManager dataManager, String maTien) throws SQLException {
        Tiente tiente =new Tiente();
        Connection connection = dataManager.getConnection();
        // int record = 3;
        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select * from currency where currencyCode='" + maTien + "' and last_update=(select max(last_update) from currency where currencyCode='" + maTien + "')";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            tiente.setMa(rs.getString("currencyCode"));
                            tiente.setUpdate(rs.getString("last_update"));
                            tiente.setGiatri(rs.getString("value"));
                            tiente.setKieuhienthi_Phai(rs.getString("rightSign"));
                            tiente.setKieuhienthi_Trai(rs.getString("leftSign"));


                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
    //       } catch (SQLException e) {
     //          System.out.println("Could not get books: " + e.getMessage());
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return tiente;
    }
        
}
