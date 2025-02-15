
package it.unipv.ingsfw.aga.model;

public class Model {
    public boolean checkLogin(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }

    public int getStaffFlag(String username) {
        if (username.equals("admin")) {
            return 1;
        }
        return 0;
    }
}
