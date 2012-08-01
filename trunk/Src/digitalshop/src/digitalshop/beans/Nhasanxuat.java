/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;


/**
 *
 * @author NTC
 */
public class Nhasanxuat {
    private String ma_nhasx = "";
    private String tennhasx = "";
    private String image = "";
    private String mota = "";
    private String url_link = "";

    public void setMa_nhasx(String ma_nhasx) {
        this.ma_nhasx = ma_nhasx;
    }
    public String getMa_nhasx() {
        return this.ma_nhasx;
    }
    public void setTennhasx(String tennhasx) {
        this.tennhasx = tennhasx;
    }
    public String getTennhasx() {
        return this.tennhasx;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return this.image;
    }
    public void setMota(String mota) {
        this.mota = mota;
    }
    public String getMota() {
        return this.mota;
    }
    public void setUrl_link(String url_link) {
        this.url_link = url_link;
    }
    public String getUrl_link() {
        return this.url_link;
    }
}
