/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.UserAccount;

/**
 *
 * @author ashwin
 */
public class DoctorAccount extends UserAccount {
    private boolean license = true;
    
    public boolean isLicense() {
        return license;
    }

    public void setLicense(boolean license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
