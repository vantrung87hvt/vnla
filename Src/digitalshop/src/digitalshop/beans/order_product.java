/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;

/**
 *
 * @author tindt
 */
public class order_product {
    private String product_name;
    private String manufacturer;
    private int quantity;
    private double total;
    private int viewed;

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    public String getProduct_name() {
        return this.product_name;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getManufacturer() {
        return this.manufacturer;
    }
    public void setQuantity(int quatity) {
        this.quantity = quatity;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public double getTotal() {
        return this.total;
    }
    public void setViewed(int viewed) {
        this.viewed = viewed;
    }
    public int getViewed() {
        return this.viewed;
    }
}
