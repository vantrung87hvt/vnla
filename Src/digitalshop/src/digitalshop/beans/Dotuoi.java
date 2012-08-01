/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;

/**
 *
 * @author NTC
 */
public class Dotuoi {

    private int madotuoi;
    private String dotuoi;

    public void setMadotuoi(int dt) {
        this.madotuoi = dt;
    }

    public void setDotuoi(String st) {
        this.dotuoi = st;
    }

    public void setAllDotuoi(int dt, String st) {
        this.setDotuoi(st);
        this.setMadotuoi(dt);
    }

    public int getMadotuoi() {
        return madotuoi;
    }

   public String getDotuoi() {
        return dotuoi;
    }
}
