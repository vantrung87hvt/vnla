/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import digitalshop.beans.Sales;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author tindt
 */
public class SalesPeer {

    public static ArrayList getListOfSales(DataManager dataManager, String startdate, String enddate, String groupby, String status, int page) throws SQLException {
        ArrayList sales = new ArrayList();
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                int limit = 10 * (page - 1);
                String sql = "select min(billDateCreated) as startdate,max(billDateCreated) as enddate,count(distinct billCode) as numberoforders,sum(totalMoney) as totalMoney from bill a, cart b where a.cartCode=b.cartCode and billDateCreated between '" + startdate + "' and '" + enddate + "'";
                if (!status.equalsIgnoreCase("All Statuses")) {
                    sql += " and trangthai='" + status + "'";
                }
                if (groupby.equalsIgnoreCase("Years")) {
                    sql += " group by year(billDateCreated)";
                } else if (groupby.equalsIgnoreCase("Months")) {
                    sql += " group by year(billDateCreated),month(billDateCreated)";
                } else if (groupby.equalsIgnoreCase("Weeks")) {
                    sql += " group by year(billDateCreated),weekofyear(billDateCreated)";
                } else {
                    sql += " group by substring(billDateCreated,1,10)";
                }
                sql += " limit " + limit + "," + 10;
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            Sales sale = new Sales();

                            sale.setStartdate(rs.getString("startdate"));
                            sale.setEnddate(rs.getString("enddate"));
                            sale.setNumberoforders(rs.getString("numberoforders"));
                            sale.setTotal(rs.getString("totalMoney"));

                            sales.add(sale);
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
        return sales;
    }

    public static int getCountSales(DataManager dataManager, String startdate, String enddate, String groupby, String status) throws SQLException {
        Connection connection = dataManager.getConnection();
        int count = 0;

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select count(distinct";
                if (groupby.equalsIgnoreCase("Years")) {
                    sql += " year(billDateCreated)) as count from bill where billDateCreated between '" + startdate + "' and '" + enddate + "'";
                } else if (groupby.equalsIgnoreCase("Months")) {
                    sql += " concat(year(billDateCreated),month(billDateCreated))) as count from bill where billDateCreated between '" + startdate + "' and '" + enddate + "'";
                } else if (groupby.equalsIgnoreCase("Weeks")) {
                    sql += " concat(year(billDateCreated),weekofyear(billDateCreated))) as count from bill where billDateCreated between '" + startdate + "' and '" + enddate + "'";
                } else {
                    sql += " substring(billDateCreated,1,10)) as count from bill where billDateCreated between '" + startdate + "' and '" + enddate + "'";
                }
                if (!status.equalsIgnoreCase("All Statuses")) {
                    sql += " and trangthai='" + status + "'";
                }
                if (groupby.equalsIgnoreCase("Years")) {
                    sql += " group by year(billDateCreated)";
                } else if (groupby.equalsIgnoreCase("Months")) {
                    sql += " group by year(billDateCreated),month(billDateCreated)";
                } else if (groupby.equalsIgnoreCase("Weeks")) {
                    sql += " group by year(billDateCreated),weekofyear(billDateCreated)";
                } else {
                    sql += " group by substring(billDateCreated,1,10)";
                }
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        if (rs.next()) {
                            count = Integer.valueOf(rs.getString("count"));
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
        return count;
    }

    public static String getCurrentDate(DataManager dataManager) throws SQLException {
        Connection connection = dataManager.getConnection();
        String currentdate = "";

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select substring(sysdate(),1,10) as currentdate from dual";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        rs.next();
                        currentdate = rs.getString("currentdate");
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
        return currentdate;
    }

    public static String getDate7ago(DataManager dataManager) throws SQLException {
        Connection connection = dataManager.getConnection();
        String date = "";

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select substring(adddate(sysdate(),-7),1,10) as date from dual";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        rs.next();
                        date = rs.getString("date");
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
        return date;
    }
}
