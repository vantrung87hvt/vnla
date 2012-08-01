/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;

/**
 *
 * @author NTC
 */
public class Nghenghiep {
    private String tennghe;
    private int manghe;

    public void setTennghe(String tennghe){
        this.tennghe=tennghe;
    }
    public void setManghe(int ma_nghe){
        this.manghe=ma_nghe;
    }
    public void setNghe(String nghe,int ma_nghe){
        this.setTennghe(nghe);
        this.setManghe(ma_nghe);
    }
    public String getTennghe(){
      return tennghe;
    }
    public int getmanghe(){
        return manghe;
    }

}
