/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;
import java.util.ArrayList;
/**
 *
 * @author Nguyen Duc Canh
 */
public class NhomSPSearch {
private String nhasx;
private ArrayList arr;
public NhomSPSearch() {
    nhasx=null;
    arr=null;
}
public void setNhasx(String nhasx) {
    this.nhasx=nhasx;
}
public void setArr(ArrayList arr) {
    this.arr=arr;
}
public String getNhasx() {
    return nhasx;
}
public ArrayList getArr() {
    return arr;
}
}
