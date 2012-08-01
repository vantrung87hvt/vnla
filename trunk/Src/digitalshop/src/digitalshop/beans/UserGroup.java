/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;

/**
 *
 * @author tindt
 */
public class UserGroup {
    private String user_group_id = "";
    private String name = "";
    private String permission = "";
    private String accessp = "";
    private String insertp = "";
    private String modifyp = "";
    private String deletep = "";
    private String exportp = "";

    public void setUser_group_id(String user_group_id) {
        this.user_group_id = user_group_id;
    }
    public String getUser_group_id() {
        return this.user_group_id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setPermission(String permission) {
        this.permission = permission;
    }
    public String getPermission() {
        return this.permission;
    }
    public void setAccessp(String accessp) {
        this.accessp = accessp;
    }
    public String getAccessp() {
        return this.accessp;
    }
    public void setInsertp(String insertp) {
        this.insertp = insertp;
    }
    public String getInsertp() {
        return this.insertp;
    }
    public void setModifyp(String modifyp) {
        this.modifyp = modifyp;
    }
    public String getModifyp() {
        return this.modifyp;
    }
    public void setDeletep(String deletep) {
        this.deletep = deletep;
    }
    public String getDeletep() {
        return this.deletep;
    }
    public void setExportp(String exportp) {
        this.exportp = exportp;
    }
    public String getExportp() {
        return this.exportp;
    }
}
