/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import digitalshop.beans.UserGroup;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author tindt
 */
public class UserGroupPeer {

    public static ArrayList getListOfUserGroup(DataManager dataManager, int page) throws SQLException {
        ArrayList usergroups = new ArrayList();
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                int limit = 10 * (page - 1);
                String sql = "select user_group_id, name, accessp, insertp, modifyp, deletep, exportp from user_group";
                sql += " limit " + limit + "," + 10;
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            UserGroup usergroup = new UserGroup();

                            usergroup.setUser_group_id(rs.getString("user_group_id"));
                            usergroup.setName(rs.getString("name"));
                            usergroup.setAccessp(rs.getString("accessp"));
                            usergroup.setInsertp(rs.getString("insertp"));
                            usergroup.setModifyp(rs.getString("modifyp"));
                            usergroup.setDeletep(rs.getString("deletep"));
                            usergroup.setExportp(rs.getString("exportp"));

                            usergroups.add(usergroup);
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
        return usergroups;
    }

    public static int getCountUserGroup(DataManager dataManager) throws SQLException {
        Connection connection = dataManager.getConnection();
        int count = 0;

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select count(*) as count from user_group";
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

    public static void deleteUserGroup(DataManager dataManager, String user_group_id) throws SQLException {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "DELETE FROM user_group WHERE user_group_id='" + user_group_id + "'";
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

    public static void insertUserGroup(DataManager dataManager, String usergroup, String access, String insert, String modify, String delete, String export) throws SQLException {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "insert into user_group(name,accessp,insertp,modifyp,deletep,exportp) values('" + usergroup + "','" + access + "','" + insert + "','" + modify + "','" + delete + "','" + export + "')";
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

    public static void updateUserGroup(DataManager dataManager, String id, String usergroup, String access, String insert, String modify, String delete, String export) throws SQLException {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "update user_group set name='" + usergroup + "',accessp='" + access + "',insertp='" + insert + "',modifyp='" + modify + "',deletep='" + delete + "',exportp='" + export + "' where user_group_id='" + id + "'";
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

    public static UserGroup getUserGroupById(DataManager dataManager, String id) throws SQLException {
        UserGroup usergroup = new UserGroup();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select * from user_group where user_group_id='" + id + "'";
                try {
                    ResultSet rs;
                    rs = stm.executeQuery(sql);
                    try {
                        if (rs.next()) {
                            usergroup.setUser_group_id(rs.getString("user_group_id"));
                            usergroup.setName(rs.getString("name"));
                            usergroup.setAccessp(rs.getString("accessp"));
                            usergroup.setInsertp(rs.getString("insertp"));
                            usergroup.setModifyp(rs.getString("modifyp"));
                            usergroup.setDeletep(rs.getString("deletep"));
                            usergroup.setExportp(rs.getString("exportp"));
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
        return usergroup;
    }

    public static ArrayList getListOfAccess(DataManager dataManager) throws SQLException {
        ArrayList accessList = new ArrayList();
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select name from permission_catalog_access order by accessId";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            String accessp = rs.getString("name");
                            accessList.add(accessp);
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
        return accessList;
    }

    public static ArrayList getListOfInsert(DataManager dataManager) throws SQLException {
        ArrayList insertList = new ArrayList();
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select name from permission_catalog_insert order by insertId";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            String insertp = rs.getString("name");
                            insertList.add(insertp);
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
        return insertList;
    }

    public static ArrayList getListOfModify(DataManager dataManager) throws SQLException {
        ArrayList modifyList = new ArrayList();
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select name from permission_catalog_modify order by modifyId";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            String modifyp = rs.getString("name");
                            modifyList.add(modifyp);
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
        return modifyList;
    }

    public static ArrayList getListOfDelete(DataManager dataManager) throws SQLException {
        ArrayList deleteList = new ArrayList();
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select name from permission_catalog_delete order by deleteId";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            String deletep = rs.getString("name");
                            deleteList.add(deletep);
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
        return deleteList;
    }

    public static ArrayList getListOfExport(DataManager dataManager) throws SQLException {
        ArrayList exportList = new ArrayList();
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select name from permission_catalog_export order by exportId";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            String exportp = rs.getString("name");
                            exportList.add(exportp);
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
        return exportList;
    }

    public static String getAccessp(DataManager dataManager, String username) throws SQLException {
        String accessp = "";
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select b.accessp from user a, user_group b where a.user_group_id=b.user_group_id and username='"+username+"'";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        rs.next();
                        accessp = rs.getString("accessp");
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
        return accessp;
    }
    public static String getInsertp(DataManager dataManager, String username) throws SQLException {
        String accessp = "";
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select b.insertp from user a, user_group b where a.user_group_id=b.user_group_id and username='"+username+"'";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        rs.next();
                        accessp = rs.getString("insertp");
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
        return accessp;
    }
    public static String getModifyp(DataManager dataManager, String username) throws SQLException {
        String accessp = "";
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select b.modifyp from user a, user_group b where a.user_group_id=b.user_group_id and username='"+username+"'";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        rs.next();
                        accessp = rs.getString("modifyp");
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
        return accessp;
    }
    public static String getDeletep(DataManager dataManager, String username) throws SQLException {
        String accessp = "";
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select b.deletep from user a, user_group b where a.user_group_id=b.user_group_id and username='"+username+"'";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        rs.next();
                        accessp = rs.getString("deletep");
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
        return accessp;
    }
}
