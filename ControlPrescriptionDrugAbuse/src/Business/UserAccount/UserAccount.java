/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.UserAccount;

import Business.Employee.Employee;
import Business.Network.Network;
import Business.Role.Role;
import Business.WorkQueue.WorkQueue;
import helper.Prescription;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ashwin
 */
public class UserAccount {
    
    private String username;
    private String password;
    private Employee employee;
    private Role role;
    private WorkQueue workQueue;
    private Network network;
    private String viewUser; 
    private Date DOB;
    private String type;
    private String secretCode;

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private String phoneNumber;
    private String email;
    
    public String getViewUser() {
        return viewUser;
    }

    public void setViewUser(String viewUser) {
        this.viewUser = viewUser;
    }
    
    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public List<Prescription> getListOfPrescriptions() {
        return listOfPrescriptions;
    }

    public void setListOfPrescriptions(List<Prescription> listOfPrescriptions) {
        this.listOfPrescriptions = listOfPrescriptions;
    }
    private List<Prescription> listOfPrescriptions;

    public UserAccount() {
        workQueue = new WorkQueue();
        listOfPrescriptions = new ArrayList<>();
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }
    
    public void addPrescription(Prescription prescription) {
        listOfPrescriptions.add(prescription);
    }

    @Override
    public String toString() {
        return employee.getName();
    }
}