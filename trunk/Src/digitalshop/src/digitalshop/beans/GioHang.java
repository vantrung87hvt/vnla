/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.beans;

import digitalshop.beans.Sanphamchon;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ArrayList;


/**
 *
 * @author Nguyen Duc Canh
 */
public class GioHang {

    private String ma_giohang;
    private String ma_khachhang;
    private String sanpham;
    private String ngaytao;
    private double tongtien;
    private String soluong;
    private String nhasanxuat;
    private String dongia;

    public GioHang() {
        this.ma_giohang = null;
        this.ma_khachhang = null;
        this.ngaytao = null;
        this.sanpham = null;
        this.tongtien = -1;
        this.soluong = "1";
    }

    public ArrayList ListGioHang(Hashtable shoppingCart) {
        ArrayList<GioHang> giohang = new ArrayList<GioHang>();
        double tongtien = 0.0;
        Enumeration enumList = shoppingCart.elements();
        while (enumList.hasMoreElements()) {

            Sanphamchon item = (Sanphamchon) enumList.nextElement();
            int sl = item.getSoluong();
            String nameProduct = item.getMa_dongsanpham();           
            tongtien += sl * item.getGia();
            GioHang gh = new GioHang();
            gh.setTongTien(tongtien);
            gh.setSoluong(String.valueOf(sl));
            gh.setSanPham(nameProduct);
            gh.setNgayTao(null);
            giohang.add(gh);
        }
        return giohang;
    }

    public void setMaGioHang(String ma_giohang) {
        this.ma_giohang = ma_giohang;
    }

    public void setMaKhachHang(String ma_khachhang) {
        this.ma_khachhang = ma_khachhang;
    }

    public void setSanPham(String sanpham) {
        this.sanpham = sanpham;
    }

    public void setNgayTao(String ngay) {
        this.ngaytao = ngay;
    }

    public void setTongTien(double tongtien) {
        this.tongtien = tongtien;
    }

    public String MaGioHang() {
        return ma_giohang;
    }

    public String MaKhachHang() {
        return ma_khachhang;
    }

    public String getSanPham() {
        return sanpham;
    }

    public String getNgayTao() {
        return ngaytao;
    }

    public double getTongTien() {
        return tongtien;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getSoluong() {
        return this.soluong;
    }

    public void setNhasanxuat(String nhasanxuat) {
        this.nhasanxuat = nhasanxuat;
    }

    public String getNhasanxuat() {
        return this.nhasanxuat;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
    }

    public String getDongia() {
        return this.dongia;
    }
}
