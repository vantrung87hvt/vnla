/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.beans;

import java.sql.Date;

/**
 *
 * @author NTC
 */
public class Thanhtoan {

    private String ma_giohang;
    private int tinh;
    private String huyen;
    private String diachinhan;
    private Date ngayhethan;
    private String email;
    private String ten_trenthe;
    private String ma_thetindung;
    private String dienthoai_nguoinhan;
    private String tennguoinhan;
    private String hinhthuc_thanhtoan;

    public void setMa_giohang(String ma_giohang) {
        this.ma_giohang = ma_giohang;
    }

    public void setTinh(int tinh) {
        this.tinh = tinh;
    }

    public void setHuyen(String huyen) {
        this.huyen = huyen;
    }

    public void setDiachinhan(String diachinhan) {
        this.diachinhan = diachinhan;
    }

    public void setNgayhethan(Date ngayhethan) {
        this.ngayhethan = ngayhethan;
    }

    public void setTen_trenthe(String ten_trenthe) {
        this.ten_trenthe = ten_trenthe;
    }

    public void setMa_thetindung(String ma_thetindung) {
        this.ma_thetindung = ma_thetindung;
    }

    public void setDienthoai_nguoinhan(String dienthoai_nguoinhan) {
        this.dienthoai_nguoinhan = dienthoai_nguoinhan;
    }

    public void setTennguoinhan(String tennguoinhan) {
        this.tennguoinhan = tennguoinhan;
    }

    public void setHinhthuc_thanhtoan(String hinhthuc_thanhtoan) {
        this.hinhthuc_thanhtoan = hinhthuc_thanhtoan;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public String getMa_giohang() {
        return ma_giohang;
    }

    public int getTinh() {
        return tinh;
    }

    public Date getNgayhethan() {
        return ngayhethan;
    }

    public String getTen_trenthe() {
        return ten_trenthe;
    }

    public String getMa_thetindung() {
        return ma_thetindung;
    }

    public String getHuyen() {
        return huyen;
    }

    public String getDiachinhan() {
        return diachinhan;
    }

    public String getTennguoinhan() {
        return tennguoinhan;
    }

    public String getHinhthuc_thanhtoan() {
        return hinhthuc_thanhtoan;
    }

    public String getDienthoai_nguoinhan() {
       return dienthoai_nguoinhan;
    }
}
