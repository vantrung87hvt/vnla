/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import digitalshop.beans.Nhasanxuat;
import java.util.ArrayList;


/**
 *
 * @author NTC
 */
public class NhasanxuatPeer {

    public static String getTenNhaSX(DataManager dataManager, String ma_nhasx) throws SQLException {
        Connection connection = dataManager.getConnection();
        String tennhasx = "";
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select manufacturerName from manufacturer where manufacturerCode=" + ma_nhasx;

                try {
                    ResultSet rs;
                    rs = stm.executeQuery(sql);

                    if (rs.next()) {

                        tennhasx = rs.getString("manufacturerName");

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
        return tennhasx;
    }

    public static ArrayList getListOfManufacturer(DataManager dataManager, int page) throws SQLException {
        ArrayList manus =new ArrayList();
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                int limit = 10*(page - 1);
                String sql = "select manufacturerCode, manufacturerName, image, description, url_link from manufacturer order by manufacturerName";
                sql += " limit " + limit + "," + 10;
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            Nhasanxuat manu = new Nhasanxuat();

                            manu.setMa_nhasx(rs.getString("manufacturerCode"));
                            manu.setTennhasx(rs.getString("manufacturerName"));
                            manu.setImage(rs.getString("image"));
                            manu.setMota(rs.getString("description"));
                            manu.setUrl_link(rs.getString("url_link"));

                            manus.add(manu);
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
        return manus;
    }

    public static int getCountManufacturer(DataManager dataManager) throws SQLException {
        Connection connection = dataManager.getConnection();
        int count=0;

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select count(*) as count from manufacturer";
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

    public static Nhasanxuat getManufacturerById(DataManager dataManager, String id) throws SQLException {
        Connection connection = dataManager.getConnection();
        Nhasanxuat manu = new Nhasanxuat();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select * from manufacturer where manufacturerCode='"+id+"'";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        rs.next();
                        manu.setMa_nhasx(rs.getString("manufacturerCode"));
                        manu.setTennhasx(rs.getString("manufacturerName"));
                        manu.setImage(rs.getString("image"));
                        manu.setMota(rs.getString("description"));
                        manu.setUrl_link(rs.getString("url_link"));
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
        return manu;
    }

    public static void updateNhasanxuat(DataManager dataManager, String id, String tennhasx, String image, String mota, String url_link) throws SQLException {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            Statement stmt = null;
            try {
                stmt = connection.createStatement();
                try {
                    connection.setAutoCommit(false);
                    String sql = "update manufacturer set manufacturerName='"+tennhasx+"',image='"+image+"',description='"+mota+"',url_link='"+url_link+"' where manufacturerCode='"+id+"'";
                    stmt.execute(sql);
                    connection.commit();
                    try {
                        stmt.close();
                    } finally {
                        stmt = null;
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    try {
                        connection.rollback();
                    } catch (SQLException ee) {
                        System.out.println(ee.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
                dataManager.putConnection(connection);
            }
        }
    }

    public static void insertNhasanxuat(DataManager dataManager, String tennhasx, String image, String mota, String url_link) throws SQLException {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            Statement stmt = null;
            try {
                stmt = connection.createStatement();
                try {
                    connection.setAutoCommit(false);
                    String sql = "insert into manufacturer(manufacturerName,image,description,url_link) values('"+tennhasx+"','"+image+"','"+mota+"','"+url_link+"')";
                    stmt.execute(sql);
                    connection.commit();
                    try {
                        stmt.close();
                    } finally {
                        stmt = null;
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    try {
                        connection.rollback();
                    } catch (SQLException ee) {
                        System.out.println(ee.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
                dataManager.putConnection(connection);
            }
        }
    }

    public static void deleteManufacturer(DataManager dataManager, String ma_nhasx) throws SQLException {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "DELETE FROM manufacturer WHERE manufacturerCode='" + ma_nhasx + "'";
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
}
