/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.model;



/**
 *
 * @author NTC
 */
import digitalshop.beans.Thanhtoan;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ThanhtoanPeer {

    public static  void insertThanhtoan(DataManager dataManager, Thanhtoan thanhtoan, long magiohang) throws SQLException{
        Connection connection = dataManager.getConnection();
        String error = "";
        if (connection != null) {
            Statement stmt = null;

            try {
                connection.setAutoCommit(false);
                stmt = connection.createStatement();
                try {
                    String sql ="insert into thanhtoan values('"+ magiohang +"'," + "'" +  thanhtoan.getHinhthuc_thanhtoan()+"',"+ "'"+thanhtoan.getTennguoinhan()+"',"+ "'"+thanhtoan.getDiachinhan()+"',"+ "'"+thanhtoan.getHuyen()+"',"+ "'"+thanhtoan.getTinh()+"',"+ "'"+thanhtoan.getDienthoai_nguoinhan()+"',"+ "'"+thanhtoan.getMa_thetindung()+"',"+ "'"+thanhtoan.getTen_trenthe()+"',"+ "'"+thanhtoan.getNgayhethan()+"',"+ "'"+thanhtoan.getEmail()+"')";
                    stmt.executeUpdate(sql);

                    connection.commit();

                } catch (SQLException e) {
                    //System.out.println("Could not insert thanhtoan: " + e.getMessage());
                    error += " "+ e.getMessage();
                    try {
                        connection.rollback();
                    } catch (SQLException ee) {
                    }
                }
//            } catch (SQLException e) {
//                //System.out.println("Could not insert thanhtoan: " + e.getMessage
//                error += e.getMessage();

            } finally {
                if (stmt != null) {
                     stmt.close();
                }
                dataManager.putConnection(connection);
            }
        }
        if(!error.equals("")){
            throw new SQLException("Could not insert into thanhtoan table: " + error);
        }
    }
}
