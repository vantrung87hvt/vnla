/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop.beans;

/**
 *
 * @author tindt
 */
public class Sales {

    private String startdate;
    private String enddate;
    private String numberoforders;
    private String total;

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStartdate() {
        return this.startdate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getEnddate() {
        return this.enddate;
    }

    public void setNumberoforders(String numberoforders) {
        this.numberoforders = numberoforders;
    }

    public String getNumberoforders() {
        return this.numberoforders;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return this.total;
    }
}
