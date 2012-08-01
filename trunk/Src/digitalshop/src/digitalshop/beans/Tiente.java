/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;

/**
 *
 * @author NTC
 */
public class Tiente {
private String ma;
private String giatri;
private String last_update;
private String kieuhienthi_trai;
private String kieuhienthi_phai;

public Tiente(String ma, String giatri, String last_update, String kieuhienthi_trai, String kieuhienthi_phai){
    this.ma=ma;
    this.giatri=giatri;
    this.last_update=last_update;
    this.kieuhienthi_trai=kieuhienthi_trai;
    this.kieuhienthi_phai=kieuhienthi_phai;
}
public Tiente(){
    this.ma="";
    this.giatri="";
    this.last_update="";
    this.kieuhienthi_trai="";
    this.kieuhienthi_phai="";
}
public void setMa(String ma){
    this.ma=ma;
}
public void setGiatri(String giatri){
    this.giatri=giatri;
}
public void setUpdate(String update){
    this.last_update=update;
}
public void setKieuhienthi_Trai(String Kht_trai){
    this.kieuhienthi_trai=Kht_trai;
}
public void setKieuhienthi_Phai(String Kht_phai){
    this.kieuhienthi_phai=Kht_phai;
}
public String getMa(){
        return ma;
}
public String getGiatri(){
    return giatri;
}
public String getLastUpdate(){
    return last_update;
}
public String getKieuhienthi_Trai(){
    return kieuhienthi_trai;
}
public String getKieuhienthi_Phai(){
    return kieuhienthi_phai;
}
}

