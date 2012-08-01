/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.model;

/**
 *
 * @author NTC
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import digitalshop.beans.AdminView;
import digitalshop.beans.Laptop;

public class AdminPeer {

	public static ArrayList getAdminLaptopView(DataManager dataManager)
			throws SQLException {
		ArrayList<AdminView> adminViews = new ArrayList<AdminView>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "SELECT E.productCategoryCode, weight, quantity, orderQuantity, color, price FROM laptop AS E, productCategory AS F, price AS G WHERE E.productCategoryCode = F.productCategoryCode AND E.productCategoryCode=G.productCategoryCode AND price=(SELECT price FROM price H WHERE E.productCategoryCode=H.productCategoryCode AND date=(SELECT max(date) FROM price AS I WHERE I.productCategoryCode=E.productCategoryCode))";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							AdminView adminView = new AdminView();

							adminView.setMa_dongsanpham(rs.getString(1));
							if (rs.getString(2) != null) {
								adminView.setTrongluong(rs.getString(2));
							} else {
								adminView.setTrongluong(String.valueOf(0));
							}
							if (rs.getString(3) != null) {
								adminView.setSoluong(rs.getString(3));
							} else {
								adminView.setSoluong(String.valueOf(0));
							}
							if (rs.getString(4) != null) {
								adminView.setSoluongdat(rs.getString(4));
							} else {
								adminView.setSoluongdat(String.valueOf(0));
							}
							if (rs.getString(5) != null) {
								adminView.setMausac(rs.getString(5));
							} else {
								adminView.setMausac("");
							}
							if (rs.getString(6) != null) {
								adminView.setGiaban(rs.getString(6));
							} else {
								adminView.setGiaban(String.valueOf(0));
							}

							adminViews.add(adminView); // dua vao danh sach

						}
					} finally {
						rs.close();
					}
				} finally {
					stm.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get adminViews: " +
				// e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return adminViews;
	}

	public static ArrayList getAdminLaptopByNhasx(DataManager dataManager,
			String nhasx) throws SQLException {
		ArrayList<AdminView> adminViews = new ArrayList<AdminView>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "SELECT E.productCategoryCode, weight, quantity, orderQuantity, color, price FROM laptop AS E, productCategory AS F, price AS G WHERE E.productCategoryCode = F.productCategoryCode AND E.productCategoryCode=G.productCategoryCode AND price=(SELECT price FROM price H WHERE E.productCategoryCode=H.productCategoryCode AND date=(SELECT max(date) FROM price AS I WHERE I.productCategoryCode=E.productCategoryCode)) AND F.manufacturerCode = "
						+ "'" + nhasx + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							AdminView adminView = new AdminView();

							adminView.setMa_dongsanpham(rs.getString(1));
							if (rs.getString(2) != null) {
								adminView.setTrongluong(rs.getString(2));
							} else {
								adminView.setTrongluong(String.valueOf(0));
							}
							if (rs.getString(3) != null) {
								adminView.setSoluong(rs.getString(3));
							} else {
								adminView.setSoluong(String.valueOf(0));
							}
							if (rs.getString(4) != null) {
								adminView.setSoluongdat(rs.getString(4));
							} else {
								adminView.setSoluongdat(String.valueOf(0));
							}
							if (rs.getString(5) != null) {
								adminView.setMausac(rs.getString(5));
							} else {
								adminView.setMausac("");
							}
							if (rs.getString(6) != null) {
								adminView.setGiaban(rs.getString(6));
							} else {
								adminView.setGiaban(String.valueOf(0));
							}

							adminViews.add(adminView); // dua vao danh sach

						}
					} finally {
						rs.close();
					}
				} finally {
					stm.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get adminViews: " +
				// e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return adminViews;
	}

	public static ArrayList searchAdminLaptop(DataManager dataManager,
			String ma_dongsanpham) throws SQLException {
		ArrayList<AdminView> adminViews = new ArrayList<AdminView>();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "SELECT E.productCategoryCode, weight, quantity, orderQuantity, color, price FROM laptop AS E, productCategory AS F, price AS G WHERE E.productCategoryCode = F.productCategoryCode AND E.productCategoryCode=G.productCategoryCode AND price=(SELECT price FROM price H WHERE E.productCategoryCode=H.productCategoryCode AND date=(SELECT max(date) FROM price AS I WHERE I.productCategoryCode=E.productCategoryCode)) AND E.productCategoryCode LIKE "
						+ "'%" + ma_dongsanpham + "%'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						if (rs.next() && ma_dongsanpham.length() > 3) {
							rs.previous();
							while (rs.next()) {
								AdminView adminView = new AdminView();

								adminView.setMa_dongsanpham(rs.getString(1));
								if (rs.getString(2) != null) {
									adminView.setTrongluong(rs.getString(2));
								} else {
									adminView.setTrongluong(String.valueOf(0));
								}
								if (rs.getString(3) != null) {
									adminView.setSoluong(rs.getString(3));
								} else {
									adminView.setSoluong(String.valueOf(0));
								}
								if (rs.getString(4) != null) {
									adminView.setSoluongdat(rs.getString(4));
								} else {
									adminView.setSoluongdat(String.valueOf(0));
								}
								if (rs.getString(5) != null) {
									adminView.setMausac(rs.getString(5));
								} else {
									adminView.setMausac("");
								}
								if (rs.getString(6) != null) {
									adminView.setGiaban(rs.getString(6));
								} else {
									adminView.setGiaban(String.valueOf(0));
								}

								adminViews.add(adminView); // dua vao danh sach

							}
						} else {
							adminViews = null;
						}
					} finally {
						rs.close();
					}
				} finally {
					stm.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not search adminViews: " +
				// e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return adminViews;
	}

	public static ArrayList searchExtAdminLaptop(DataManager dataManager,
			String chonhang, String chonsanpham, String giatu, String giaden)
			throws SQLException {
		ArrayList<AdminView> adminViews = new ArrayList<AdminView>();
		boolean ktra = false;
		String sql = "";
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();

				if (!chonsanpham.equals("")) {
					return searchAdminLaptop(dataManager, chonsanpham);
				} else if (!chonhang.equals("")) {
					if (giatu.equals("") && giaden.equals("")) {
						sql = "SELECT manufacturerCode FROM manufacturer WHERE manufacturerName='"
								+ chonhang + "'";
						ResultSet rs = stm.executeQuery(sql);
						rs.next();
						return getAdminLaptopByNhasx(dataManager,
								rs.getString(1));
					} else {
						ktra = true;
					}
				} else if (chonhang.equals("")) {
					ktra = false;
				}
				if (!giatu.equals("") && giaden.equals("")) {
					if (!ktra) {
						sql = "SELECT distinct E.productCategoryCode, weight, quantity, orderQuantity, color, price FROM laptop AS E, productCategory AS F, price AS G WHERE E.productCategoryCode=F.productCategoryCode AND E.productCategoryCode=G.productCategoryCode AND price=(SELECT price FROM price H WHERE E.productCategoryCode=H.productCategoryCode AND date=(SELECT max(date) FROM price AS I WHERE I.productCategoryCode=E.productCategoryCode)) AND price >= '"
								+ Integer.parseInt(giatu) / 16000 + "'";
					} else {
						sql = "SELECT distinct E.productCategoryCode, weight, quantity, orderQuantity, color, price FROM laptop AS E, productCategory AS F, price AS G, manufacturer AS A WHERE E.productCategoryCode=F.productCategoryCode AND E.productCategoryCode=G.productCategoryCode AND F.manufacturerCode=A.manufacturerCodeAND A.manufacturerName='"
								+ chonhang
								+ "' AND price=(SELECT price FROM price H WHERE E.productCategoryCode=H.productCategoryCode AND date=(SELECT max(date) FROM price AS I WHERE I.productCategoryCode=E.productCategoryCode)) AND price >= '"
								+ Integer.parseInt(giatu) / 16000 + "'";
					}
				} else if (giatu.equals("") && !giaden.equals("")) {
					if (!ktra) {
						sql = "SELECT distinct E.productCategoryCode, weight, quantity, orderQuantity, color, price FROM laptop AS E, productCategory AS F, price AS G WHERE E.productCategoryCode=F.productCategoryCode AND E.productCategoryCode=G.productCategoryCode AND price=(SELECT price FROM price H WHERE E.productCategoryCode=H.productCategoryCode AND date=(SELECT max(date) FROM price AS I WHERE I.productCategoryCode=E.productCategoryCode)) AND price <= '"
								+ Integer.parseInt(giaden) / 16000 + "'";
					} else {
						sql = "SELECT distinct E.productCategoryCode, weight, quantity, orderQuantity, color, price FROM laptop AS E, productCategory AS F, price AS G, manufacturer AS A WHERE E.productCategoryCode=F.productCategoryCode AND E.productCategoryCode=G.productCategoryCode AND F.manufacturerCode=A.manufacturerCode AND A.manufacturerName='"
								+ chonhang
								+ "' AND price=(SELECT price FROM price H WHERE E.productCategoryCode=H.productCategoryCode AND date=(SELECT max(date) FROM price AS I WHERE I.productCategoryCode=E.productCategoryCode)) AND price <= '"
								+ Integer.parseInt(giaden) / 16000 + "'";
					}
				} else if (!giatu.equals("") && !giaden.equals("")) {
					if (!ktra) {
						sql = "SELECT distinct E.productCategoryCode, weight, quantity, orderQuantity, color, price FROM laptop AS E, productCategory AS F, price AS G WHERE E.productCategoryCode=F.productCategoryCode AND E.productCategoryCode=G.productCategoryCode AND price=(SELECT price FROM price H WHERE E.productCategoryCode=H.productCategoryCode AND date=(SELECT max(date) FROM price AS I WHERE I.productCategoryCode=E.productCategoryCode)) AND price BETWEEN '"
								+ Integer.parseInt(giatu)
								/ 16000
								+ "' AND '"
								+ Integer.parseInt(giaden) / 16000 + "'";
					} else {
						sql = "SELECT distinct E.productCategoryCode, weight, quantity, orderQuantity, color, price FROM laptop AS E, productCategory AS F, price AS G, manufacturer AS A WHERE E.productCategoryCode=F.productCategoryCode AND E.productCategoryCode=G.productCategoryCode AND F.manufacturerCode=A.manufacturerCode AND A.manufacturerName='"
								+ chonhang
								+ "' AND price=(SELECT price FROM price H WHERE E.productCategoryCode=H.productCategoryCode AND date=(SELECT max(date) FROM price AS I WHERE I.productCategoryCode=E.productCategoryCode)) AND price BETWEEN '"
								+ Integer.parseInt(giatu)
								/ 16000
								+ "' AND '"
								+ Integer.parseInt(giaden) / 16000 + "'";
					}
				}
				ResultSet rs = stm.executeQuery(sql);
				try {
					if (rs.next()) {
						rs.previous();
						while (rs.next()) {
							AdminView adminView = new AdminView();

							adminView.setMa_dongsanpham(rs.getString(1));
							if (rs.getString(2) != null) {
								adminView.setTrongluong(rs.getString(2));
							} else {
								adminView.setTrongluong(String.valueOf(0));
							}
							if (rs.getString(3) != null) {
								adminView.setSoluong(rs.getString(3));
							} else {
								adminView.setSoluong(String.valueOf(0));
							}
							if (rs.getString(4) != null) {
								adminView.setSoluongdat(rs.getString(4));
							} else {
								adminView.setSoluongdat(String.valueOf(0));
							}
							if (rs.getString(5) != null) {
								adminView.setMausac(rs.getString(5));
							} else {
								adminView.setMausac("");
							}
							if (rs.getString(6) != null) {
								adminView.setGiaban(rs.getString(6));
							} else {
								adminView.setGiaban(String.valueOf(0));
							}

							adminViews.add(adminView); // dua vao danh sach

						}
					} else {
						adminViews = null;
					}
				} finally {
					rs.close();
				}

				// } catch (SQLException e) {
				// System.out.println("Could not search adminViews: " +
				// e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return adminViews;
	}

	public static void deleteAdminLaptop(DataManager dataManager, String madong)
			throws SQLException {
		Connection connection = dataManager.getConnection();
		String error = "";
		if (connection != null) {
			try {
				Statement stm1 = connection.createStatement();
				connection.setAutoCommit(false);
				String sql1 = "DELETE FROM laptop WHERE productCategoryCode='"
						+ madong + "'";
				try {
					stm1.executeUpdate(sql1);
				} finally {
					stm1.close();
				}
				connection.commit();
				Statement stm2 = connection.createStatement();
				String sql2 = "DELETE FROM productCategory WHERE productCategoryCode='"
						+ madong + "'";
				try {
					stm2.executeUpdate(sql2);
				} finally {
					stm2.close();
				}
				connection.commit();
			} catch (SQLException e) {
				error = e.getMessage();
				System.out.println("Could not delete this Laptop: "
						+ e.getMessage());
				try {
					connection.rollback();
				} catch (SQLException ee) {
				}
			} finally {
				dataManager.putConnection(connection);
			}
		}
		if (!error.equals(""))
			throw new SQLException(error);
	}

	public static Laptop getLaptopGeneral(DataManager dataManager,
			String ma_dongsanpham) throws SQLException {
		Laptop laptop = new Laptop();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();

				String sql = "SELECT E.productCategoryCode, manufacturerName, nation, warranty, quantity, orderQuantity, image, F.description, weight, color, price FROM laptop AS E, productCategory AS F, manufacturer G, price H WHERE E.productCategoryCode = F.productCategoryCode AND price=(SELECT price FROM price H WHERE E.productCategoryCode=H.productCategoryCode AND date=(SELECT max(date) FROM price AS I WHERE I.productCategoryCode=E.productCategoryCode)) AND E.productCategoryCode=H.productCategoryCode AND E.productCategoryCode = "
						+ "'"
						+ ma_dongsanpham
						+ "'"
						+ "AND F.manufacturerCode = G.manufacturerCode";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							laptop.setMadong(rs.getString("productCategoryCode"));
							laptop.setNhasx(rs.getString("manufacturerName"));
							if (rs.getString("nation") != null) {
								laptop.setQuocgia(rs.getString("nation"));
							} else {
								laptop.setQuocgia("");
							}
							if (rs.getString("warranty") != null) {
								laptop.setBaohanh(Integer.parseInt(rs
										.getString("warranty")));
							} else {
								laptop.setBaohanh(0);
							}
							if (rs.getString("quantity") != null) {
								laptop.setSoluong(Integer.parseInt(rs
										.getString("quantity")));
							} else {
								laptop.setSoluong(0);
							}
							if (rs.getString("orderQuantity") != null) {
								laptop.setSoluongdat(Integer.parseInt(rs
										.getString("orderQuantity")));
							} else {
								laptop.setSoluongdat(0);
							}
							if (rs.getString("image") != null) {
								laptop.setImage(rs.getString("image"));
							} else {
								laptop.setImage("");
							}
							if (rs.getString("description") != null) {
								laptop.setMota(rs.getString("description"));
							} else {
								laptop.setMota("");
							}
							if (rs.getString("weight") != null) {
								laptop.setTrongluong(rs.getString("weight"));
							} else {
								laptop.setTrongluong("");
							}
							if (rs.getString("color") != null) {
								laptop.setMausac(rs.getString("color"));
							} else {
								laptop.setMausac("");
							}
							if (rs.getString("price") != null) {
								laptop.setGia(Double.parseDouble(rs
										.getString("price")));
							} else {
								laptop.setGia(Double.parseDouble("0"));
							}
						}
					} finally {
						rs.close();
					}
				} finally {
					stm.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get laptop general: " +
				// e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return laptop;
	}

	public static Laptop getLaptopMain(DataManager dataManager,
			String ma_dongsanpham) throws SQLException {
		Laptop laptop = new Laptop();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "SELECT productCategoryCode, cpu_kind, cpu_manu, cpu_speed, cpu_tech, cache, cpu_info, main, main_manu, main_bus, main_ram  FROM laptop WHERE productCategoryCode = "
						+ "'" + ma_dongsanpham + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							laptop.setMadong(rs.getString("productCategoryCode"));
							if (rs.getString("cpu_kind") != null) {
								laptop.setCpu_kind(rs.getString("cpu_kind"));
							} else {
								laptop.setCpu_kind("");
							}
							if (rs.getString("cpu_manu") != null) {
								laptop.setCpu_manu(rs.getString("cpu_manu"));
							} else {
								laptop.setCpu_manu("");
							}
							if (rs.getString("cpu_speed") != null) {
								laptop.setCpu_speed(rs.getString("cpu_speed"));
							} else {
								laptop.setCpu_speed("");
							}
							if (rs.getString("cpu_tech") != null) {
								laptop.setCpu_tech(rs.getString("cpu_tech"));
							} else {
								laptop.setCpu_tech("");
							}
							if (rs.getString("cache") != null) {
								laptop.setCache(rs.getString("cache"));
							} else {
								laptop.setCache("");
							}
							if (rs.getString("cpu_info") != null) {
								laptop.setCpu_info(rs.getString("cpu_info"));
							} else {
								laptop.setCpu_info("");
							}
							if (rs.getString("main") != null) {
								laptop.setMain(rs.getString("main"));
							} else {
								laptop.setMain("");
							}
							if (rs.getString("main_manu") != null) {
								laptop.setMain_manu(rs.getString("main_manu"));
							} else {
								laptop.setMain_manu("");
							}
							if (rs.getString("main_bus") != null) {
								laptop.setMain_bus(rs.getString("main_bus"));
							} else {
								laptop.setMain_bus("");
							}
							if (rs.getString("main_ram") != null) {
								laptop.setMain_ram(rs.getString("main_ram"));
							} else {
								laptop.setMain_ram("");
							}

						}
					} finally {
						rs.close();
					}
				} finally {
					stm.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get laptop cpu, mainboard: " +
				// e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return laptop;
	}

	public static Laptop getLaptopHdd(DataManager dataManager,
			String ma_dongsanpham) throws SQLException {
		Laptop laptop = new Laptop();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "SELECT productCategoryCode, hdd, hdd_cap, hdd_manu, hdd_speed, ram, ram_bus, ram_cap, ram_tech, compactdisk, disk_info  FROM laptop WHERE productCategoryCode = "
						+ "'" + ma_dongsanpham + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							laptop.setMadong(rs.getString("productCategoryCode"));
							if (rs.getString("hdd") != null) {
								laptop.setHdd(rs.getString("hdd"));
							} else {
								laptop.setHdd("");
							}
							if (rs.getString("hdd_cap") != null) {
								laptop.setHdd_cap(rs.getString("hdd_cap"));
							} else {
								laptop.setHdd_cap("");
							}
							if (rs.getString("hdd_manu") != null) {
								laptop.setHdd_manu(rs.getString("hdd_manu"));
							} else {
								laptop.setHdd_manu("");
							}
							if (rs.getString("hdd_speed") != null) {
								laptop.setHdd_speed(rs.getString("hdd_speed"));
							} else {
								laptop.setHdd_speed("");
							}
							if (rs.getString("ram") != null) {
								laptop.setRam(rs.getString("ram"));
							} else {
								laptop.setRam("");
							}
							if (rs.getString("ram_bus") != null) {
								laptop.setRam_bus(rs.getString("ram_bus"));
							} else {
								laptop.setRam_bus("");
							}
							if (rs.getString("ram_cap") != null) {
								laptop.setRam_cap(rs.getString("ram_cap"));
							} else {
								laptop.setRam_cap("");
							}
							if (rs.getString("ram_tech") != null) {
								laptop.setRam_tech(rs.getString("ram_tech"));
							} else {
								laptop.setRam_tech("");
							}
							if (rs.getString("compactdisk") != null) {
								laptop.setCompactdisk(rs
										.getString("compactdisk"));
							} else {
								laptop.setCompactdisk("");
							}
							if (rs.getString("disk_info") != null) {
								laptop.setDisk_info(rs.getString("disk_info"));
							} else {
								laptop.setDisk_info("");
							}
						}
					} finally {
						rs.close();
					}
				} finally {
					stm.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get laptop Hdd, cd, ram: " +
				// e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return laptop;
	}

	public static Laptop getLaptopSound(DataManager dataManager,
			String ma_dongsanpham) throws SQLException {
		Laptop laptop = new Laptop();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "SELECT productCategoryCode, sound, sound_info, graphic, graphic_info, display, display_size, network, network_info FROM laptop WHERE productCategoryCode = "
						+ "'" + ma_dongsanpham + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							laptop.setMadong(rs.getString("productCategoryCode"));
							if (rs.getString("sound") != null) {
								laptop.setSound(rs.getString("sound"));
							} else {
								laptop.setSound("");
							}
							if (rs.getString("sound_info") != null) {
								laptop.setSound_info(rs.getString("sound_info"));
							} else {
								laptop.setSound_info("");
							}
							if (rs.getString("graphic") != null) {
								laptop.setGraphic(rs.getString("graphic"));
							} else {
								laptop.setGraphic("");
							}
							if (rs.getString("graphic_info") != null) {
								laptop.setGraphic_info(rs
										.getString("graphic_info"));
							} else {
								laptop.setGraphic_info("");
							}
							if (rs.getString("display") != null) {
								laptop.setDisplay(rs.getString("display"));
							} else {
								laptop.setDisplay("");
							}
							if (rs.getString("display_size") != null) {
								laptop.setDisplay_size(rs
										.getString("display_size"));
							} else {
								laptop.setDisplay_size("");
							}
							if (rs.getString("network") != null) {
								laptop.setNetwork(rs.getString("network"));
							} else {
								laptop.setNetwork("");
							}
							if (rs.getString("network_info") != null) {
								laptop.setNetwork_info(rs
										.getString("network_info"));
							} else {
								laptop.setNetwork_info("");
							}
						}
					} finally {
						rs.close();
					}
				} finally {
					stm.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get laptop Sound, Graphic, Network: "
				// + e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return laptop;
	}

	public static Laptop getLaptopAnother(DataManager dataManager,
			String ma_dongsanpham) throws SQLException {
		Laptop laptop = new Laptop();
		Connection connection = dataManager.getConnection();
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				String sql = "SELECT productCategoryCode, os, battery, card_reader, webcam, accessories, tinhnangmorong FROM laptop WHERE productCategoryCode = "
						+ "'" + ma_dongsanpham + "'";
				try {
					ResultSet rs = stm.executeQuery(sql);
					try {
						while (rs.next()) {
							laptop.setMadong(rs.getString("productCategoryCode"));
							if (rs.getString("os") != null) {
								laptop.setOs(rs.getString("os"));
							} else {
								laptop.setOs("");
							}
							if (rs.getString("battery") != null) {
								laptop.setBattery(rs.getString("battery"));
							} else {
								laptop.setBattery("");
							}
							if (rs.getString("card_reader") != null) {
								laptop.setCard_reader(rs
										.getString("card_reader"));
							} else {
								laptop.setCard_reader("");
							}
							if (rs.getString("webcam") != null) {
								laptop.setWebcam(rs.getString("webcam"));
							} else {
								laptop.setWebcam("");
							}
							if (rs.getString("accessories") != null) {
								laptop.setPhukien(rs.getString("accessories"));
							} else {
								laptop.setPhukien("");
							}
							if (rs.getString("tinhnangmorong") != null) {
								laptop.setTinhnangmorong(rs
										.getString("tinhnangmorong"));
							} else {
								laptop.setTinhnangmorong("");
							}
						}
					} finally {
						rs.close();
					}
				} finally {
					stm.close();
				}
				// } catch (SQLException e) {
				// System.out.println("Could not get laptop Sound, Graphic, Network: "
				// + e.getMessage());
			} finally {
				dataManager.putConnection(connection);
			}
		}
		return laptop;
	}

	public static void updateLaptopGeneral(DataManager dataManager,
			Laptop laptop, String ma_dongsanpham) throws SQLException {
		Connection connection = dataManager.getConnection();
		String error = "";
		if (connection != null) {
			try {
				Statement stm1 = connection.createStatement();
				connection.setAutoCommit(false);
				String sql1 = "UPDATE productCategory SET manufacturerCode=(SELECT manufacturerCode FROM manufacturer WHERE manufacturerName="
						+ "'"
						+ laptop.getNhasx()
						+ "'), nation='"
						+ laptop.getQuocgia()
						+ "',warranty='"
						+ laptop.getBaohanh()
						+ "',quantity='"
						+ laptop.getSoluong()
						+ "',orderQuantity='"
						+ laptop.getSoluongdat()
						+ "',image='"
						+ laptop.getImage()
						+ "',description='"
						+ laptop.getMota()
						+ "' WHERE productCategoryCode = "
						+ "'"
						+ ma_dongsanpham
						+ "'";
				try {
					stm1.executeUpdate(sql1);
				} finally {
					stm1.close();
				}
				connection.commit();
				Statement stm2 = connection.createStatement();
				String sql2 = "UPDATE laptop SET weight='"
						+ laptop.getTrongluong() + "',color='"
						+ laptop.getMausac() + "' WHERE productCategoryCode = "
						+ "'" + ma_dongsanpham + "'";
				try {
					stm2.executeUpdate(sql2);
				} finally {
					stm2.close();
				}
				connection.commit();
				Statement stm3 = connection.createStatement();
				String sql3 = "UPDATE price SET price='" + laptop.getGia()
						+ "' WHERE productCategoryCode = " + "'" + ma_dongsanpham
						+ "'";
				try {
					stm3.executeUpdate(sql3);
				} finally {
					stm3.close();
				}
				connection.commit();
			} catch (SQLException e) {
				error = e.getMessage();
				System.out.println("Could not update Laptop general: "
						+ e.getMessage());
				try {
					connection.rollback();
				} catch (SQLException ee) {
				}
			} finally {
				dataManager.putConnection(connection);
			}
		}
		if (!error.equals(""))
			throw new SQLException(error);
	}

	public static void updateLaptopMain(DataManager dataManager, Laptop laptop,
			String ma_dongsanpham) throws SQLException {
		Connection connection = dataManager.getConnection();
		String error = "";
		if (connection != null) {
			try {
				Statement stm = connection.createStatement();
				connection.setAutoCommit(false);
				String sql = "UPDATE laptop SET cpu_kind='"
						+ laptop.getCpu_kind() + "',cpu_manu='"
						+ laptop.getCpu_manu() + "',cpu_speed='"
						+ laptop.getCpu_speed() + "',cpu_tech='"
						+ laptop.getCpu_tech() + "',cache='"
						+ laptop.getCache() + "',cpu_info='"
						+ laptop.getCpu_info() + "',main='" + laptop.getMain()
						+ "',main_manu='" + laptop.getMain_manu()
						+ "',main_bus='" + laptop.getMain_bus()
						+ "',main_ram='" + laptop.getMain_ram()
						+ "' WHERE productCategoryCode = " + "'" + ma_dongsanpham
						+ "'";
				try {
					stm.executeUpdate(sql);
				} finally {
					stm.close();
				}
				connection.commit();
			} catch (SQLException e) {
				error = e.getMessage();
				System.out.println("Could not update Laptop cpu, mainboard: "
						+ e.getMessage());
				try {
					connection.rollback();
				} catch (SQLException ee) {
				}
			} finally {
				dataManager.putConnection(connection);
			}
		}
		if (!error.equals(""))
			throw new SQLException(error);
	}
}
