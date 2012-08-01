/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import digitalshop.beans.GioHang;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author NTC
 */
public class GiohangPeer {
    public static String insert(DataManager dataManager, ArrayList<GioHang> listGioHang, String makh) {
        Connection connection = dataManager.getConnection();
        String magiohang="";
        if (connection != null) {
            Statement stmt = null;
            try {
                stmt = connection.createStatement();
                try {
                    connection.setAutoCommit(false);
                    if(listGioHang.size()>0)
                    {
                        magiohang = getMaxGioHang(dataManager);
                        for (int i = 0; i < listGioHang.size(); i++) {

                            GioHang giohang = listGioHang.get(i);
                            String name = giohang.getSanPham();
                            int sl = Integer.parseInt(giohang.getSoluong());

                            String sql = "insert into cart(cartCode,customerCode,product,quantity,createdDate,totalMoney) " +
                                         "values('" +magiohang +"','" + makh + "','" + name + "','"+sl +"',(select curdate())," +
                                         +giohang.getTongTien() + ")";
                            stmt.execute(sql);
                            connection.commit();
                    }
                     }

                    try {
                        stmt.close();
                    } finally {
                        stmt = null;
                    }
                } catch (SQLException e) {
                    System.out.println("Could not insert laptop: " + e.getMessage());
                    try {
                        connection.rollback();
                    } catch (SQLException ee) {
                    }
                }
            } catch (SQLException e) {

            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                    }
                }
                dataManager.putConnection(connection);
            }
        }
        return magiohang;
    }

    //get ma gio hang
     public static String getMaxGioHang(DataManager dataManager) throws SQLException {
        Connection connection = dataManager.getConnection();
        String result="-1";
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select max(cartCode)+1 from cart";
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        if (rs.next()) {
                            result= rs.getString(1);
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
        return result;
    }

     //insert vao ass_theovetgiohang: luu thong tin khach hang va san pham khi tao giohang
     public static boolean insertTheoVetGioHang(DataManager dataManager, String makh, String sanpham)  throws SQLException {
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            Statement stmt = null;
            try {
                stmt = connection.createStatement();
                try {
                    connection.setAutoCommit(false);
                    String sql = " insert into ass_productbuycoupletracking (UserId,date,productString) values ('" +
                                    makh +"',(select sysdate()),'" +sanpham +"')";
                    stmt.execute(sql);
                    connection.commit();

                    stmt.close();
                } finally {
                    stmt = null;
                }
            } catch (SQLException e) {
                System.out.println("Could not insert thong tin san pham: " + e.getMessage());
                try {
                    connection.rollback();
                } catch (SQLException ee) {
                    return false;
                    }

              }finally {
                    dataManager.putConnection(connection);
                  }

              return true;
         } else
             return  false;
     }
    
// lay san pham trong gio hang dua vao ma gio hang
      public static String getListSPByIDGioHang(DataManager dataManager, String magh) throws SQLException {
        Connection connection = dataManager.getConnection();
        String result="";
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select product from cart where cartCode='" + magh +"'";
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            result+= rs.getString("product") +",";
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
        return result;
    }




     //--------------------TinDT-------------------------------
    public static ArrayList getGiohangById(DataManager dataManager, String ma_giohang) throws SQLException {
        ArrayList<GioHang> giohangs =  new ArrayList<GioHang>();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select * from cart a, manufacturer b, productCategory c where a.cartCode='" + ma_giohang + "' and b.manufacturerCode=c.manufacturerCode and a.product=c.productCategoryCode";
          
                try {
                    ResultSet rs;
                    rs = stm.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            GioHang giohang = new GioHang();
                            giohang.setMaGioHang(rs.getString("cartCode"));
                            giohang.setMaKhachHang(rs.getString("customerCode"));
                            giohang.setNgayTao(rs.getString("createdDate"));
                            giohang.setSanPham(rs.getString("product"));
                            giohang.setSoluong(rs.getString("quantity"));
                            giohang.setTongTien(Double.valueOf(rs.getString("totalMoney")));
                            giohang.setDongia(rs.getString("salePrice"));
                            giohang.setNhasanxuat(rs.getString("manufacturerName"));
                            giohangs.add(giohang);
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
        return giohangs;
    }


}
