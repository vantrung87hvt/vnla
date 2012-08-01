/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.beans;

/**
 *
 * @author NTC
 */
public class Phukien {

    private String sanpham;
    private String nhasanxuat;
    private String baohanh;
    private double  giaban;
    private String kind;
    private String thongtin;
    private String url;

    public void setSanpham(String sanpham) {
        this.sanpham = sanpham;
    }

    public String getSanpham() {
        return this.sanpham;
    }

    public void setNhasanxuat(String nhasanxuat) {
        this.nhasanxuat = nhasanxuat;
    }

    public String getNhasanxuat() {
        return this.nhasanxuat;
    }

    public void setBaohanh(String baohanh) {
        this.baohanh = baohanh;
    }

    public String getBaohanh() {
        return this.baohanh;
    }

    public void setGiaban(double  giaban) {
        this.giaban = giaban;
    }

    public double  getGiaban() {
        return this.giaban;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return this.kind;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }

    public String getThongtin() {
        return this.thongtin;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
