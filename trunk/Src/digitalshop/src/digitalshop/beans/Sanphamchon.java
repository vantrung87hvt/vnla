/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.beans;

/**
 *
 * @author NTC
 */
public class Sanphamchon {

    private String ma_dongsanpham;
    private double gia;
    private int soluong;

    public Sanphamchon (Laptop laptop, int soluong, double gia) {
        this.ma_dongsanpham = laptop.ma_dongsanpham;
        this.gia = gia;
        this.soluong = soluong;
    }
    public Sanphamchon (Laptop laptop, int soluong) {
        this.ma_dongsanpham = laptop.ma_dongsanpham;
        this.soluong = soluong;
    }
     public Sanphamchon (Phukien phukien, int soluong, double gia) {
        this.ma_dongsanpham = phukien.getSanpham();
        this.gia = gia;
        this.soluong = soluong;
    }
    public Sanphamchon (Phukien phukien, int soluong) {
        this.ma_dongsanpham = phukien.getSanpham();
        this.soluong = soluong;
    }

    public String getMa_dongsanpham() {
        return ma_dongsanpham;
    }

    public void setMa_dongsanpham(String ma_dongsanpham) {
        this.ma_dongsanpham = ma_dongsanpham;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}