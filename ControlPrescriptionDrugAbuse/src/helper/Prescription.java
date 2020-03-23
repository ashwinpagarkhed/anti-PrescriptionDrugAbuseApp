/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import Business.UserAccount.DoctorAccount;
import Business.UserAccount.UserAccount;
import java.util.List;

/**
 *
 * @author ashwin
 */
public class Prescription {
    
    List<Medicine> medicines;
    DoctorAccount doctor;
    UserAccount patient;
    String timeIssued;

    
    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
    
    public String getTimeIssued() {
        return timeIssued;
    }

    public void setTimeIssued(String timeIssued) {
        this.timeIssued = timeIssued;
    }

    public Prescription() {
        medicines = new MedicineDirectory().getRandomMedicines();
        timeIssued = Helper.getCurrentDateTime();
    }

    public DoctorAccount getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorAccount doctor) {
        this.doctor = doctor;
    }

    public UserAccount getPatient() {
        return patient;
    }

    public void setPatient(UserAccount patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return doctor.toString();
    }
}
