/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;

import java.util.Date;


/**
 *
 * @author NTC
 */
public class Khachhang {
    String makh;
    String tenkh;
    String hokh;
    String username;
    String password;
    String diachi;
    String huyen;
    int tinh;
    String email;
    String dienthoai;
    int dotuoi;
    String gioitinh;
    int ma_nghenghiep;
    int status;
    String date_added;
    String level;

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return this.level;
    }

    public void setMakh(String makh){
        this.makh = makh;
    }
    public void setTenkh(String tenkh){
        this.tenkh = tenkh;
    }
    public void setHokh(String hokh){
        this.hokh = hokh;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setDiachi(String diachi){
        this.diachi = diachi;
    }
    public void setHuyen(String huyen){
        this.huyen = huyen;
    }
    public void setTinh(int tinh){
        this.tinh = tinh;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setDienthoai(String dienthoai){
        this.dienthoai = dienthoai;
    }
    public void setdotuoi(int dotuoi){
        this.dotuoi = dotuoi;
    }
    public void setGioitinh(String gioitinh){
        this.gioitinh = gioitinh;
    }
    public void setma_Nghenghiep(int ma_nghenghiep){
        this.ma_nghenghiep = ma_nghenghiep;
    }
    public String getMakh(){
        return makh;
    }
     public String getTenkh(){
        return tenkh;
    }
     public String getHokh(){
        return hokh;
    }
     public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getDiachi(){
        return diachi;
    }
    public String getHuyen(){
        return huyen;
    }
    public int getTinh(){
        return tinh;
    }
    public String getEmail(){
        return email;
    }
    public String getDienthoai(){
        return dienthoai;
    }
    public int getDotuoi(){
        return dotuoi;
    }
    public String getGioitinh(){
        return gioitinh;
    }
    public int getMa_nghenghiep(){
        return ma_nghenghiep;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return this.status;
    }
    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }
    public String getDate_added() {
        return this.date_added;
    }

}
