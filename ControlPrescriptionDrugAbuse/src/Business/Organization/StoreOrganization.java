/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.Role;
import Business.Role.StoreRole;
import java.util.ArrayList;

/**
 *
 * @author ashwin
 */
public class StoreOrganization extends Organization{
    
    public StoreOrganization() {
        super(Organization.GoverningBodyEnterpriseOrganizationType.StoreOrganization.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new StoreRole());
        return roles;
    }

}
