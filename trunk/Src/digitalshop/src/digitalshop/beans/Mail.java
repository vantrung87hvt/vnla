/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;

/**
 *
 * @author Nguyen Duc Canh
 */
public class Mail {
private String mailKhachhang;
private String noidungMail;
private String ngaytao;
private String makhachhang;
private String trangthai;
private String ID;

    public Mail(String mailKhachhang, String noidungMail, String makhachhang) {
        this.mailKhachhang = mailKhachhang;
        this.noidungMail = noidungMail;
        this.makhachhang = makhachhang;
    }

    public Mail(String mailKhachhang, String noidungMail, String ngaytao, String makhachhang, String trangthai) {
        this.mailKhachhang = mailKhachhang;
        this.noidungMail = noidungMail;
        this.ngaytao = ngaytao;
        this.makhachhang = makhachhang;
        this.trangthai = trangthai;
    }



    public Mail() {
    }


    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setMailKhachhang(String mailKhachhang) {
        this.mailKhachhang = mailKhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public void setNoidungMail(String noidungMail) {
        this.noidungMail = noidungMail;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getMailKhachhang() {
        return mailKhachhang;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public String getNoidungMail() {
        return noidungMail;
    }

    public String getTrangthai() {
        return trangthai;
    }
}
