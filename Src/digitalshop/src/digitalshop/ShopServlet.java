package digitalshop;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import digitalshop.beans.GioHang;
import digitalshop.beans.HoaDon;
import digitalshop.beans.Khachhang;
import digitalshop.beans.Laptop;
import digitalshop.beans.MD5;
import digitalshop.beans.Mail;
import digitalshop.beans.Phukien;
import digitalshop.beans.Sanphamchon;
import digitalshop.beans.SpMuaCung;
import digitalshop.beans.Thanhtoan;
import digitalshop.model.DataManager;
import digitalshop.model.KhachhangPeer;

public class ShopServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;
	DataManager dataManager = new DataManager();

	public ShopServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		System.out.println("*** initializing controller servlet.");
		super.init(config);

		// DataManager dataManager = new DataManager();
		dataManager.setDbURL(config.getInitParameter("dbURL"));
		dataManager.setDbUserName(config.getInitParameter("dbUserName"));
		dataManager.setDbPassword(config.getInitParameter("dbPassword"));

		ServletContext context = config.getServletContext();
		context.setAttribute("base", config.getInitParameter("base"));
		context.setAttribute("imageURL", config.getInitParameter("imageURL"));
		context.setAttribute("dataManager", dataManager);

		try { // load the database JDBC driver

			Class.forName(config.getInitParameter("jdbcDriver"));
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String base = "/laptop/";
		String url = base + "index.jsp";

		try {

			String type = request.getParameter("type");

			if (type != null
					&& (type.equals("laptop") || type.equals("miningProduct"))) {
				// if (type != null && type.equals("laptop")) {
				int rcd = Integer.parseInt(request.getParameter("record"));
				int off = Integer.parseInt(request.getParameter("offset"));
				double strang1 = Double.parseDouble(request
						.getParameter("sotrang"));
				int strang = (int) strang1;
				int sluong = Integer.parseInt(request.getParameter("total"));
				String localshow = (String) request.getParameter("localshow");
				Object xmlHttp = (Object) request.getParameter("xmlHttp");
				// /
				int sta = (off - 1) * rcd;

				url = base + "getdata.jsp";
				ArrayList laptops = new ArrayList();
				if (type.equals("laptop")) {
					laptops = dataManager.getSpecialProducts(sta, rcd);
				} else if (type.equals("miningProduct")) {
					laptops = dataManager.getMiningProducts(sta, rcd);
				}
				session.setAttribute("Specialproduct", laptops);
				session.setAttribute("records", rcd);
				session.setAttribute("offsets", off);
				session.setAttribute("totals", sluong);
				session.setAttribute("sotrangs", strang);
				session.setAttribute("type", type);
				session.setAttribute("localshow", localshow);
				session.setAttribute("xmlHttp", xmlHttp);

			} else {

				String action = request.getParameter("action");
				String tiente = request.getParameter("tiente");
				if (action != null) {
					if (action.equals("search")) {
						url = search(base);
					} else if (action.equals("SPXemCung")) {
						url = spXemCung(base);
					} else if (action.equals("getProManu")) {
						url = getProManu(request, session, base);
					} else if (action.equals("selectCatalog")) {
						url = selectCatalog(request, session, base);

					} else if (action.equals("selectCatalogPhuKien")) {
						url = selectCatalogPhuKien(request, session, base);

					} else if (action.equals("detailPhukien")) {
						url = detailPhukien(request, session, base);
					} else if (action.equals("laptopDetails")) {
						url = laptopDetails(request, session, base);
					} else if (action
							.matches("(showCart|(add|update|delete)Item)")
							|| action.equals("addPK")) {

						Hashtable<String, Sanphamchon> shoppingCart = (Hashtable) session
								.getAttribute("shoppingCart");
						Hashtable<String, SpMuaCung> ListSpMuaCung = (Hashtable) session
								.getAttribute("ListSpMuaCung");
						if (shoppingCart == null) {
							shoppingCart = new Hashtable<String, Sanphamchon>(
									10);
						}
						if (ListSpMuaCung == null) {
							ListSpMuaCung = new Hashtable<String, SpMuaCung>(10);
						}

						if (action != null && action.equals("addItem")) {
							addItem(request, session, shoppingCart,
									ListSpMuaCung);
						}
						if (action != null && action.equals("addPK")) {
							addPK(request, session, shoppingCart);
						}
						if (action != null && action.equals("updateItem")) {

							updateItem(request, session, shoppingCart);

						}
						if (action != null && action.equals("deleteItem")) {
							deleteItem(request, shoppingCart);
						}
						url = base + "AddShoppingCart.jsp";

					} else if (action.equals("checkOut")) {

						url = checkOut(session, base);
					} else if (action.equals("orderConfirmation")) {
						url = orderConfirmation(base);
					} else if (action.equals("completed")) {
						url = completed(request, session, base);
					} else if (action.equals("myAccount")) {

						url = myAccount(session, base);
						// url = base + "MyAccount.jsp";
					
					} 
					else if (action.equals("advancedSearch")) {
						url = base + "AdvancedSearch.jsp";
					} 
					else if (action.equals("ContactUs")) {
						url = base + "ContactUs.jsp";
					} else if (action.equals("selectTinhthanh")) {
						url = selectTinhthanh(session, base);
					} else if (action.equals("selectNghenghiep")) {
						url = selectNghenghiep(session, base);
					} else if (action.equals("selectDotuoi")) {
						url = selectDotuoi(session, base);

					} else if (action.equals("signup")) {
						url = signup(session, base);

					} // /*********************Quick
						// Search*****************************//
					else if (action.equals("QuickSearch")) {
						url = quickSearch(request, session, base);

					} // **********************End Quick
						// Search************************//
					else if (action.equals("advanceSearch")) {
						url = advanceSearch(request, session, base);
					} else if (action.equals("saveRegistration")
							|| action.equals("updateKhachhang")) {
						String userID = (String) session.getAttribute("UserID");
						String sRegErr = "";

						String username = "";
						String password = "";
						String password2 = "";
						String tenkh = "";
						String hokh = "";
						String email = "";
						String diachi = "";
						String huyen = "";
						String tinh = "0";
						String dienthoai = "";
						String dotuoi = "0";
						String gioitinh = "M";
						String ma_nghenghiep = "0";

						// Load all form fields into variables

						if (action.equals("saveRegistration"))
							;
						{
							password = (String) request
									.getParameter("member_password");
							password2 = (String) request
									.getParameter("member_password2");
						}
						username = (String) request
								.getParameter("member_username");
						tenkh = (String) request.getParameter("last_name");
						hokh = (String) request.getParameter("first_name");
						email = (String) request.getParameter("email");
						diachi = (String) request.getParameter("address");
						dienthoai = (String) request.getParameter("phone");
						huyen = (String) request.getParameter("huyen");
						String tinhle = (String) request.getParameter("tinh");
						if (tinhle != null && !tinhle.equals("")) {
							tinh = tinhle;
						}
						if (request.getParameter("gioitinh") != null
								&& !((String) request.getParameter("gioitinh"))
										.equals("")) {
							gioitinh = (String) request
									.getParameter("gioitinh");
						}
						if (request.getParameter("dotuoi") != null
								&& !((String) request.getParameter("dotuoi"))
										.equals("")) {
							dotuoi = (String) request.getParameter("dotuoi");
						}
						if (request.getParameter("nghenghiep") != null
								&& !((String) request
										.getParameter("nghenghiep")).equals("")) {
							ma_nghenghiep = (String) request
									.getParameter("nghenghiep");
						}

						if (action.equals("saveRegistration")) {
							if (!dataManager.isEmpty(username)) {
								if (!dataManager.kiemtratrung(username)) {
									sRegErr = sRegErr
											+ "Username này đã tồn tại.<br>";
								}
							}
						}

						if (sRegErr.length() == 0) {
							Khachhang khachhang = new Khachhang();
							khachhang.setUsername(username);
							if (action.equals("saveRegistration")) {
								khachhang.setPassword(MD5.encode(password));
							}
							khachhang.setTenkh(tenkh);
							khachhang.setHokh(hokh);
							khachhang.setEmail(email);
							khachhang.setTinh(Integer.parseInt(tinh));
							khachhang.setHuyen(huyen);
							khachhang.setGioitinh(gioitinh);
							khachhang.setdotuoi(Integer.parseInt(dotuoi));
							khachhang.setDienthoai(dienthoai);
							khachhang.setDiachi(diachi);
							khachhang.setma_Nghenghiep(Integer
									.parseInt(ma_nghenghiep));
							if (action.equals("saveRegistration")) {
								dataManager.insertKhachhang(khachhang);
							} else {
								dataManager.updateKhachhang(khachhang, userID);
							}
							session.setAttribute("username",
									khachhang.getUsername());
							url = base + "Result.jsp";

						} else {
							session.setAttribute("loginerr", sRegErr);
							url = base + "Registration.jsp";
						}

					} else if (action.equals("changePass")) {
						url = base + "ChangePass.jsp";
					} else if (action.equals("changePass1")) {
						url = changePass1(request, session, base);
					} else if (action.equals("CheckOut")) {
						checkOut(request);

					} else if (action.equals("login")) {

						url = login(request, session, base);

					} else if (action.equals("denghi")) {
						url = denghi(session, base);
					} else if (action.equals("laphome")) {
						url = base + "personality.jsp";
					} else if (action.equals("donhang")) {
						url = donhang(session, base);
					} else if (action.contains("khuyenmai")) {
						url = khuyenmai(session, base);
					} else if (action.equals("logout")) {
						url = logout(session, base);
					} else if (action.equals("compareLap")) {
						url = compareLap(request, session, base);
					} else if (action.equals("lichsugd")) {// mua
						url = lichsugd(request, session, base);
					} else if (action.equals("lichsugdmua")) {// xem

						url = lichsugdmua(request, session, base);
					} else if (action.equals("mailkh")) {
						url = mailkh(request, session, base);
					} else if (action.equals("sosanhkythuat")) {
						url = sosanhkythuat(request, session, base);
					} else if (action.equals("classification")) {
						// phan trang
						url = classification(request, session, base);

					} else // action = index
					{
						long currenttime = System.currentTimeMillis();

						Timestamp time = new Timestamp(currenttime);
						session.setAttribute("time", time);
					}
				}
			}
		} catch (SQLException error) {
			System.out.println(error.getMessage());
		} finally {
			RequestDispatcher requestDispatcher = getServletContext()
					.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		}

	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String classification(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		int numProInPage = Integer.parseInt((String) request
				.getParameter("recordClass"));
		int currPage = Integer.parseInt((String) request
				.getParameter("offsetClass"));
		int numPages = (Integer.parseInt((String) request
				.getParameter("sotrangClass")));
		String id = request.getParameter("idclass");

		int from = (currPage - 1) * numProInPage + 1;
		int to = numProInPage * currPage;

		String classId = "";
		if (id.equals("1")) {
			classId = "Sản phẩm dành cho Game thủ và thiết kế đồ họa";
		} else if (id.equals("2")) {
			classId = "Sản phẩm dành cho doanh nhân";
		} else if (id.equals("3")) {
			classId = "Sản phẩm dành cho học tập và văn phòng";
		} else if (id.equals("4")) {
			classId = "Sản phẩm dành cho giải trí";
		}

		ArrayList listLapClass = dataManager.getLaptopClassification(id, from,
				to);

		session.setAttribute("numProInPage", numProInPage);
		session.setAttribute("currPage", currPage);

		session.setAttribute("numPages", numPages);
		session.setAttribute("titleClass", classId);
		session.setAttribute("listLapClass", listLapClass);
		session.setAttribute("id", id);
		url = base + "ClassificationLaptop.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String sosanhkythuat(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String lapmain = request.getParameter("lapmain");
		String lapsecond = request.getParameter("lapsecond");
		Laptop infoLapmain = dataManager.getLaptopByID(lapmain);
		Laptop infoLapsecond = dataManager.getLaptopByID(lapsecond);
		session.setAttribute("infoLapmain", infoLapmain);
		session.setAttribute("infoLapsecond", infoLapsecond);
		url = base + "SosanhLaptop.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String mailkh(HttpServletRequest request, HttpSession session,
			String base) throws SQLException {
		String url;
		String emailUser = request.getParameter("emailUser");
		String mailContent = request.getParameter("mailContent");
		String userId = (String) session.getAttribute("UserID");
		String makh = "";
		if (userId != null) {
			makh = (KhachhangPeer.getthongtinkhachhang(dataManager, userId))
					.getMakh();
		}
		Mail mailcustomer = new Mail(emailUser, mailContent, makh);
		boolean flag = dataManager.insertMail(mailcustomer);
		String status = "";
		if (flag) {
			status = "true";
		} else {
			status = "false";
		}
		session.setAttribute("statusmail", status);
		url = base + "ContactUs.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String lichsugdmua(HttpServletRequest request, HttpSession session,
			String base) throws SQLException {
		String url;
		String username = request.getParameter("username");
		String col = request.getParameter("col");
		String order = request.getParameter("order");
		ArrayList arrListHistory = dataManager.getLichsuGiaodich(username, col,
				order);
		session.setAttribute("arrListHistory", arrListHistory);
		session.setAttribute("focus", "xem");
		url = base + "LichsuGiaodich.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String lichsugd(HttpServletRequest request, HttpSession session,
			String base) throws SQLException {
		String url;
		String userId = (String) session.getAttribute("UserID");
		String makh = (KhachhangPeer.getthongtinkhachhang(dataManager, userId))
				.getMakh();
		String col = request.getParameter("col");
		String order = request.getParameter("order");
		ArrayList listSPMua = dataManager.getSanPhamGiaoDich(makh, "Completed",
				col, order);
		session.setAttribute("arrListHistory", listSPMua);
		session.setAttribute("focus", "mua");
		url = base + "LichsuGiaodich.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String compareLap(HttpServletRequest request, HttpSession session,
			String base) throws SQLException {
		String url;
		String gia = request.getParameter("gia");
		String sp = request.getParameter("sp");
		ArrayList arrSimilarSap = dataManager.getLaptopPriceSimilar(gia);
		Laptop lapmain = dataManager.getLaptopByID(sp);
		session.setAttribute("arrSimilarSap", arrSimilarSap);
		session.setAttribute("lapmain", lapmain);
		url = base + "compareLap.jsp";
		return url;
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 */
	private String logout(HttpSession session, String base) {
		String url;
		session.setAttribute("priv", "");
		session.setAttribute("UserID", "");
		url = base + "index.jsp";
		return url;
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String khuyenmai(HttpSession session, String base)
			throws SQLException {
		String url;
		String userId = (String) session.getAttribute("UserID");
		String makh = (KhachhangPeer.getthongtinkhachhang(dataManager, userId))
				.getMakh();
		String levelKH = dataManager.getLevelByIDCustomer(makh);
		ArrayList listSPMua = dataManager.getSanPhamGiaoDich(makh, "Completed",
				"billDateCreated", "desc");
		ArrayList km = dataManager.getSPKhuyenMai(levelKH);
		session.setAttribute("khuyenmai", km);
		session.setAttribute("listSPMua", listSPMua);
		url = base + "khuyenmai.jsp";
		return url;
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String donhang(HttpSession session, String base)
			throws SQLException {
		String url;
		String userId = (String) session.getAttribute("UserID");
		String makh = (KhachhangPeer.getthongtinkhachhang(dataManager, userId))
				.getMakh();
		ArrayList<HoaDon> listHoaDon = (ArrayList<HoaDon>)dataManager.getHoaDonByMaKH(makh, "Processing");
		
		session.setAttribute("listHoaDon", listHoaDon);
		url = base + "DonHang.jsp";
		return url;
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String denghi(HttpSession session, String base) throws SQLException {
		String url;
		ArrayList denghi = dataManager.getMiningProducts(1, 12);
		session.setAttribute("sanphamdenghi", denghi);
		url = base + "Sanphamdenghi.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String login(HttpServletRequest request, HttpSession session,
			String base) throws SQLException {
		String url;
		String ma_kh = "";
		String admin_id = "";
		String sLoginErr = "";
		String sUsername = request.getParameter("username");
		String sPassword = request.getParameter("password");
		ma_kh = dataManager.kiemtrathongtin(sUsername, MD5.encode(sPassword));
		ArrayList<?> arr = dataManager.getSpxemnhieunhat();

		if (!ma_kh.equals("")) {
			// Login and password passed
			// add thong tin vao log table
			dataManager.insertAccountLog(ma_kh, sUsername);
			String level = dataManager.getLevelByIDCustomer(ma_kh);
			ArrayList<Laptop> arrLapByLevel = dataManager
					.getLaptopByLevel(level);

			session.setAttribute("UserID", sUsername);
			session.setAttribute("UserRights", sPassword);
			session.setAttribute("ma_kh", ma_kh);
			session.setAttribute("priv", "khachhang");
			session.setAttribute("sanphamnoibat", arr);
			session.setAttribute("arrLapByLevel", arrLapByLevel);
			url = base + "personality.jsp";

		} else {
			admin_id = dataManager.kiemtraadmin(sUsername, sPassword);
			if (!admin_id.equals("")) {
				session.setAttribute("UserID", sUsername);
				session.setAttribute("UserRights", sPassword);
				session.setAttribute("priv", "quantri");

			} else {
				sLoginErr = "Username hoặc Password bạn nhập vào chưa đúng.";
				session.setAttribute("incorrect", sLoginErr);
				session.setAttribute("priv", "none");
			}
			url = base + "index.jsp";
		}

		// url = base + "test.jsp";
		return url;
	}

	/**
	 * @param request
	 */
	private void checkOut(HttpServletRequest request) {
		String sCheckErr = "";
		String tennguoinhan = "";
		String diachi = "";
		String huyen = "";
		int tinh = 0;
		String email = "";
		String phone = "";
		String hinhthucthanhtoan = "";
		String masothe = "";
		String tentrenthe = "";
		String ngayhethan = "";

		tennguoinhan = request.getParameter("tennguoinhan");
		diachi = request.getParameter("address");
		huyen = request.getParameter("huyen");
		if (request.getParameter("tinh").equals("")) {
			tinh = 0;
		} else {
			tinh = Integer.parseInt(request.getParameter("tinh"));
		}
		email = request.getParameter("email");
		phone = request.getParameter("phone");
		hinhthucthanhtoan = request.getParameter("hinhthucthanhtoan");
		tentrenthe = request.getParameter("tentrenthe");
		masothe = request.getParameter("mathe");
		ngayhethan = request.getParameter("ngayhethan");

		if (dataManager.isEmpty(tennguoinhan)) {
			sCheckErr = sCheckErr
					+ "The value in field Ho Ten is required.<br>";
		}
		if (dataManager.isEmpty(diachi)) {
			sCheckErr = sCheckErr
					+ "The value in field Confirm Dia Chi is required.<br>";
		}
		if (dataManager.isEmpty(huyen)) {
			sCheckErr = sCheckErr
					+ "The value in field  Quan /Huyen is required.<br>";
		}
		if (dataManager.isEmpty(tinh)) {
			sCheckErr = sCheckErr + "The value in field Tinh is required.<br>";
		}
		if (dataManager.isEmpty(phone)) {
			sCheckErr = sCheckErr + "The value in field Phone is required.<br>";
		}
		if (dataManager.isEmpty(hinhthucthanhtoan)) {
			sCheckErr = sCheckErr
					+ "The value in field Hinh thuc thanh toan is required.<br>";
		}
		if (dataManager.isEmpty(masothe)) {
			sCheckErr = sCheckErr
					+ "The value in field Ma so the is required.<br>";
		}
		if (dataManager.isEmpty(tentrenthe)) {
			sCheckErr = sCheckErr
					+ "The value in field Ten tren the is required.<br>";
		}
		// if (ngayhethan == null) {
		// sCheckErr = sCheckErr +
		// "The value in field Ngay het han is required.<br>";
		// }

		if (sCheckErr.length() != 0) {
			Thanhtoan thanhtoan = new Thanhtoan();
			thanhtoan.setDiachinhan(diachi);
			thanhtoan.setDienthoai_nguoinhan(phone);
			thanhtoan.setHinhthuc_thanhtoan(hinhthucthanhtoan);
			thanhtoan.setHuyen(huyen);
			thanhtoan.setMa_thetindung(masothe);
			String[] split = ngayhethan.split("/");

			Date hethan = new Date(Integer.valueOf(split[2]) - 1900,
					Integer.valueOf(split[1]) - 1, Integer.valueOf(split[0]));

			thanhtoan.setNgayhethan(hethan);
			thanhtoan.setTen_trenthe(tentrenthe);
			thanhtoan.setTennguoinhan(tennguoinhan);
			thanhtoan.setTinh(tinh);
			thanhtoan.setEmail(email);

		}
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String changePass1(HttpServletRequest request, HttpSession session,
			String base) throws SQLException {
		String url;
		String userID = (String) session.getAttribute("UserID");
		String newpass = (String) request.getParameter("new_password");
		String oldpass = (String) request.getParameter("old_password");
		String kq = dataManager.kiemtrathongtin(userID, MD5.encode(oldpass));
		if (!kq.equals("")) {
			session.setAttribute("result", "1");
			if (dataManager.ChangePass(userID, MD5.encode(newpass))) {
				session.setAttribute("resultChange", "1");
			} else {
				session.setAttribute("resultChange", "0");
			}
		} else {
			session.setAttribute("result", "-1");

		}
		session.setAttribute("changePassword", "changePass");
		session.setAttribute("userId", userID);
		url = base + "Result.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String advanceSearch(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String tukhoa = (String) request.getParameter("tukhoa");
		String ma_dongSp = (String) request.getParameter("dongSP");
		System.out.println(tukhoa);
		String ma_loaiSp = (String) request.getParameter("ma_loaiSp");
		// String ma_tenSp = (String)
		// request.getParameter("ma_tenSp");
		String ma_giaMin = (String) request.getParameter("ma_giaMin");
		String ma_giaMax = (String) request.getParameter("ma_giaMax");
		String RAM = (String) request.getParameter("RAM");
		String CPU = (String) request.getParameter("CPU");
		String ma_dotuoi = (String) request.getParameter("ma_dotuoi");
		String ma_nghenghiep = (String) request.getParameter("ma_nghenghiep");

		ArrayList resultSearch1 = dataManager.quickSearch(tukhoa, ma_dongSp,
				"", ma_loaiSp, ma_giaMin, ma_giaMax);
		ArrayList result = new ArrayList();
		ArrayList resultSearch = new ArrayList();
		if (!ma_loaiSp.equals("Mục đích sử dụng")) {
			resultSearch1 = dataManager.FilterByClassification(resultSearch1,
					ma_loaiSp);
		}
		if (!CPU.equals("Chọn cấu hình CPU")) {
			resultSearch1 = dataManager.FilterByCPU(resultSearch1, CPU);
		}
		if (!RAM.equals("Chọn dung lượng RAM")) {
			resultSearch1 = dataManager.FilterByRAM(resultSearch1, RAM);
		}
		if (!ma_nghenghiep.equals("Chọn nghề nghiệp")) {
			resultSearch1 = dataManager.FilterByJob(resultSearch1,
					ma_nghenghiep);
		}
		// else {
		// resultSearch =
		// dataManager.getPhanNhomSpSearch(resultSearch1);
		// }
		resultSearch = dataManager.getPhanNhomSpSearch(resultSearch1);
		String tygia = dataManager.getTiente("USD").getGiatri();
		session.setAttribute("resultSearch", resultSearch);
		session.setAttribute("tygia", tygia);
		url = base + "ResultSearch.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String quickSearch(HttpServletRequest request, HttpSession session,
			String base) throws SQLException {
		String url;
		String tukhoa = (String) request.getParameter("tukhoa");
		// String ma_dongSp = (String)
		// request.getParameter("ma_dongSp");
		// String ma_loaiSp = (String)
		// request.getParameter("ma_loaiSp");
		// String ma_tenSp = (String)
		// request.getParameter("ma_tenSp");
		// String ma_giaMin = (String)
		// request.getParameter("ma_giaMin");
		// String ma_giaMax = (String)
		// request.getParameter("ma_giaMax");
		ArrayList resultSearch1 = dataManager.quickSearch(tukhoa, "", "", "",
				"", "");

		ArrayList resultSearch = new ArrayList();

		resultSearch = dataManager.getPhanNhomSpSearch(resultSearch1);

		String tygia = dataManager.getTiente("USD").getGiatri();
		session.setAttribute("resultSearch", resultSearch);
		session.setAttribute("tygia", tygia);
		url = base + "ResultSearch.jsp";
		return url;
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String signup(HttpSession session, String base) throws SQLException {
		String url;
		String sql = "SELECT districtsCode, districtsName FROM districts";
		String tinhOptions = dataManager.getOptionsID(sql, false, false, false,
				"");
		session.setAttribute("tinhOptions", tinhOptions);
		sql = "SELECT ageCode, age FROM age";
		String dotuoiOptions = dataManager.getOptionsID(sql, false, false,
				false, "");
		session.setAttribute("dotuoiOptions", dotuoiOptions);
		sql = "SELECT jobCode, jobName FROM job";
		String nghenghiepOptions = dataManager.getOptionsID(sql, false, false,
				false, "");
		session.setAttribute("nghenghiepOptions", nghenghiepOptions);

		url = base + "Registration.jsp";
		return url;
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String selectDotuoi(HttpSession session, String base)
			throws SQLException {
		String url;
		ArrayList dotuoi = dataManager.getAllDotuoi();
		session.setAttribute("dotuoi", dotuoi);
		url = base + "LeftMenu.jsp";
		return url;
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String selectNghenghiep(HttpSession session, String base)
			throws SQLException {
		String url;
		ArrayList nghenghiep = dataManager.getAllNghenghiep();
		session.setAttribute("nghenghiep", nghenghiep);
		url = base + "LeftMenu.jsp";
		return url;
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String selectTinhthanh(HttpSession session, String base)
			throws SQLException {
		String url;
		ArrayList tinhthanh = dataManager.getAllTinhthanh();
		session.setAttribute("tinhthanh", tinhthanh);
		url = base + "LeftMenu.jsp";
		return url;
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String myAccount(HttpSession session, String base)
			throws SQLException {
		String url;
		String userID = (String) session.getAttribute("UserID");
		String sessionid = session.getId();
		if (userID != null && !userID.equals("")) {
			Khachhang khachhang = dataManager.getThongtinKhachhang(userID);
			ArrayList dsNgheNghiep = (ArrayList) dataManager.getAllNghenghiep();
			ArrayList dsTinhthanh = (ArrayList) dataManager.getAllTinhthanh();
			ArrayList dsDoTuoi = (ArrayList) dataManager.getAllDotuoi();
			session.setAttribute("khachhang", khachhang);
			session.setAttribute("nghenghiep", dsNgheNghiep);
			session.setAttribute("tinhthanh", dsTinhthanh);
			session.setAttribute("dotuoi", dsDoTuoi);
		} else {
			session.setAttribute("khachhang", null);
		}
		url = base + "InfoAccount.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String completed(HttpServletRequest request, HttpSession session,
			String base) throws SQLException {
		String url;
		String nguoinhan = (String) request.getParameter("tennguoinhan");
		String diachi = (String) request.getParameter("address");
		String huyen = (String) request.getParameter("huyen");
		String tinh = (String) request.getParameter("tinh");
		String email = (String) request.getParameter("email");
		String phone = (String) request.getParameter("phone");
		String hinhthucthanhtoan = (String) request
				.getParameter("hinhthucthanhtoan");
		String ma_khachhang = (String) session.getAttribute("ma_khachhang");
		String ma_giohang = "";
		try {

			ma_giohang = (String) session.getAttribute("magiohang");
		} catch (Exception e) {
			String r = e.getMessage();
			r += r + "dsd";
		}
		HoaDon hoadon = new HoaDon();
		hoadon.setMaGioHang(ma_giohang);
		hoadon.setMaKhachHang(ma_khachhang);
		hoadon.setDiaChi(diachi);
		hoadon.setNguoiNhanHang(nguoinhan);
		hoadon.setDiaChi(diachi);
		hoadon.setQuanHuyen(huyen);
		hoadon.setTinh(tinh);
		hoadon.setEmail(email);
		hoadon.setPhone(phone);
		hoadon.setThanhToan(hinhthucthanhtoan);

		String listSP = dataManager.getListSPByIDGioHang(ma_giohang);
		dataManager.insertTheoVetGioHang(ma_khachhang, listSP);
		dataManager.insertHoaDon(hoadon);
		session.setAttribute("checkoutcompleted", "completed");
		url = base + "index.jsp";
		return url;
	}

	/**
	 * @param base
	 * @return
	 */
	private String orderConfirmation(String base) {
		String url;
		url = base + "OrderConfirmation.jsp";
		return url;
	}

	/**
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String checkOut(HttpSession session, String base)
			throws SQLException {
		String url;
		if (session.getAttribute("UserID") == null
				|| session.getAttribute("UserID").equals("")) {// chua dang nhap
			session.setAttribute("nologin", "bạn chưa đang nhập");
		} else {
			String ma_gh = "";
			Hashtable<String, Sanphamchon> shoppingCart = (Hashtable) session
					.getAttribute("shoppingCart");
			if (shoppingCart != null && !shoppingCart.isEmpty()) {
				ArrayList<GioHang> giohang = new ArrayList<GioHang>();
				GioHang giohangtemp = new GioHang();
				giohang = giohangtemp.ListGioHang(shoppingCart);
				String userId = (String) session.getAttribute("UserID");
				String makh = (KhachhangPeer.getthongtinkhachhang(dataManager,
						userId)).getMakh();

				session.setAttribute("ma_khachhang", makh);
				ma_gh = dataManager.insertGiohang(giohang, makh);

			}

			session.setAttribute("nologin", "");

			session.setAttribute("magiohang", ma_gh);
			Khachhang khachhang = dataManager
					.getThongtinKhachhang((String) session
							.getAttribute("UserID"));
			session.setAttribute("khachhang", khachhang);
			ArrayList dsTinhthanh = (ArrayList) dataManager.getAllTinhthanh();
			session.setAttribute("tinhthanh", dsTinhthanh);
		}
		url = base + "Checkout.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param shoppingCart
	 */
	private void deleteItem(HttpServletRequest request,
			Hashtable<String, Sanphamchon> shoppingCart) {
		try {
			String laptopId = request.getParameter("laptopId");
			shoppingCart.remove(laptopId);
		} catch (Exception e) {
			System.out
					.println("Error deleting the selected item from the shopping cart!");
		}
	}

	/**
	 * @param request
	 * @param session
	 * @param shoppingCart
	 */
	private void updateItem(HttpServletRequest request, HttpSession session,
			Hashtable<String, Sanphamchon> shoppingCart) {
		try {
			String updateInfoId = request.getParameter("updateInfoId");// updateInfo
																		// =
																		// "laptopID,soluong"
			String updateInfoVal = request.getParameter("updateInfoVal");
			String deleteInfo = request.getParameter("deleteInfo");
			String[] id = updateInfoId.split(",");
			String[] value = updateInfoVal.split(",");
			String[] del = deleteInfo.split(",");
			for (int i = 0; i < value.length; i++) {
				if (del[i].equals("true")) {
					shoppingCart.remove(id[i]);
				} else {
					Sanphamchon item = (Sanphamchon) shoppingCart.get(id[i]);
					if (item != null) {

						item.setSoluong(Integer.parseInt(value[i]));
						shoppingCart.remove(id[i]);
						shoppingCart.put(id[i], item);
					}
				}
			}
			session.setAttribute("shoppingCart", shoppingCart);
		} catch (Exception e) {
			System.out.println("Error updating shopping cart!");
		}
	}

	/**
	 * @param request
	 * @param session
	 * @param shoppingCart
	 */
	private void addPK(HttpServletRequest request, HttpSession session,
			Hashtable<String, Sanphamchon> shoppingCart) {
		try {
			String dup = "";// duplicate
			String IdPK = request.getParameter("IdPK");

			Phukien phukien = dataManager.getPhuKienByName(IdPK);

			// SpMuaCung spMuaCung = dataManager.getSpMuaCung(IdLaptop);

			if (phukien != null) {
				Sanphamchon item = shoppingCart.get(IdPK);
				if (item != null) {
					// item.setSoluong(item.getSoluong()+1);
					dup = "Đã có sản phẩm "
							+ IdPK
							+ " trong giỏ hàng.\n Bạn có thể cập nhật số lượng của sản phẩm.";

				} else {
					item = new Sanphamchon(phukien, 1, phukien.getGiaban());
					shoppingCart.put(IdPK, item);
				}
				session.setAttribute("shoppingCart", shoppingCart);

				session.setAttribute("dup", dup);

				String sessionid = session.getId();
				Timestamp newtime = (Timestamp) session.getAttribute("time");

				// dataManager.insertTheovetShoppingCart(sessionid, newtime,
				// IdLaptop);

			}
			// if (spMuaCung.getDsSpMuaCung().size() > 0) {
			// SpMuaCung item = ListSpMuaCung.get(IdLaptop);
			// if (item == null) {
			// ListSpMuaCung.put(IdLaptop, spMuaCung);
			// }
			// }
			// if (ListSpMuaCung.size() > 0) {
			// session.setAttribute("ListSpMuaCung", ListSpMuaCung);
			// }
		} catch (Exception e) {
			System.out
					.println("Error adding the selected laptop to the shopping cart!");
		}
	}

	/**
	 * @param request
	 * @param session
	 * @param shoppingCart
	 * @param ListSpMuaCung
	 */
	private void addItem(HttpServletRequest request, HttpSession session,
			Hashtable<String, Sanphamchon> shoppingCart,
			Hashtable<String, SpMuaCung> ListSpMuaCung) {
		try {
			String dup = "";// duplicate
			String IdLaptop = request.getParameter("IdLaptop");

			Laptop laptop = dataManager.getLaptopByID(IdLaptop);

			SpMuaCung spMuaCung = dataManager.getSpMuaCung(IdLaptop);

			if (laptop != null) {
				Sanphamchon item = shoppingCart.get(IdLaptop);
				if (item != null) {
					// item.setSoluong(item.getSoluong()+1);
					dup = "Đã có sản phẩm "
							+ IdLaptop
							+ " trong giỏ hàng.\n Bạn có thể cập nhật số lượng của sản phẩm.";

				} else {
					item = new Sanphamchon(laptop, 1,
							dataManager.getGialaptop(laptop.getMadong()));
					shoppingCart.put(IdLaptop, item);
				}
				session.setAttribute("shoppingCart", shoppingCart);

				session.setAttribute("dup", dup);

				String sessionid = session.getId();
				Timestamp newtime = (Timestamp) session.getAttribute("time");
				String userID = (String) session.getAttribute("UserID");
				if (userID != null) {

				} else {
					dataManager.insertTheovetShoppingCart(sessionid, newtime,
							IdLaptop);
				}
			}
			if (spMuaCung.getDsSpMuaCung().size() > 0) {
				SpMuaCung item = ListSpMuaCung.get(IdLaptop);
				if (item == null) {
					ListSpMuaCung.put(IdLaptop, spMuaCung);
				}
			}
			if (ListSpMuaCung.size() > 0) {
				session.setAttribute("ListSpMuaCung", ListSpMuaCung);
			}
		} catch (Exception e) {
			System.out
					.println("Error adding the selected laptop to the shopping cart!");
		}
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String laptopDetails(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String laptopId = request.getParameter("laptopId");
		Laptop laptop = dataManager.getLaptopByID(laptopId);

		ArrayList xemcung = new ArrayList();
		try {
			xemcung = dataManager.getSpXemCung(laptopId);
			session.setAttribute("laptop", laptop);
			session.setAttribute("xemcung", xemcung);
			session.setAttribute("laptopID", laptopId);
		}// lay cac sp duoc xem cung
		catch (Exception e) {
			String t = e.getMessage();
			t += "u";
		}
		session.setAttribute("laptop", laptop);
		session.setAttribute("xemcung", xemcung);
		session.setAttribute("laptopID", laptopId);

		String userID = (String) session.getAttribute("UserID");
		String sessionid = session.getId();

		Timestamp newtime = (Timestamp) session.getAttribute("time");
		if (userID != null && !userID.equals("")) // da dang nhap
		{

			dataManager.insertSPtheovet(userID, newtime, laptopId);
		} else { // chua dang nhap

			dataManager.insertSPtheovetGuest(sessionid, newtime, laptopId);
		}
	
		url = base + "LoadDetailLaptop.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String detailPhukien(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		String name = request.getParameter("tenPK");
		Phukien PK = dataManager.getPhuKienByName(name);
		session.setAttribute("listPK", PK);
		url = base + "detailOtherParts.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String selectCatalogPhuKien(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		int numProInPage = Integer.parseInt((String) request
				.getParameter("recordClass"));
		int currPage = Integer.parseInt((String) request
				.getParameter("offsetClass"));
		int numPages = (Integer.parseInt((String) request
				.getParameter("sotrangClass")));

		int from = (currPage - 1) * numProInPage;
		int to = numProInPage * currPage;
		String categoryId = request.getParameter("idclass");

		if (categoryId != null && !categoryId.trim().equals("")) {
			ArrayList phukien = dataManager.getPhuKienByIDPaging(categoryId,
					from, to);
			String tenDM = dataManager.getTenNhomPK(categoryId);
			session.setAttribute("phukien", phukien);
			session.setAttribute("tenDM", tenDM);
			session.setAttribute("currPage", currPage);
			session.setAttribute("numPages", numPages);
			session.setAttribute("idclass", categoryId);
		}

		url = base + "DanhmucPhukien.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String selectCatalog(HttpServletRequest request,
			HttpSession session, String base) throws SQLException {
		String url;
		int numProInPage = Integer.parseInt((String) request
				.getParameter("recordClass"));
		int currPage = Integer.parseInt((String) request
				.getParameter("offsetClass"));
		int numPages = (Integer.parseInt((String) request
				.getParameter("sotrangClass")));

		int from = (currPage - 1) * numProInPage;
		int to = numProInPage * currPage;
		String categoryId = request.getParameter("idclass");

		if (categoryId != null && !categoryId.trim().equals("")) {
			ArrayList laptops = dataManager.getLaptopByNhasanxuatPaging(
					categoryId, from, to);
			String TenNhaSX = dataManager.getTenNhaSX(categoryId);
			session.setAttribute("selectcatalog", laptops);
			session.setAttribute("idclass", categoryId);
			session.setAttribute("TenNhaSX", TenNhaSX);

			session.setAttribute("currPage", currPage);

			session.setAttribute("numPages", numPages);

		}

		url = base + "loadfromcatalog.jsp";
		return url;
	}

	/**
	 * @param request
	 * @param session
	 * @param base
	 * @return
	 * @throws SQLException
	 */
	private String getProManu(HttpServletRequest request, HttpSession session,
			String base) throws SQLException {
		String url;
		String mansx = request.getParameter("maNSX");
		ArrayList listlaptop = dataManager.getLaptopByNhasanxuat(mansx);
		session.setAttribute("listlaptop", listlaptop);
		session.setAttribute("typeAction", "spMuacung");
		url = base + "getInfo.jsp";
		return url;
	}

	/**
	 * @param base
	 * @return
	 */
	private String spXemCung(String base) {
		String url;
		url = base + "test.jsp";
		return url;
	}

	/**
	 * @param base
	 * @return
	 */
	private String search(String base) {
		String url;
		url = base + "SearchOutcome.jsp";
		return url;
	}
}
