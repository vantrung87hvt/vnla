/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author NTC
 */
public class User {

	public static String kiemtra(DataManager dataManager, String username,
			String password) throws SQLException {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select customerCode from customer where username = "
						+ "'"
						+ username
						+ "'"
						+ " and password = "
						+ "'"
						+ password + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						if (rs.next()) {
							return rs.getString(1);
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
		return "";
	}

	public static String kiemtraadmin(DataManager dataManager, String username,
			String password) throws SQLException {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select admin_id from administrator where admin_name = "
						+ "'"
						+ username
						+ "'"
						+ " and admin_pass = "
						+ "'"
						+ password + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						if (rs.next()) {
							return rs.getString(1);
						}
					} finally {
						rs.close();
					}
				} finally {
					stm.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not test:" + e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return "";
	}

	public static boolean kiemtratrung(DataManager dataManager, String username)
			throws SQLException {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select count(*) from customer where username = "
						+ "'" + username + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						if (rs.next()) {
							if (rs.getLong(1) > 0)
								return false;
							else
								return true;
						}
					} finally {
						rs.close();
					}
				} finally {
					stm.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not test:" + e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return false;
	}

	public static boolean ChangePass(DataManager dataManager, String userId,
			String newPass) throws SQLException {
		String error = "";
		boolean flag = false;
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			Statement stmt = null;

			try {
				connection.setAutoCommit(false);
				stmt = connection.createStatement();
				try {
					String sql = "update customer set password=" + "'"
							+ newPass + "' where username='" + userId + "'";

					stmt.executeUpdate(sql);

					connection.commit();
					flag = true;

				} catch (SQLException e) {

					error += e.getMessage();
					connection.rollback();
				}

			} finally {
				connection.setAutoCommit(true);
				if (stmt != null) {
					stmt.close();
				}
				dataManager.putConnection(connection);
			}
			if (!error.equals("")) {
				throw new SQLException(error);
			}

		}
		return flag;
	}
}
