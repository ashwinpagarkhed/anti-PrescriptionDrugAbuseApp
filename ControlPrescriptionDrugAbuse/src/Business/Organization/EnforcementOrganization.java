/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.DoctorRole;
import Business.Role.EnforcementRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author ashwin
 */
public class EnforcementOrganization extends Organization{

    public EnforcementOrganization() {
        super(Organization.EnforcementEnterPriseOrganizationType.EnforcementOrganization.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new EnforcementRole());
        return roles;
    }
     
}
