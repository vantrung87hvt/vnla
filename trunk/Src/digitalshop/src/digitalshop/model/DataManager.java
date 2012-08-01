/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.model;

import java.util.Hashtable;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import digitalshop.beans.Dienthoai;
import digitalshop.beans.Laptop;
import digitalshop.beans.Tiente;
import digitalshop.beans.SpMuaCung;
import digitalshop.beans.Khuyenmai;
import digitalshop.beans.Khachhang;
import digitalshop.beans.Thanhtoan;
import digitalshop.beans.Rule;
import javax.servlet.http.*;
import java.sql.Timestamp;
import digitalshop.beans.Phukien;
import digitalshop.beans.GioHang;
import digitalshop.beans.HoaDon;
import digitalshop.beans.Mail;
import digitalshop.beans.Dongsanpham;
import digitalshop.beans.Nhasanxuat;
import digitalshop.beans.tinhthanh;
import digitalshop.beans.UserGroup;

/**
 *
 * @author NTC
 */
public class DataManager {

    private String dbURL = "";
    private String dbUserName = "";
    private String dbPassword = "";
    static final int UNDEFINT = Integer.MIN_VALUE;

    public DataManager(String dbURL, String dbUserName, String dbPassword) {
        this.dbURL = dbURL;
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
    }

    public DataManager() {

        this.dbURL = "";
        this.dbUserName = "";
        this.dbPassword = "";
    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public String getDbURL() {
        return dbURL;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(getDbURL(), getDbUserName(),
                    getDbPassword());
        } catch (SQLException e) {
            System.out.println("Could not connect to DB: " + e.getMessage());
        }
        return conn;
    }

    public void putConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //--- Laptop operator
    public ArrayList searchLaptop(String keyword) throws SQLException {
        return LaptopPeer.search(this, keyword);
    }

    public ArrayList getLaptopByNhasanxuat(String ma_nhasx) throws SQLException {
        return LaptopPeer.getLaptopByNhasanxuat(this, ma_nhasx);
    }

    public ArrayList getLaptopByNhasanxuatPaging(String ma_nhasx, int from, int to) throws SQLException {
        return LaptopPeer.getLaptopByNhasanxuatPaging(this, ma_nhasx, from, to);
    }

    public String getTenNhaSX(String ma_nhasx) throws SQLException {
        return NhasanxuatPeer.getTenNhaSX(this, ma_nhasx);
    }

    public Hashtable getAllLaptopByNhasanxuat() throws SQLException {
        return LaptopPeer.getAllLaptopByNhasanxuat(this);
    }

    public Hashtable getAllLoaiPhuKien() throws SQLException {
        return OtherProcess.getAllLoaiPhuKien(this);
    }

    public ArrayList getPhuKienByID(String Idphukien) throws SQLException {
        return OtherProcess.getPhuKienByID(this, Idphukien);
    }

    public ArrayList getPhuKienByIDPaging(String Idphukien, int from, int to) throws SQLException {
        return OtherProcess.getPhuKienByIDPaging(this, Idphukien, from, to);
    }

    public String getTenNhomPK(String Idphukien) throws SQLException {
        return OtherProcess.getTenNhomPK(this, Idphukien);
    }

    public ArrayList getSpecialProducts(int start, int record) throws SQLException {
        return LaptopPeer.getSpecialProducts(this, start, record);
    }

    public Laptop getLaptopByID(String ma_dongsanpham) throws SQLException {
        return LaptopPeer.getLaptopByID(this, ma_dongsanpham);
    }

    public double getGialaptop(String ma_dongsanpham) throws SQLException {
        return LaptopPeer.getGialaptop(this, ma_dongsanpham);
    }

    public int getBaohanh(String ma_dongsanpham) throws SQLException {
        return LaptopPeer.getBaohanh(this, ma_dongsanpham);
    }

    public int getTotalRowSpecPro() throws SQLException {
        return LaptopPeer.getTotalRowSpecialPro(this);
    }

    public ArrayList getMiningProducts(int start, int record) throws SQLException {
        return OtherProcess.getMiningProducts(this, start, record);
    }

    public ArrayList getSpXemCung(String IdLaptop) throws SQLException {
        return OtherProcess.getSpXemCung(this, IdLaptop);
    }

    public SpMuaCung getSpMuaCung(String Idlaptop) throws SQLException {
        return SpMuaCungPeer.getSpMuaCung(this, Idlaptop);
    }
// public ArrayList getSpMuaCung(String Idlaptop) throws SQLException {
     //   return OtherProcess.getSpMuaCung(this, Idlaptop);

   // }
    public ArrayList getSpxemnhieunhat() throws SQLException {
        return OtherProcess.getSpXemNhieuNhat(this);
    }

    public ArrayList getAllTinhthanh() throws SQLException {
        return OtherProcess.getAllTinhthanh(this);
    }

    public ArrayList getAllNghenghiep() throws SQLException {
        return OtherProcess.getAllNghenghiep(this);
    }

    public ArrayList getAllDotuoi() throws SQLException {
        return OtherProcess.getAllDotuoi(this);
    }

    public void insertLaptop(Laptop laptop) throws SQLException {
        LaptopPeer.insert(this, laptop);
    }

    public void updateLaptop(Laptop laptop) throws SQLException {
        LaptopPeer.update(this, laptop);
    }

    public void removeLaptop(String ma_dongsanpham) throws SQLException {
        LaptopPeer.remove(this, ma_dongsanpham);
    }

    //--- Dienthoai operator
    public ArrayList searchDienthoai(String keyword) {
        return DienthoaiPeer.search(this, keyword);
    }

    public ArrayList getDienthoaiByNhasanxuat(String ma_nhasx) {
        return DienthoaiPeer.getDienthoaiByNhasanxuat(this, ma_nhasx);
    }

    public Dienthoai getDienthoaiByID(String ma_dongsanpham) {
        return DienthoaiPeer.getDienthoaiByID(this, ma_dongsanpham);
    }

    public void insertDienthoai(Dienthoai dienthoai) {
        DienthoaiPeer.insert(this, dienthoai);
    }

    public void updateDienthoai(Dienthoai dienthoai) {
        DienthoaiPeer.update(this, dienthoai);
    }

    public void removeDienthoai(String ma_dongsanpham) {
        DienthoaiPeer.remove(this, ma_dongsanpham);
    }

    //--- Thongtinkhuyenmai operator
    public Khuyenmai getThongtinkhuyenmai(String ma_dongsanpham) throws SQLException {
        return Thongtinkhuyenmai.getThongtinkhuyenmai(this, ma_dongsanpham);
    }

    public void insertThongtinkhuyenmai(Khuyenmai khuyenmai) throws SQLException {
        Thongtinkhuyenmai.insertThongtinkhuyenmai(this, khuyenmai);
    }

    public void updateThongtinkhuyenmai(Khuyenmai khuyenmai) {
        Thongtinkhuyenmai.updateThongtinkhuyenmai(this, khuyenmai);
    }

    public void removeThongtinkhuyenmai(Khuyenmai khuyenmai) {
        Thongtinkhuyenmai.removeThongtinkhuyenmai(this, dbUserName);
    }

    public ArrayList<Khuyenmai> getSPKhuyenMai(String levelKH) throws SQLException {
        return Thongtinkhuyenmai.getSPKhuyenMai(this, levelKH);
    }
    //---------------- Check username pass-----------

    public String kiemtrathongtin(String username, String password) throws SQLException {
        return User.kiemtra(this, username, password);
    }

    public String kiemtraadmin(String username, String password) throws SQLException {
        return User.kiemtraadmin(this, username, password);
    }

    public boolean kiemtratrung(String username) throws SQLException {
        return User.kiemtratrung(this, username);// trung thi tra ve false

    }
    //------------------so sanh laptop---------------

    public ArrayList getLaptopPriceSimilar(String gia) throws SQLException {
        return LaptopPeer.getLaptopPriceSimilar(this, gia);
    }

    // ----------------- Thanh toan------------------
    public void insertThanhtoan(Thanhtoan thanhtoan, long magiohang) throws SQLException {

        ThanhtoanPeer.insertThanhtoan(this, thanhtoan, magiohang);
    }

    public String getMaxGioHang() throws SQLException {
        return GiohangPeer.getMaxGioHang(this);
    }

    public void insertHoaDon(HoaDon hoadon) throws SQLException {
        HoaDonPeer.insertHoaDon(this, hoadon);
    }

    public ArrayList getHoaDonByMaKH(String ma_kh, String trangthai) throws SQLException {
        return HoaDonPeer.getHoaDonByMaKH(this, ma_kh, trangthai);
    }

    //Canh---------------------------------------------------------------------------------------
    public Phukien getPhuKienByName(String name) throws SQLException {
        return PhuKienPeer.getPhuKienByName(this, name);
    }

    ///mail khach hang
    public ArrayList getMailContenByEmail(String email) throws SQLException {
        return MailPeer.getMailContenByEmail(this, email);
    }

    public boolean insertMail(Mail mail) throws SQLException {
        return MailPeer.insertMail(this, mail);
    }
    
    public boolean deleteMail(int ID){
    	return MailPeer.deleteMail(this,ID);
    }
    //get laptop from classsifiaction

    public ArrayList getLaptopClassification(String idClass, int from, int to) throws SQLException {
        return OtherProcess.getLaptopClassification(this, idClass, from, to);
    }

    public int getLaptopClassificationTotal(String idClass) throws SQLException {
        return OtherProcess.getLaptopClassificationTotal(this, idClass);
    }

    //filter by classification
    public ArrayList FilterByClassification(ArrayList arr, String Class) throws SQLException {
        return OtherProcess.FilterByClassification(this, arr, Class);
    }

    public ArrayList FilterByCPU(ArrayList arr, String cpu) throws SQLException {
        return OtherProcess.FilterByCPU(this, arr, cpu);
    }

    public ArrayList FilterByRAM(ArrayList arr, String ram) throws SQLException {
        return OtherProcess.FilterByRAM(this, arr, ram);
    }

    public ArrayList FilterByJob(ArrayList arr, String job) throws SQLException {
        return OtherProcess.FilterByJob(this, arr, job);
    }

    //--------Lay du lieu cho trang quan tri--------------
    //--------Lay du lieu cho trang quan tri--------------
    public ArrayList getAdminLaptopView() throws SQLException {
        return AdminPeer.getAdminLaptopView(this);
    }

    public ArrayList getAdministratorUser() throws SQLException {
        return KhachhangPeer.getAdministratorUser(this);
    }

    public ArrayList getAdminLaptopByNhasx(String nhasx) throws SQLException {
        return AdminPeer.getAdminLaptopByNhasx(this, nhasx);
    }

    public ArrayList searchAdminLaptop(String ma_dongsanpham) throws SQLException {
        return AdminPeer.searchAdminLaptop(this, ma_dongsanpham);
    }

    public ArrayList searchExtAdminLaptop(String chonhang, String chonsanpham, String giatu, String giaden) throws SQLException {
        return AdminPeer.searchExtAdminLaptop(this, chonhang, chonsanpham, giatu, giaden);
    }

    public ArrayList selectLaptop(String ma_nhaSx, String name, String giaMin, String giaMax) throws SQLException {
        return LaptopPeer.selectLaptop(this, ma_nhaSx, name, giaMin, giaMax);
    }

    public ArrayList quickSearch(String tukhoa, String dongSp, String tenSp, String loaiSp, String giaMin, String giaMax) throws SQLException {
        return OtherProcess.quickSearch(this, tukhoa, dongSp, tenSp, loaiSp, giaMin, giaMax);
    }

    public void deleteAdminLaptop(String madong) throws SQLException {
        AdminPeer.deleteAdminLaptop(this, madong);
    }

    public Laptop getLaptopGeneral(String ma_dongsanpham) throws SQLException {
        return AdminPeer.getLaptopGeneral(this, ma_dongsanpham);
    }

    public Laptop getLaptopMain(String ma_dongsanpham) throws SQLException {
        return AdminPeer.getLaptopMain(this, ma_dongsanpham);
    }

    public Laptop getLaptopHdd(String ma_dongsanpham) throws SQLException {
        return AdminPeer.getLaptopHdd(this, ma_dongsanpham);
    }

    public Laptop getLaptopSound(String ma_dongsanpham) throws SQLException {
        return AdminPeer.getLaptopSound(this, ma_dongsanpham);
    }

    public Laptop getLaptopAnother(String ma_dongsanpham) throws SQLException {
        return AdminPeer.getLaptopAnother(this, ma_dongsanpham);
    }

    public void updateLaptopGeneral(Laptop laptop, String ma_dongsanpham) throws SQLException {
        AdminPeer.updateLaptopGeneral(this, laptop, ma_dongsanpham);
    }

    public void updateLaptopMain(Laptop laptop, String ma_dongsanpham) throws SQLException {
        AdminPeer.updateLaptopMain(this, laptop, ma_dongsanpham);
    }

    public String getOptions(String sql, boolean allRequired, boolean nullRequired, boolean diffRequired, String selectedValue) {
        return OtherProcess.getOptions(this, sql, allRequired, nullRequired, diffRequired, selectedValue);
    }

    public String getOptionsID(String sql, boolean allRequired, boolean nullRequired, boolean diffRequired, String selectedValue) throws SQLException {
        return OtherProcess.getOptionsID(this, sql, allRequired, nullRequired, diffRequired, selectedValue);
    }

    public String getOptionsID2(String sql, boolean allRequired, boolean nullRequired, boolean diffRequired, String selectedValue) throws SQLException {
        return OtherProcess.getOptionsID2(this, sql, allRequired, nullRequired, diffRequired, selectedValue);
    }

    public Tiente getTiente(String matien) throws SQLException {
        return TientePeer.getTiente(this, matien);
    }

    public ArrayList<Rule> getRulesByProduct(String product) throws SQLException {
        return RulePeer.getRulesByProduct(this, product);
    }

    public Rule getRuleByStt(String stt) throws SQLException {
        return RulePeer.getRuleByStt(this, stt);
    }

    public Rule getPurchasedRuleByStt(String stt) throws SQLException {
        return RulePeer.getPurchasedRuleByStt(this, stt);
    }

    public void updateRuleByStt(String stt, String rightRule) throws SQLException {
        RulePeer.updateRuleByStt(this, stt, rightRule);
    }

    public void updatePurchasedRuleByStt(String stt, String rightRule) throws SQLException {
        RulePeer.updatePurchasedRuleByStt(this, stt, rightRule);
    }

    public String getTinhByMaTinh(int maTinh) throws SQLException {
        return KhachhangPeer.getTinhByMaTinh(this, maTinh);
    }

    public String getMaTinhByTinh(String tinh) throws SQLException {
        return KhachhangPeer.getMaTinhByTinh(this, tinh);
    }

    public String getDotuoiByMaDotuoi(int maDotuoi) throws SQLException {
        return KhachhangPeer.getDotuoiByMaDotuoi(this, maDotuoi);
    }

    public String getMaDotuoiByDotuoi(String dotuoi) throws SQLException {
        return KhachhangPeer.getMaDotuoiByDotuoi(this, dotuoi);
    }

    public String getNghenghiepByMaNghenghiep(int maNghenghiep) throws SQLException {
        return KhachhangPeer.getNghenghiepByMaNghenghiep(this, maNghenghiep);
    }

    public String getMaNghenghiepByNghenghiep(String nghenghiep) throws SQLException {
        return KhachhangPeer.getMaNghenghiepByNghenghiep(this, nghenghiep);
    }

    public ArrayList getNormalUser() throws SQLException {
        return KhachhangPeer.getNormalUser(this);
    }

    public ArrayList getAdminUserSearch(String keyword) throws SQLException {
        return KhachhangPeer.getAdminUserSearch(this, keyword);
    }

    //------------------ Theo vet san pham----------------------
    public void insertSPtheovet(String userID, Timestamp time, String dongsp) throws SQLException {
        OtherProcess.insertSPtheovet(this, userID, time, dongsp);
    }

    public void insertSPtheovetGuest(String sessionid, Timestamp time, String dongsp) throws SQLException {
        OtherProcess.insertSPtheovetGuest(this, sessionid, time, dongsp);
    }

    //---- giohang
    public String insertGiohang(ArrayList<GioHang> listGioHang, String makh) throws SQLException {
        return GiohangPeer.insert(this, listGioHang, makh);
    }
    ////////////////---- Khach hang---

    public void insertKhachhang(Khachhang khachhang) throws SQLException {
        KhachhangPeer.insertKhachhang(this, khachhang);
    }

    public String getLevelByIDCustomer(String makh) throws SQLException {
        return KhachhangPeer.getLevelCustomerByIDCustomer(this, makh);
    }

    public void updateKhachhang(Khachhang khachhang, String userId) throws SQLException {
        KhachhangPeer.updateKhachhang(this, khachhang, userId);
    }

    public Khachhang getThongtinKhachhang(String userId) throws SQLException {
        return KhachhangPeer.getthongtinkhachhang(this, userId);
    }

    //lay thong tin lich su giao dich cua khach hang
    public ArrayList getLichsuGiaodich(String username, String col, String order) throws SQLException {
        return OtherProcess.getLichsuGiaodichD(this, username, col, order);
    }

    // phan nhom san pham search
    public ArrayList getPhanNhomSpSearch(ArrayList arr) throws SQLException {
        return OtherProcess.PhanNhomSPSearch(this, arr);
    }
    //-----------theo vet shoppingcart-------

    public void insertTheovetShoppingCart(String sessionid, Timestamp newtime, String IdLaptop) throws SQLException {
        OtherProcess.insertTheovetShoppingCart(this, sessionid, newtime, IdLaptop);
    }

    public ArrayList getSanPhamGiaoDich(String ma_kh, String trangthai, String col, String order) throws SQLException {
        return HoaDonPeer.getSanPhamGiaoDich(this, ma_kh, trangthai, col, order);
    }

    //----mot so ham kiem tra kieu thong dung
    public boolean isNumber(String param) {
        boolean result;
        if (param == null || param.equals("")) {
            return true;
        }
        param = param.replace('d', '_').replace('f', '_');
        try {
            Double dbl = new Double(param);
            result = true;
        } catch (NumberFormatException nfe) {
            result = false;
        }
        return result;
    }

    public boolean isEmpty(int val) {
        return val == UNDEFINT;
    }

    public boolean isEmpty(String val) {
        return (val == null || val.equals("") || val.equals(Integer.toString(UNDEFINT)));
    }

    //--------Check Security----------
    public boolean ChangePass(String userId, String newPass) throws SQLException {
        return User.ChangePass(this, userId, newPass);
    }

    //------Cookies Operations---------------
    public String getCookieValue(Cookie cookies[], String name) {
        for (int i = 0; i < cookies.length; i++) {
            Cookie aCookie = cookies[i];

            if (aCookie.getName().equals(name)) {
                return aCookie.getValue();
            }
        }
        return null;
    }

    //Tin
    //------Index---------------
    public int checkUser(String username, String password) throws SQLException {
        return UserPeer.checkUser(this, username, password);
    }

    public String[] splitPermission(String username) throws SQLException {
        return UserPeer.splitPermission(this, username);
    }

    public int getSalesStatisticInDay(int i) throws SQLException {
        return HoaDonPeer.getSalesStatisticInDay(this, i);
    }

    public int getCustomersStatisticInDay(int i) throws SQLException {
        return KhachhangPeer.getCustomersStatisticInDay(this, i);
    }

    //------Reports---------------
    public ArrayList getListOfPurchasedProducts(int page) throws SQLException {
        return order_product_Peer.getListOfPurchasedProducts(this, page);
    }

    public int getCountPurchasedProducts() throws SQLException {
        return order_product_Peer.getCountPurchasedProducts(this);
    }

    public ArrayList getListOfViewedProducts(int page) throws SQLException {
        return order_product_Peer.getListOfViewedProducts(this, page);
    }

    public int getCountViewedProducts() throws SQLException {
        return order_product_Peer.getCountViewedProducts(this);
    }

    public int getSumViewed() throws SQLException {
        return order_product_Peer.getSumViewed(this);
    }

    public ArrayList getListOfSales(String startdate, String enddate, String groupby, String status, int page) throws SQLException {
        return SalesPeer.getListOfSales(this, startdate, enddate, groupby, status, page);
    }

    public int getCountSales(String startdate, String enddate, String groupby, String status) throws SQLException {
        return SalesPeer.getCountSales(this, startdate, enddate, groupby, status);
    }

    public String getCurrentDate() throws SQLException {
        return SalesPeer.getCurrentDate(this);
    }

    public String getDate7ago() throws SQLException {
        return SalesPeer.getDate7ago(this);
    }

    //------Customers---------------
    public ArrayList getCustomerView(
            int page) throws SQLException {
        return KhachhangPeer.getCustomerView(this, page);
    }

    public int getCountCustomer() throws SQLException {
        return KhachhangPeer.getCountCustomer(this);
    }

    public ArrayList getCustomerFilter(String filter_name, String filter_email, String filter_status, String filter_date_added, int page) throws SQLException {
        return KhachhangPeer.getCustomerFilter(this, filter_name, filter_email, filter_status, filter_date_added, page);
    }

    public int getCountCustomerFilter(String filter_name, String filter_email, String filter_status, String filter_date_added) throws SQLException {
        return KhachhangPeer.getCountCustomerFilter(this, filter_name, filter_email, filter_status, filter_date_added);
    }

    public ArrayList getProductsLaptopFilter(String filter_name, String filter_manu, String filter_quantity, String filter_status, int page) throws SQLException {
        return LaptopPeer.getProductsLaptopFilter(this, filter_name, filter_manu, filter_quantity, filter_status, page);
    }

    public int getCountProductsLaptopFilter(String filter_name, String filter_manu, String filter_quantity, String filter_status) throws SQLException {
        return LaptopPeer.getCountProductsLaptopFilter(this, filter_name, filter_manu, filter_quantity, filter_status);
    }

    public void deleteCustomer(String makh) throws SQLException {
        KhachhangPeer.deleteCustomer(this, makh);
    }

    public void insertCustomer(Khachhang customer) throws SQLException {
        KhachhangPeer.insertCustomer(this, customer);
    }

    public void resetPassword(String makh, String password) throws SQLException {
        KhachhangPeer.resetPassword(this, makh, password);
    }

    public ArrayList getListOfDMCustomer(String filter_customerID, String filter_customerName, String filter_class, int page) throws SQLException {
        return KhachhangPeer.getListOfDMCustomer(this, filter_customerID, filter_customerName, filter_class, page);
    }

    public int getCountDMCustomer(String filter_customerID, String filter_customerName, String filter_class) throws SQLException {
        return KhachhangPeer.getCountDMCustomer(this, filter_customerID, filter_customerName, filter_class);
    }

    public ArrayList getListOfDMLaptop(String filter_laptopName, String filter_laptopClass, int page) throws SQLException {
        return LaptopPeer.getListOfDMLaptop(this, filter_laptopName, filter_laptopClass, page);
    }

    public int getCountDMLaptop(String filter_laptopName, String filter_laptopClass) throws SQLException {
        return LaptopPeer.getCountDMLaptop(this, filter_laptopName, filter_laptopClass);
    }

    public void updateDataMiningCustomer(String makh, String filter_class) throws SQLException {
        KhachhangPeer.updateDataMiningCustomer(this, makh, filter_class);
    }

    public void updateDataMiningLaptop(String sp, String filter_laptopClass) throws SQLException {
        LaptopPeer.updateDataMiningLaptop(this, sp, filter_laptopClass);
    }

    public ArrayList getListOfDMViewed(String filter_manu, String filter_product, String filter_viewed_product, int page) throws SQLException {
        return RulePeer.getListOfDMViewed(this, filter_manu, filter_product, filter_viewed_product, page);
    }

    public int getCountDMViewed(String filter_manu, String filter_product, String filter_viewed_product) throws SQLException {
        return RulePeer.getCountDMViewed(this, filter_manu, filter_product, filter_viewed_product);
    }

    public ArrayList getListOfDMPurchased(String filter_manu, String filter_product, String filter_purchased_product, int page) throws SQLException {
        return RulePeer.getListOfDMPurchased(this, filter_manu, filter_product, filter_purchased_product, page);
    }

    public int getCountDMPurchased(String filter_manu, String filter_product, String filter_purchased_product) throws SQLException {
        return RulePeer.getCountDMPurchased(this, filter_manu, filter_product, filter_purchased_product);
    }

    public ArrayList getListOfOrders(int page) throws SQLException {
        return HoaDonPeer.getListOfOrders(this, page);
    }

    public int getCountOrders() throws SQLException {
        return HoaDonPeer.getCountOrders(this);
    }

    public void deleteOrder(String ma_hoadon) throws SQLException {
        HoaDonPeer.deleteOrder(this, ma_hoadon);
    }

    public HoaDon getOrderById(String ma_hoadon) throws SQLException {
        return HoaDonPeer.getOrderById(this, ma_hoadon);
    }

    public ArrayList getListOfMails(int page) throws SQLException {
        return MailPeer.getListOfMails(this, page);
    }

    public int getCountMails() throws SQLException {
        return MailPeer.getCountMails(this);
    }

    public ArrayList getGiohangById(String ma_giohang) throws SQLException {
        return GiohangPeer.getGiohangById(this, ma_giohang);
    }

    public void updateOrderStatus(String id, String status, String deliverer, String comments) throws SQLException {
        HoaDonPeer.updateOrderStatus(this, id, status, deliverer, comments);
    }

    //------Catalog---------------
    public ArrayList getListOfManufacturer(int page) throws SQLException {
        return NhasanxuatPeer.getListOfManufacturer(this, page);
    }

    public int getCountManufacturer() throws SQLException {
        return NhasanxuatPeer.getCountManufacturer(this);
    }

    public Nhasanxuat getManufacturerById(String id) throws SQLException {
        return NhasanxuatPeer.getManufacturerById(this, id);
    }

    public void updateNhasanxuat(String id, String tennhasx, String image, String mota, String url_link) throws SQLException {
        NhasanxuatPeer.updateNhasanxuat(this, id, tennhasx, image, mota, url_link);
    }

    public void insertNhasanxuat(String tennhasx, String image, String mota, String url_link) throws SQLException {
        NhasanxuatPeer.insertNhasanxuat(this, tennhasx, image, mota, url_link);
    }

    public void deleteManufacturer(String ma_nhasx) throws SQLException {
        NhasanxuatPeer.deleteManufacturer(this, ma_nhasx);
    }

    public ArrayList getListOfProvince(int page) throws SQLException {
        return TinhThanhPeer.getListOfProvince(this, page);
    }

    public int getCountProvince() throws SQLException {
        return TinhThanhPeer.getCountProvince(this);
    }

    public tinhthanh getProvinceById(String id) throws SQLException {
        return TinhThanhPeer.getProvinceById(this, id);
    }

    //------Admin---------------
    public ArrayList getListOfUser(int page) throws SQLException {
        return UserPeer.getListOfUser(this, page);
    }

    public int getCountUser() throws SQLException {
        return UserPeer.getCountUser(this);
    }

    public ArrayList getListOfUserGroup(int page) throws SQLException {
        return UserGroupPeer.getListOfUserGroup(this, page);
    }

    public int getCountUserGroup() throws SQLException {
        return UserGroupPeer.getCountUserGroup(this);
    }

    public int getCountCustomerHour(String hour, String h1) throws SQLException {
        return KhachhangPeer.getCountCustomerHour(this, hour, h1);
    }

    public void insertUserGroup(String usergroup, String access, String insert, String modify, String delete, String export) throws SQLException {
        UserGroupPeer.insertUserGroup(this, usergroup, access, insert, modify, delete, export);
    }

    public UserGroup getUserGroupById(String id) throws SQLException {
        return UserGroupPeer.getUserGroupById(this, id);
    }

    public void updateUserGroup(String id, String usergroup, String access, String insert, String modify, String delete, String export) throws SQLException {
        UserGroupPeer.updateUserGroup(this, id, usergroup, access, insert, modify, delete, export);
    }

    public void deleteUser(String user_id) throws SQLException {
        UserPeer.deleteUser(this, user_id);
    }

    public void insertUser(digitalshop.beans.User user) throws SQLException {
        UserPeer.insertUser(this, user);
    }

    public digitalshop.beans.User getUserById(String id) throws SQLException {
        return UserPeer.getUserById(this, id);
    }

    public void updateUser(String uid, digitalshop.beans.User user) throws SQLException {
        UserPeer.updateUser(this, uid, user);
    }

    public void deleteUserGroup(String user_group_id) throws SQLException {
        UserGroupPeer.deleteUserGroup(this, user_group_id);
    }

    public ArrayList getListOfAccess() throws SQLException {
        return UserGroupPeer.getListOfAccess(this);
    }

    public ArrayList getListOfInsert() throws SQLException {
        return UserGroupPeer.getListOfInsert(this);
    }

    public ArrayList getListOfModify() throws SQLException {
        return UserGroupPeer.getListOfModify(this);
    }

    public ArrayList getListOfDelete() throws SQLException {
        return UserGroupPeer.getListOfDelete(this);
    }

    public ArrayList getListOfExport() throws SQLException {
        return UserGroupPeer.getListOfExport(this);
    }

    //------Products---------------
    public ArrayList getListOfLaptops(int page) throws SQLException {
        return LaptopPeer.getListOfLaptops(this, page);
    }

    public int getCountLaptops() throws SQLException {
        return LaptopPeer.getCountLaptops(this);
    }

    public Dongsanpham getDongspByMadong(String ma_dongsanpham) throws SQLException {
        return LaptopPeer.getDongspByMadong(this, ma_dongsanpham);
    }

    public Laptop getLaptopByMadong(String ma_dongsanpham) throws SQLException {
        return LaptopPeer.getLaptopByMadong(this, ma_dongsanpham);
    }

    public void updateDongsanpham(String ma_dongsanpham, Dongsanpham dongsp) throws SQLException {
        LaptopPeer.updateDongsanpham(this, ma_dongsanpham, dongsp);
    }

    public void deleteDongsanpham(String ma_dongsanpham) throws SQLException {
        LaptopPeer.deleteDongsanpham(this, ma_dongsanpham);
    }

    //canh add log account
    public void insertAccountLog(String makh, String tenkh) throws SQLException {
        OtherProcess.insertAccountLog(this, makh, tenkh);
    }
    //lay cac sp duoc xem nhieu theo level cua khach hang

    public ArrayList<Laptop> getLaptopByLevel(String level) throws SQLException {
        return OtherProcess.getLaptopByLevel(this, level);
    }
    //insert theo vet gio hang

    public boolean insertTheoVetGioHang(String makh, String sanpham) throws SQLException {
        return GiohangPeer.insertTheoVetGioHang(this, makh, sanpham);
    }
    //lay san pham theo ma gio hang

    public String getListSPByIDGioHang(String magh) throws SQLException {
        return GiohangPeer.getListSPByIDGioHang(this, magh);
    }
    //lay gia laptop theo muc thu nhap cua khach hang

    public String[] getPriceLapopByIncome(String mathunhap) throws SQLException {
        return OtherProcess.getPriceLapopByIncome(this, mathunhap);
    }

    public void updateLaptopById(String ma_dongsanpham, Laptop laptop) throws SQLException {
        LaptopPeer.updateLaptopById(this, ma_dongsanpham, laptop);
    }

    public void insertDongsanpham(Dongsanpham dongsp) throws SQLException {
        LaptopPeer.insertDongsanpham(this, dongsp);
    }

    public String getAccessp(String username) throws SQLException {
        return UserGroupPeer.getAccessp(this, username);
    }

    public String getInsertp(String username) throws SQLException {
        return UserGroupPeer.getInsertp(this, username);
    }

    public String getModifyp(String username) throws SQLException {
        return UserGroupPeer.getModifyp(this, username);
    }

    public String getDeletep(String username) throws SQLException {
        return UserGroupPeer.getDeletep(this, username);
    }

    //public String getExportp(String username) throws SQLException {
      //  return UserGroupPeer.getExportp(this, username);
    //}
}
