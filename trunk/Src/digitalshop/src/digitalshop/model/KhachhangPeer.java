/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import digitalshop.beans.Khachhang;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 
 * @author NTC
 */
public class KhachhangPeer {

	public static void insertKhachhang(DataManager dataManager,
			Khachhang khachhang) throws SQLException {
		String error = "";
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			Statement stmt = null;

			try {
				connection.setAutoCommit(false);
				stmt = connection.createStatement();
				try {
					String sql = "insert into customer(customerFirstname,customerLastname,username,password,address,districts,province,email,phone,age,sex,jobCode) values("
							+ "'"
							+ khachhang.getHokh()
							+ "',"
							+ "'"
							+ khachhang.getTenkh()
							+ "',"
							+ "'"
							+ khachhang.getUsername()
							+ "',"
							+ "'"
							+ khachhang.getPassword()
							+ "',"
							+ "'"
							+ khachhang.getDiachi()
							+ "',"
							+ "'"
							+ khachhang.getHuyen()
							+ "',"
							+ "'"
							+ khachhang.getTinh()
							+ "',"
							+ "'"
							+ khachhang.getEmail()
							+ "',"
							+ "'"
							+ khachhang.getDienthoai()
							+ "',"
							+ "'"
							+ khachhang.getDotuoi()
							+ "',"
							+ "'"
							+ khachhang.getGioitinh()
							+ "',"
							+ "'"
							+ khachhang.getMa_nghenghiep() + "')";
					stmt.executeUpdate(sql);

					connection.commit();

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
	}

	public static Khachhang getthongtinkhachhang(DataManager dataManager,
			String userId) throws SQLException {
		Khachhang khachhang = new Khachhang();
		Connection connection = dataManager.getConnection();
		// int record = 3;
		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select * from customer where username= '"
						+ userId + "'";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							// Laptop laptop = new Laptop();
							// laptop.setMadong(rs.getString(1));
							khachhang.setUsername(rs.getString("username"));
							khachhang.setDiachi(rs.getString("address"));
							khachhang.setDienthoai(rs.getString("phone"));
							khachhang.setEmail(rs.getString("email"));
							khachhang.setGioitinh(rs.getString("sex"));
							khachhang.setHokh(rs.getString("customerFirstname"));
							khachhang.setHuyen(rs.getString("districts"));
							khachhang.setTinh(rs.getInt("province"));
							khachhang.setMakh(rs.getString("customerCode"));
							khachhang.setPassword(rs.getString("password"));
							khachhang.setTenkh(rs.getString("customerLastname"));
							khachhang.setdotuoi(rs.getInt("age"));
							khachhang.setma_Nghenghiep(rs.getInt("jobCode"));

						}
					} finally {
						rs.close();
					}
				} finally {
					s.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get books: " + e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return khachhang;
	}

	public static void updateKhachhang(DataManager dataManager,
			Khachhang khachhang, String userId) throws SQLException {
		String error = "";
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			Statement stmt = null;

			try {
				connection.setAutoCommit(false);
				stmt = connection.createStatement();
				try {
					String sql = "update customer set customerLastname=" + "'"
							+ khachhang.getTenkh() + "', username =" + "'"
							+ khachhang.getUsername() + "', address=" + "'"
							+ khachhang.getDiachi() + "', districts=" + "'"
							+ khachhang.getHuyen() + "', province=" + "'"
							+ khachhang.getTinh() + "', email=" + "'"
							+ khachhang.getEmail() + "', phone=" + "'"
							+ khachhang.getDienthoai() + "', age=" + "'"
							+ khachhang.getDotuoi() + "', sex='"
							+ khachhang.getGioitinh() + "', jobCode=" + "'"
							+ khachhang.getMa_nghenghiep()
							+ "', customerFirstname=" + "'"
							+ khachhang.getHokh() + "' where username='"
							+ userId + "'";

					stmt.executeUpdate(sql);

					connection.commit();

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
	}

	public static ArrayList getCustomerFilter(DataManager dataManager,
			String filter_name, String filter_email, String filter_status,
			String filter_date_added, int page) throws SQLException {
		ArrayList khachhangs = new ArrayList();
		Connection connection = dataManager.getConnection();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				int limit = 10 * (page - 1);
				String sql = "select customerCode, customerFirstname, customerLastname, username, email, status, cast(date_added as char) as date_added from customer where 1=1";
				if (!filter_name.equalsIgnoreCase("") && filter_name != null) {
					sql = sql
							+ " and concat(customerFirstname,' ',customerLastname) like '%"
							+ filter_name + "%'";
				}
				if (!filter_email.equalsIgnoreCase("") && filter_email != null) {
					sql += " and email like '%" + filter_email + "%'";
				}
				if (!filter_status.equalsIgnoreCase("")
						&& filter_status != null) {
					sql += " and status = '" + filter_status + "'";
				}
				if (!filter_date_added.equalsIgnoreCase("")
						&& filter_date_added != null) {
					sql += " and cast(date_added as char) like '%"
							+ filter_date_added + "%'";
				}
				sql += " order by customerFirstname";
				sql += " limit " + limit + "," + 10;
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							Khachhang khachhang = new Khachhang();

							khachhang.setMakh(rs.getString("customerCode"));
							khachhang.setUsername(rs.getString("username"));
							khachhang.setHokh(rs.getString("customerFirstname"));
							khachhang
									.setTenkh(rs.getString("customerLastname"));
							khachhang.setEmail(rs.getString("email"));
							khachhang.setStatus(rs.getInt("status"));
							khachhang.setDate_added(rs.getString("date_added"));

							khachhangs.add(khachhang);
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
		return khachhangs;
	}

	public static int getCountCustomerFilter(DataManager dataManager,
			String filter_name, String filter_email, String filter_status,
			String filter_date_added) throws SQLException {
		Connection connection = dataManager.getConnection();
		int count = 0;

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select count(*) as count from customer where 1=1";
				if (!filter_name.equalsIgnoreCase("") && filter_name != null) {
					sql = sql
							+ " and concat(customerFirstname,' ',customerLastname) like '%"
							+ filter_name + "%'";
				}
				if (!filter_email.equalsIgnoreCase("") && filter_email != null) {
					sql += " and email like '%" + filter_email + "%'";
				}
				if (!filter_status.equalsIgnoreCase("")
						&& filter_status != null) {
					sql += " and status = '" + filter_status + "'";
				}
				if (!filter_date_added.equalsIgnoreCase("")
						&& filter_date_added != null) {
					sql += " and cast(date_added as char) like '%"
							+ filter_date_added + "%'";
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

	public static ArrayList getListOfDMCustomer(DataManager dataManager,
			String filter_customerID, String filter_customerName,
			String filter_class, int page) throws SQLException {
		ArrayList khachhangs = new ArrayList();
		Connection connection = dataManager.getConnection();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				int limit = 10 * (page - 1);
				String sql = "select a.customerCode, a.customerClassified, b.customerFirstname, b.customerLastname from class_customer a, customer b where a.customerCode = b.customerCode";
				if (!filter_customerID.equalsIgnoreCase("")
						&& filter_customerID != null) {
					sql = sql + " and a.customerCode like '%"
							+ filter_customerID + "%'";
				}
				if (!filter_customerName.equalsIgnoreCase("")
						&& filter_customerName != null) {
					sql = sql
							+ " and concat(b.customerFirstname,b.customerLastname) like '%"
							+ filter_customerName + "%'";
				}
				if (!filter_class.equalsIgnoreCase("") && filter_class != null) {
					sql = sql + " and a.customerClassified = '" + filter_class
							+ "'";
				}
				sql += " order by customerCode";
				sql += " limit " + limit + "," + 10;
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							Khachhang khachhang = new Khachhang();

							khachhang.setMakh(rs.getString("customerCode"));
							khachhang.setHokh(rs.getString("customerFirstname"));
							khachhang
									.setTenkh(rs.getString("customerLastname"));
							khachhang.setLevel(rs
									.getString("customerClassified"));

							khachhangs.add(khachhang);
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
		return khachhangs;
	}

	public static int getCountDMCustomer(DataManager dataManager,
			String filter_customerID, String filter_customerName,
			String filter_class) throws SQLException {
		Connection connection = dataManager.getConnection();
		int count = 0;

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select count(*) as count from class_customer a, customer b where a.customerCode=b.customerCode";
				if (!filter_customerID.equalsIgnoreCase("")
						&& filter_customerID != null) {
					sql = sql + " and a.customerCode like '%"
							+ filter_customerID + "%'";
				}
				if (!filter_customerName.equalsIgnoreCase("")
						&& filter_customerName != null) {
					sql = sql
							+ " and concat(b.customerFirstname,b.customerLastname) like '%"
							+ filter_customerName + "%'";
				}
				if (!filter_class.equalsIgnoreCase("") && filter_class != null) {
					sql = sql + " and a.customerClassified = '" + filter_class
							+ "'";
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

	public static void updateDataMiningCustomer(DataManager dataManager,
			String makh, String filter_class) throws SQLException {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "update class_customer set customerClassified='"
						+ filter_class + "' where customerCode='" + makh + "'";
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

	public static ArrayList getCustomerView(DataManager dataManager, int page)
			throws SQLException {
		ArrayList khachhangs = new ArrayList();
		Connection connection = dataManager.getConnection();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				int limit = 10 * (page - 1);
				String sql = "select customerCode, customerFirstname, customerLastname, username, email, status, cast(date_added as char) as date_added from customer order by customerFirstname";
				sql += " limit " + limit + "," + 10;
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							Khachhang khachhang = new Khachhang();

							khachhang.setMakh(rs.getString("customerCode"));
							khachhang.setUsername(rs.getString("username"));
							khachhang.setHokh(rs.getString("customerFirstname"));
							khachhang
									.setTenkh(rs.getString("customerLastname"));
							khachhang.setEmail(rs.getString("email"));
							khachhang.setStatus(rs.getInt("status"));
							khachhang.setDate_added(rs.getString("date_added"));

							khachhangs.add(khachhang);
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
		return khachhangs;
	}

	public static int getCountCustomer(DataManager dataManager)
			throws SQLException {
		Connection connection = dataManager.getConnection();
		int count = 0;

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select count(*) as count from customer";
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

	public static void deleteCustomer(DataManager dataManager, String makh)
			throws SQLException {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "DELETE FROM customer WHERE customerCode='" + makh
						+ "'";
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

	public static int getCustomersStatisticInDay(DataManager dataManager, int i)
			throws SQLException {
		int customerNo = 0;
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "";
				if (i != 24) {
					sql = "select count(distinct id) as customerNo from account_log where date(logdate)=date(sysdate()) and hour(logdate)<"
							+ i
							+ " or (hour(logdate)="
							+ i
							+ " and minute(logdate)='00' and second(logdate)='00')";
				} else {
					sql = "select count(distinct id) as customerNo from account_log where date(logdate)=date(sysdate())";
				}
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							customerNo = Integer.valueOf(rs
									.getString("customerNo"));
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
		return customerNo;
	}

	public static void insertCustomer(DataManager dataManager,
			Khachhang customer) throws SQLException {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "insert into customer(customerFirstname,customerLastname,username,password,address,districts,province,email,phone,age,sex,jobCode,status,date_added) values('"
						+ customer.getHokh()
						+ "','"
						+ customer.getTenkh()
						+ "','"
						+ customer.getUsername()
						+ "','"
						+ customer.getPassword()
						+ "','"
						+ customer.getDiachi()
						+ "','"
						+ customer.getHuyen()
						+ "','"
						+ customer.getTinh()
						+ "','"
						+ customer.getEmail()
						+ "','"
						+ customer.getDienthoai()
						+ "','"
						+ customer.getDotuoi()
						+ "','"
						+ customer.getGioitinh()
						+ "','"
						+ customer.getMa_nghenghiep()
						+ "','"
						+ customer.getStatus() + "',sysdate())";
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

	public static void resetPassword(DataManager dataManager, String makh,
			String password) throws SQLException {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "update customer set password='" + password
						+ "' where customerCode='" + makh + "'";
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

	public static String getTinhByMaTinh(DataManager dataManager, int maTinh)
			throws SQLException {
		Connection connection = dataManager.getConnection();
		String tinh = new String();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select districtsName from districts where districtsCode = "
						+ maTinh;
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						rs.next();
						tinh = rs.getString("districtsName");
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
		return tinh;
	}

	public static String getMaTinhByTinh(DataManager dataManager, String tinh)
			throws SQLException {
		Connection connection = dataManager.getConnection();
		String maTinh = new String();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select districtsCode from districts where districtsName = '"
						+ tinh + "'";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						rs.next();
						maTinh = rs.getString("districtsCode");
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
		return maTinh;
	}

	public static String getDotuoiByMaDotuoi(DataManager dataManager,
			int maDotuoi) throws SQLException {
		Connection connection = dataManager.getConnection();
		String dotuoi = new String();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select age from age where ageCode = '" + maDotuoi
						+ "'";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						rs.next();
						dotuoi = rs.getString("age");
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
		return dotuoi;
	}

	public static String getMaDotuoiByDotuoi(DataManager dataManager,
			String dotuoi) throws SQLException {
		Connection connection = dataManager.getConnection();
		String maDotuoi = new String();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select ageCode from age where age = '" + dotuoi
						+ "'";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						rs.next();
						maDotuoi = rs.getString("ageCode");
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
		return maDotuoi;
	}

	public static String getNghenghiepByMaNghenghiep(DataManager dataManager,
			int maNghenghiep) throws SQLException {
		Connection connection = dataManager.getConnection();
		String nghenghiep = new String();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select jobName from job where jobCode = '"
						+ maNghenghiep + "'";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						rs.next();
						nghenghiep = rs.getString("jobName");
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
		return nghenghiep;
	}

	public static String getMaNghenghiepByNghenghiep(DataManager dataManager,
			String nghenghiep) throws SQLException {
		Connection connection = dataManager.getConnection();
		String maNghenghiep = new String();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select jobCode from job where jobName = '"
						+ nghenghiep + "'";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						rs.next();
						maNghenghiep = rs.getString("jobCode");
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
		return maNghenghiep;
	}

	public static ArrayList getNormalUser(DataManager dataManager)
			throws SQLException {
		ArrayList khachhangs = new ArrayList();
		Connection connection = dataManager.getConnection();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select * from customer";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							Khachhang khachhang = new Khachhang();

							khachhang.setUsername(rs.getString("username"));
							khachhang.setDiachi(rs.getString("address"));
							khachhang.setDienthoai(rs.getString("phone"));
							khachhang.setEmail(rs.getString("email"));
							khachhang.setGioitinh(rs.getString("sex"));
							khachhang.setHokh(rs.getString("customerFirstname"));
							khachhang.setHuyen(rs.getString("districts"));
							khachhang.setTinh(rs.getInt("province"));
							khachhang.setMakh(rs.getString("customerCode"));
							khachhang.setPassword(rs.getString("password"));
							khachhang
									.setTenkh(rs.getString("customerLastname"));
							khachhang.setdotuoi(rs.getInt("age"));
							khachhang.setma_Nghenghiep(rs.getInt("jobCode"));

							khachhangs.add(khachhang);
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
		return khachhangs;
	}

	public static ArrayList getAdministratorUser(DataManager dataManager)
			throws SQLException {
		ArrayList admins = new ArrayList();
		Connection connection = dataManager.getConnection();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select * from customer where username in (select admin_name from administrator)";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							Khachhang khachhang = new Khachhang();

							khachhang.setUsername(rs.getString("username"));
							khachhang.setDiachi(rs.getString("address"));
							khachhang.setDienthoai(rs.getString("phone"));
							khachhang.setEmail(rs.getString("email"));
							khachhang.setGioitinh(rs.getString("sex"));
							khachhang.setHokh(rs.getString("customerFirstname"));
							khachhang.setHuyen(rs.getString("districts"));
							khachhang.setTinh(rs.getInt("province"));
							khachhang.setMakh(rs.getString("customerCode"));
							khachhang.setPassword(rs.getString("password"));
							khachhang
									.setTenkh(rs.getString("customerLastname"));
							khachhang.setdotuoi(rs.getInt("age"));
							khachhang.setma_Nghenghiep(rs.getInt("jobCode"));

							admins.add(khachhang);
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
		return admins;
	}

	public static ArrayList getAdminUserSearch(DataManager dataManager,
			String keyword) throws SQLException {
		ArrayList khachhangs = new ArrayList();
		Connection connection = dataManager.getConnection();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select * from customer where username like '%"
						+ keyword + "%'";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							Khachhang khachhang = new Khachhang();

							khachhang.setUsername(rs.getString("username"));
							khachhang.setDiachi(rs.getString("address"));
							khachhang.setDienthoai(rs.getString("phone"));
							khachhang.setEmail(rs.getString("email"));
							khachhang.setGioitinh(rs.getString("sex"));
							khachhang.setHokh(rs.getString("customerFirstname"));
							khachhang.setHuyen(rs.getString("districts"));
							khachhang.setTinh(rs.getInt("province"));
							khachhang.setMakh(rs.getString("customerCode"));
							khachhang.setPassword(rs.getString("password"));
							khachhang
									.setTenkh(rs.getString("customerLastname"));
							khachhang.setdotuoi(rs.getInt("age"));
							khachhang.setma_Nghenghiep(rs.getInt("jobCode"));

							khachhangs.add(khachhang);
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
		return khachhangs;
	}

	public static int getCountCustomerHour(DataManager dataManager,
			String hour, String h1) throws SQLException {
		Connection connection = dataManager.getConnection();
		int count = 0;
		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select count(*) as count from customer where date_added <= concat(cast(date(sysdate()) as char),' ','"
						+ hour
						+ "':00:00"
						+ "date_added > concat(cast(date(sysdate()) as char),' ','"
						+ h1 + "':00:00";
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

	// canhnd get level khach hang theo ma khach hang
	public static String getLevelCustomerByIDCustomer(DataManager dataManager,
			String makh) throws SQLException {
		Connection connection = dataManager.getConnection();
		String makhachhang = "";
		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select cl.customerClassified"
						+ " from customerLevel cl, customer kh where cl.customerCode=kh.customerCode and kh.customerCode='"
						+ makh + "'";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						if (rs.next()) {
							makhachhang = rs.getString("customerClassified");
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
		return makhachhang;
	}

	// lay taat ca khach hang thuoc cung level voi kh dang xet
	public static String getCustomerByLevCustomer(DataManager dataManager,
			String makh) throws SQLException {
		Connection connection = dataManager.getConnection();
		String makhachhang = "";
		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select cl.customerClassified"
						+ " from customerLevel cl, customer kh where cl.customerCode=kh.customerCode and kh.customerCode='"
						+ makh + "'";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						if (rs.next()) {
							makhachhang = rs.getString("cl.customerClassified");
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
		return makhachhang;
	}
}
