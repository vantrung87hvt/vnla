/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.model;

/**
 *
 * @author PlateT
 */

import digitalshop.beans.Rule;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RulePeer {
    public static ArrayList getRulesByProduct(DataManager dataManager, String product) throws SQLException {
        ArrayList<Rule> ruleList = new ArrayList<Rule>();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "SELECT stt, leftrule, rightrule FROM ass_productviewcouple WHERE leftrule='" + product + "' OR leftrule LIKE '" + product + ",%' OR leftrule LIKE '%, " + product + ",%' OR leftrule LIKE '%, " + product +"'";
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            Rule rule = new Rule();
                            rule.setStt(Integer.valueOf(rs.getString(1)));
                            rule.setLeftRule(rs.getString(2));
                            rule.setRightRule(rs.getString(3));
                            ruleList.add(rule);
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
        return ruleList;
    }

    public static Rule getRuleByStt(DataManager dataManager, String stt) throws SQLException {
        Rule rule = new Rule();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "SELECT stt, leftrule, rightrule FROM ass_productviewcouple WHERE stt=" + stt;
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            rule.setStt(Integer.valueOf(rs.getString(1)));
                            rule.setLeftRule(rs.getString(2));
                            rule.setRightRule(rs.getString(3));
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
        return rule;
    }

    public static Rule getPurchasedRuleByStt(DataManager dataManager, String stt) throws SQLException {
        Rule rule = new Rule();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "SELECT stt, leftrule, rightrule FROM ass_productbuycouple WHERE stt=" + stt;
                try {
                    ResultSet rs = stm.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            rule.setStt(Integer.valueOf(rs.getString(1)));
                            rule.setLeftRule(rs.getString(2));
                            rule.setRightRule(rs.getString(3));
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
        return rule;
    }

    public static void updateRuleByStt(DataManager dataManager, String stt, String rightRule) throws SQLException {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "UPDATE ass_productviewcouple SET rightrule='" + rightRule + "' WHERE stt=" + stt;
                try {
                    connection.setAutoCommit(true);
                    stm.executeUpdate(sql);
                } finally {
                    stm.close();
                }
            } finally {
                dataManager.putConnection(connection);
            }
        }
    }

    public static void updatePurchasedRuleByStt(DataManager dataManager, String stt, String rightRule) throws SQLException {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "UPDATE ass_productbuycouple SET rightrule='" + rightRule + "' WHERE stt=" + stt;
                try {
                    connection.setAutoCommit(true);
                    stm.executeUpdate(sql);
                } finally {
                    stm.close();
                }
            } finally {
                dataManager.putConnection(connection);
            }
        }
    }

    public static ArrayList getListOfDMViewed(DataManager dataManager, String filter_manu, String filter_product, String filter_viewed_product, int page) throws SQLException {
        ArrayList rules = new ArrayList();
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                int limit = 10 * (page - 1);
                String sql = "select * from ass_productviewcouple a, manufacturer b, productCategory c where a.leftrule=c.productCategoryCode and c.manufacturerCode=b.manufacturerCode";
                if (!filter_manu.equalsIgnoreCase("") && filter_manu != null) {
                    sql = sql + " and b.manufacturerName = '" + filter_manu + "'";
                }
                if (!filter_product.equalsIgnoreCase("") && filter_product != null) {
                    sql = sql + " and leftrule like '%" + filter_product + "%'";
                }
                if (!filter_viewed_product.equalsIgnoreCase("") && filter_viewed_product != null) {
                    sql = sql + " and rightrule like '%" + filter_viewed_product + "%'";
                }
                sql += " order by leftrule";
                sql += " limit " + limit + "," + 10;
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            Rule rule = new Rule();

                            rule.setStt(rs.getShort("stt"));
                            rule.setNhasx(rs.getString("manufacturerName"));
                            rule.setLeftRule(rs.getString("leftrule"));
                            rule.setRightRule(rs.getString("rightrule"));

                            rules.add(rule);
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
        return rules;
    }

    public static int getCountDMViewed(DataManager dataManager, String filter_manu, String filter_product, String filter_viewed_product) throws SQLException {
        Connection connection = dataManager.getConnection();
        int count = 0;

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select count(a.stt) as count from ass_productviewcouple a, manufacturer b, productCategory c where a.leftrule=c.productCategoryCode and c.manufacturerCode=b.manufacturerCode";
                if (!filter_manu.equalsIgnoreCase("") && filter_manu != null) {
                    sql = sql + " and b.manufacturerName = '" + filter_manu + "'";
                }
                if (!filter_product.equalsIgnoreCase("") && filter_product != null) {
                    sql = sql + " and leftrule like '%" + filter_product + "%'";
                }
                if (!filter_viewed_product.equalsIgnoreCase("") && filter_viewed_product != null) {
                    sql = sql + " and rightrule like '%" + filter_viewed_product + "%'";
                }
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

    public static ArrayList getListOfDMPurchased(DataManager dataManager, String filter_manu, String filter_product, String filter_purchased_product, int page) throws SQLException {
        ArrayList rules = new ArrayList();
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                int limit = 10 * (page - 1);
                String sql = "select * from ass_productbuycouple a, manufacturer b, productCategory c where a.leftrule=c.productCategoryCode and c.manufacturerCode=b.manufacturerCode";
                if (!filter_manu.equalsIgnoreCase("") && filter_manu != null) {
                    sql = sql + " and b.manufacturerName = '" + filter_manu + "'";
                }
                if (!filter_product.equalsIgnoreCase("") && filter_product != null) {
                    sql = sql + " and leftrule like '%" + filter_product + "%'";
                }
                if (!filter_purchased_product.equalsIgnoreCase("") && filter_purchased_product != null) {
                    sql = sql + " and rightrule like '%" + filter_purchased_product + "%'";
                }
                sql += " order by leftrule";
                sql += " limit " + limit + "," + 10;
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            Rule rule = new Rule();

                            rule.setStt(rs.getShort("stt"));
                            rule.setNhasx(rs.getString("manufacturerName"));
                            rule.setLeftRule(rs.getString("leftrule"));
                            rule.setRightRule(rs.getString("rightrule"));

                            rules.add(rule);
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
        return rules;
    }

    public static int getCountDMPurchased(DataManager dataManager, String filter_manu, String filter_product, String filter_purchased_product) throws SQLException {
        Connection connection = dataManager.getConnection();
        int count = 0;

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select count(a.stt) as count from ass_productbuycouple a, manufacturer b, productCategory c where a.leftrule=c.productCategoryCode and c.manufacturerCode=b.manufacturerCode";
                if (!filter_manu.equalsIgnoreCase("") && filter_manu != null) {
                    sql = sql + " and b.manufacturerName = '" + filter_manu + "'";
                }
                if (!filter_product.equalsIgnoreCase("") && filter_product != null) {
                    sql = sql + " and leftrule like '%" + filter_product + "%'";
                }
                if (!filter_purchased_product.equalsIgnoreCase("") && filter_purchased_product != null) {
                    sql = sql + " and rightrule like '%" + filter_purchased_product + "%'";
                }
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
}
