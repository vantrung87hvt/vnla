/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;

/**
 *
 * @author NTC
 */
public class tinhthanh {

    private String tenTinh;
    private int maTinh;

    public tinhthanh() {
        this.tenTinh = "";
    }

    public tinhthanh(int  maTinh, String tenTinh) {
        this.tenTinh = tenTinh;
        this.maTinh = maTinh;
    }

    public void setMatinh(int maTinh) {
        this.maTinh = maTinh;
    }

    public int getMatinh() {
        return this.maTinh;
    }

    public void setTentinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public String getTentinh(){
        return tenTinh;
    }
}
