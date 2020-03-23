/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.StoreOrganization;
import Business.UserAccount.UserAccount;
import userinterface.StoreRole.StoreUserWorkAreaJPanel;
import javax.swing.JPanel;

/**
 *
 * @author ashwin
 */
public class StoreRole extends Role{
    
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new StoreUserWorkAreaJPanel(userProcessContainer, account, (StoreOrganization) organization, enterprise, business);
    }
}
