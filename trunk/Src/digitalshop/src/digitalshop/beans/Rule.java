/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;

/**
 *
 * @author PlateT
 */
public class Rule {
    private int stt;
    private String leftRule;
    private String rightRule;
    private double conf;
    private int support;
    private String nhasx;

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getStt() {
        return this.stt;
    }

    public void setLeftRule(String leftRule) {
        this.leftRule = leftRule;
    }

    public String getLeftRule() {
        return this.leftRule;
    }

    public void setRightRule(String rightRule) {
        this.rightRule = rightRule;
    }

    public String getRightRule() {
        return this.rightRule;
    }

    public void setConf(double conf) {
        this.conf = conf;
    }

    public double getConf() {
        return this.conf;
    }

    public void setSupport(int support) {
        this.support = support;
    }

    public int getSupport() {
        return this.support;
    }

    public void setNhasx(String nhasx) {
        this.nhasx = nhasx;
    }

    public String getNhasx() {
        return this.nhasx;
    }
}
