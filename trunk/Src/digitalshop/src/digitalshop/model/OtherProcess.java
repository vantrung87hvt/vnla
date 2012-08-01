/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import digitalshop.beans.Laptop;
import digitalshop.beans.Phukien;
import digitalshop.beans.Nghenghiep;
import digitalshop.beans.tinhthanh;
import digitalshop.beans.Dotuoi;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.Iterator;
import digitalshop.beans.NhomSPSearch;
import digitalshop.beans.LichsuGiaodich;

/**
 * 
 * @author NTC
 */
public class OtherProcess {

	public static ArrayList getMiningProducts(DataManager dataManager,
			int start, int record) throws SQLException {
		ArrayList<Laptop> laptops = new ArrayList<Laptop>();
		Connection connection = dataManager.getConnection();
		// int record = 3;
		String error = "";
		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select productCategoryCode from laptop LIMIT "
						+ start + "," + record;
				;
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							// Laptop laptop = new Laptop();
							// laptop.setMadong(rs.getString(1));

							laptops.add(LaptopPeer.getLaptopByID(dataManager,
									rs.getString(1)));
						}
					} finally {
						rs.close();
					}
				} finally {
					s.close();
				}
			} catch (SQLException e) {
				error = e.getMessage();
				System.out.println("Could not get books: " + e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		if (!error.equals("")) {
			throw new SQLException(error);
		}
		return laptops;
	}

	public static ArrayList getAllTinhthanh(DataManager dataManager)
			throws SQLException {
		ArrayList<tinhthanh> tinhthanh = new ArrayList<tinhthanh>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select * from districts;";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							tinhthanh.add(new tinhthanh(rs.getInt(1), rs
									.getString(2)));
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
		return tinhthanh;
	}

	public static ArrayList getAllNghenghiep(DataManager dataManager)
			throws SQLException {
		ArrayList<Nghenghiep> nghenghiep = new ArrayList<Nghenghiep>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select jobCode,jobName from job";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							Nghenghiep nghe = new Nghenghiep();
							nghe.setManghe(Integer.parseInt(rs
									.getString("jobCode")));
							nghe.setTennghe(rs.getString("jobName"));
							nghenghiep.add(nghe);
						}
					} finally {
						rs.close();
					}
				} finally {
					s.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get categories: " +
				// e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return nghenghiep;
	}

	public static ArrayList getAllDotuoi(DataManager dataManager)
			throws SQLException {
		ArrayList<Dotuoi> dotuoi = new ArrayList<Dotuoi>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select ageCode,age from age";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							Dotuoi tuoi = new Dotuoi();
							tuoi.setAllDotuoi(rs.getInt("ageCode"),
									rs.getString("age"));
							dotuoi.add(tuoi);
						}
					} finally {
						rs.close();
					}
				} finally {
					s.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get categories: " +
				// e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return dotuoi;
	}

	public static ArrayList getSpXemCung(DataManager dataManager,
			String IdSanPham) throws SQLException {
		ArrayList laptops = new ArrayList();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement s = null;
				try {
					s = connection.createStatement();
				} catch (Exception e) {
					String t = e.getMessage();
					t += ";";
				}
				String sql = "select rightrule from ass_productviewcouple where leftrule='"
						+ IdSanPham + "'";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						if (rs.next()) {
							String sp = rs.getString(1);
							String[] temp = sp.split(",");
							if (temp.length > 0) {
								for (int i = 0; i < temp.length; i++) {
									DataManager datatem = dataManager;
									if (isLaptop(datatem, temp[i])) {
										DataManager dm = dataManager;
										Laptop temppro = LaptopPeer
												.getLaptopByID(dm, temp[i]);
										laptops.add(temppro);
									} else {
										DataManager dm = dataManager;
										Phukien temppro = PhuKienPeer
												.getPhuKienByName(dm, temp[i]);
										laptops.add(temppro);
									}

								}
							}

						}
					} catch (Exception ex) {

						System.out.println(ex.getMessage());
					} finally {
						rs.close();
					}
				} finally {
					s.close();
				}
			} catch (SQLException e) {
				System.out.println("Could not get: " + e.getMessage());

			} finally {
				// dataManager.putConnection(connection);
			}
		}
		return laptops;
	}

	// lay san pham thuong duoc xem cung
	public static ArrayList getSpMuaCung(DataManager dataManager,
			String IdSanPham) throws SQLException {
		ArrayList laptops = new ArrayList();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement s = null;
				try {
					s = connection.createStatement();
				} catch (Exception e) {
					String t = e.getMessage();
					t += ";";
				}
				String sql = "select rightrule from ass_productbuycouple where leftrule='"
						+ IdSanPham + "'";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						if (rs.next()) {
							String sp = rs.getString(1);
							String[] temp = sp.split(",");
							if (temp.length > 0) {

								for (int i = 0; i < temp.length; i++) {

									DataManager datatem = dataManager;
									if (isLaptop(datatem, temp[i])) {
										DataManager dm = dataManager;
										Laptop temppro = LaptopPeer
												.getLaptopByID(dm,
														temp[i].trim());
										laptops.add(temppro);
									} else {
										DataManager dm = dataManager;
										Phukien temppro = PhuKienPeer
												.getPhuKienByName(dm,
														temp[i].trim());
										laptops.add(temppro);
									}

								}
							}

						}
					} catch (Exception ex) {

						System.out.println(ex.getMessage());
					} finally {
						rs.close();
					}
				} finally {
					s.close();
				}
			} catch (SQLException e) {
				System.out.println("Could not get: " + e.getMessage());

			} finally {
				// dataManager.putConnection(connection);
			}
		}
		return laptops;
	}

	// lay san pham xem nhieu nhat
	public static ArrayList getSpXemNhieuNhat(DataManager dataManager)
			throws SQLException {
		ArrayList<Laptop> laptops = new ArrayList<Laptop>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {

				Statement s = connection.createStatement();
				String sql = "select * from mostViewedProducts ORDER BY numbersOfview DESC limit 1,5";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							String sp = rs.getString(1);
							int soluotxem = Integer.parseInt(rs.getString(2));
							Laptop latop = LaptopPeer.getLaptopByID(
									dataManager, sp);
							latop.setSoluotxem(soluotxem);
							laptops.add(latop);
						}
					} finally {
						rs.close();
					}
				} finally {
					s.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get books: " + e.getMessage());
			} catch (Exception e) {

			}
		}
		return laptops;
	}

	public static String getOptions(DataManager dataManger, String sql,
			boolean allRequired, boolean nullRequired, boolean diffRequired,
			String selectedValue) {
		Connection connection = dataManger.getConnection();
		String sOptions = "";
		String sSel = "";

		if (allRequired) {
			sOptions += "<option value=\"all\">All</option>";
		} else {
			if (nullRequired) {
				sOptions += "<option value=\"null\"></option>";
			}
		}

		if (connection != null) {
			Statement stat = null;
			try {
				stat = connection.createStatement();
				ResultSet rs = stat.executeQuery(sql);
				while (rs.next()) {
					String val = rs.getString(1);
					if (val.compareTo(selectedValue) == 0) {
						sSel = "SELECTED";
					} else {
						sSel = "";
					}
					sOptions += "<option value=\"" + val + "\" " + sSel + ">"
							+ val + "</option>";
				}
				rs.close();
				stat.close();
			} catch (Exception e) {
			}
		}

		if (diffRequired) {
			sOptions += "<option value=\"khac\">Khác</option>";
		}
		return sOptions;
	}

	public static String getOptionsID(DataManager dataManager, String sql,
			boolean allRequired, boolean nullRequired, boolean diffRequired,
			String selectedValue) throws SQLException {
		Connection connection = dataManager.getConnection();
		String sOptions = "";
		String sSel = "";

		if (allRequired) {
			sOptions += "<option value=\"all\">All</option>";
		} else {
			if (nullRequired) {
				sOptions += "<option value=\"null\"></option>";
			}
		}

		if (connection != null) {
			Statement stat = null;
			try {
				stat = connection.createStatement();
				ResultSet rs = stat.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString(1);
					String val = rs.getString(2);
					if (val.compareTo(selectedValue) == 0) {
						sSel = "SELECTED";
					} else {
						sSel = "";
					}
					sOptions += "<option value=\"" + id + "\" " + sSel + ">"
							+ val + "</option>";
				}
				rs.close();
				stat.close();

			} finally {
				if (stat != null) {
					stat.close();
				}
			}
		}

		if (diffRequired) {
			sOptions += "<option value=\"khac\">Khác</option>";
		}
		return sOptions;
	}

	public static String getOptionsID2(DataManager dataManager, String sql,
			boolean allRequired, boolean nullRequired, boolean diffRequired,
			String selectedValue) throws SQLException {
		Connection connection = dataManager.getConnection();
		String sOptions = "";
		String sSel = "";

		if (allRequired) {
			sOptions += "<option value=\"all\">All</option>";
		} else {
			if (nullRequired) {
				sOptions += "<option value=\"null\"></option>";
			}
		}

		if (connection != null) {
			Statement stat = null;
			try {
				stat = connection.createStatement();
				ResultSet rs = stat.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString(1);
					String val = rs.getString(2);
					if (id.compareTo(selectedValue) == 0) {
						sSel = "SELECTED";
					} else {
						sSel = "";
					}
					sOptions += "<option value=\"" + id + "\" " + sSel + ">"
							+ val + "</option>";
				}
				rs.close();
				stat.close();

			} finally {
				if (stat != null) {
					stat.close();
				}
			}
		}

		if (diffRequired) {
			sOptions += "<option value=\"khac\">Khác</option>";
		}
		return sOptions;
	}

	// ------------------ Theo vet san pham----------------------
	public static void insertSPtheovet(DataManager dataManager, String userID,
			Timestamp time, String dongsp) throws SQLException {
		String error = "";
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			Statement stmt = null;
			try {
				connection.setAutoCommit(false);
				stmt = connection.createStatement();
				try {
					String sql = "select max(date) from ass_producttracking where userID ="
							+ "'" + userID + "'";
					ResultSet rs = stmt.executeQuery(sql);
					rs.next();

					Timestamp oldtime = rs.getTimestamp(1);

					/*
					 * neu trong khoang thoi gian 30 phut khach dang nhap thi du
					 * lieu cua khach se duoc xem nhu la cua thanh vien
					 */
					ResultSet rs1;
					String chuoisp;

					// khac date - hoac sau 2 tieng dang nhap ma khong dang xuat
					// se duoc tinh la lan dang nhap tiep theo
					if (oldtime == null
							|| ((time.getTime() - oldtime.getTime()) > 2 * 1000 * 3600)) { // insert

						// kiem tra voi gia tri gan nhat cua theovetguest
						sql = "select max(date),sessionID from ass_guestproducttracking;";
						rs1 = stmt.executeQuery(sql);
						rs1.next();

						Timestamp tmp = rs1.getTimestamp(1);
						String sessionID = rs1.getString(2);
						if (time.getTime() - tmp.getTime() < 1800 * 1000) {
							sql = "select productString from ass_guestproducttracking where date in (select max(date) from ass_guestproducttracking);";
							rs1 = stmt.executeQuery(sql);
							rs1.next();

							chuoisp = rs1.getString(1) + ", " + dongsp;
							String sql1 = "delete from ass_guestproducttracking where sessionID = '"
									+ sessionID + "';";
							stmt.executeUpdate(sql1);
						} else {
							chuoisp = dongsp;
						}

						sql = "insert into ass_producttracking values (" + "'"
								+ userID + "',(select sysdate()),'" + chuoisp
								+ "')";

					} else // update
					{
						sql = "select productString from ass_producttracking where userID = '"
								+ userID + "' and date = '" + oldtime + "'";
						rs1 = stmt.executeQuery(sql);
						rs1.next();

						chuoisp = rs1.getString(1);
						chuoisp += ", " + dongsp;
						sql = "update ass_producttracking set productString ="
								+ "'" + chuoisp + "' " + "where userID = '"
								+ userID + "' and date = '" + oldtime + "'";
					}
					stmt.executeUpdate(sql);

					connection.commit();

				} catch (SQLException e) {
					// System.out.println("Could not insert sptheovet: " +
					// e.getMessage());
					error = e.getMessage();
					try {
						connection.rollback();
					} catch (SQLException ee) {
					}
				}
			} catch (SQLException e) {
				// System.out.println("Could not insert theovet: " +
				// e.getMessage());
				error += e.getMessage();
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
		if (!error.equals("")) {
			throw new SQLException(error);
		}
	}

	public static void insertSPtheovetGuest(DataManager dataManager,
			String sessionid, Timestamp time, String dongsp)
			throws SQLException {
		String error = "";
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			Statement stmt = null;
			try {
				connection.setAutoCommit(false);
				stmt = connection.createStatement();
				try {
					String sql = "select max(date) from ass_guestproducttracking where sessionid ="
							+ "'" + sessionid + "'";
					ResultSet rs = stmt.executeQuery(sql);
					rs.next();

					Timestamp oldtime = rs.getTimestamp(1);
					// khac date
					if (oldtime == null
							|| ((time.getTime() - oldtime.getTime()) > 1 * 1000 * 3600)) { // trong
																							// vong
																							// 1h
																							// thi
																							// xem
																							// nhu
																							// la
																							// cua
																							// 1
																							// user

						sql = "insert into ass_guestproducttracking values ("
								+ "'" + sessionid + "','" + time + "','"
								+ dongsp + "')";

					} else // update
					{
						sql = "select productString from ass_guestproducttracking where sessionid = '"
								+ sessionid + "' and date = '" + oldtime + "'";
						ResultSet rs1 = stmt.executeQuery(sql);
						rs1.next();

						String chuoisp = rs1.getString(1);
						chuoisp += ", " + dongsp;
						sql = "update ass_guestproducttracking set productString ="
								+ "'"
								+ chuoisp
								+ "' "
								+ "where sessionid = '"
								+ sessionid + "' and date = '" + oldtime + "'";
					}
					stmt.executeUpdate(sql);

					connection.commit();

				} catch (SQLException e) {
					// System.out.println("Could not insert sptheovet: " +
					// e.getMessage());
					error = e.getMessage();
					try {
						connection.rollback();
					} catch (SQLException ee) {
					}
				}
			} catch (SQLException e) {
				// System.out.println("Could not insert theovet: " +
				// e.getMessage());
				error += e.getMessage();
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
		if (!error.equals("")) {
			throw new SQLException(error);
		}
	}

	static void insertTheovetShoppingCart(DataManager dataManager,
			String sessionid, Timestamp time, String dongsp)
			throws SQLException {
		String error = "";
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			Statement stmt = null;
			try {
				connection.setAutoCommit(false);
				stmt = connection.createStatement();
				try {
					String sql = "select max(date) from ass_shoppingcarttracking where sessionid ="
							+ "'" + sessionid + "'";
					ResultSet rs = stmt.executeQuery(sql);
					rs.next();

					Timestamp oldtime = rs.getTimestamp(1);
					// khac ngay
					if (oldtime == null
							|| ((time.getTime() - oldtime.getTime()) > 1 * 1000 * 3600)) {

						sql = "insert into ass_shoppingcarttracking values ("
								+ "'" + sessionid + "','" + dongsp + "','"
								+ time + "')";

					} else // update
					{
						sql = "select productString from ass_shoppingcarttracking where sessionid = '"
								+ sessionid + "' and date = '" + oldtime + "'";
						ResultSet rs1 = stmt.executeQuery(sql);
						rs1.next();

						String chuoisp = rs1.getString(1);
						chuoisp += ", " + dongsp;
						sql = "update ass_shoppingcarttracking set productString ="
								+ "'"
								+ chuoisp
								+ "' "
								+ "where sessionid = '"
								+ sessionid + "' and date = '" + oldtime + "'";
					}
					stmt.executeUpdate(sql);

					connection.commit();

				} catch (SQLException e) {
					// System.out.println("Could not insert sptheovet: " +
					// e.getMessage());
					error = e.getMessage();
					try {
						connection.rollback();
					} catch (SQLException ee) {
					}
				}
			} catch (SQLException e) {
				// System.out.println("Could not insert theovet: " +
				// e.getMessage());
				error += e.getMessage();
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

		if (!error.equals("")) {
			throw new SQLException(error);
		}
	}

	public static Hashtable getAllLoaiPhuKien(DataManager dataManager)
			throws SQLException {
		Hashtable<String, String> categoriesPhuKien = new Hashtable<String, String>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select id, accessoriesCategory from accessorieslist";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							categoriesPhuKien.put(rs.getString(1),
									rs.getString(2));
						}
					} finally {
						rs.close();
					}
				} finally {
					s.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get categories: " +
				// e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return categoriesPhuKien;
	}

	public static String getTenNhomPK(DataManager dataManager, String IdPhukien)
			throws SQLException {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select accessoriesCategory from accessorieslist where id='"
						+ IdPhukien + "'";
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
		return null;
	}

	public static Phukien getPhuKienByIdPhuKien(DataManager dataManager,
			String IdPk) throws SQLException {
		Phukien phukien = null;
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select * from accessories where product='" + IdPk
						+ "'";

				try {
					ResultSet rs;
					rs = stm.executeQuery(sql);
					try {
						if (rs.next()) {

							phukien = new Phukien();
							phukien.setBaohanh(rs.getString("warranty"));
							phukien.setGiaban(rs.getDouble("price"));
							phukien.setKind(rs.getString("kind"));
							phukien.setNhasanxuat(rs.getString("manufacturer"));
							phukien.setSanpham(rs.getString("product"));
							phukien.setThongtin(rs.getString("information"));
							phukien.setUrl(rs.getString("url"));

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
		return phukien;
	}

	public static ArrayList getPhuKienByID(DataManager dataManager,
			String ma_nhasx) throws SQLException {

		ArrayList<Phukien> dsPhukien = new ArrayList<Phukien>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "SELECT * FROM accessories p, accessorieslist d WHERE p.kind = d.id and d.id ='"
						+ ma_nhasx + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							Phukien phukien = new Phukien();
							phukien.setBaohanh(rs.getString("warranty"));
							phukien.setGiaban(rs.getDouble("price"));
							phukien.setKind(rs.getString("kind"));
							phukien.setNhasanxuat(rs.getString("manufacturer"));
							phukien.setSanpham(rs.getString("product"));
							phukien.setThongtin(rs.getString("information"));
							phukien.setUrl(rs.getString("url"));
							dsPhukien.add(phukien);
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
		return dsPhukien;
	}

	public static ArrayList getPhuKienByIDPaging(DataManager dataManager,
			String ma_nhasx, int from, int to) throws SQLException {

		ArrayList<Phukien> dsPhukien = new ArrayList<Phukien>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "SELECT * FROM accessories p, accessorieslist d WHERE p.kind = d.id and d.id ='"
						+ ma_nhasx + "' limit " + from + "," + to;
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							Phukien phukien = new Phukien();
							phukien.setBaohanh(rs.getString("warranty"));
							phukien.setGiaban(rs.getDouble("price"));
							phukien.setKind(rs.getString("kind"));
							phukien.setNhasanxuat(rs.getString("manufacturer"));
							phukien.setSanpham(rs.getString("product"));
							phukien.setThongtin(rs.getString("information"));
							phukien.setUrl(rs.getString("url"));
							dsPhukien.add(phukien);
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
		return dsPhukien;
	}

	// tim kiem nhanh
	public static ArrayList quickSearch(DataManager dataManager, String tukhoa,
			String dongSp, String tenSp, String loaiSp, String giaMin,
			String giaMax) throws SQLException {

		ArrayList<Laptop> dsLaptop = new ArrayList<Laptop>();
		if (!tenSp.equals("")) {// neu chon san pham cu the thi chi hien ra sp
								// do thoi
			Laptop lap = dataManager.getLaptopByID(tenSp);
			dsLaptop.add(lap);
			return dsLaptop;
		} else if ((!dongSp.equals("") || !tukhoa.equals("Tên sản phẩm"))
				&& giaMax.equals("") && giaMin.equals("")) {// neu chi chon ma
															// nha san xuat thi
															// hien thi tat ca
															// cac sp cua nha sx
															// do
			if (!dongSp.equals("")) {
				dsLaptop = dataManager.getLaptopByNhasanxuat(dongSp);
			} else {
				dsLaptop = dataManager.selectLaptop("", tukhoa, "", "");
			}
			return dsLaptop;
		} else if ((!dongSp.equals("") || !tukhoa.equals("Tên sản phẩm"))
				&& (!giaMax.equals("") || !giaMin.equals(""))) {
			return dsLaptop = dataManager.selectLaptop(dongSp, tukhoa, giaMin,
					giaMax);
		} else if (!giaMax.equals("") || !giaMin.equals("Tên sản phẩm")) {
			return dsLaptop = dataManager.selectLaptop(dongSp, tukhoa, giaMin,
					giaMax);
		}

		return dsLaptop;
	}

	public static ArrayList getLichsuGiaodichD(DataManager dataManager,
			String username, String col, String order) throws SQLException {

		ArrayList<LichsuGiaodich> dsGiaodich = new ArrayList<LichsuGiaodich>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				if (col.equals("date") || col.equals("chuoisp")) {
					String sql = "SELECT * FROM ass_producttracking where userID='"
							+ username + "' order by " + col + "  " + order;
					try {
						ResultSet rs = stm.executeQuery(sql);
						try {
							while (rs.next()) {
								LichsuGiaodich lichsuGd = new LichsuGiaodich();
								lichsuGd.setDate(rs.getDate("date").toString());
								String temp = rs.getString("productString");
								String[] arr = temp.split(",");
								lichsuGd.setProductList(arr);
								dsGiaodich.add(lichsuGd);
							}
						} catch (Exception e) {
							String r = e.getMessage();
							r = r + "err";
						} finally {
							rs.close();
						}

					} finally {
						stm.close();
					}
					// } catch (SQLException e) {
					// System.out.println("Could not get laptop: " +
					// e.getMessage());
				}
			} catch (Exception e) {
				return null;
			} finally {

				dataManager.putConnection(connection);
			}

		}
		return dsGiaodich;
	}

	// gom nhom sp tu ket qua tim kiem
	public static ArrayList PhanNhomSPSearch(DataManager dataManager,
			ArrayList arr) throws SQLException {
		ArrayList<NhomSPSearch> result = new ArrayList<NhomSPSearch>();
		if (arr.size() > 0) {

			ArrayList DellArr = new ArrayList();
			ArrayList HPComqArr = new ArrayList();
			ArrayList IBMArr = new ArrayList();
			ArrayList AcerArr = new ArrayList();
			ArrayList AppleArr = new ArrayList();
			ArrayList AsusArr = new ArrayList();
			ArrayList CMSArr = new ArrayList();
			ArrayList SonyArr = new ArrayList();
			ArrayList ToshibaArr = new ArrayList();
			ArrayList MSIArr = new ArrayList();

			Iterator iterator = arr.iterator();
			while (iterator.hasNext()) {
				Laptop laptop = (Laptop) iterator.next();
				String nhaSx = laptop.getNhasx();
				if (nhaSx.equals("1")) {
					DellArr.add(laptop);
				} else if (nhaSx.equals("2")) {
					HPComqArr.add(laptop);
				} else if (nhaSx.equals("3")) {
					IBMArr.add(laptop);
				} else if (nhaSx.equals("4")) {
					AcerArr.add(laptop);
				} else if (nhaSx.equals("5")) {
					AppleArr.add(laptop);
				} else if (nhaSx.equals("6")) {
					AsusArr.add(laptop);
				} else if (nhaSx.equals("7")) {
					CMSArr.add(laptop);
				} else if (nhaSx.equals("8")) {
					SonyArr.add(laptop);
				} else if (nhaSx.equals("9")) {
					ToshibaArr.add(laptop);
				} else if (nhaSx.equals("10")) {
					MSIArr.add(laptop);
				}

			}

			if (DellArr.size() > 0) {
				NhomSPSearch nhomSp = new NhomSPSearch();
				nhomSp.setNhasx("DELL");
				nhomSp.setArr(DellArr);
				result.add(nhomSp);
			}
			if (HPComqArr.size() > 0) {
				NhomSPSearch nhomSp = new NhomSPSearch();
				nhomSp.setNhasx("HP&COMPAQ");
				nhomSp.setArr(HPComqArr);
				result.add(nhomSp);
			}
			if (IBMArr.size() > 0) {
				NhomSPSearch nhomSp = new NhomSPSearch();
				nhomSp.setNhasx("IBM");
				nhomSp.setArr(IBMArr);
				result.add(nhomSp);
			}
			if (AcerArr.size() > 0) {
				NhomSPSearch nhomSp = new NhomSPSearch();
				nhomSp.setNhasx("ACER");
				nhomSp.setArr(AcerArr);
				result.add(nhomSp);
			}
			if (AppleArr.size() > 0) {
				NhomSPSearch nhomSp = new NhomSPSearch();
				nhomSp.setNhasx("APPLE");
				nhomSp.setArr(AppleArr);
				result.add(nhomSp);
			}
			if (AsusArr.size() > 0) {
				NhomSPSearch nhomSp = new NhomSPSearch();
				nhomSp.setNhasx("ASUS");
				nhomSp.setArr(DellArr);
				result.add(nhomSp);
			}
			if (DellArr.size() > 0) {
				NhomSPSearch nhomSp = new NhomSPSearch();
				nhomSp.setNhasx("DELL");
				nhomSp.setArr(AsusArr);
				result.add(nhomSp);
			}
			if (CMSArr.size() > 0) {
				NhomSPSearch nhomSp = new NhomSPSearch();
				nhomSp.setNhasx("CMS");
				nhomSp.setArr(CMSArr);
				result.add(nhomSp);
			}
			if (ToshibaArr.size() > 0) {
				NhomSPSearch nhomSp = new NhomSPSearch();
				nhomSp.setNhasx("TOSHIBA");
				nhomSp.setArr(ToshibaArr);
				result.add(nhomSp);
			}
			if (MSIArr.size() > 0) {
				NhomSPSearch nhomSp = new NhomSPSearch();
				nhomSp.setNhasx("MSI");
				nhomSp.setArr(MSIArr);
				result.add(nhomSp);
			}

		}
		return result;
	}

	// canh
	public static ArrayList getLaptopClassification(DataManager dataManager,
			String idClass, int from, int to) throws SQLException {

		ArrayList<Laptop> dsLaptop = new ArrayList<Laptop>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select productCategoryCode from laptop where type='"
						+ idClass + "' limit " + from + "," + to;
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							String idlap = rs.getString(1);
							Laptop tempLap = LaptopPeer.getLaptopByID(
									dataManager, idlap);
							dsLaptop.add(tempLap);
						}
					} catch (Exception e) {

					} finally {
						rs.close();
					}

				} finally {
					stm.close();
				}
			} catch (Exception e) {
				return null;
			} finally {

				dataManager.putConnection(connection);
			}

		}
		return dsLaptop;
	}

	public static int getLaptopClassificationTotal(DataManager dataManager,
			String idClass) throws SQLException {

		int result = 0;
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select count(*) from laptop where type='"
						+ idClass + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						if (rs.next()) {
							result = rs.getInt(1);

						}
					} catch (Exception e) {

					} finally {
						rs.close();
					}

				} finally {
					stm.close();
				}
			} catch (Exception e) {
				return 0;
			} finally {

				dataManager.putConnection(connection);
			}

		}
		return result;
	}

	// get laptop belong to one classification
	public static ArrayList FilterByClassification(DataManager dataManager,
			ArrayList arr, String Class) throws SQLException {
		ArrayList<Laptop> result = new ArrayList<Laptop>();
		if (arr.size() > 0) {
			Iterator iterator = arr.iterator();
			while (iterator.hasNext()) {
				Laptop laptop = (Laptop) iterator.next();
				String type = laptop.getType();
				if (type.equals(Class)) {
					result.add(laptop);
				}
			}
		}

		return result;
	}

	// filter by CPU
	public static ArrayList FilterByCPU(DataManager dataManager, ArrayList arr,
			String cpu) throws SQLException {
		ArrayList<Laptop> result = new ArrayList<Laptop>();
		if (arr.size() > 0) {
			int cpuInt = Integer.parseInt(cpu);
			Iterator iterator = arr.iterator();
			while (iterator.hasNext()) {
				Laptop laptop = (Laptop) iterator.next();
				double cpuSpeed = Double.parseDouble(laptop.getCpu_speed());
				switch (cpuInt) {
				case 1:
					if (cpuSpeed < 1.66)
						result.add(laptop);
					break;
				case 2:
					if (cpuSpeed >= 1.66 && cpuSpeed <= 2)
						result.add(laptop);
					break;
				case 3:
					if (cpuSpeed > 2 && cpuSpeed <= 3)
						result.add(laptop);
					break;
				case 4:
					if (cpuSpeed > 3)
						result.add(laptop);
					break;

				}

			}
		}

		return result;
	}

	// filter by RAM
	public static ArrayList FilterByRAM(DataManager dataManager, ArrayList arr,
			String ramin) throws SQLException {
		ArrayList<Laptop> result = new ArrayList<Laptop>();
		if (arr.size() > 0) {
			int ramInt = Integer.parseInt(ramin);
			Iterator iterator = arr.iterator();
			while (iterator.hasNext()) {
				Laptop laptop = (Laptop) iterator.next();
				int ramCap = Integer.parseInt(laptop.getRam_cap());
				switch (ramInt) {
				case 1:
					if (ramCap == 1)
						result.add(laptop);
					break;
				case 2:
					if (ramCap == 2)
						result.add(laptop);
					break;
				case 3:
					if (ramCap == 3)
						result.add(laptop);
					break;
				case 4:
					if (ramCap == 4)
						result.add(laptop);
					break;
				case 5:
					if (ramCap > 4)
						result.add(laptop);
					break;
				}

			}
		}

		return result;
	}

	// lay ma loai san pham dua vao nghe nghiep
	public static String getProductTypeByIdJob(DataManager dataManager,
			String idjob) throws SQLException {

		String result = "";
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select productCategoryCode from jobProductMapping where jobCode='"
						+ idjob + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						if (rs.next()) {
							result = rs.getString(1);

						}
					} catch (Exception e) {

					} finally {
						rs.close();
					}

				} finally {
					stm.close();
				}
			} catch (Exception e) {
				return null;
			} finally {

				dataManager.putConnection(connection);
			}

		}
		return result;
	}

	public static ArrayList FilterByJob(DataManager dataManager, ArrayList arr,
			String job) throws SQLException {
		ArrayList<Laptop> result = new ArrayList<Laptop>();
		if (arr.size() > 0) {
			Iterator iterator = arr.iterator();
			while (iterator.hasNext()) {
				Laptop laptop = (Laptop) iterator.next();
				String loaiSP = getProductTypeByIdJob(dataManager, job);
				if (loaiSP.equals(laptop.getType())) {
					result.add(laptop);
				}
			}
		}

		return result;
	}

	// add thong tin dang nhap cua khach hang
	public static void insertAccountLog(DataManager dataManager, String ma_kh,
			String tenkh) throws SQLException {

		//
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			Statement stmt = null;
			try {
				stmt = connection.createStatement();
				try {
					connection.setAutoCommit(false);
					String sql1 = "delete from account_log where id = " + ma_kh;
					String sql = "insert into account_log(id,name,logdate) values("
							+ ma_kh + ",'" + tenkh + "',(select sysdate()))";
					stmt.execute(sql1);
					stmt.execute(sql);
					connection.commit();

					try {
						stmt.close();
					} finally {
						stmt = null;
					}
				} catch (SQLException e) {
					System.out.println("Khong  insert acc login log: "
							+ e.getMessage());
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

	// canhnd lay san pham duoc xem nhieu theo tung level khach hang
	public static ArrayList<Laptop> getLaptopByLevel(DataManager dataManager,
			String level) throws SQLException {

		String listSP = "";
		String[] arrSP = null;
		ArrayList<Laptop> arrLap = new ArrayList<Laptop>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select productString from ass_productproposallevel where level='"
						+ level + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						if (rs.next()) {
							listSP = rs.getString("productString");
							arrSP = listSP.split(",");
							if (arrSP.length > 0) {
								for (int i = 0; i < arrSP.length; i++) {
									Laptop lap = dataManager
											.getLaptopByID(arrSP[i]);
									arrLap.add(lap);
								}
							}

						}
					} catch (Exception e) {

					} finally {
						rs.close();
					}

				} finally {
					stm.close();
				}
			} catch (Exception e) {
				return null;
			} finally {

				dataManager.putConnection(connection);
			}

		}
		return arrLap;
	}

	// lay gia laptop de nghi theo muc thu nhap
	public static String[] getPriceLapopByIncome(DataManager dataManager,
			String idIncome) throws SQLException {

		String[] result = null;
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select laptop1Price,laptop2Price from salarypricemapping where salaryCode='"
						+ idIncome + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						if (rs.next()) {
							result[0] = rs.getString(1);
							result[1] = rs.getString(2);

						}
					} catch (Exception e) {

					} finally {
						rs.close();
					}

				} finally {
					stm.close();
				}
			} catch (Exception e) {
				return null;
			} finally {

				dataManager.putConnection(connection);
			}

		}
		return result;
	}

	// kiem tra mot san pham la laptop hay phu kien
	public static boolean isLaptop(DataManager dataManager, String product)
			throws SQLException {

		boolean result = false;
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select productCategoryCode from laptop where productCategoryCode='"
						+ product + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						if (rs.next()) {
							result = true;

						}

					} catch (Exception e) {

					} finally {
						rs.close();
					}

				} finally {
					stm.close();
				}
			} catch (Exception e) {
				return false;
			} finally {

				dataManager.putConnection(connection);
			}

		}
		return result;
	}
}
