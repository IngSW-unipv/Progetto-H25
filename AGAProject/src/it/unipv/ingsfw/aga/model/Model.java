
package it.unipv.ingsfw.aga.model;

public class Model {
    public boolean checkLogin(String username, String password) {
        return username.equals("") && password.equals("");
    }

    public int getStaffFlag(String username) {
        if (username.equals("admin")) {
            return 1;
        }
        return 0;
    }
}
