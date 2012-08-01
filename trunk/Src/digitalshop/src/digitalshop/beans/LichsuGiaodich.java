/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;

/**
 *
 * @author Nguyen Duc Canh
 */
public class LichsuGiaodich {
private String date;
String[] productList;
public LichsuGiaodich() {
    date="";
    productList=null;
}
public void setDate(String date) {
    this.date=date;
}
public void setProductList(String[] arr) {
    this.productList=arr;
}
public String getDate() {
    return date;
}
public String[] getProductList() {
    return productList;
}
}

