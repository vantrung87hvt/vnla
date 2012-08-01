/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import digitalshop.beans.tinhthanh;
import java.util.ArrayList;

/**
 *
 * @author tindt
 */
public class TinhThanhPeer {
    public static ArrayList getListOfProvince(DataManager dataManager, int page) throws SQLException {
        ArrayList provinces =new ArrayList();
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                int limit = 10*(page - 1);
                String sql = "select districtsCode, districtsName from districts order by districtsCode";
                sql += " limit " + limit + "," + 10;
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            tinhthanh province = new tinhthanh();

                            province.setMatinh(Integer.valueOf(rs.getString("districtsCode")));
                            province.setTentinh(rs.getString("districtsName"));

                            provinces.add(province);
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
        return provinces;
    }

    public static int getCountProvince(DataManager dataManager) throws SQLException {
        Connection connection = dataManager.getConnection();
        int count=0;

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select count(*) as count from districts";
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

    public static tinhthanh getProvinceById(DataManager dataManager, String id) throws SQLException {
        Connection connection = dataManager.getConnection();
        tinhthanh province = new tinhthanh();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select * from districts where districtsCode='"+id+"'";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        rs.next();
                        province.setMatinh(Integer.valueOf(rs.getString("districtsCode")));
                        province.setTentinh(rs.getString("districtsName"));
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
        return province;
    }
}
