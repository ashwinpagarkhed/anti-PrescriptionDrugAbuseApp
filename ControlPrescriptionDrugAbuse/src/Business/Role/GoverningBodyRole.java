/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.GoverningBodyOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import userinterface.GoverningOrgRole.GoverningOrgWorkAreaJPanel;
import javax.swing.JPanel;

/**
 *
 * @author ashwin
 */
public class GoverningBodyRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new GoverningOrgWorkAreaJPanel(userProcessContainer, account, (GoverningBodyOrganization)organization, enterprise, business);
    }
    
}
