/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import digitalshop.beans.HoaDon;
import java.sql.ResultSet;
import java.util.ArrayList;
import digitalshop.beans.LichsuGiaodich;
/**
 *
 * @author Nguyen Duc Canh
 */
public class HoaDonPeer {
public static void insertHoaDon(DataManager dataManager,HoaDon hoadon) {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            Statement stmt = null;
            try {
                stmt = connection.createStatement();
                try {
                    connection.setAutoCommit(false);

                   String sql ="insert into bill(cartCode,customerCode,deliverer,receiver,address,address1,districts,province,fax,email,phone,pay,status,billDateCreated) values (" +
                                hoadon.getMaGioHang() + ",'" +
                                hoadon.getMaKhachHang() +"','','" +
                                hoadon.getNguoiNhanHang() +"','" +
                                hoadon.getDiaChi() + "','','" +
                                hoadon.getQuanHuyen() + "'," +
                                hoadon.getTinh() + ",'','" +
                                //hoadon.getFax() +"','" +
                                hoadon.getEmail() + "','" +
                                hoadon.getPhone() + "','" +
                                hoadon.getThanhToan() +"','Processing'," +
                                "(select curdate())" +  ")";

                    stmt.execute(sql);
                    connection.commit();

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
    }

     public static ArrayList getHoaDonByMaKH(DataManager dataManager, String ma_kh ,String trangthai) throws SQLException{

        ArrayList<HoaDon> listHoadon = new ArrayList<HoaDon>();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select * from bill where customerCode='" + ma_kh +"' and status='" + trangthai +"'";
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        while (rs.next()) {
                           HoaDon hoadon = new HoaDon();
                           hoadon.setDiaChi(rs.getString("address"));
                           hoadon.setDiaChi1(rs.getString("address1"));
                           hoadon.setEmail(rs.getString("email"));
                           hoadon.setFax(rs.getString("fax"));
                           hoadon.setLoaiTienThanhtoan(rs.getString("currency"));
                           Integer magh= rs.getInt("cartCode");
                           String ma_gh = magh.toString();
                           Integer mahd =rs.getInt("billCode");
                           String ma_hd= mahd.toString();
                           Integer makh =rs.getInt("customerCode");
                           String kh =makh.toString();
                           hoadon.setMaGioHang(ma_gh);
                           hoadon.setMaHoaDon(ma_hd);
                           hoadon.setMaKhachHang(kh);
                           if(rs.getDate("fixedDate")==null) {
                               hoadon.setNgaySuaHD("");
                           } else {
                               hoadon.setNgaySuaHD(rs.getDate("fixedDate").toString());
                           }

                           if(rs.getDate("billDateCreated")==null) {
                               hoadon.setNgayTaoHoaDon("");
                           } else {
                              hoadon.setNgayTaoHoaDon((rs.getDate("billDateCreated")).toString());
                           }if(rs.getDate("paidDate")==null) {
                               hoadon.setNgayThanhToan("");
                           } else {
                               hoadon.setNgayThanhToan((rs.getDate("paidDate")).toString());
                           }
                                                    
                           hoadon.setNguoiGiaoHang(rs.getString("deliverer"));
                           hoadon.setNguoiNhanHang(rs.getString("receiver"));
                           hoadon.setPhone(rs.getString("phone"));
                           hoadon.setQuanHuyen(rs.getString("districts"));
                           hoadon.setThanhToan(rs.getString("pay"));
                           hoadon.setTrangThaiHoaDon(rs.getString("status"));
                           Integer tinhthanh = rs.getInt("province");
                           String tinh = tinhthanh.toString();
                           hoadon.setTinh(tinh);

                           listHoadon.add(hoadon);
                        }
                    }catch(Exception e) {
                        String r=e.getMessage();
                        r+="sd";
                    }
                    finally {
                        rs.close();
                    }
                } finally {
                    stm.close();
                }
//            } catch (SQLException e) {
//                System.out.println("Could not get laptop: " + e.getMessage());
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return listHoadon;
    }

       public static ArrayList getSanPhamGiaoDich(DataManager dataManager, String ma_kh ,String trangthai,String col, String order) throws SQLException{

       ArrayList<LichsuGiaodich> listBuy = new ArrayList<LichsuGiaodich>();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select g.product, g.createdDate from bill as h, cart as g where h.cartCode=g.cartCode and h.customerCode=' " + ma_kh +"' and status='" + trangthai +"' order by " + col+ "  " + order;
               
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        while (rs.next()) {
                          LichsuGiaodich lichsuGd = new LichsuGiaodich();
                          String ngay =(rs.getDate(2)).toString();
                          String kq=rs.getString(1);
                          String[] listSP=kq.split(",");
                          lichsuGd.setDate(ngay);
                          lichsuGd.setProductList(listSP);

                          listBuy.add(lichsuGd);


                        }
                    }
                    finally {
                        rs.close();
                    }
                } finally {
                    stm.close();
                }
//            } catch (SQLException e) {
//                System.out.println("Could not get laptop: " + e.getMessage());
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return listBuy;
    }

      //Admin-TinDT----------------------
    public static ArrayList getListOfOrders(DataManager dataManager, int page) throws SQLException {
        ArrayList orders = new ArrayList();
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                int limit = 10 * (page - 1);
                String sql = "select a.billCode, a.receiver, a.status, a.billDateCreated, b.cartCode, sum(b.totalMoney) as totalMoney from bill a, cart b where a.cartCode=b.cartCode group by b.cartCode";
                sql += " limit " + limit + "," + 10;
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            HoaDon order = new HoaDon();

                            order.setMaHoaDon(rs.getString("billCode"));
                            order.setNguoiNhanHang(rs.getString("receiver"));
                            order.setTrangThaiHoaDon(rs.getString("status"));
                            order.setNgayTaoHoaDon(rs.getString("billDateCreated"));
                            order.setTongtien(rs.getString("totalMoney"));

                            orders.add(order);
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return orders;
    }

    public static int getCountOrders(DataManager dataManager) throws SQLException {
        Connection connection = dataManager.getConnection();
        int count = 0;

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select count(*) as count from bill";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        rs.next();
                        count = Integer.valueOf(rs.getString("count"));
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return count;
    }

    public static void deleteOrder(DataManager dataManager, String ma_hoadon) throws SQLException {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "DELETE FROM bill WHERE billCode='" + ma_hoadon + "'";
                try {
                    stm.executeUpdate(sql);
                } finally {
                    stm.close();
                }
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                try {
                    connection.rollback();
                } catch (SQLException ee) {
                    System.out.println(ee.getMessage());
                }
            } finally {
                dataManager.putConnection(connection);
            }
        }
    }

    public static HoaDon getOrderById(DataManager dataManager, String ma_hoadon) throws SQLException {
        HoaDon order = new HoaDon();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select * from bill where billCode='" + ma_hoadon + "'";
                try {
                    ResultSet rs;
                    rs = stm.executeQuery(sql);
                    try {
                        if (rs.next()) {
                            order.setMaHoaDon(rs.getString("billCode"));
                            order.setMaGioHang(rs.getString("cartCode"));
                            order.setMaKhachHang(rs.getString("customerCode"));
                            order.setNguoiGiaoHang(rs.getString("deliverer"));
                            order.setNguoiNhanHang(rs.getString("receiver"));
                            order.setDiaChi(rs.getString("address"));
                            order.setQuanHuyen(rs.getString("districts"));
                            order.setTinh(rs.getString("province"));
                            order.setEmail(rs.getString("email"));
                            order.setPhone(rs.getString("phone"));
                            order.setFax(rs.getString("fax"));
                            order.setThanhToan(rs.getString("pay"));
                            order.setTrangThaiHoaDon(rs.getString("status"));
                            order.setNgayTaoHoaDon(rs.getString("billDateCreated"));
                            order.setNgayThanhToan(rs.getString("payDate"));
                            order.setNgaySuaHD(rs.getString("fixedDate"));
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
        return order;
    }

    public static void updateOrderStatus(DataManager dataManager, String ma_hoadon, String status, String deliverer, String comments) throws SQLException {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "update bill set trangthai='" + status + "',deliverer='" + deliverer + "',comments='" + comments + "',fixedDate=sysdate()";
                if (status.equalsIgnoreCase("Completed")) {
                    sql += ",payDate=sysdate()";
                }
                sql += " where billCode='" + ma_hoadon + "'";
                try {
                    stm.executeUpdate(sql);
                } finally {
                    stm.close();
                }
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                try {
                    connection.rollback();
                } catch (SQLException ee) {
                    System.out.println(ee.getMessage());
                }
            } finally {
                dataManager.putConnection(connection);
            }
        }
    }

    public static int getSalesStatisticInDay(DataManager dataManager, int i) throws SQLException {
        int orderNo = 0;
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "";
                if (i != 24) {
                    sql = "select count(billCode) as orderNo from bill where date(billDateCreated)=date(sysdate()) and hour(billDateCreated)<" + i + " or (hour(billDateCreated)=" + i + " and minute(billDateCreated)='00' and second(billDateCreated)='00')";
                } else {
                    sql = "select count(billCode) as orderNo from bill where date(billDateCreated)=date(sysdate())";
                }
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            orderNo = Integer.valueOf(rs.getString("orderNo"));
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
            } finally {
                dataManager.putConnection(connection);
            }
        }
        return orderNo;
    }
}
