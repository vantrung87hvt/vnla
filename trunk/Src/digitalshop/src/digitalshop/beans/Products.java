/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.beans;

/**
 *
 * @author NTC
 */
public abstract class Products {

    String ma_dongsanpham;
    String nhasx;
    String quocgia;
    String mota;
    int soluong;
    String image;
    int soluongdat;
    String maloai;
    int baohanh;
    int soluotxem;
    public abstract String setMadong(String ma_dongsanpham);

    public abstract String getMadong();

    public abstract String setQuocgia(String quocgia);

    public abstract String getQuocgia();
    
    public abstract String setMota(String mota);
    
    public abstract String getMota();

    public abstract int setSoluong(int soluong);

    public abstract int getSoluong();

    public abstract String setImage(String image);

    public abstract String getImage();

    public abstract int setSoluongdat(int soluongdat);

    public abstract int getSoluongdat();

    public abstract String setMaloai(String maloai);

    public abstract String getMaloai();

    public abstract String setNhasx(String ma_nhasx);

    public abstract String getNhasx();
    
    public abstract int setBaohanh(int baohanh);
    
    public abstract int getBaohanh();

    public abstract int setSoluotxem(int soluotxem);

    public abstract int getSoluotxem();

}
