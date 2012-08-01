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
import digitalshop.beans.Tiente;
import java.util.Hashtable;
import java.util.Iterator;
import digitalshop.beans.Dongsanpham;

/**
 * 
 * @author NTC
 */
public class LaptopPeer {

	public static ArrayList search(DataManager dataManager, String keyword)
			throws SQLException {// search gan dung theo ma dong
		ArrayList<Laptop> laptops = new ArrayList<Laptop>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select * from productCategory d, laptop l "
						+ " where d.productCategoryCode = l.productCategoryCode"
						+ " and  d.productCategoryCode like %" + keyword.trim()
						+ "%";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							Laptop laptop = new Laptop();

							laptop.setCpu_tech(rs.getString("cpu_tech"));
							laptop.setCpu_speed(rs.getString("cpu_speed"));
							laptop.setCpu_kind(rs.getString("cpu_kind"));
							laptop.setCache(rs.getString("cache"));
							laptop.setCpu_info(rs.getString("cpu_info"));
							laptop.setCpu_manu(rs.getString("cpu_manu"));
							laptop.setMain(rs.getString("main"));
							laptop.setMain_manu(rs.getString("main_manu"));
							laptop.setMain_bus(rs.getString("main_bus"));
							laptop.setMain_ram(rs.getString("main_ram"));
							laptop.setRam(rs.getString("ram"));
							laptop.setRam_bus(rs.getString("ram_bus"));
							laptop.setRam_cap(rs.getString("ram_cap"));
							laptop.setRam_tech(rs.getString("ram_tech"));
							laptop.setHdd(rs.getString("hdd"));
							laptop.setHdd_manu(rs.getString("hdd_manu"));
							laptop.setHdd_cap(rs.getString("hdd_cap"));
							laptop.setHdd_speed(rs.getString("hdd_speed"));
							laptop.setDisplay(rs.getString("display"));
							laptop.setDisplay_size(rs.getString("display_size"));
							laptop.setGraphic(rs.getString("graphic"));
							laptop.setGraphic_info(rs.getString("graphic_info"));
							laptop.setSound(rs.getString("sound"));
							laptop.setSound_info(rs.getString("sound_info"));
							laptop.setCompactdisk(rs.getString("compactdisk"));
							laptop.setDisk_info(rs.getString("disk_info"));
							laptop.setTinhnangmorong(rs
									.getString("extendedFeatures"));
							laptop.setNetwork(rs.getString("network"));
							laptop.setNetwork_info(rs.getString("network_info"));
							laptop.setCard_reader(rs.getString("card_reader"));
							laptop.setWebcam(rs.getString("webcam"));
							laptop.setOs(rs.getString("os"));
							laptop.setBattery(rs.getString("battery"));
							laptop.setPhukien(rs.getString("accessories"));
							laptop.setMausac(rs.getString("color"));
							laptop.setTrongluong(rs.getString("weight"));
							laptop.setThongtinmota(rs
									.getString("descriptionInformation"));
							laptop.setMadong(rs
									.getString("productCategoryCode"));
							laptop.setNhasx(rs.getString("productCategoryCode"));
							laptop.setQuocgia(rs.getString("nation"));
							laptop.setSoluong(rs.getInt("quantity"));
							laptop.setImage(rs.getString("image"));
							laptop.setSoluongdat(rs.getInt("sellQuantity"));
							laptop.setMaloai(rs.getString("categoryCode"));
							laptop.setType(rs.getString("type"));
							laptops.add(laptop); // dua vao danh sach
						}
					} finally {
						rs.close();
					}
				} finally {
					stm.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not search for laptop:" +
				// e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return laptops;
	}

	public static ArrayList getLaptopByNhasanxuat(DataManager dataManager,
			String ma_nhasx) throws SQLException {

		ArrayList<Laptop> laptops = new ArrayList<Laptop>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select * from productCategory d, laptop l "
						+ " where d.productCategoryCode = "
						+ "l.productCategoryCode and d.manufacturerCode ="
						+ ma_nhasx;

				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							Laptop laptop = new Laptop();

							laptop.setCpu_tech(rs.getString("cpu_tech"));
							laptop.setCpu_speed(rs.getString("cpu_speed"));
							laptop.setCpu_kind(rs.getString("cpu_kind"));
							laptop.setCache(rs.getString("cache"));
							laptop.setCpu_info(rs.getString("cpu_info"));
							laptop.setCpu_manu(rs.getString("cpu_manu"));
							laptop.setMain(rs.getString("main"));
							laptop.setMain_manu(rs.getString("main_manu"));
							laptop.setMain_bus(rs.getString("main_bus"));
							laptop.setMain_ram(rs.getString("main_ram"));
							laptop.setRam(rs.getString("ram"));
							laptop.setRam_bus(rs.getString("ram_bus"));
							laptop.setRam_cap(rs.getString("ram_cap"));
							laptop.setRam_tech(rs.getString("ram_tech"));
							laptop.setHdd(rs.getString("hdd"));
							laptop.setHdd_manu(rs.getString("hdd_manu"));
							laptop.setHdd_cap(rs.getString("hdd_cap"));
							laptop.setHdd_speed(rs.getString("hdd_speed"));
							laptop.setDisplay(rs.getString("display"));
							laptop.setDisplay_size(rs.getString("display_size"));
							laptop.setGraphic(rs.getString("graphic"));
							laptop.setGraphic_info(rs.getString("graphic_info"));
							laptop.setSound(rs.getString("sound"));
							laptop.setSound_info(rs.getString("sound_info"));
							laptop.setCompactdisk(rs.getString("compactdisk"));
							laptop.setDisk_info(rs.getString("disk_info"));
							laptop.setTinhnangmorong(rs
									.getString("extendedFeatures"));
							laptop.setNetwork(rs.getString("network"));
							laptop.setNetwork_info(rs.getString("network_info"));
							laptop.setCard_reader(rs.getString("card_reader"));
							laptop.setWebcam(rs.getString("webcam"));
							laptop.setOs(rs.getString("os"));
							laptop.setBattery(rs.getString("battery"));
							laptop.setPhukien(rs.getString("accessories"));
							laptop.setMausac(rs.getString("color"));
							laptop.setTrongluong(rs.getString("weight"));
							laptop.setThongtinmota(rs
									.getString("descriptionInformation"));
							laptop.setMadong(rs
									.getString("productCategoryCode"));
							laptop.setNhasx(rs.getString("productCategoryCode"));
							laptop.setQuocgia(rs.getString("nation"));
							laptop.setSoluong(rs.getInt("quantity"));
							laptop.setImage(rs.getString("image"));
							laptop.setSoluongdat(rs.getInt("sellQuantity"));
							laptop.setMaloai(rs.getString("categoryCode"));
							laptop.setType(rs.getString("type"));
							laptops.add(laptop); // dua vao danh sach
						}
					} finally {
						rs.close();
					}
					Iterator iterator = laptops.iterator();
					while (iterator.hasNext()) {
						Laptop laptop = (Laptop) iterator.next();
						sql = "select price from price g, (select max(date) as n from price where productCategoryCode="
								+ "'"
								+ laptop.getMadong()
								+ "'"
								+ ")  t"
								+ " where g.date = t.n and productCategoryCode="
								+ "'" + laptop.getMadong() + "'";
						try {
							rs = stm.executeQuery(sql);

							if (rs.next()) {
								laptop.setGia(rs.getDouble("price"));
							}

						} finally {
							rs.close();
						}
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
		return laptops;
	}

	public static ArrayList getLaptopByNhasanxuatPaging(
			DataManager dataManager, String ma_nhasx, int from, int to)
			throws SQLException {

		ArrayList<Laptop> laptops = new ArrayList<Laptop>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select * from productCategory d, laptop l "
						+ " where d.productCategoryCode = "
						+ "l.productCategoryCode and d.manufacturerCode = "
						+ ma_nhasx + " limit " + from + "," + to;
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							Laptop laptop = new Laptop();

							laptop.setCpu_tech(rs.getString("cpu_tech"));
							laptop.setCpu_speed(rs.getString("cpu_speed"));
							laptop.setCpu_kind(rs.getString("cpu_kind"));
							laptop.setCache(rs.getString("cache"));
							laptop.setCpu_info(rs.getString("cpu_info"));
							laptop.setCpu_manu(rs.getString("cpu_manu"));
							laptop.setMain(rs.getString("main"));
							laptop.setMain_manu(rs.getString("main_manu"));
							laptop.setMain_bus(rs.getString("main_bus"));
							laptop.setMain_ram(rs.getString("main_ram"));
							laptop.setRam(rs.getString("ram"));
							laptop.setRam_bus(rs.getString("ram_bus"));
							laptop.setRam_cap(rs.getString("ram_cap"));
							laptop.setRam_tech(rs.getString("ram_tech"));
							laptop.setHdd(rs.getString("hdd"));
							laptop.setHdd_manu(rs.getString("hdd_manu"));
							laptop.setHdd_cap(rs.getString("hdd_cap"));
							laptop.setHdd_speed(rs.getString("hdd_speed"));
							laptop.setDisplay(rs.getString("display"));
							laptop.setDisplay_size(rs.getString("display_size"));
							laptop.setGraphic(rs.getString("graphic"));
							laptop.setGraphic_info(rs.getString("graphic_info"));
							laptop.setSound(rs.getString("sound"));
							laptop.setSound_info(rs.getString("sound_info"));
							laptop.setCompactdisk(rs.getString("compactdisk"));
							laptop.setDisk_info(rs.getString("disk_info"));
							laptop.setTinhnangmorong(rs
									.getString("extendedFeatures"));
							laptop.setNetwork(rs.getString("network"));
							laptop.setNetwork_info(rs.getString("network_info"));
							laptop.setCard_reader(rs.getString("card_reader"));
							laptop.setWebcam(rs.getString("webcam"));
							laptop.setOs(rs.getString("os"));
							laptop.setBattery(rs.getString("battery"));
							laptop.setPhukien(rs.getString("accessories"));
							laptop.setMausac(rs.getString("color"));
							laptop.setTrongluong(rs.getString("weight"));
							laptop.setThongtinmota(rs
									.getString("descriptionInformation"));
							laptop.setMadong(rs
									.getString("productCategoryCode"));
							laptop.setNhasx(rs.getString("productCategoryCode"));
							laptop.setQuocgia(rs.getString("nation"));
							laptop.setSoluong(rs.getInt("quantity"));
							laptop.setImage(rs.getString("image"));
							laptop.setSoluongdat(rs.getInt("sellQuantity"));
							laptop.setMaloai(rs.getString("categoryCode"));
							laptop.setType(rs.getString("type"));
							laptops.add(laptop); // dua vao danh sach
						}
					} finally {
						rs.close();
					}
					Iterator iterator = laptops.iterator();
					while (iterator.hasNext()) {
						Laptop laptop = (Laptop) iterator.next();
						sql = "select price from price g, (select max(date) as n from price where productCategoryCode="
								+ "'"
								+ laptop.getMadong()
								+ "'"
								+ ")  t"
								+ " where g.date = t.n and productCategoryCode="
								+ "'" + laptop.getMadong() + "'";
						try {
							rs = stm.executeQuery(sql);

							if (rs.next()) {
								laptop.setGia(rs.getDouble("price"));
							}

						} finally {
							rs.close();
						}
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
		return laptops;
	}

	public static Laptop getLaptopByID(DataManager dataManager,
			String ma_dongsanpham) throws SQLException {
		Laptop laptop = null;
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select * from productCategory d,laptop l where d.productCategoryCode = l.productCategoryCode and l.productCategoryCode ='"
						+ ma_dongsanpham + "'";

				try {
					ResultSet rs;
					rs = stm.executeQuery(sql);
					try {
						if (rs.next()) {

							laptop = new Laptop();

							laptop.setCpu_tech(rs.getString("cpu_tech"));
							laptop.setCpu_speed(rs.getString("cpu_speed"));
							laptop.setCpu_kind(rs.getString("cpu_kind"));
							laptop.setCache(rs.getString("cache"));
							laptop.setCpu_info(rs.getString("cpu_info"));
							laptop.setCpu_manu(rs.getString("cpu_manu"));
							laptop.setMain(rs.getString("main"));
							laptop.setMain_manu(rs.getString("main_manu"));
							laptop.setMain_bus(rs.getString("main_bus"));
							laptop.setMain_ram(rs.getString("main_ram"));
							laptop.setRam(rs.getString("ram"));
							laptop.setRam_bus(rs.getString("ram_bus"));
							laptop.setRam_cap(rs.getString("ram_cap"));
							laptop.setRam_tech(rs.getString("ram_tech"));
							laptop.setHdd(rs.getString("hdd"));
							laptop.setHdd_manu(rs.getString("hdd_manu"));
							laptop.setHdd_cap(rs.getString("hdd_cap"));
							laptop.setHdd_speed(rs.getString("hdd_speed"));
							laptop.setDisplay(rs.getString("display"));
							laptop.setDisplay_size(rs.getString("display_size"));
							laptop.setGraphic(rs.getString("graphic"));
							laptop.setGraphic_info(rs.getString("graphic_info"));
							laptop.setSound(rs.getString("sound"));
							laptop.setSound_info(rs.getString("sound_info"));
							laptop.setCompactdisk(rs.getString("compactdisk"));
							laptop.setDisk_info(rs.getString("disk_info"));
							laptop.setTinhnangmorong(rs
									.getString("extendedFeatures"));
							laptop.setNetwork(rs.getString("network"));
							laptop.setNetwork_info(rs.getString("network_info"));
							laptop.setCard_reader(rs.getString("card_reader"));
							laptop.setWebcam(rs.getString("webcam"));
							laptop.setOs(rs.getString("os"));
							laptop.setBattery(rs.getString("battery"));
							laptop.setPhukien(rs.getString("accessories"));
							laptop.setMausac(rs.getString("color"));
							laptop.setTrongluong(rs.getString("weight"));
							laptop.setThongtinmota(rs
									.getString("descriptionInformation"));
							laptop.setMadong(rs
									.getString("productCategoryCode"));
							laptop.setNhasx(rs.getString("productCategoryCode"));
							laptop.setQuocgia(rs.getString("nation"));
							laptop.setSoluong(rs.getInt("quantity"));
							laptop.setImage(rs.getString("image"));
							laptop.setSoluongdat(rs.getInt("sellQuantity"));
							laptop.setMaloai(rs.getString("categoryCode"));
							laptop.setType(rs.getString("type"));

						}

						sql = "select price from price g, (select max(date) as n from price where productCategoryCode="
								+ "'"
								+ laptop.getMadong()
								+ "'"
								+ ")  t"
								+ " where g.date = t.n and productCategoryCode="
								+ "'" + laptop.getMadong() + "'";

						rs = stm.executeQuery(sql);

						if (rs.next()) {
							laptop.setGia(rs.getDouble("price"));
						}

					} catch (Exception ex) {
						System.out.println(ex.getMessage());
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
		return laptop;
	}

	public static double getGialaptop(DataManager dataManager,
			String ma_dongsanpham) throws SQLException {
		Connection connection = dataManager.getConnection();
		double giaban = 0;
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select price from price g, (select max(date) as n from price where productCategoryCode="
						+ "'"
						+ ma_dongsanpham
						+ "'"
						+ ")  t"
						+ " where g.date = t.n and productCategoryCode="
						+ "'"
						+ ma_dongsanpham + "'";

				try {
					ResultSet rs;
					rs = stm.executeQuery(sql);

					if (rs.next()) {

						giaban = rs.getDouble("price");

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
		return giaban;
	}

	public static double getGiaculaptop(DataManager dataMangager,
			String ma_dongsanpham) {
		return 0;
	}

	public static void insert(DataManager dataManager, Laptop laptop) {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			Statement stmt = null;
			try {
				stmt = connection.createStatement();
				try {
					connection.setAutoCommit(false);

					String sql3 = "INSERT INTO laptop(productCategoryCode,cpu_tech,cpu_speed,cpu_kind,cache,cpu_manu,main,main_manu,main_bus,main_ram,ram,ram_bus,ram_cap,ram_tech,hdd,hdd_cap,hdd_speed,display,display_size,graphic,sound,compactdisk,extendedFeatures,network,card_reader,webcam,battery,os,accessories,color,weight,hdd_manu) VALUES('"
							+ laptop.getMadong()
							+ "','"
							+ laptop.getCpu_tech()
							+ "','"
							+ laptop.getCpu_speed()
							+ "','"
							+ laptop.getCpu_kind()
							+ "','"
							+ laptop.getCache()
							+ "','"
							+ laptop.getCpu_manu()
							+ "','"
							+ laptop.getMain()
							+ "','"
							+ laptop.getMain_manu()
							+ "','"
							+ laptop.getMain_bus()
							+ "','"
							+ laptop.getMain_ram()
							+ "','"
							+ laptop.getRam()
							+ "','"
							+ laptop.getRam_bus()
							+ "','"
							+ laptop.getRam_cap()
							+ "','"
							+ laptop.getRam_tech()
							+ "','"
							+ laptop.getHdd()
							+ "','"
							+ laptop.getHdd_cap()
							+ "','"
							+ laptop.getHdd_speed()
							+ "','"
							+ laptop.getDisplay()
							+ "','"
							+ laptop.getDisplay_size()
							+ "','"
							+ laptop.getGraphic()
							+ "','"
							+ laptop.getSound()
							+ "','"
							+ laptop.getCompactdisk()
							+ "','"
							+ laptop.getTinhnangmorong()
							+ "','"
							+ laptop.getNetwork()
							+ "','"
							+ laptop.getCard_reader()
							+ "','"
							+ laptop.getWebcam()
							+ "','"
							+ laptop.getBattery()
							+ "','"
							+ laptop.getOs()
							+ "','"
							+ laptop.getPhukien()
							+ "','"
							+ laptop.getMausac()
							+ "','"
							+ laptop.getTrongluong()
							+ "','"
							+ laptop.getHdd_manu() + "')";
					stmt.execute(sql3);
					connection.commit();

					try {
						stmt.close();
					} finally {
						stmt = null;
					}
				} catch (SQLException e) {
					System.out.println("Could not insert laptop: "
							+ e.getMessage());
					try {
						connection.rollback();
					} catch (SQLException ee) {
					}
				}
			} catch (SQLException e) {
				System.out
						.println("Could not insert laptop: " + e.getMessage());
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

	public static int getBaohanh(DataManager dataManager, String ma_dongsanpham)
			throws SQLException {
		Connection connection = dataManager.getConnection();
		int baohanh = 0;
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select warranty from productCategory where productCategoryCode="
						+ "'" + ma_dongsanpham + "'";

				try {
					ResultSet rs;
					rs = stm.executeQuery(sql);

					if (rs.next()) {

						baohanh = rs.getInt("warranty");

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
		return baohanh;
	}

	public static void update(DataManager dataManager, Laptop laptop)
			throws SQLException {
	}

	public static void remove(DataManager dataManager, String ma_dongsanpham)
			throws SQLException {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			Statement stmt = null;
			try {
				connection.setAutoCommit(false);
				stmt = connection.createStatement();
				try {
					String sql = "delete from productCategory where productCategoryCode = "
							+ "'" + ma_dongsanpham + "'";
					stmt.executeUpdate(sql);

					connection.commit();
				} catch (SQLException e) {
					System.out.println("Could not remove laptop: "
							+ e.getMessage());

					connection.rollback();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not remove laptop: " +
				// e.getMessage());
			} finally {
				if (stmt != null) {
					stmt.close();
				}
				dataManager.putConnection(connection);
			}
		}
	}

	public static Hashtable getAllLaptopByNhasanxuat(DataManager dataManager)
			throws SQLException {
		Hashtable<String, String> categoriesLaptop = new Hashtable<String, String>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select manufacturerCode,manufacturerName from manufacturer";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							categoriesLaptop.put(rs.getString(1),
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
		return categoriesLaptop;
	}

	public static ArrayList getSpecialProducts(DataManager dataManager,
			int start, int record) throws SQLException {
		ArrayList<Laptop> laptops = new ArrayList<Laptop>();
		Connection connection = dataManager.getConnection();
		// int record = 3;
		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select productCategoryCode from specialproducts LIMIT "
						+ start + "," + record;
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							laptops.add(getLaptopByID(dataManager,
									rs.getString(1)));
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
		return laptops;
	}

	public static int getTotalRowSpecialPro(DataManager dataManager)
			throws SQLException {
		int total = 0;
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select count(*) from specialproducts;";
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							total = rs.getInt(1);
						}
					} finally {
						rs.close();
					}
				} finally {
					s.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get laptop: " +
				// e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return total;
	}

	// lay tat ca cac laptop dung cho search
	public static ArrayList selectLaptop(DataManager dataManager,
			String ma_nhaSx, String name, String giaMin, String giaMax)
			throws SQLException {

		Tiente tiente = dataManager.getTiente("USD");
		String tygia = tiente.getGiatri();
		ArrayList<Laptop> laptops = new ArrayList<Laptop>();
		Connection connection = dataManager.getConnection();
		// int record = 3;
		if (connection != null) {
			try {
				String sql = "";
				Statement stm = connection.createStatement();
				if (!ma_nhaSx.equals("") && !giaMax.equals("")
						&& !giaMin.equals("")) {
					sql = "select * from productCategory d, laptop l, price g "
							+ " where d.productCategoryCode = "
							+ "l.productCategoryCode and d.productCategoryCode=g.productCategoryCode and (g.price * "
							+ tygia + " between " + giaMin + " and " + giaMax
							+ " ) and  d.productCategoryCode = " + ma_nhaSx;
				} else if (!ma_nhaSx.equals("") && giaMax.equals("")
						&& !giaMin.equals("")) {
					sql = "select * from productCategory d, laptop l, price g "
							+ " where d.productCategoryCode = "
							+ "l.productCategoryCode and d.productCategoryCode=g.productCategoryCode and (g.price * "
							+ tygia + " >= " + giaMin
							+ ") and  d.productCategoryCode = " + ma_nhaSx;
				} else if (!ma_nhaSx.equals("") && !giaMax.equals("")
						&& giaMin.equals("")) {
					sql = "select * from productCategory d, laptop l, price g "
							+ " where d.productCategoryCode = "
							+ "l.productCategoryCode and d.productCategoryCode=g.productCategoryCode and (g.price * "
							+ tygia + " <= " + giaMax
							+ ") and  d.productCategoryCode = " + ma_nhaSx;
				} else if (ma_nhaSx.equals("") && !giaMax.equals("")
						&& !giaMin.equals("") && !name.equals("Tên sản phẩm")) {
					sql = "select * from productCategory d, laptop l, price g "
							+ " where d.productCategoryCode = "
							+ "l.productCategoryCode and d.productCategoryCode=g.productCategoryCode and (g.price * "
							+ tygia + " between " + giaMin + " and " + giaMax
							+ " ) and  d.productCategoryCode LIKE '%" + name
							+ "%'";
				} else if (ma_nhaSx.equals("") && giaMax.equals("")
						&& !giaMin.equals("") && !name.equals("Tên sản phẩm")) {
					sql = "select * from productCategory d, laptop l, price g "
							+ " where d.productCategoryCode = "
							+ "l.productCategoryCode and d.productCategoryCode=g.productCategoryCode and (g.price * "
							+ tygia + " >= " + giaMin
							+ " ) and  d.productCategoryCode LIKE '%" + name
							+ "%'";
				} else if (ma_nhaSx.equals("") && !giaMax.equals("")
						&& giaMin.equals("") && !name.equals("Tên sản phẩm")) {
					sql = "select * from productCategory d, laptop l, price g "
							+ " where d.productCategoryCode = "
							+ "l.productCategoryCode and d.productCategoryCode=g.productCategoryCode and g.price * "
							+ tygia + " <= " + giaMax
							+ " and  d.productCategoryCode LIKE '%" + name
							+ "%'";
				} else if (ma_nhaSx.equals("") && giaMax.equals("")
						&& giaMin.equals("") && !name.equals("Tên sản phẩm")) {
					sql = "select * from  productCategory d, laptop l, price g "
							+ " where d.productCategoryCode = "
							+ "l.productCategoryCode and d.productCategoryCode=g.productCategoryCode and  d.productCategoryCode LIKE '%"
							+ name + "%'";
				} else if (ma_nhaSx.equals("") && !giaMax.equals("")
						&& !giaMin.equals("") && name.equals("Tên sản phẩm")) {
					sql = "select * from productCategory d, laptop l, price g "
							+ " where d.productCategoryCode = "
							+ "l.productCategoryCode and d.productCategoryCode=g.productCategoryCode and (g.price * "
							+ tygia + " between " + giaMin + " and " + giaMax
							+ " )";
				}
				try {
					ResultSet rs;
					sql += "  order by g.price";
					rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {

							Laptop laptop = new Laptop();

							laptop.setCpu_tech(rs.getString("cpu_tech"));
							laptop.setCpu_speed(rs.getString("cpu_speed"));
							laptop.setCpu_kind(rs.getString("cpu_kind"));
							laptop.setCache(rs.getString("cache"));
							laptop.setCpu_info(rs.getString("cpu_info"));
							laptop.setCpu_manu(rs.getString("cpu_manu"));
							laptop.setMain(rs.getString("main"));
							laptop.setMain_manu(rs.getString("main_manu"));
							laptop.setMain_bus(rs.getString("main_bus"));
							laptop.setMain_ram(rs.getString("main_ram"));
							laptop.setRam(rs.getString("ram"));
							laptop.setRam_bus(rs.getString("ram_bus"));
							laptop.setRam_cap(rs.getString("ram_cap"));
							laptop.setRam_tech(rs.getString("ram_tech"));
							laptop.setHdd(rs.getString("hdd"));
							laptop.setHdd_manu(rs.getString("hdd_manu"));
							laptop.setHdd_cap(rs.getString("hdd_cap"));
							laptop.setHdd_speed(rs.getString("hdd_speed"));
							laptop.setDisplay(rs.getString("display"));
							laptop.setDisplay_size(rs.getString("display_size"));
							laptop.setGraphic(rs.getString("graphic"));
							laptop.setGraphic_info(rs.getString("graphic_info"));
							laptop.setSound(rs.getString("sound"));
							laptop.setSound_info(rs.getString("sound_info"));
							laptop.setCompactdisk(rs.getString("compactdisk"));
							laptop.setDisk_info(rs.getString("disk_info"));
							laptop.setTinhnangmorong(rs
									.getString("extendedFeatures"));
							laptop.setNetwork(rs.getString("network"));
							laptop.setNetwork_info(rs.getString("network_info"));
							laptop.setCard_reader(rs.getString("card_reader"));
							laptop.setWebcam(rs.getString("webcam"));
							laptop.setOs(rs.getString("os"));
							laptop.setBattery(rs.getString("battery"));
							laptop.setPhukien(rs.getString("accessories"));
							laptop.setMausac(rs.getString("color"));
							laptop.setTrongluong(rs.getString("weight"));
							laptop.setThongtinmota(rs
									.getString("descriptionInformation"));
							laptop.setMadong(rs
									.getString("productCategoryCode"));
							laptop.setNhasx(rs.getString("productCategoryCode"));
							laptop.setQuocgia(rs.getString("nation"));
							laptop.setSoluong(rs.getInt("quantity"));
							laptop.setImage(rs.getString("image"));
							laptop.setSoluongdat(rs.getInt("sellQuantity"));
							laptop.setMaloai(rs.getString("categoryCode"));
							laptop.setGia(rs.getDouble("price"));
							laptop.setType(rs.getString("type"));
							laptops.add(laptop);
						}

					} finally {
						rs.close();
					}

				} finally {
					stm.close();
				}

				// } catch (SQLException e) {
				// System.out.println("Could not get books: " + e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return laptops;
	}

	// get laptop co gia tuong duong +-50$
	public static ArrayList getLaptopPriceSimilar(DataManager dataManager,
			String gia) throws SQLException {

		ArrayList<Laptop> laptops = new ArrayList<Laptop>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String delta = "50";
				String sql = "select * from price join laptop on price.productCategoryCode=laptop.productCategoryCode join productCategory on laptop.productCategoryCode=productCategory.productCategoryCode where price.price > "
						+ gia
						+ " - "
						+ delta
						+ " and price.price < "
						+ gia
						+ " + " + delta;
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							Laptop laptop = new Laptop();

							laptop.setCpu_tech(rs.getString("cpu_tech"));
							laptop.setCpu_speed(rs.getString("cpu_speed"));
							laptop.setCpu_kind(rs.getString("cpu_kind"));
							laptop.setCache(rs.getString("cache"));
							laptop.setCpu_info(rs.getString("cpu_info"));
							laptop.setCpu_manu(rs.getString("cpu_manu"));
							laptop.setMain(rs.getString("main"));
							laptop.setMain_manu(rs.getString("main_manu"));
							laptop.setMain_bus(rs.getString("main_bus"));
							laptop.setMain_ram(rs.getString("main_ram"));
							laptop.setRam(rs.getString("ram"));
							laptop.setRam_bus(rs.getString("ram_bus"));
							laptop.setRam_cap(rs.getString("ram_cap"));
							laptop.setRam_tech(rs.getString("ram_tech"));
							laptop.setHdd(rs.getString("hdd"));
							laptop.setHdd_manu(rs.getString("hdd_manu"));
							laptop.setHdd_cap(rs.getString("hdd_cap"));
							laptop.setHdd_speed(rs.getString("hdd_speed"));
							laptop.setDisplay(rs.getString("display"));
							laptop.setDisplay_size(rs.getString("display_size"));
							laptop.setGraphic(rs.getString("graphic"));
							laptop.setGraphic_info(rs.getString("graphic_info"));
							laptop.setSound(rs.getString("sound"));
							laptop.setSound_info(rs.getString("sound_info"));
							laptop.setCompactdisk(rs.getString("compactdisk"));
							laptop.setDisk_info(rs.getString("disk_info"));
							laptop.setTinhnangmorong(rs
									.getString("extendedFeatures"));
							laptop.setNetwork(rs.getString("network"));
							laptop.setNetwork_info(rs.getString("network_info"));
							laptop.setCard_reader(rs.getString("card_reader"));
							laptop.setWebcam(rs.getString("webcam"));
							laptop.setOs(rs.getString("os"));
							laptop.setBattery(rs.getString("battery"));
							laptop.setPhukien(rs.getString("accessories"));
							laptop.setMausac(rs.getString("color"));
							laptop.setTrongluong(rs.getString("weight"));
							laptop.setThongtinmota(rs
									.getString("descriptionInformation"));
							laptop.setMadong(rs
									.getString("productCategoryCode"));
							laptop.setNhasx(rs.getString("productCategoryCode"));
							// laptop.setQuocgia(rs.getString("quocgia"));
							// laptop.setSoluong(rs.getInt("soluong"));
							laptop.setImage(rs.getString("image"));
							// laptop.setSoluongdat(rs.getInt("soluongdat"));
							// laptop.setMaloai(rs.getString("maloai"))
							laptop.setGia(rs.getDouble("price"));
							laptop.setType(rs.getString("type"));

							laptops.add(laptop); // dua vao danh sach
						}
					} catch (Exception e) { // finally {
						// rs.close();
						String r = e.getMessage();
						r = r + "dsd";
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
		return laptops;
	}

	// -------Products_TinDT------------
	public static ArrayList getListOfLaptops(DataManager dataManager, int page)
			throws SQLException {
		ArrayList laptops = new ArrayList();
		Connection connection = dataManager.getConnection();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				int limit = 10 * (page - 1);
				String sql = "select a.productCategoryCode as productCategoryCode, b.manufacturerName as manufacturerName, a.quantity-a.sellQuantity as inventoryQuantity, a.status as status from productCategory a, manufacturer b, productType c where a.manufacturerCode = b.manufacturerCode and a.categoryCode = c.categoryCode and c.productTypeName = 'Laptop' order by a.productCategoryCode";
				sql += " limit " + limit + "," + 10;
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							Dongsanpham laptop = new Dongsanpham();

							laptop.setMa_dongsanpham(rs
									.getString("productCategoryCode"));
							laptop.setNhasx(rs.getString("manufacturerName"));
							laptop.setSoluongton(rs
									.getString("inventoryQuantity"));
							laptop.setTrangthai(rs.getString("status"));

							laptops.add(laptop);
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
		return laptops;
	}

	public static int getCountLaptops(DataManager dataManager)
			throws SQLException {
		Connection connection = dataManager.getConnection();
		int count = 0;

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select count(*) as count from productCategory";
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

	public static ArrayList getProductsLaptopFilter(DataManager dataManager,
			String filter_name, String filter_manu, String filter_quantity,
			String filter_status, int page) throws SQLException {
		ArrayList laptops = new ArrayList();
		Connection connection = dataManager.getConnection();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				int limit = 10 * (page - 1);
				String sql = "select a.productCategoryCode as productCategoryCode, b.manufacturerName as manufacturerName, a.quantity-a.sellQuantity as inventoryQuantity, a.status as status from productCategory a, manufacturer b, productType c where a.manufacturerCode = b.manufacturerCode and a.categoryCode = c.categoryCode and c.productTypeName = 'Laptop'";
				if (!filter_name.equalsIgnoreCase("") && filter_name != null) {
					sql = sql + " and a.productCategoryCode like '%"
							+ filter_name + "%'";
				}
				if (!filter_manu.equalsIgnoreCase("") && filter_manu != null) {
					sql += " and b.manufacturerName like '%" + filter_manu
							+ "%'";
				}
				if (!filter_quantity.equalsIgnoreCase("")
						&& filter_quantity != null) {
					sql += " and (a.quantity-a.sellQuantity) = '"
							+ filter_quantity + "'";
				}
				if (!filter_status.equalsIgnoreCase("")
						&& filter_status != null) {
					sql += " and a.status = '" + filter_status + "'";
				}
				sql += " order by a.productCategoryCode";
				sql += " limit " + limit + "," + 10;
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							Dongsanpham laptop = new Dongsanpham();

							laptop.setMa_dongsanpham(rs
									.getString("productCategoryCode"));
							laptop.setNhasx(rs.getString("manufacturerName"));
							laptop.setSoluongton(rs
									.getString("inventoryQuantity"));
							laptop.setTrangthai(rs.getString("status"));

							laptops.add(laptop);
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
		return laptops;
	}

	public static int getCountProductsLaptopFilter(DataManager dataManager,
			String filter_name, String filter_manu, String filter_quantity,
			String filter_status) throws SQLException {
		Connection connection = dataManager.getConnection();
		int count = 0;

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select count(*) as count from productCategory";
				if (!filter_name.equalsIgnoreCase("") && filter_name != null) {
					sql = sql + " and a.productCategoryCode like '%"
							+ filter_name + "%'";
				}
				if (!filter_manu.equalsIgnoreCase("") && filter_manu != null) {
					sql += " and b.manufacturerName like '%" + filter_manu
							+ "%'";
				}
				if (!filter_quantity.equalsIgnoreCase("")
						&& filter_quantity != null) {
					sql += " and (a.quantity-a.sellQuantity) = '"
							+ filter_quantity + "'";
				}
				if (!filter_status.equalsIgnoreCase("")
						&& filter_status != null) {
					sql += " and a.status = '" + filter_status + "'";
				}
				sql += " order by a.productCategoryCode";
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

	public static Dongsanpham getDongspByMadong(DataManager dataManager,
			String ma_dongsanpham) throws SQLException {
		Dongsanpham dongsp = new Dongsanpham();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select *, a.sellQuantity as sellQuantity, a.quantity-a.sellQuantity as inventoryQuantity from productCategory a, laptop b, manufacturer c where a.manufacturerCode=b.manufacturerCode and a.productCategoryCode=c.productCategoryCode and a.productCategoryCode = "
						+ "'" + ma_dongsanpham + "'";

				try {
					ResultSet rs;
					rs = stm.executeQuery(sql);
					try {
						if (rs.next()) {
							dongsp.setMa_dongsanpham(rs
									.getString("productCategoryCode"));
							dongsp.setQuocgia(rs.getString("nation"));
							dongsp.setNhasx(rs.getString("manufacturerName"));
							dongsp.setImage(rs.getString("image"));
							dongsp.setGianhap(rs.getString("importPrice"));
							dongsp.setTax(rs.getString("tax"));
							dongsp.setGiaban(rs.getString("price"));
							dongsp.setMausac(rs.getString("color"));
							dongsp.setTrongluong(rs.getString("weight"));
							dongsp.setSoluongban(rs.getString("sellQuantity"));
							dongsp.setSoluongton(rs
									.getString("inventoryQuantity"));
							dongsp.setBaohanh(rs.getString("warranty"));
							dongsp.setTrangthai(rs.getString("status"));
							dongsp.setMota(rs.getString("description"));
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
		return dongsp;
	}

	public static Laptop getLaptopByMadong(DataManager dataManager,
			String ma_dongsanpham) throws SQLException {
		Laptop laptop = new Laptop();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "select * from laptop where productCategoryCode = "
						+ "'" + ma_dongsanpham + "'";
				try {
					ResultSet rs;
					rs = stm.executeQuery(sql);
					try {
						if (rs.next()) {
							laptop.setCpu_tech(rs.getString("cpu_tech"));
							laptop.setCpu_manu(rs.getString("cpu_manu"));
							laptop.setCpu_speed(rs.getString("cpu_speed"));
							laptop.setCpu_kind(rs.getString("cpu_kind"));
							laptop.setCache(rs.getString("cache"));
							laptop.setMain(rs.getString("main"));
							laptop.setMain_manu(rs.getString("main_manu"));
							laptop.setMain_bus(rs.getString("main_bus"));
							laptop.setMain_ram(rs.getString("main_ram"));
							laptop.setRam(rs.getString("ram"));
							laptop.setRam_bus(rs.getString("ram_bus"));
							laptop.setRam_cap(rs.getString("ram_cap"));
							laptop.setRam_tech(rs.getString("ram_tech"));
							laptop.setHdd(rs.getString("hdd"));
							laptop.setHdd_manu(rs.getString("hdd_manu"));
							laptop.setHdd_cap(rs.getString("hdd_cap"));
							laptop.setHdd_speed(rs.getString("hdd_speed"));
							laptop.setGraphic(rs.getString("graphic"));
							laptop.setSound(rs.getString("sound"));
							laptop.setNetwork(rs.getString("network"));
							laptop.setCompactdisk(rs.getString("compactdisk"));
							laptop.setDisplay(rs.getString("display"));
							laptop.setDisplay_size(rs.getString("display_size"));
							laptop.setBattery(rs.getString("battery"));
							laptop.setOs(rs.getString("os"));
							laptop.setTinhnangmorong(rs
									.getString("extendedFeatures"));
							laptop.setWebcam(rs.getString("webcam"));
							laptop.setCard_reader(rs.getString("card_reader"));
							laptop.setPhukien(rs.getString("accessories"));
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
		return laptop;
	}

	public static void updateDongsanpham(DataManager dataManager,
			String ma_dongsanpham, Dongsanpham dongsp) throws SQLException {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			Statement stmt = null;
			try {
				stmt = connection.createStatement();
				try {
					connection.setAutoCommit(false);
					if (dongsp.getImage() == null
							|| dongsp.getImage().equalsIgnoreCase("")) {
						Dongsanpham sp = new Dongsanpham();
						sp = getDongspByMadong(dataManager, ma_dongsanpham);
						dongsp.setImage(sp.getImage());
					}
					String sql = "update productCategory set productCategoryCode='"
							+ dongsp.getMa_dongsanpham()
							+ "', productCategoryCode='"
							+ dongsp.getNhasx()
							+ "', nation='"
							+ dongsp.getQuocgia()
							+ "', quantity='"
							+ dongsp.getSoluong()
							+ "', image='"
							+ dongsp.getImage()
							+ "', sellQuantity='"
							+ dongsp.getSoluongban()
							+ "', description='"
							+ dongsp.getMota()
							+ "', warranty='"
							+ dongsp.getBaohanh()
							+ "', importPrice='"
							+ dongsp.getGianhap()
							+ "', tax='"
							+ dongsp.getTax()
							+ "', price='"
							+ dongsp.getGiaban()
							+ "', status='"
							+ dongsp.getTrangthai()
							+ "', date_modified=sysdate() where productCategoryCode='"
							+ ma_dongsanpham + "'";
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

	public static void updateLaptopById(DataManager dataManager,
			String ma_dongsanpham, Laptop laptop) throws SQLException {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			Statement stmt = null;
			try {
				stmt = connection.createStatement();
				try {
					connection.setAutoCommit(false);
					String sql = "update laptop set cpu_tech='"
							+ laptop.getCpu_tech() + "',cpu_manu='"
							+ laptop.getCpu_manu() + "',cpu_kind='"
							+ laptop.getCpu_kind() + "',cpu_speed='"
							+ laptop.getCpu_speed() + "',cache='"
							+ laptop.getCache() + "',main='" + laptop.getMain()
							+ "',main_manu='" + laptop.getMain_manu()
							+ "',main_bus='" + laptop.getMain_bus()
							+ "',main_ram='" + laptop.getMain_ram() + "',ram='"
							+ laptop.getRam() + "',ram_cap='"
							+ laptop.getRam_cap() + "',ram_bus='"
							+ laptop.getRam_bus() + "',ram_tech='"
							+ laptop.getRam_tech() + "',hdd='"
							+ laptop.getHdd() + "',hdd_manu='"
							+ laptop.getHdd_manu() + "',hdd_cap='"
							+ laptop.getHdd_cap() + "',hdd_speed='"
							+ laptop.getHdd_speed() + "',graphic='"
							+ laptop.getGraphic() + "',sound='"
							+ laptop.getSound() + "',network='"
							+ laptop.getNetwork() + "',compactdisk='"
							+ laptop.getCompactdisk() + "',display='"
							+ laptop.getDisplay() + "',display_size='"
							+ laptop.getDisplay_size() + "',battery='"
							+ laptop.getBattery() + "',os='" + laptop.getOs()
							+ "',extendedFeatures='"
							+ laptop.getTinhnangmorong() + "',webcam='"
							+ laptop.getWebcam() + "',card_reader='"
							+ laptop.getCard_reader() + "',accessories='"
							+ laptop.getPhukien()
							+ "' where productCategoryCode='" + ma_dongsanpham
							+ "'";
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

	public static void deleteDongsanpham(DataManager dataManager, String madong)
			throws SQLException {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "DELETE FROM productCategory WHERE productCategoryCode='"
						+ madong + "'";
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

	public static ArrayList getListOfDMLaptop(DataManager dataManager,
			String filter_laptopName, String filter_laptopClass, int page)
			throws SQLException {
		ArrayList laptops = new ArrayList();
		Connection connection = dataManager.getConnection();

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				int limit = 10 * (page - 1);
				String sql = "select productCategoryCode, class_result from class_laptop where 1=1";
				if (!filter_laptopName.equalsIgnoreCase("")
						&& filter_laptopName != null) {
					sql = sql + " and productCategoryCode like '%"
							+ filter_laptopName + "%'";
				}
				if (!filter_laptopClass.equalsIgnoreCase("")
						&& filter_laptopClass != null) {
					sql = sql + " and class_result = '" + filter_laptopClass
							+ "'";
				}
				sql += " order by productCategoryCode";
				sql += " limit " + limit + "," + 10;
				try {
					ResultSet rs = s.executeQuery(sql);
					try {
						while (rs.next()) {
							Laptop laptop = new Laptop();

							laptop.setMadong(rs
									.getString("productCategoryCode"));
							laptop.setClassLaptop(rs.getString("class_result"));

							laptops.add(laptop);
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
		return laptops;
	}

	public static int getCountDMLaptop(DataManager dataManager,
			String filter_laptopName, String filter_laptopClass)
			throws SQLException {
		Connection connection = dataManager.getConnection();
		int count = 0;

		if (connection != null) {
			try {
				Statement s = connection.createStatement();
				String sql = "select count(productCategoryCode) as count from class_laptop where 1=1";
				if (!filter_laptopName.equalsIgnoreCase("")
						&& filter_laptopName != null) {
					sql = sql + " and productCategoryCode like '%"
							+ filter_laptopName + "%'";
				}
				if (!filter_laptopClass.equalsIgnoreCase("")
						&& filter_laptopClass != null) {
					sql = sql + " and class_result = '" + filter_laptopClass
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

	public static void updateDataMiningLaptop(DataManager dataManager,
			String sp, String filter_laptopClass) throws SQLException {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "update class_laptop set class_result='"
						+ filter_laptopClass + "' where productCategoryCode='"
						+ sp + "'";
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

	public static void insertDongsanpham(DataManager dataManager,
			Dongsanpham dongsp) {
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			Statement stmt = null;
			try {
				stmt = connection.createStatement();
				try {
					connection.setAutoCommit(false);

					String sql = "INSERT INTO productCategory(productCategoryCode,productCategoryCode,nation,quantity,image,sellQuantity,description,warranty,importPrice,tax,price,status,date_added) values('"
							+ dongsp.getMa_dongsanpham()
							+ "','"
							+ dongsp.getNhasx()
							+ "','"
							+ dongsp.getQuocgia()
							+ "','"
							+ dongsp.getSoluong()
							+ "','"
							+ dongsp.getImage()
							+ "',0,'"
							+ dongsp.getMota()
							+ "','"
							+ dongsp.getBaohanh()
							+ "','"
							+ dongsp.getGianhap()
							+ "','"
							+ dongsp.getTax()
							+ "','"
							+ dongsp.getGiaban()
							+ "','"
							+ dongsp.getTrangthai() + "',sysdate())";
					stmt.execute(sql);
					connection.commit();

					try {
						stmt.close();
					} finally {
						stmt = null;
					}
				} catch (SQLException e) {
					System.out.println("Could not insert laptop: "
							+ e.getMessage());
					try {
						connection.rollback();
					} catch (SQLException ee) {
					}
				}
			} catch (SQLException e) {
				System.out
						.println("Could not insert laptop: " + e.getMessage());
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

}
