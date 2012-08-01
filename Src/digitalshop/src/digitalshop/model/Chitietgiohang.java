/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.model;

import java.util.Hashtable;
import java.util.Enumeration;
import java.sql.SQLException;
import java.sql.Statement;
import digitalshop.beans.Sanphamchon;

/**
 *
 * @author NTC
 */
public class Chitietgiohang {

    public static void insertChitietgiohang(Statement stmt, long ma_giohang, Hashtable ShoppingCart) throws SQLException {
        if (!ShoppingCart.isEmpty()) {
            Enumeration enumList = ShoppingCart.elements();
            double itemPrice = 0;
            double totalPrice = 0;
            while (enumList.hasMoreElements()) {
                Sanphamchon item = (Sanphamchon) enumList.nextElement();
                String sql = "insert into chitietgiohang (ma_dongsp,cartCode,soluong,gia) values ('" + item.getMa_dongsanpham() + "','" + ma_giohang + "','" + item.getSoluong()+ "','" + item.getGia() + "')";
                stmt.executeUpdate(sql);
            }
        }
    }
}


