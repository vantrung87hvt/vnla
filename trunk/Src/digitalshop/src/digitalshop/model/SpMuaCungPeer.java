/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import digitalshop.beans.SpMuaCung;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author NTC
 */
public class SpMuaCungPeer {

    public static SpMuaCung getSpMuaCung(DataManager dataManager, String IdLap) throws SQLException {
        ArrayList<String> muacung = new ArrayList<String>();
        Connection connection = dataManager.getConnection();
        String sp="";
        String imglink="";
        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql1="select image from productCategory where productCategoryCode='"+IdLap+"'";
                try{
                    ResultSet rs1=s.executeQuery(sql1);
                    try{
                        while(rs1.next()){
                            imglink=rs1.getString(1);
                        }
                    }
                    finally{
                        rs1.close();
                    }
                }
               finally{
                    
                }

                String sql = "select rightrule from ass_productbuycouple  where leftrule='" + IdLap + "'";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        if (rs.next()) {
                             sp = rs.getString(1);
                           
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
//            } catch (SQLException e) {
//                System.out.println("Could not get books: " + e.getMessage());
            } finally {
                dataManager.putConnection(connection);
            }
        }
        if(!sp.equals("")) {
        String[] temp = sp.split(",");
        for(int i=0;i<temp.length;i++) {
            muacung.add(temp[i]);
        }
        IdLap +="," + imglink;
        SpMuaCung spmuacung = new SpMuaCung(IdLap, muacung);
        return spmuacung;
    }
        return null;
    }

}
