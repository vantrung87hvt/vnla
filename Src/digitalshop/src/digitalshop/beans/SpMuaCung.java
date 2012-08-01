/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;
import java.util.ArrayList;
/**
 *
 * @author NTC
 */
public class SpMuaCung {
    private String laptopId;
    private ArrayList dsmuaCung;

    public SpMuaCung(String Idlap, ArrayList dsXemcung) {
        this.laptopId=Idlap;
        this.dsmuaCung=dsXemcung;
    }

    public void setSpMuaCung(String IdLap, ArrayList dsxemcung){
        this.laptopId=IdLap;
        this.dsmuaCung=dsxemcung;
    }
    public String getIdSpMua(){
        return laptopId;
    }
    public ArrayList getDsSpMuaCung(){
        return dsmuaCung;
    }
}
