/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.model;

import digitalshop.beans.Mail;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Nguyen Duc Canh
 */
public class MailPeer {
	public static ArrayList getMailContenByEmail(DataManager dataManager,
			String mailKH) throws SQLException {

		ArrayList<Mail> listMail = new ArrayList<Mail>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select * from Mail where customerMail = '"
						+ mailKH + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							Mail mail = new Mail();
							Integer id = rs.getInt("ID");
							Integer makh = rs.getInt("customerCode");
							mail.setID(id.toString());
							mail.setMakhachhang(makh.toString());
							mail.setMailKhachhang(rs.getString("customerMail"));
							Date date = rs.getDate("mailDate");
							mail.setNgaytao(date.toString());
							mail.setNoidungMail(rs.getString("mailContent"));
							mail.setTrangthai(rs.getString("mailStatus"));
							listMail.add(mail);

						}
					} finally {
						rs.close();
					}
				} finally {
					stm.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get laptop: " +
				// e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return listMail;
	}

	public static boolean insertMail(DataManager dataManager, Mail mail) {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			Statement stmt = null;
			try {
				stmt = connection.createStatement();
				try {
					connection.setAutoCommit(false);
					String makh = mail.getMakhachhang();
					String sql = "";
					if (makh.equals("")) {
						sql = " insert into mail (customerMail, mailContent, mailDate, mailStatus) values ('"
								+ mail.getMailKhachhang()
								+ "','"
								+ mail.getNoidungMail()
								+ "',(select curdate()),"
								+ "'processing'"
								+ ")";

					} else {
						sql = " insert into mail (customerMail, mailContent, mailDate, mailStatus, customerCode) values ('"
								+ mail.getMailKhachhang()
								+ "','"
								+ mail.getNoidungMail()
								+ "',(select curdate()),"
								+ "'processing',"
								+ makh + ")";

					}

					stmt.execute(sql);
					connection.commit();

					try {
						stmt.close();
					} finally {
						stmt = null;
					}
				} catch (SQLException e) {
					System.out.println("Could not insert mail: "
							+ e.getMessage());
					try {
						connection.rollback();
						return false;
					} catch (SQLException ee) {
						return false;
					}
				}
			} catch (SQLException e) {
				return false;
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
						return false;
					} catch (SQLException e) {
						return false;
					}
				}
				dataManager.putConnection(connection);
			}
		}
		return true;
	}

	public static ArrayList getListOfMails(DataManager dataManager, int page)
			throws SQLException {
		ArrayList mails = new ArrayList();
		Connection connection = dataManager.getConnection();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				int limit = 10 * (page - 1);
				String sql = "select * from mail";
				sql += " limit " + limit + "," + 10;
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							Mail mail = new Mail();

							mail.setID(rs.getString("id"));
							mail.setMailKhachhang(rs.getString("customerMail"));
							mail.setNoidungMail(rs.getString("mailContent"));
							mail.setNgaytao(rs.getString("mailDate"));
							mail.setTrangthai(rs.getString("mailStatus"));
							mail.setMakhachhang(rs.getString("customerCode"));

							mails.add(mail);
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
		return mails;
	}

	public static int getCountMails(DataManager dataManager)
			throws SQLException {
		Connection connection = dataManager.getConnection();
		int count = 0;

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select count(*) as count from mail";
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

	public static boolean deleteMail(DataManager dataManager, int iD) {
		// TODO Auto-generated method stub
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "Delete from mail where ID =" + iD;
				stm.execute(sql);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

}
