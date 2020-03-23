/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.UserAccount.StoreAccount;
import Business.UserAccount.UserAccount;
import helper.Prescription;

public class PrescriptionRequest extends WorkRequest{
    
    private Prescription prescription;
    private UserAccount storeAccount;
    private UserAccount doctorAccount;

    public UserAccount getDoctorAccount() {
        return doctorAccount;
    }

    public void setDoctorAccount(UserAccount doctorAccount) {
        this.doctorAccount = doctorAccount;
    }
    
    public UserAccount getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(StoreAccount storeAccount) {
        this.storeAccount = storeAccount;
    }
    
    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    @Override
    public String toString() {
        return prescription.getPatient().getEmployee().getName();
    }
}
