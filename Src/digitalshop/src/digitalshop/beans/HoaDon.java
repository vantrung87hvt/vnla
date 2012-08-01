/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;

/**
 *
 * @author Nguyen Duc Canh
 */
public class HoaDon {
private String ma_hoadon;
private String ma_khachhang;
private String ma_giohang;
private String nguoigiao;
private String nguoinhan;
private String diachi;
private String diachi1;
private String quanhuyen;
private String tinhthanh;
private String fax;
private String email;
private String phone;
private String thanhtoan;
private String trangthaihoadon;
private String ngaytaoHD;
private String ngaythanhtoan;
private String ngaysuaHD;
private String loaitienthanhtoan;
private String sanpham;
private String tongtien;

public HoaDon() {
    this.diachi=null;
    this.diachi1=null;
    this.email=null;
    this.fax=null;
    this.loaitienthanhtoan=null;
    this.ma_giohang=null;
    this.ma_hoadon=null;
    this.ma_khachhang=null;
    this.ngaysuaHD=null;
    this.ngaytaoHD=null;
    this.ngaythanhtoan=null;
    this.nguoigiao=null;
    this.nguoinhan=null;
    this.phone=null;
    this.quanhuyen=null;
    this.thanhtoan=null;
    this.tinhthanh=null;
    this.trangthaihoadon=null;
}
public void setMaHoaDon(String ma_hoadon) {
    this.ma_hoadon=ma_hoadon;
}
public void setMaGioHang(String ma_giohang){
    this.ma_giohang=ma_giohang;
}
public void setMaKhachHang(String ma_khachhang) {
    this.ma_khachhang=ma_khachhang;
}
public void setNguoiGiaoHang(String nguoigiao) {
    this.nguoigiao=nguoigiao;
}
public void setNguoiNhanHang(String nguoinhan) {
    this.nguoinhan=nguoinhan;
}
public void setDiaChi(String diachi) {
    this.diachi=diachi;
}
public void setDiaChi1(String diachi1) {
    this.diachi1=diachi1;
}
public void setQuanHuyen(String quanhuyen) {
    this.quanhuyen=quanhuyen;
}
public void setTinh(String tinh) {
    this.tinhthanh=tinh;
}
public void setFax(String fax) {
    this.fax=fax;
}
public void setEmail(String email) {
    this.email=email;
}
public void setPhone(String phone) {
    this.phone=phone;
}
public void setThanhToan(String thanhtoan) {
    this.thanhtoan=thanhtoan;
}
public void setTrangThaiHoaDon(String trangthai) {
    this.trangthaihoadon=trangthai;
}
public void setNgayTaoHoaDon(String ngaytao) {
    this.ngaytaoHD=ngaytao;
}
public void setNgayThanhToan(String ngaythanhtoan) {
    this.ngaythanhtoan=ngaythanhtoan;
}
public void setNgaySuaHD(String ngaysua) {
    this.ngaysuaHD=ngaysua;
}
public void setLoaiTienThanhtoan(String tien) {
    this.loaitienthanhtoan=tien;
}

public String getMaHoaDon() {
    return ma_hoadon;
}
public String getMaGioHang(){
    return ma_giohang;
}
public String getMaKhachHang() {
    return ma_khachhang;
}
public String getNguoiGiaoHang() {
     return nguoigiao;
}
public String getNguoiNhanHang() {
     return nguoinhan;
}
public String getDiaChi() {
     return diachi;
}
public String getDiaChi1() {
     return diachi1;
}
public String getQuanHuyen() {
     return quanhuyen;
}
public String getTinh() {
    return tinhthanh;
}
public String getFax() {
     return fax;
}
public String getEmail() {
     return email;
}
public String getPhone() {
     return phone;
}
public String getThanhToan() {
     return thanhtoan;
}
public String getTrangThaiHoaDon() {
     return trangthaihoadon;
}
public String getNgayTaoHoaDon() {
     return ngaytaoHD;
}
public String getNgayThanhToan() {
    return ngaythanhtoan;
}
public String getNgaySuaHD() {
     return ngaysuaHD;
}
public String getLoaiTienThanhtoan() {
     return loaitienthanhtoan;
}
public void setSanpham(String sanpham) {
    this.sanpham = sanpham;
}
public String getSanpham() {
    return this.sanpham;
}
public void setTongtien(String tongtien) {
    this.tongtien = tongtien;
}
public String getTongtien() {
    return this.tongtien;
}

}