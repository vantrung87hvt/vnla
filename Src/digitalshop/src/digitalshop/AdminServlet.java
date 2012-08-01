/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop;

import digitalshop.beans.Khachhang;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.*;

import digitalshop.model.DataManager;
import digitalshop.beans.Laptop;
import digitalshop.beans.Dongsanpham;
import digitalshop.beans.GioHang;
import digitalshop.beans.Nhasanxuat;
import digitalshop.beans.order_product;
import digitalshop.beans.Rule;
import digitalshop.beans.User;
import digitalshop.beans.UserGroup;
import digitalshop.beans.tinhthanh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import digitalshop.beans.MD5;
import digitalshop.beans.HoaDon;

/**
 * 
 * @author DANG TRUNG TIN
 */
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DataManager dataManager = new DataManager();

	public void init(ServletConfig config) throws ServletException {
		System.out.println("*** initializing controller servlet.");
		super.init(config);

		// DataManager dataManager = new DataManager();
		dataManager.setDbURL(config.getInitParameter("dbURL"));
		dataManager.setDbUserName(config.getInitParameter("dbUserName"));
		dataManager.setDbPassword(config.getInitParameter("dbPassword"));

		ServletContext context = config.getServletContext();
		context.setAttribute("baseAdmin", config.getInitParameter("baseAdmin"));
		context.setAttribute("imageURL", config.getInitParameter("imageURL"));
		context.setAttribute("dataManager", dataManager);

		try { // load the database JDBC driver

			Class.forName(config.getInitParameter("jdbcDriver"));
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String base = "/Admin/";
		String url = base + "AdminLogin.jsp";
		Laptop newLaptop = new Laptop();

		try {
			String action = request.getParameter("action");
			if (action != null) {
				if (action.equalsIgnoreCase("checkUser")) {
					url = checkUser(request, response, session, base, url);
				} else {
					if (session.getAttribute("username") == null
							|| ((String) session.getAttribute("username"))
									.equalsIgnoreCase("")
							|| session.getAttribute("username") == null
							|| ((String) session.getAttribute("username"))
									.equalsIgnoreCase("")) {
						url = adminLogout(session, base);
					} // Data Mining
					else if (action.equals("DataMiningAssociationRules")) {
						url = dataMiningAssociationRules(session, base);
					} else if (action.equals("getProductListByProducer")) {
						url = getProductListByProducer(request, session, base);
					} else if (action.equals("getProducts")) {
						url = getProducts(request, session, base);
					} else if (action.equals("getProductList")) {
						url = getProducts(request, session, base);
					} else if (action.equals("getRulesForProduct")) {
						url = getRulesForProduct(request, session, base);
					} else if (action.equals("AdminRuleEditPopup")) {
						url = adminRuleEditPopup(request, session, base);
					} else if (action.equals("getProductByProducer")) {
						url = getProductByProducer(request, session, base);
					} else if (action.equals("AdminRuleUpdated")) {
						url = adminRuleUpdated(request, session, base);
					} else if (action.equalsIgnoreCase("DataMiningCustomer")) {
						response.sendRedirect("admin?action=DataMiningCustomerFilter&filter_customerID=&filter_customerName=&filter_class=");
					} else if (action
							.equalsIgnoreCase("DataMiningCustomerFilter")) {
						url = dataMiningCustomerFilter(request, session, base);
					} else if (action
							.equalsIgnoreCase("DataMiningCustomerEdit")) {
						url = dataMiningCustomerEdit(request, session, base);
					} else if (action
							.equalsIgnoreCase("DataMiningCustomerEditSave")) {
						dataMiningCustomerEditSave(request, response, session);
					} else if (action.equalsIgnoreCase("DataMiningLaptop")) {
						response.sendRedirect("admin?action=DataMiningLaptopFilter&filter_laptopName=&filter_laptopClass=");
					} else if (action
							.equalsIgnoreCase("DataMiningLaptopFilter")) {
						url = dataMiningLaptopFilter(request, session, base);
					} else if (action.equalsIgnoreCase("DataMiningLaptopEdit")) {
						url = dataMiningLaptopEdit(request, session, base);
					} else if (action
							.equalsIgnoreCase("DataMiningLaptopEditSave")) {
						dataMiningLaptopEditSave(request, response, session);
					} else if (action.equalsIgnoreCase("DataMiningViewed")) {
						response.sendRedirect("admin?action=DataMiningViewedFilter&filter_manu=&filter_product=&filter_viewed_product=");
					} else if (action
							.equalsIgnoreCase("DataMiningViewedFilter")) {
						url = dataMiningViewedFilter(request, session, base);
					} else if (action.equalsIgnoreCase("DataMiningPurchased")) {
						response.sendRedirect("admin?action=DataMiningPurchasedFilter&filter_manu=&filter_product=&filter_purchased_product=");
					} else if (action
							.equalsIgnoreCase("DataMiningPurchasedFilter")) {
						url = dataMiningPurchasedFilter(request, session, base);
					} else if (action.equals("AdminChartView")) {
						url = adminChartView(request, session, base);
					} else if (action.equals("GetChart")) {
						url = getChart(request, session, base);
					} else if (action.equals("AdminUserCatalog")) {
						url = adminUserCatalog(request, session, base);
					} else if (action.equalsIgnoreCase("AdminUserNew")) {
						url = adminUserNew(session, base);
					} else if (action.equalsIgnoreCase("AdminResult")) {
						url = adminResult(request, session, base);
					} else if (action.equalsIgnoreCase("AdminUserSearch")) {
						url = adminUserSearch(request, session, base);
					} /*-------Reports---------------*/else if (action
							.equalsIgnoreCase("ReportProductPurchased")) {
						url = reportProductPurchased(request, session, base);
					} else if (action.equalsIgnoreCase("ReportProductViewed")) {
						url = reportProductViewed(request, session, base);
					} else if (action.equalsIgnoreCase("ReportSales")) {
						reportSales(response);
					} else if (action.equalsIgnoreCase("ReportSalesFilter")) {
						url = reportSalesFilter(request, session, base);
					} /*-------Customers---------------*/else if (action
							.equalsIgnoreCase("CustomerView")) {
						url = customerView(request, session, base);
					} else if (action.equalsIgnoreCase("CustomerFilter")) {
						url = customerFilter(request, session, base);
					} else if (action.equalsIgnoreCase("ProductsLaptopFilter")) {
						url = productsLaptopFilter(request, session, base);
					} else if (action.equalsIgnoreCase("CustomerDelete")) {
						customerDelete(request, response, session);
					} else if (action.equalsIgnoreCase("CustomerInsert")) {
						url = customerInsert(session, base);
					} else if (action.equalsIgnoreCase("CustomerInsertSave")) {
						customerInsertSave(request, response, session);
					} else if (action.equalsIgnoreCase("CustomerReset")) {
						url = customerReset(request, session, base);
					} else if (action.equalsIgnoreCase("CustomerResetSave")) {
						customerResetSave(request, response, session);
					} else if (action.equalsIgnoreCase("CustomerOrder")) {
						url = customerOrder(request, session, base);
					} else if (action.equalsIgnoreCase("CustomerOrderDelete")) {
						customerOrderDelete(request, response, session);
					} else if (action.equalsIgnoreCase("CustomerOrderEdit")) {
						url = customerOrderEdit(request, session, base);
					} else if (action.equalsIgnoreCase("CustomerOrderEditSave")) {
						customerOrderEditSave(request, response);
					} else if (action.equalsIgnoreCase("CustomerOrderPrint")) {
						url = customerOrderPrint(request, session, base);
					} else if (action.equalsIgnoreCase("CustomerMail")) {
						url = customerMail(request, session, base);
					} /*-------Catalog---------------*/else if (action
							.equalsIgnoreCase("CatalogManufacturer")) {
						url = catalogManufacturer(request, session, base);
					} else if (action
							.equalsIgnoreCase("CatalogManufacturerEdit")) {
						url = catalogManufacturerEdit(request, session, base);
					} else if (action
							.equalsIgnoreCase("CatalogManufacturerSave")) {
						catalogManufacturerSave(request, response, session);
					} else if (action
							.equalsIgnoreCase("CatalogManufacturerInsert")) {
						url = base + "CatalogManufacturerInsert.jsp";
					} else if (action
							.equalsIgnoreCase("CatalogManufacturerInsertSave")) {
						catalogManufacturerInsertSave(request, response,
								session);
					} else if (action
							.equalsIgnoreCase("CatalogManufacturerDelete")) {
						catalogManufacturerDelete(request, response, session);
					} else if (action.equalsIgnoreCase("CatalogProvince")) {
						url = catalogProvince(request, session, base);
					} else if (action.equalsIgnoreCase("CatalogProvinceEdit")) {
						url = catalogProvinceEdit(request, session, base);
					} /*-------Index---------------*/else if (action
							.equalsIgnoreCase("AdminLogin")) {
						url = base + "AdminLogin.jsp";
					} else if (action.equalsIgnoreCase("index")) {
						url = base + "index.jsp";
					} else if (action.equalsIgnoreCase("getSalesStatistic")) {
						url = getSalesStatistic(request, session, base);
					} else if (action.equalsIgnoreCase("AdminLogout")) {
						url = adminLogout(session, base);
					} /*-------Admin---------------*/else if (action
							.equalsIgnoreCase("AdminUser")) {
						url = adminUser(request, session, base);
					} else if (action.equalsIgnoreCase("AdminUserDelete")) {
						adminUserDelete(request, response, session);
					} else if (action.equalsIgnoreCase("AdminUserEdit")) {
						url = adminUserEdit(request, session, base);
					} else if (action.equalsIgnoreCase("AdminUserEditSave")) {
						adminUserEditSave(request, response, session);
					} else if (action.equalsIgnoreCase("AdminUserInsert")) {
						url = adminUserInsert(session, base);
					} else if (action.equalsIgnoreCase("AdminUserInsertSave")) {
						adminUserInsertSave(request, response, session);
					} else if (action.equalsIgnoreCase("AdminUserGroup")) {
						url = adminUserGroup(request, session, base);
					} else if (action.equalsIgnoreCase("AdminUserGroupDelete")) {
						adminUserGroupDelete(request, response, session);
					} else if (action.equalsIgnoreCase("AdminUserGroupInsert")) {
						url = adminUserGroupInsert(session, base);
					} else if (action
							.equalsIgnoreCase("AdminUserGroupInsertSave")) {
						adminUserGroupInsertSave(request, response, session);
					} else if (action.equalsIgnoreCase("AdminUserGroupEdit")) {
						url = adminUserGroupEdit(request, session, base);
					} else if (action
							.equalsIgnoreCase("AdminUserGroupEditSave")) {
						adminUserGroupEditSave(request, response, session);
					} /*-------Products---------------*/else if (action
							.equalsIgnoreCase("ProductsLaptop")) {
						url = productsLaptop(request, session, base);
					} else if (action.equalsIgnoreCase("ProductsLaptopEdit")) {
						url = productsLaptopEdit(request, session, base);
					} else if (action.equalsIgnoreCase("ProductsLaptopSave")) {
						productsLaptopSave(request, response, session);
					} else if (action.equalsIgnoreCase("ProductsLaptopInsert")) {
						url = productsLaptopInsert(session, base);
					} else if (action
							.equalsIgnoreCase("ProductsLaptopInsertSave")) {
						productsLaptopInsertSave(request, response, session);
					} else if (action.equalsIgnoreCase("ProductsLaptopDelete")) {
						productsLaptopDelete(request, response, session);
					} // Charts
					else if (action.equalsIgnoreCase("SalesChart")) {
						url = salesChart(request, session, base);
					} else if (action.equalsIgnoreCase("CustomerMailDelete")) {
						url = customerMailDelete(request, response, base);
					}
				}
			}

			RequestDispatcher requestDispatcher = getServletContext()
					.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);

		} catch (Exception error) {
			System.out.println(error.getMessage());
		}
	}

	private String customerMailDelete(HttpServletRequest request,
			HttpServletResponse response, String base) {
		// TODO Auto-generated method stub
		String url = null;
		String ID = request.getParameter("ID");
		Integer deleteID = null;
		if (ID != "") {
			for (int i = 0; i < ID.length(); i++) {
				deleteID = Integer.parseInt(ID.charAt(i)+"");
				dataManager.deleteMail(deleteID);
			}
		}
		url = base + "CustomerMail.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String salesChart(HttpServletRequest request, HttpSession session,
			String base) throws SQLException {
		String url;
		String range = request.getParameter("range");
		range = "day";
		ArrayList<Integer> orderNos = new ArrayList<Integer>();
		ArrayList<Integer> customerNos = new ArrayList<Integer>();
		if (range.equalsIgnoreCase("day")) {
			for (int i = 1; i <= 24; i++) {
				int orderNo = 0;
				int customerNo = 0;
				orderNo = dataManager.getSalesStatisticInDay(i);
				orderNos.add(orderNo);
				customerNo = dataManager.getCustomersStatisticInDay(i);
				customerNos.add(customerNo);
			}
		} else if (range.equalsIgnoreCase("week")) {
		} else if (range.equalsIgnoreCase("month")) {
		} else if (range.equalsIgnoreCase("year")) {
		}
		session.setAttribute("orderNos", orderNos);
		session.setAttribute("customerNos", customerNos);
		url = base + "SalesChart.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void productsLaptopDelete(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		ArrayList laptops = (ArrayList) session.getAttribute("laptops");
		String[] StrChecked = new String[laptops.size()];
		for (int i = 0; i < laptops.size(); i++) {
			StrChecked[i] = request.getParameter(String.valueOf(i));
			if (StrChecked[i] != null && StrChecked[i].equalsIgnoreCase("true")) {
				dataManager.deleteDongsanpham(((Dongsanpham) laptops.get(i))
						.getMa_dongsanpham());
			}
		}
		response.sendRedirect("admin?action=ProductsLaptop");
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void productsLaptopInsertSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		Dongsanpham dongsp = new Dongsanpham();
		dongsp.setMa_dongsanpham(request.getParameter("ma_dongsanpham"));
		dongsp.setNhasx(request.getParameter("ma_nhasx"));
		dongsp.setQuocgia(request.getParameter("quocgia"));
		dongsp.setImage(request.getParameter("image"));
		String soluongnhap = request.getParameter("soluongnhap");
		int soluong = Integer.valueOf(soluongnhap);
		dongsp.setSoluong(String.valueOf(soluong));
		dongsp.setMota(request.getParameter("mota"));
		dongsp.setBaohanh(request.getParameter("baohanh"));
		dongsp.setGianhap(request.getParameter("gianhap"));
		dongsp.setTax(request.getParameter("tax"));
		dongsp.setGiaban(request.getParameter("giaban"));
		dongsp.setTrangthai(request.getParameter("trangthai"));
		dataManager.insertDongsanpham(dongsp);

		Laptop laptop = new Laptop();
		laptop.setMadong(request.getParameter("ma_dongsanpham"));
		laptop.setCpu_tech(request.getParameter("cpu_tech"));
		laptop.setCpu_manu(request.getParameter("cpu_manu"));
		laptop.setCpu_kind(request.getParameter("cpu_kind"));
		laptop.setCpu_speed(request.getParameter("cpu_speed"));
		laptop.setCache(request.getParameter("cache"));
		laptop.setMain(request.getParameter("main"));
		laptop.setMain_manu(request.getParameter("main_manu"));
		laptop.setMain_bus(request.getParameter("main_bus"));
		laptop.setMain_ram(request.getParameter("main_ram"));
		laptop.setRam(request.getParameter("ram"));
		laptop.setRam_cap(request.getParameter("ram_cap"));
		laptop.setRam_bus(request.getParameter("ram_bus"));
		laptop.setRam_tech(request.getParameter("ram_tech"));
		laptop.setHdd(request.getParameter("hdd"));
		laptop.setHdd_manu(request.getParameter("hdd_manu"));
		laptop.setHdd_cap(request.getParameter("hdd_cap"));
		laptop.setHdd_speed(request.getParameter("hdd_speed"));
		laptop.setGraphic(request.getParameter("graphic"));
		laptop.setSound(request.getParameter("sound"));
		laptop.setNetwork(request.getParameter("network"));

		laptop.setCompactdisk(request.getParameter("compactdisk"));
		laptop.setDisplay(request.getParameter("display"));
		laptop.setDisplay_size(request.getParameter("display_size"));
		laptop.setBattery(request.getParameter("battery"));
		laptop.setOs(request.getParameter("os"));
		laptop.setTinhnangmorong(request.getParameter("tinhnangmorong"));
		laptop.setWebcam(request.getParameter("webcam"));
		laptop.setCard_reader(request.getParameter("card_reader"));
		laptop.setPhukien(request.getParameter("phukien"));

		dataManager.insertLaptop(laptop);
		session.setAttribute("ProductsLaptopSaveSuccess", "1");
		response.sendRedirect("admin?action=ProductsLaptop");
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String productsLaptopInsert(HttpSession session, String base)
			throws SQLException {
		String url;
		String strManu = dataManager
				.getOptionsID(
						"select manufacturerCode, manufacturerName from manufacturer order by manufacturerName",
						false, false, false, "");
		String strCpu_manu = dataManager.getOptions(
				"select distinct cpu_manu from laptop order by cpu_manu",
				false, false, false, "");
		String strMain_manu = dataManager.getOptions(
				"select distinct main_manu from laptop order by main_manu",
				false, false, false, "");
		String strHdd_manu = dataManager.getOptions(
				"select distinct hdd_manu from laptop order by hdd_manu",
				false, false, false, "");
		session.setAttribute("strManu", strManu);
		session.setAttribute("strCpu_manu", strCpu_manu);
		session.setAttribute("strMain_manu", strMain_manu);
		session.setAttribute("strHdd_manu", strHdd_manu);
		url = base + "ProductsLaptopInsert.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void productsLaptopSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		String ma_dongsanpham = (String) session.getAttribute("ma_dongsanpham");
		session.setAttribute("ma_dongsanpham", "");
		Dongsanpham dongsp = new Dongsanpham();
		dongsp.setMa_dongsanpham(request.getParameter("ma_dongsanpham"));
		dongsp.setNhasx(request.getParameter("ma_nhasx"));
		dongsp.setQuocgia(request.getParameter("quocgia"));
		dongsp.setImage(request.getParameter("image"));
		String soluongban = (String) session.getAttribute("soluongban");
		String soluongton = request.getParameter("soluongton");
		int soluong = Integer.valueOf(soluongban) + Integer.valueOf(soluongton);
		dongsp.setSoluong(String.valueOf(soluong));
		dongsp.setSoluongban(soluongban);
		dongsp.setMota(request.getParameter("mota"));
		dongsp.setBaohanh(request.getParameter("baohanh"));
		dongsp.setGianhap(request.getParameter("gianhap"));
		dongsp.setTax(request.getParameter("tax"));
		dongsp.setGiaban(request.getParameter("giaban"));
		dongsp.setTrangthai(request.getParameter("trangthai"));
		dataManager.updateDongsanpham(ma_dongsanpham, dongsp);
		session.setAttribute("ProductsLaptopSaveSuccess", "1");

		Laptop laptop = new Laptop();
		laptop.setCpu_tech(request.getParameter("cpu_tech"));
		laptop.setCpu_manu(request.getParameter("cpu_manu"));
		laptop.setCpu_kind(request.getParameter("cpu_kind"));
		laptop.setCpu_speed(request.getParameter("cpu_speed"));
		laptop.setCache(request.getParameter("cache"));
		laptop.setMain(request.getParameter("main"));
		laptop.setMain_manu(request.getParameter("main_manu"));
		laptop.setMain_bus(request.getParameter("main_bus"));
		laptop.setMain_ram(request.getParameter("main_ram"));
		laptop.setRam(request.getParameter("ram"));
		laptop.setRam_cap(request.getParameter("ram_cap"));
		laptop.setRam_bus(request.getParameter("ram_bus"));
		laptop.setRam_tech(request.getParameter("ram_tech"));
		laptop.setHdd(request.getParameter("hdd"));
		laptop.setHdd_manu(request.getParameter("hdd_manu"));
		laptop.setHdd_cap(request.getParameter("hdd_cap"));
		laptop.setHdd_speed(request.getParameter("hdd_speed"));
		laptop.setGraphic(request.getParameter("graphic"));
		laptop.setSound(request.getParameter("sound"));
		laptop.setNetwork(request.getParameter("network"));

		laptop.setCompactdisk(request.getParameter("compactdisk"));
		laptop.setDisplay(request.getParameter("display"));
		laptop.setDisplay_size(request.getParameter("display_size"));
		laptop.setBattery(request.getParameter("battery"));
		laptop.setOs(request.getParameter("os"));
		laptop.setTinhnangmorong(request.getParameter("tinhnangmorong"));
		laptop.setWebcam(request.getParameter("webcam"));
		laptop.setCard_reader(request.getParameter("card_reader"));
		laptop.setPhukien(request.getParameter("phukien"));

		dataManager.updateLaptopById(ma_dongsanpham, laptop);

		response.sendRedirect("admin?action=ProductsLaptop");
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String productsLaptopEdit(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String lap = request.getParameter("lap");
		Dongsanpham dongsp = dataManager.getDongspByMadong(lap);
		session.setAttribute("dongsp", dongsp);
		String strManu = dataManager
				.getOptionsID(
						"select manufacturerCode, manufacturerName from manufacturer order by manufacturerName",
						false, false, false, dongsp.getNhasx());
		session.setAttribute("strManu", strManu);
		Laptop laptop = dataManager.getLaptopByMadong(lap);
		session.setAttribute("laptop", laptop);
		String strCpu_manu = dataManager.getOptions(
				"select distinct cpu_manu from laptop order by cpu_manu",
				false, false, false, laptop.getCpu_manu());
		String strMain_manu = dataManager.getOptions(
				"select distinct main_manu from laptop order by main_manu",
				false, false, false, laptop.getMain_manu());
		String strHdd_manu = dataManager.getOptions(
				"select distinct hdd_manu from laptop order by hdd_manu",
				false, false, false, laptop.getHdd_manu());
		session.setAttribute("strCpu_manu", strCpu_manu);
		session.setAttribute("strMain_manu", strMain_manu);
		session.setAttribute("strHdd_manu", strHdd_manu);
		url = base + "ProductsLaptopEdit.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String productsLaptop(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		ArrayList laptops = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			laptops = dataManager.getListOfLaptops(1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			laptops = dataManager.getListOfLaptops(page);
		}
		int count = dataManager.getCountLaptops();
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("laptops", laptops);
		url = base + "ProductsLaptop.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void adminUserGroupEditSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		String access = "";
		String insert = "";
		String modify = "";
		String delete = "";
		String export = "";
		String id = (String) session.getAttribute("id");
		String usergroup = request.getParameter("usergroup");
		for (int i = 0; i < 14; i++) {
			if (request.getParameter("access[" + i + "]") != null) {
				access += request.getParameter("access[" + i + "]") + ";";
			}
			if (request.getParameter("insert[" + i + "]") != null) {
				insert += request.getParameter("insert[" + i + "]") + ";";
			}
			if (request.getParameter("modify[" + i + "]") != null) {
				modify += request.getParameter("modify[" + i + "]") + ";";
			}
			if (request.getParameter("delete[" + i + "]") != null) {
				delete += request.getParameter("delete[" + i + "]") + ";";
			}
			if (request.getParameter("export[" + i + "]") != null) {
				export += request.getParameter("export[" + i + "]") + ";";
			}
		}
		dataManager.updateUserGroup(id, usergroup, access, insert, modify,
				delete, export);
		session.setAttribute("AdminUserGroupSaveSuccess", "1");
		response.sendRedirect("admin?action=AdminUserGroup");
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String adminUserGroupEdit(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String id = request.getParameter("id");
		UserGroup usergroup = dataManager.getUserGroupById(id);
		session.setAttribute("usergroup", usergroup);
		ArrayList accessList = dataManager.getListOfAccess();
		session.setAttribute("accessList", accessList);
		ArrayList insertList = dataManager.getListOfInsert();
		session.setAttribute("insertList", insertList);
		ArrayList modifyList = dataManager.getListOfModify();
		session.setAttribute("modifyList", modifyList);
		ArrayList deleteList = dataManager.getListOfDelete();
		session.setAttribute("deleteList", deleteList);
		ArrayList exportList = dataManager.getListOfExport();
		session.setAttribute("exportList", exportList);
		url = base + "AdminUserGroupEdit.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void adminUserGroupInsertSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		String access = "";
		String insert = "";
		String modify = "";
		String delete = "";
		String export = "";
		String usergroup = request.getParameter("usergroup");
		for (int i = 0; i < 14; i++) {
			if (request.getParameter("access[" + i + "]") != null) {
				access += request.getParameter("access[" + i + "]") + ";";
			}
			if (request.getParameter("insert[" + i + "]") != null) {
				insert += request.getParameter("insert[" + i + "]") + ";";
			}
			if (request.getParameter("modify[" + i + "]") != null) {
				modify += request.getParameter("modify[" + i + "]") + ";";
			}
			if (request.getParameter("delete[" + i + "]") != null) {
				delete += request.getParameter("delete[" + i + "]") + ";";
			}
			if (request.getParameter("export[" + i + "]") != null) {
				export += request.getParameter("export[" + i + "]") + ";";
			}
		}
		dataManager.insertUserGroup(usergroup, access, insert, modify, delete,
				export);
		session.setAttribute("AdminUserGroupSaveSuccess", "1");
		response.sendRedirect("admin?action=AdminUserGroup");
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String adminUserGroupInsert(HttpSession session, String base)
			throws SQLException {
		String url;
		ArrayList accessList = dataManager.getListOfAccess();
		session.setAttribute("accessList", accessList);
		ArrayList insertList = dataManager.getListOfInsert();
		session.setAttribute("insertList", insertList);
		ArrayList modifyList = dataManager.getListOfModify();
		session.setAttribute("modifyList", modifyList);
		ArrayList deleteList = dataManager.getListOfDelete();
		session.setAttribute("deleteList", deleteList);
		ArrayList exportList = dataManager.getListOfExport();
		session.setAttribute("exportList", exportList);
		url = base + "AdminUserGroupInsert.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void adminUserGroupDelete(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		ArrayList usergroups = (ArrayList) session.getAttribute("usergroups");
		String[] StrChecked = new String[usergroups.size()];
		for (int i = 0; i < usergroups.size(); i++) {
			StrChecked[i] = request.getParameter(String.valueOf(i));
			if (StrChecked[i] != null && StrChecked[i].equalsIgnoreCase("true")) {
				dataManager.deleteUserGroup(((UserGroup) usergroups.get(i))
						.getUser_group_id());
			}
		}
		session.setAttribute("AdminUserGroupSaveSuccess", "1");
		response.sendRedirect("admin?action=AdminUserGroup");
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String adminUserGroup(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		ArrayList usergroups = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			usergroups = dataManager.getListOfUserGroup(1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			usergroups = dataManager.getListOfUserGroup(page);
		}
		int count = dataManager.getCountUserGroup();
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("usergroups", usergroups);
		url = base + "AdminUserGroup.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void adminUserInsertSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setFirstName(request.getParameter("firstname"));
		user.setLastName(request.getParameter("lastname"));
		user.setEmail(request.getParameter("email"));
		user.setUser_group_id(request.getParameter("user_group_id"));
		user.setPassword(request.getParameter("password"));
		user.setStatus(Integer.valueOf(request.getParameter("status")));
		if (!user.getUsername().equalsIgnoreCase("")
				&& !user.getFirstName().equalsIgnoreCase("")
				&& !user.getLastName().equalsIgnoreCase("")) {
			dataManager.insertUser(user);
			session.setAttribute("AdminUserSaveSuccess", "1");
		}
		response.sendRedirect("admin?action=AdminUser");
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String adminUserInsert(HttpSession session, String base)
			throws SQLException {
		String url;
		String strUserGroup = dataManager.getOptionsID(
				"select user_group_id, name from user_group", false, false,
				false, "");
		session.setAttribute("strUserGroup", strUserGroup);
		url = base + "AdminUserInsert.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void adminUserEditSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		String uid = (String) session.getAttribute("uid");
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setFirstName(request.getParameter("firstname"));
		user.setLastName(request.getParameter("lastname"));
		user.setEmail(request.getParameter("email"));
		user.setUser_group_id(request.getParameter("user_group_id"));
		user.setPassword(request.getParameter("password"));
		user.setStatus(Integer.valueOf(request.getParameter("status")));
		if (!user.getUsername().equalsIgnoreCase("")
				&& !user.getFirstName().equalsIgnoreCase("")
				&& !user.getLastName().equalsIgnoreCase("")) {
			dataManager.updateUser(uid, user);
			session.setAttribute("AdminUserSaveSuccess", "1");
		}
		response.sendRedirect("admin?action=AdminUser");
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String adminUserEdit(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String uid = request.getParameter("uid");
		User user = dataManager.getUserById(uid);
		session.setAttribute("user", user);
		String strUserGroup = dataManager.getOptionsID2(
				"select user_group_id, name from user_group", false, false,
				false, user.getUser_group_id());
		session.setAttribute("strUserGroup", strUserGroup);
		url = base + "AdminUserEdit.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void adminUserDelete(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		ArrayList users = (ArrayList) session.getAttribute("users");
		String[] StrChecked = new String[users.size()];
		for (int i = 0; i < users.size(); i++) {
			StrChecked[i] = request.getParameter(String.valueOf(i));
			if (StrChecked[i] != null && StrChecked[i].equalsIgnoreCase("true")) {
				dataManager.deleteUser(((User) users.get(i)).getUser_id());
			}
		}
		session.setAttribute("AdminUserSaveSuccess", "1");
		response.sendRedirect("admin?action=AdminUser");
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String adminUser(HttpServletRequest request, HttpSession session,
			String base) throws SQLException {
		String url;
		ArrayList users = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			users = dataManager.getListOfUser(1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			users = dataManager.getListOfUser(page);
		}
		int count = dataManager.getCountUser();
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("users", users);
		url = base + "AdminUser.jsp";
		return url;
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 */
	private String adminLogout(HttpSession session, String base) {
		String url;
		session.setAttribute("username", "");
		session.setAttribute("password", "");
		url = base + "AdminLogin.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String getSalesStatistic(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String range = request.getParameter("range");
		ArrayList<Integer> orderNos = new ArrayList<Integer>();
		ArrayList<Integer> customerNos = new ArrayList<Integer>();
		if (range.equalsIgnoreCase("day")) {
			for (int i = 1; i <= 24; i++) {
				int orderNo = 0;
				int customerNo = 0;
				orderNo = dataManager.getSalesStatisticInDay(i);
				orderNos.add(orderNo);
				customerNo = dataManager.getCustomersStatisticInDay(i);
				customerNos.add(customerNo);
			}
		} else if (range.equalsIgnoreCase("week")) {
		} else if (range.equalsIgnoreCase("month")) {
		} else if (range.equalsIgnoreCase("year")) {
		}
		session.setAttribute("orderNos", orderNos);
		session.setAttribute("customerNos", customerNos);
		url = base + "AdminSalesStatistic.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String catalogProvinceEdit(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String id = request.getParameter("id");
		tinhthanh province = dataManager.getProvinceById(id);
		session.setAttribute("province", province);
		url = base + "CatalogProvinceEdit.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String catalogProvince(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		ArrayList provinces = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			provinces = dataManager.getListOfProvince(1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			provinces = dataManager.getListOfProvince(page);
		}
		int count = dataManager.getCountProvince();
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("provinces", provinces);
		url = base + "CatalogProvince.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void catalogManufacturerDelete(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		ArrayList manus = (ArrayList) session.getAttribute("manus");
		String[] StrChecked = new String[manus.size()];
		for (int i = 0; i < manus.size(); i++) {
			StrChecked[i] = request.getParameter(String.valueOf(i));
			if (StrChecked[i] != null && StrChecked[i].equalsIgnoreCase("true")) {
				dataManager.deleteManufacturer(((Nhasanxuat) manus.get(i))
						.getMa_nhasx());
			}
		}
		session.setAttribute("CatalogManufacturerSaveSuccess", "1");
		response.sendRedirect("admin?action=CatalogManufacturer");
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void catalogManufacturerInsertSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		String tennhasx = request.getParameter("tennhasx");
		String image = request.getParameter("image");
		String url_link = request.getParameter("url_link");
		String mota = request.getParameter("mota");
		dataManager.insertNhasanxuat(tennhasx, image, mota, url_link);
		session.setAttribute("CatalogManufacturerSaveSuccess", "1");
		response.sendRedirect("admin?action=CatalogManufacturer");
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void catalogManufacturerSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		String id = request.getParameter("id");
		String tennhasx = request.getParameter("tennhasx");
		String image = request.getParameter("image");
		String url_link = request.getParameter("url_link");
		String mota = request.getParameter("mota");
		dataManager.updateNhasanxuat(id, tennhasx, image, mota, url_link);
		session.setAttribute("CatalogManufacturerSaveSuccess", "1");
		response.sendRedirect("admin?action=CatalogManufacturer");
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String catalogManufacturerEdit(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String id = request.getParameter("id");
		Nhasanxuat manu = dataManager.getManufacturerById(id);
		session.setAttribute("manu", manu);
		url = base + "CatalogManufacturerEdit.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String catalogManufacturer(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		ArrayList manus = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			manus = dataManager.getListOfManufacturer(1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			manus = dataManager.getListOfManufacturer(page);
		}
		int count = dataManager.getCountManufacturer();
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("manus", manus);
		url = base + "CatalogManufacturer.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String customerMail(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		ArrayList mails = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			mails = dataManager.getListOfMails(1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			mails = dataManager.getListOfMails(page);
		}
		int count = dataManager.getCountMails();
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("mails", mails);
		url = base + "CustomerMail.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String customerOrderPrint(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		url = customerOrderEdit(request, session, base);
		url = base + "CustomerOrderPrint.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	private void customerOrderEditSave(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		String id = request.getParameter("id");
		String status = request.getParameter("order_status");
		String deliverer = request.getParameter("deliverer");
		String comments = request.getParameter("comment");
		dataManager.updateOrderStatus(id, status, deliverer, comments);
		response.sendRedirect("admin?action=CustomerOrder");
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String customerOrderEdit(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String id = request.getParameter("id");
		HoaDon order = dataManager.getOrderById(id);
		session.setAttribute("order", order);
		ArrayList giohangs = dataManager.getGiohangById(order.getMaGioHang());
		session.setAttribute("giohangs", giohangs);
		url = base + "CustomerOrderEdit.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void customerOrderDelete(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		ArrayList orders = (ArrayList) session.getAttribute("orders");
		String[] StrChecked = new String[orders.size()];
		for (int i = 0; i < orders.size(); i++) {
			StrChecked[i] = request.getParameter(String.valueOf(i));
			if (StrChecked[i] != null && StrChecked[i].equalsIgnoreCase("true")) {
				dataManager.deleteOrder(((HoaDon) orders.get(i)).getMaHoaDon());
			}
		}
		session.setAttribute("CustomerOrderSaveSuccess", "1");
		response.sendRedirect("admin?action=CustomerOrder");
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String customerOrder(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		ArrayList orders = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			orders = dataManager.getListOfOrders(1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			orders = dataManager.getListOfOrders(page);
		}
		int count = dataManager.getCountOrders();
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("orders", orders);
		url = base + "CustomerOrder.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void customerResetSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		String makh = request.getParameter("makh");
		String password = request.getParameter("password");
		dataManager.resetPassword(makh, MD5.encode(password));
		session.setAttribute("CustomerSaveSuccess", "1");
		response.sendRedirect("admin?action=CustomerView");
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 */
	private String customerReset(HttpServletRequest request,
			HttpSession session, String base) {
		String url;
		String makh = request.getParameter("makh");
		String kh = request.getParameter("kh");
		session.setAttribute("makh", makh);
		session.setAttribute("kh", kh);
		url = base + "CustomerReset.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void customerInsertSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		Khachhang customer = new Khachhang();
		customer.setUsername(request.getParameter("username"));
		customer.setHokh(request.getParameter("firstname"));
		customer.setTenkh(request.getParameter("lastname"));
		customer.setPassword(MD5.encode(request.getParameter("password")));
		customer.setEmail(request.getParameter("email"));
		customer.setDienthoai(request.getParameter("telephone"));
		customer.setDiachi(request.getParameter("address"));
		customer.setHuyen(request.getParameter("district"));
		customer.setTinh(Integer.valueOf(request.getParameter("province")));
		customer.setdotuoi(Integer.valueOf(request.getParameter("age")));
		customer.setGioitinh(request.getParameter("sex"));
		customer.setma_Nghenghiep(Integer.valueOf(request.getParameter("job")));
		customer.setStatus(Integer.valueOf(request.getParameter("status")));
		dataManager.insertCustomer(customer);
		session.setAttribute("CustomerSaveSuccess", "1");
		response.sendRedirect("admin?action=CustomerView");
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String customerInsert(HttpSession session, String base)
			throws SQLException {
		String url;
		String strProvince = dataManager
				.getOptionsID(
						"select districtsCode, districtsName from districts order by districtsName",
						false, false, false, "");
		session.setAttribute("strProvince", strProvince);
		String strAge = dataManager.getOptionsID(
				"select ageCode, age from age order by age", false, false,
				false, "");
		session.setAttribute("strAge", strAge);
		String strJob = dataManager.getOptionsID(
				"select jobCode, jobName from job order by jobCode", false,
				false, false, "");
		session.setAttribute("strJob", strJob);
		url = base + "CustomerInsert.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void customerDelete(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		ArrayList customers = (ArrayList) session.getAttribute("customers");
		String[] StrChecked = new String[customers.size()];
		for (int i = 0; i < customers.size(); i++) {
			StrChecked[i] = request.getParameter(String.valueOf(i));
			if (StrChecked[i] != null && StrChecked[i].equalsIgnoreCase("true")) {
				dataManager.deleteCustomer(((Khachhang) customers.get(i))
						.getMakh());
			}
		}
		session.setAttribute("CustomerSaveSuccess", "1");
		response.sendRedirect("admin?action=CustomerView");
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String productsLaptopFilter(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		ArrayList laptops = new ArrayList();
		String filter_name = "";
		String filter_manu = "";
		String filter_quantity = "";
		String filter_status = "";
		if (request.getParameter("filter_name") != null) {
			filter_name = request.getParameter("filter_name");
		}
		if (request.getParameter("filter_manu") != null) {
			filter_manu = request.getParameter("filter_manu");
		}
		if (request.getParameter("filter_quantity") != null) {
			filter_quantity = request.getParameter("filter_quantity");
		}
		if (request.getParameter("filter_status") != null) {
			filter_status = request.getParameter("filter_status");
		}
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			laptops = dataManager.getProductsLaptopFilter(filter_name,
					filter_manu, filter_quantity, filter_status, 1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			laptops = dataManager.getCustomerFilter(filter_name, filter_manu,
					filter_quantity, filter_status, page);
		}
		int count = dataManager.getCountProductsLaptopFilter(filter_name,
				filter_manu, filter_quantity, filter_status);
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);

		session.setAttribute("filter_name", filter_name);
		session.setAttribute("filter_manu", filter_manu);
		session.setAttribute("filter_quantity", filter_quantity);
		session.setAttribute("filter_status", filter_status);
		session.setAttribute("laptops", laptops);
		url = base + "ProductsLaptopFilter.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String customerFilter(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		ArrayList khachhangs = new ArrayList();
		String filter_name = "";
		String filter_email = "";
		String filter_status = "";
		String filter_date_added = "";
		if (request.getParameter("filter_name") != null) {
			filter_name = request.getParameter("filter_name");
		}
		if (request.getParameter("filter_email") != null) {
			filter_email = request.getParameter("filter_email");
		}
		if (request.getParameter("filter_status") != null) {
			filter_status = request.getParameter("filter_status");
		}
		if (request.getParameter("filter_date_added") != null) {
			filter_date_added = request.getParameter("filter_date_added");
		}
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			khachhangs = dataManager.getCustomerFilter(filter_name,
					filter_email, filter_status, filter_date_added, 1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			khachhangs = dataManager.getCustomerFilter(filter_name,
					filter_email, filter_status, filter_date_added, page);
		}
		int count = dataManager.getCountCustomerFilter(filter_name,
				filter_email, filter_status, filter_date_added);
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);

		session.setAttribute("filter_name", filter_name);
		session.setAttribute("filter_email", filter_email);
		session.setAttribute("filter_status", filter_status);
		session.setAttribute("filter_date_added", filter_date_added);
		session.setAttribute("khachhangs", khachhangs);
		url = base + "CustomerFilter.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String customerView(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		ArrayList khachhangs = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			khachhangs = dataManager.getCustomerView(1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			khachhangs = dataManager.getCustomerView(page);
		}
		int count = dataManager.getCountCustomer();
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);

		session.setAttribute("khachhangs", khachhangs);
		url = base + "CustomerView.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String reportSalesFilter(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String startdate = request.getParameter("filter_date_start");
		String enddate = request.getParameter("filter_date_end");
		String groupby = request.getParameter("filter_group");
		String status = request.getParameter("filter_order_status");
		ArrayList sales = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			sales = dataManager.getListOfSales(startdate, enddate, groupby,
					status, 1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			sales = dataManager.getListOfSales(startdate, enddate, groupby,
					status, page);
		}
		int count = dataManager.getCountSales(startdate, enddate, groupby,
				status);
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("startdate", startdate);
		session.setAttribute("enddate", enddate);
		session.setAttribute("groupby", groupby);
		session.setAttribute("status", status);
		session.setAttribute("sales", sales);
		url = base + "ReportSales.jsp";
		return url;
	}

	/**
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	private void reportSales(HttpServletResponse response) throws SQLException,
			IOException {
		String currentdate = dataManager.getCurrentDate();
		String date = dataManager.getDate7ago();
		response.sendRedirect("admin?action=ReportSalesFilter&filter_date_start="
				+ date
				+ "&filter_date_end="
				+ currentdate
				+ "&filter_group=Days&filter_order_status=All Statuses");
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String reportProductViewed(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		ArrayList order_products = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			order_products = dataManager.getListOfViewedProducts(1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			order_products = dataManager.getListOfViewedProducts(page);
		}
		int sum = dataManager.getSumViewed();
		int count = dataManager.getCountViewedProducts();
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("sum", sum);
		session.setAttribute("order_products", order_products);
		url = base + "ReportProductViewed.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String reportProductPurchased(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		ArrayList order_products = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			order_products = dataManager.getListOfPurchasedProducts(1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			order_products = dataManager.getListOfPurchasedProducts(page);
		}
		int count = dataManager.getCountPurchasedProducts();
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("order_products", order_products);
		url = base + "ReportProductPurchased.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String adminUserSearch(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String keyword = request.getParameter("keyword");
		ArrayList khachhangs = new ArrayList();
		khachhangs = dataManager.getAdminUserSearch(keyword);
		session.setAttribute("khachhangs", khachhangs);
		url = base + "AdminUserView.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String adminResult(HttpServletRequest request, HttpSession session,
			String base) throws SQLException {
		String url;
		String sErr = "";
		String ho = request.getParameter("input_ho");
		if (ho == null || ho.equalsIgnoreCase("")) {
			sErr += "Chưa nhập họ người dùng.<br>";
		}
		String ten = request.getParameter("input_ten");
		if (ten == null || ten.equalsIgnoreCase("")) {
			sErr += "Chưa nhập tên người dùng.<br>";
		}
		String username = request.getParameter("input_username");
		if (username == null || username.equalsIgnoreCase("")) {
			sErr += "Chưa nhập tên đăng nhập.<br>";
		}
		String password = request.getParameter("input_password");
		if (password == null || password.equalsIgnoreCase("")) {
			sErr += "Chưa nhập mật khẩu.<br>";
		}
		if (!password.equals(request.getParameter("input_repassword"))) {
			sErr += "Mật khẩu và xác nhận mật khẩu khác nhau.<br>";
		}
		String diachi = request.getParameter("input_address");
		if (diachi == null || diachi.equalsIgnoreCase("")) {
			sErr += "Chưa nhập địa chỉ.<br>";
		}
		String huyen = request.getParameter("input_huyen");
		if (huyen == null || huyen.equalsIgnoreCase("")) {
			sErr += "Chưa nhập huyện.<br>";
		}
		String tinh = request.getParameter("select_tinh");
		String email = request.getParameter("input_email");
		if (email == null || email.equalsIgnoreCase("")) {
			sErr += "Chưa nhập email.<br>";
		}
		String dienthoai = request.getParameter("input_dienthoai");
		if (dienthoai == null || dienthoai.equalsIgnoreCase("")) {
			sErr += "Chưa nhập điện thoại.<br>";
		}
		String dotuoi = request.getParameter("select_dotuoi");
		String gioitinh = request.getParameter("select_gioitinh");
		String nghenghiep = request.getParameter("select_nghenghiep");
		if (!sErr.equalsIgnoreCase("")) {
			session.setAttribute("sErr", sErr);
			url = base + "AdminUserNew.jsp";
		} else {
			Khachhang kh = new Khachhang();
			kh.setHokh(ho);
			kh.setTenkh(ten);
			kh.setUsername(username);
			kh.setPassword(password);
			kh.setDiachi(diachi);
			kh.setHuyen(huyen);
			kh.setTinh(Integer.valueOf(dataManager.getMaTinhByTinh(tinh)));
			kh.setEmail(email);
			kh.setDienthoai(dienthoai);
			kh.setdotuoi(Integer.valueOf(dataManager
					.getMaDotuoiByDotuoi(dotuoi)));
			kh.setGioitinh(gioitinh);
			kh.setma_Nghenghiep(Integer.valueOf(dataManager
					.getMaNghenghiepByNghenghiep(nghenghiep)));
			dataManager.insertKhachhang(kh);
			url = base + "AdminUserResult.jsp";
		}
		return url;
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 */
	private String adminUserNew(HttpSession session, String base) {
		String url;
		String sql = "SELECT distinct districtsName from districts";
		String tinhthanhOptions = dataManager.getOptions(sql, false, false,
				false, "");
		session.setAttribute("tinhthanhOptions", tinhthanhOptions);
		sql = "SELECT distinct age from age";
		String dotuoiOptions = dataManager.getOptions(sql, false, false, false,
				"");
		session.setAttribute("dotuoiOptions", dotuoiOptions);
		sql = "SELECT distinct jobName from job";
		String nghenghiepOptions = dataManager.getOptions(sql, false, false,
				false, "");
		session.setAttribute("nghenghiepOptions", nghenghiepOptions);
		url = base + "AdminUserNew.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String adminUserCatalog(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		ArrayList khachhangs = new ArrayList();
		String id = request.getParameter("id");
		if (id.equalsIgnoreCase("user")) {
			khachhangs = dataManager.getNormalUser();
		} else if (id.equalsIgnoreCase("admin")) {
			khachhangs = dataManager.getAdministratorUser();
		}
		session.setAttribute("khachhangs", khachhangs);
		url = base + "AdminUserView.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 */
	private String getChart(HttpServletRequest request, HttpSession session,
			String base) {
		String url;
		String kind = request.getParameter("kind");
		String nhasx = request.getParameter("nhasx");
		String sp = request.getParameter("sp");
		if (kind.equals("1") || kind.equals("2")) {
			if (nhasx.equalsIgnoreCase("")) {
				String values = "9,6,7,9,5,7,6,9,9";
				session.setAttribute("values", values);
			}
		}
		url = base + "FlashChart.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String adminChartView(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String thongke = "";
		String daily = "";
		String loaisp = "";
		String nhasx = "";
		String sanpham = "";
		String year = "";
		String loaibieudo = "";
		String month = "";
		if (request.getParameter("selectThongke") != null
				&& !request.getParameter("selectThongke").equalsIgnoreCase("")) {
			thongke = request.getParameter("selectThongke");
			daily = request.getParameter("nameDaily");
			loaisp = request.getParameter("selectSpType");
			nhasx = request.getParameter("nameNhasx");
			sanpham = request.getParameter("sp");
			year = request.getParameter("selectYear");
			month = request.getParameter("selectMonth");
			loaibieudo = request.getParameter("chartType");
			session.setAttribute("chart", "yes");
			session.setAttribute("thongke", thongke);
			session.setAttribute("daily", daily);
			session.setAttribute("loaisp", loaisp);
			session.setAttribute("nhasx", nhasx);
			session.setAttribute("sanpham", sanpham);
			session.setAttribute("year", year);
			session.setAttribute("loaibieudo", loaibieudo);
			session.setAttribute("month", month);
		} else {
			session.setAttribute("chart", "no");
		}
		String sql = "select manufacturerCode, manufacturerName from manufacturer";
		String nhaSxResult = dataManager.getOptionsID(sql, false, false, false,
				"");
		session.setAttribute("nhaSxResult", nhaSxResult);
		url = base + "AdminChartView.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String dataMiningPurchasedFilter(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String filter_manu = "";
		String filter_product = "";
		String filter_purchased_product = "";
		if (request.getParameter("filter_manu") != null) {
			filter_manu = request.getParameter("filter_manu");
		}
		if (request.getParameter("filter_product") != null) {
			filter_product = request.getParameter("filter_product");
		}
		if (request.getParameter("filter_purchased_product") != null) {
			filter_purchased_product = request
					.getParameter("filter_purchased_product");
		}
		ArrayList rules = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			rules = dataManager.getListOfDMPurchased(filter_manu,
					filter_product, filter_purchased_product, 1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			rules = dataManager.getListOfDMPurchased(filter_manu,
					filter_product, filter_purchased_product, page);
		}
		int count = dataManager.getCountDMPurchased(filter_manu,
				filter_product, filter_purchased_product);
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		String strManu = dataManager
				.getOptions(
						"select distinct manufacturerName from manufacturer order by manufacturerName",
						false, false, false, filter_manu);
		session.setAttribute("strManu", strManu);
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("filter_manu", filter_manu);
		session.setAttribute("filter_product", filter_product);
		session.setAttribute("filter_purchased_product",
				filter_purchased_product);
		session.setAttribute("rules", rules);
		url = base + "DataMiningAssociationRules_Purchased.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String dataMiningViewedFilter(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String filter_manu = "";
		String filter_product = "";
		String filter_viewed_product = "";
		if (request.getParameter("filter_manu") != null) {
			filter_manu = request.getParameter("filter_manu");
		}
		if (request.getParameter("filter_product") != null) {
			filter_product = request.getParameter("filter_product");
		}
		if (request.getParameter("filter_viewed_product") != null) {
			filter_viewed_product = request
					.getParameter("filter_viewed_product");
		}
		ArrayList rules = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			rules = dataManager.getListOfDMViewed(filter_manu, filter_product,
					filter_viewed_product, 1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			rules = dataManager.getListOfDMViewed(filter_manu, filter_product,
					filter_viewed_product, page);
		}
		int count = dataManager.getCountDMViewed(filter_manu, filter_product,
				filter_viewed_product);
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		String strManu = dataManager
				.getOptions(
						"select distinct manufacturerName from manufacturer order by manufacturerName",
						false, false, false, filter_manu);
		session.setAttribute("strManu", strManu);
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("filter_manu", filter_manu);
		session.setAttribute("filter_product", filter_product);
		session.setAttribute("filter_viewed_product", filter_viewed_product);
		session.setAttribute("rules", rules);
		url = base + "DataMiningAssociationRules_Viewed.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void dataMiningLaptopEditSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		String sp = request.getParameter("sp");
		String filter_laptopClass = request.getParameter("filter_laptopClass");
		if (filter_laptopClass.equalsIgnoreCase("Game thu & Thiet ke")) {
			filter_laptopClass = "1";
		} else if (filter_laptopClass.equalsIgnoreCase("Doanh nhan")) {
			filter_laptopClass = "2";
		} else if (filter_laptopClass.equalsIgnoreCase("Hoc tap - Van phong")) {
			filter_laptopClass = "3";
		} else if (filter_laptopClass.equalsIgnoreCase("Giai tri Multimedia")) {
			filter_laptopClass = "4";
		}
		dataManager.updateDataMiningLaptop(sp, filter_laptopClass);
		session.setAttribute("DataMiningLaptopSaveSuccess", "1");
		response.sendRedirect("admin?action=DataMiningLaptop");
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 */
	private String dataMiningLaptopEdit(HttpServletRequest request,
			HttpSession session, String base) {
		String url;
		String sp = request.getParameter("sp");
		String filter_laptopClass = request.getParameter("filter_laptopClass");
		session.setAttribute("sp", sp);
		session.setAttribute("filter_laptopClass", filter_laptopClass);
		url = base + "DataMiningLaptopEdit.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String dataMiningLaptopFilter(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String filter_laptopName = "";
		String filter_laptopClass = "";
		String filter_laptopClass1 = "";
		if (request.getParameter("filter_laptopName") != null) {
			filter_laptopName = request.getParameter("filter_laptopName");
		}
		if (request.getParameter("filter_laptopClass") != null) {
			filter_laptopClass = request.getParameter("filter_laptopClass");
		}
		if (filter_laptopClass.equalsIgnoreCase("Game thu & Thiet ke")) {
			filter_laptopClass1 = "1";
		} else if (filter_laptopClass.equalsIgnoreCase("Doanh nhan")) {
			filter_laptopClass1 = "2";
		} else if (filter_laptopClass.equalsIgnoreCase("Hoc tap - Van phong")) {
			filter_laptopClass1 = "3";
		} else if (filter_laptopClass.equalsIgnoreCase("Giai tri Multimedia")) {
			filter_laptopClass1 = "4";
		}
		ArrayList laptops = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			laptops = dataManager.getListOfDMLaptop(filter_laptopName,
					filter_laptopClass1, 1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			laptops = dataManager.getListOfDMLaptop(filter_laptopName,
					filter_laptopClass1, page);
		}
		int count = dataManager.getCountDMLaptop(filter_laptopName,
				filter_laptopClass1);
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("filter_laptopName", filter_laptopName);
		session.setAttribute("filter_laptopClass", filter_laptopClass);
		session.setAttribute("laptops", laptops);
		url = base + "DataMiningLaptop.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param response
	 * @param session
	 * @throws SQLException
	 * @throws IOException
	 */
	private void dataMiningCustomerEditSave(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
		String makh = request.getParameter("makh");
		String filter_class = request.getParameter("filter_class");
		if (filter_class.equalsIgnoreCase("Not Potential")) {
			filter_class = "level 1";
		} else if (filter_class.equalsIgnoreCase("Rather Potential")) {
			filter_class = "level 2";
		} else if (filter_class.equalsIgnoreCase("Normal")) {
			filter_class = "level 3";
		} else if (filter_class.equalsIgnoreCase("Potential")) {
			filter_class = "level 4";
		}
		dataManager.updateDataMiningCustomer(makh, filter_class);
		session.setAttribute("DataMiningCustomerSaveSuccess", "1");
		response.sendRedirect("admin?action=DataMiningCustomer");
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 */
	private String dataMiningCustomerEdit(HttpServletRequest request,
			HttpSession session, String base) {
		String url;
		String makh = request.getParameter("makh");
		String kh = request.getParameter("kh");
		String filter_class = request.getParameter("filter_class");
		session.setAttribute("makh", makh);
		session.setAttribute("kh", kh);
		session.setAttribute("filter_class", filter_class);
		url = base + "DataMiningCustomerEdit.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String dataMiningCustomerFilter(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String filter_customerID = "";
		String filter_customerName = "";
		String filter_class = "";
		String filter_class1 = "";
		if (request.getParameter("filter_customerID") != null) {
			filter_customerID = request.getParameter("filter_customerID");
		}
		if (request.getParameter("filter_customerName") != null) {
			filter_customerName = request.getParameter("filter_customerName");
		}
		if (request.getParameter("filter_class") != null) {
			filter_class = request.getParameter("filter_class");
		}
		if (filter_class.equalsIgnoreCase("Not Potential")) {
			filter_class1 = "level 1";
		} else if (filter_class.equalsIgnoreCase("Rather Potential")) {
			filter_class1 = "level 2";
		} else if (filter_class.equalsIgnoreCase("Normal")) {
			filter_class1 = "level 3";
		} else if (filter_class.equalsIgnoreCase("Potential")) {
			filter_class1 = "level 4";
		}
		ArrayList khachhangs = new ArrayList();
		int page = 1;
		if (request.getParameter("page") == null
				|| Integer.valueOf(request.getParameter("page")) == 1) {
			khachhangs = dataManager.getListOfDMCustomer(filter_customerID,
					filter_customerName, filter_class1, 1);
		} else {
			page = Integer.valueOf(request.getParameter("page"));
			khachhangs = dataManager.getListOfDMCustomer(filter_customerID,
					filter_customerName, filter_class1, page);
		}
		int count = dataManager.getCountDMCustomer(filter_customerID,
				filter_customerName, filter_class1);
		int numberOfPage = 0;
		if (count % 10 == 0) {
			numberOfPage = count / 10;
		} else {
			numberOfPage = count / 10 + 1;
		}
		session.setAttribute("page", page);
		session.setAttribute("numberOfPage", numberOfPage);
		session.setAttribute("count", count);
		session.setAttribute("filter_customerID", filter_customerID);
		session.setAttribute("filter_customerName", filter_customerName);
		session.setAttribute("filter_class", filter_class);
		session.setAttribute("khachhangs", khachhangs);
		url = base + "DataMiningCustomer.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String adminRuleUpdated(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String type = request.getParameter("type");
		String stt = request.getParameter("stt");
		String rightRuleTxt = request.getParameter("rightRuleTxt");
		Rule rule = new Rule();
		if (type.equalsIgnoreCase("viewed")) {
			dataManager.updateRuleByStt(stt, rightRuleTxt);
			rule = dataManager.getRuleByStt(stt);
			session.setAttribute("rule", rule);
		} else if (type.equalsIgnoreCase("purchased")) {
			dataManager.updatePurchasedRuleByStt(stt, rightRuleTxt);
			rule = dataManager.getPurchasedRuleByStt(stt);
			session.setAttribute("rule", rule);
		}
		url = base + "AdminRulePreviewPopup.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 */
	private String getProductByProducer(HttpServletRequest request,
			HttpSession session, String base) {
		String url;
		String producerId = request.getParameter("producerId");
		if (!producerId.equalsIgnoreCase("")) {
			String sql = "select productCategoryCode from productCategory where manufacturerCode="
					+ producerId;
			String productListOptions = dataManager.getOptions(sql, false,
					false, false, "");
			session.setAttribute("productListOptions", productListOptions);
		} else {
			session.setAttribute("productListOptions", "");
		}
		url = base + "AdminGetProductByProducer.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String adminRuleEditPopup(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String sql = "select manufacturerCode, manufacturerName from manufacturer";
		String nhaSxResult = dataManager.getOptionsID(sql, false, false, false,
				"");
		session.setAttribute("nhaSxResult", nhaSxResult);

		String type = request.getParameter("type");
		session.setAttribute("type", type);

		String stt = request.getParameter("stt");
		Rule rule = new Rule();
		if (type.equalsIgnoreCase("viewed")) {
			rule = dataManager.getRuleByStt(stt);
		} else if (type.equalsIgnoreCase("purchased")) {
			rule = dataManager.getPurchasedRuleByStt(stt);
		}
		ArrayList<String> rightList = new ArrayList<String>();
		String rightRule = rule.getRightRule();
		int start = 0;
		int end = 0;
		while (true) {
			if ((rightRule.indexOf(", ", start)) != -1) {
				end = rightRule.indexOf(", ", start);
				rightList.add(rightRule.substring(start, end));
				start = end + 2;
			} else {
				rightList.add(rightRule.substring(start));
				break;
			}
		}
		session.setAttribute("rightList", rightList);
		url = base + "AdminRuleEditPopup.jsp";
		return url;
	}

	private String getRulesForProduct(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String product = request.getParameter("product");
		ArrayList<Rule> ruleList = dataManager.getRulesByProduct(product);
		session.setAttribute("ruleList", ruleList);
		url = base + "AdminGetRuleList.jsp";
		return url;
	}

	private String getProducts(HttpServletRequest request, HttpSession session,
			String base) {
		String url;
		String producerId = request.getParameter("producerId");
		if (!producerId.equalsIgnoreCase("")) {
			String sql = "select productCategoryCode from productCategory where manufacturerCode="
					+ producerId;
			String productListOptions = dataManager.getOptions(sql, false,
					false, false, "");
			session.setAttribute("productListOptions", productListOptions);
		} else {
			session.setAttribute("productListOptions", "");
		}
		url = base + "AdminGetProductForChart.jsp";
		return url;
	}

	private String getProductListByProducer(HttpServletRequest request,
			HttpSession session, String base) {
		String url;
		String producerId = request.getParameter("producerId");
		if (!producerId.equalsIgnoreCase("")) {
			String sql = "select productCategoryCode from productCategory where manufacturerCode="
					+ producerId;
			String productListOptions = dataManager.getOptions(sql, false,
					false, false, "");
			session.setAttribute("productListOptions", productListOptions);
		} else {
			session.setAttribute("productListOptions", "");
		}
		url = base + "AdminGetProductList.jsp";
		return url;
	}

	private String dataMiningAssociationRules(HttpSession session, String base)
			throws SQLException {
		String url;
		String sql = "select manufacturerCode, manufacturerName from manufacturer";
		String nhaSxResult = dataManager.getOptionsID(sql, false, false, false,
				"");
		session.setAttribute("nhaSxResult", nhaSxResult);
		url = base + "DataMiningAssociationRules.jsp";
		return url;
	}

	private String checkUser(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, String base,
			String url) throws IOException, SQLException {
		String[] PermissionList = new String[100];
		int checkUser = 0;
		String username = "";
		String password = "";
		if (request.getParameter("username") != null) {
			username = request.getParameter("username");
		}
		if (request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
		if (username.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
			if (session.getAttribute("username") == null
					|| ((String) session.getAttribute("username"))
							.equalsIgnoreCase("")
					|| session.getAttribute("username") == null
					|| ((String) session.getAttribute("username"))
							.equalsIgnoreCase("")) {
				url = adminLogout(session, base);
			} else {
				response.sendRedirect("admin?action=index");
			}
		} else {
			checkUser = dataManager.checkUser(username, password);
			if (checkUser == 1) {
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				// PermissionList = dataManager.splitPermission(username);
				session.setAttribute("PermissionList", PermissionList);
				response.sendRedirect("admin?action=index");
			} else {
				url = adminLogout(session, base);
			}
		}
		return url;
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
