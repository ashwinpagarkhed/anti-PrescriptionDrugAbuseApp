/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.GoverningBodyRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author ashwin
 */
public class GoverningBodyOrganization extends Organization{
    
    public GoverningBodyOrganization() {
        super(Organization.GoverningBodyEnterpriseOrganizationType.GoverningBodyOrganization.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new GoverningBodyRole());
        return roles;
    }
    
}
