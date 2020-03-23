/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.Role.SystemAdminRole;
import Business.UserAccount.UserAccount;
import Business.UserAccount.UserAccountDirectory;
import java.util.ArrayList;

/**
 *
 * @author ashwin
 */
public class EcoSystem extends Organization{
    
    private static EcoSystem business;
    private ArrayList<Network> networkList;
    private ArrayList<UserAccount> userAccountDir;

    public static EcoSystem getBusiness() {
        return business;
    }

    public static void setBusiness(EcoSystem business) {
        EcoSystem.business = business;
    }

    public ArrayList<UserAccount> getUserAccountDir() {
        return userAccountDir;
    }
    //
    public UserAccount getUserAccountFromDir(String username) {
        for (UserAccount ua : getUserAccountDir()) {
            if (ua.getUsername().equals(username)){
                return ua;
            }
        }
        return null;
    }
    
    public Boolean checkIfUserExistsInUserDirectory(String username) {
        for (UserAccount ua : getUserAccountDir()) {
            if (ua.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    
    public boolean authenticateUser(String username, String password) {
        for (UserAccount ua : getUserAccountDir()) {
            if (ua.getUsername().equals(username) && ua.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }


    public Boolean addUserToAccountDir(UserAccount userAccount) {        
        for (UserAccount account : this.userAccountDir) {
            if(account.getUsername().equals(userAccount.getUsername())) {
                return false;
            }
        }
        
        this.userAccountDir.add(userAccount);
        return true;
    }
    
    public static EcoSystem getInstance(){
        if(business==null){
            business=new EcoSystem();
        }
        return business;
    }
    
    public Network createAndAddNetwork(){
        Network network=new Network();
        networkList.add(network);
        return network;
    }
    
    public void deleteNetwork(Network network){
        if( networkList.contains(network))
            networkList.remove(network);
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList=new ArrayList<Role>();
        roleList.add(new SystemAdminRole());
        return roleList;
    }
    public EcoSystem(){
        super(null);
        networkList=new ArrayList<Network>();
        userAccountDir = new ArrayList<UserAccount>();
    }

    public ArrayList<Network> getNetworkList() {
        return networkList;
    }

    public void setNetworkList(ArrayList<Network> networkList) {
        this.networkList = networkList;
    }
    
    public boolean checkIfUsernameExists(String userName){
        return this.getUserAccountDirectory().checkIfUsernameExists(userName);
    }
}
