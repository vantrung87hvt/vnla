/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.beans;
import digitalshop.model.LaptopPeer;
import digitalshop.model.DataManager;
import java.sql.SQLException;
/**
 *
 * @author NTC
 */
public class Khuyenmai {
    private Laptop laptop;
    private String ma_dongsanpham;
    private String dieukien;
    private String ngaybatdau;
    private String ngayketthuc;
    private double tilegiamgia;
    private String tangpham;

    public String setMa_dongsanpham(String ma_dongsanpham) {
        return this.ma_dongsanpham = ma_dongsanpham;
    }

    public void setLapKhuyenmai(DataManager dataManager, String ma_dongsanpham) throws SQLException{
        LaptopPeer lappeer = new LaptopPeer();
        laptop= lappeer.getLaptopByID(dataManager, ma_dongsanpham);
    }

    public Laptop getLapKhuyenmai() {
        return laptop;
    }
    public String getMa_dongsanpham() {
        return ma_dongsanpham;
    }

    public String setDieukien(String dieukien) {
        return this.dieukien = dieukien;
    }

    public String getDieukien() {
        return dieukien;
    }

    public String setNgaybatdau(String ngaybatdau) {
        return this.ngaybatdau = ngaybatdau;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public String setNgayketthuc(String ngayketthuc) {
        return this.ngayketthuc = ngayketthuc;
    }

    public String getNgayketthuc() {
        return ngayketthuc;
    }

    public double setTilegiamgia(double tilegiamgia) {
        return this.tilegiamgia = tilegiamgia;
    }

    public double getTilegiamgia() {
        return tilegiamgia;
    }

    public String setTangpham(String tangpham) {
        return this.tangpham = tangpham;
    }

    public String getTangpham() {
        return tangpham;
    }
}
