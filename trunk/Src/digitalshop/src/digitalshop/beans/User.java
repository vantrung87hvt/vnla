/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalshop.beans;

/**
 *
 * @author tindt
 */
public class User {
    private String user_id = "";
    private String user_group_id = "";
    private String username = "";
    private String password = "";
    private String firstname = "";
    private String lastname = "";
    private String email = "";
    private int status;
    private String date_added;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_id() {
        return this.user_id;
    }
    public void setUser_group_id(String user_group_id) {
        this.user_group_id = user_group_id;
    }
    public String getUser_group_id() {
        return this.user_group_id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return this.username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }
    public void setFirstName(String firstname) {
        this.firstname= firstname;
    }
    public String getFirstName() {
        return this.firstname;
    }
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
    public String getLastName() {
        return this.lastname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return this.status;
    }
    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }
    public String getDate_added() {
        return this.date_added;
    }

}
