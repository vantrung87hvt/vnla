/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.model;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import digitalshop.beans.User;
import digitalshop.beans.MD5;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author tindt
 */
public class UserPeer {
    public static ArrayList getListOfUser(DataManager dataManager, int page) throws SQLException {
        ArrayList users =new ArrayList();
        Connection connection = dataManager.getConnection();

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                int limit = 10*(page - 1);
                String sql = "select user_id, username, status, date_added from user";
                sql += " limit " + limit + "," + 10;
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            User user = new User();

                            user.setUser_id(rs.getString("user_id"));
                            user.setUsername(rs.getString("username"));
                            user.setStatus(rs.getInt("status"));
                            user.setDate_added(rs.getString("date_added"));

                            users.add(user);
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
        return users;
    }

    public static int getCountUser(DataManager dataManager) throws SQLException {
        Connection connection = dataManager.getConnection();
        int count=0;

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select count(*) as count from user";
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

    public static int checkUser(DataManager dataManager, String username, String password) throws SQLException {
        Connection connection = dataManager.getConnection();
        int count=0;

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String password_md5 = MD5.encode(password);
                String sql = "select count(*) as count from user where username='" + username + "' and password='" + password_md5 + "'";
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

    public static String[] splitPermission(DataManager dataManager, String username) throws SQLException {
        String[] PermissionList = new String[100];
        Connection connection = dataManager.getConnection();
        String permission = "";

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select b.permission as permission from user a, user_group b where a.user_group_id = b.user_group_id and a.username='" + username + "'";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        rs.next();
                        permission = rs.getString("permission");
                        PermissionList = permission.split(";");
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
        return PermissionList;
    }

    public static void deleteUser(DataManager dataManager, String user_id) throws SQLException {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "DELETE FROM user WHERE user_id='" + user_id + "'";
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

    public static void insertUser(DataManager dataManager, User user) throws SQLException {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "insert into user(username,firstname,lastname,email,user_group_id,password,status,date_added) values('"+user.getUsername()+"','"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getEmail()+"','"+user.getUser_group_id()+"','"+user.getPassword()+"','"+user.getStatus()+"',sysdate())";
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

    public static User getUserById(DataManager dataManager, String id) throws SQLException {
        User user = new User();
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "select * from user where user_id='"+id+"'";
                try {
                    ResultSet rs;
                    rs = stm.executeQuery(sql);
                    try {
                        if (rs.next()) {
                            user.setUser_id(rs.getString("user_id"));
                            user.setUser_group_id(rs.getString("user_group_id"));
                            user.setUsername(rs.getString("username"));
                            user.setFirstName(rs.getString("firstname"));
                            user.setLastName(rs.getString("lastname"));
                            user.setEmail(rs.getString("email"));
                            user.setStatus(Integer.valueOf(rs.getString("status")));
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
        return user;
    }

    public static void updateUser(DataManager dataManager, String uid, User user) throws SQLException {
        Connection connection = dataManager.getConnection();
        if (connection != null) {
            try {
                Statement stm = connection.createStatement();
                String sql = "update user set username='"+user.getUsername()+"',firstname='"+user.getFirstName()+"',lastname='"+user.getLastName()+"',email='"+user.getEmail()+"',user_group_id='"+user.getUser_group_id()+"',password='"+user.getPassword()+"',status='"+user.getStatus()+"' where user_id='"+uid+"'";
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
