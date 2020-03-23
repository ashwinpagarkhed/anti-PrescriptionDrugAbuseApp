/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Enterprise.GoverningBodyEnterprise;
import Business.Organization.Organization.GoverningBodyEnterpriseOrganizationType;
import java.util.ArrayList;

/**
 *
 * @author ashwin
 */
public class OrganizationDirectory {
    
    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }
    
    public Boolean doesOrgTypeExist(Organization o) {
        for(Organization organization: getOrganizationList()) {
            if(organization.getClass() == o.getClass()) {
                return true;
            }
        }
        return false;
    }
    
    public Organization createOrganization(GoverningBodyEnterpriseOrganizationType type){
        Organization organization = null;
        if (type.getValue().equals(GoverningBodyEnterpriseOrganizationType.Doctor.getValue())){
            if(doesOrgTypeExist(new DoctorOrganization())){
                return organization;
            }
            organization = new DoctorOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(GoverningBodyEnterpriseOrganizationType.GoverningBodyOrganization.getValue())){
            if(doesOrgTypeExist(new GoverningBodyOrganization())){
                return organization;
            }
            organization = new GoverningBodyOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(GoverningBodyEnterpriseOrganizationType.StoreOrganization.getValue())){
            if(doesOrgTypeExist(new StoreOrganization())){
                return organization;
            }
            organization = new StoreOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(GoverningBodyEnterpriseOrganizationType.EnforcementOrganization.getValue())){
            if(doesOrgTypeExist(new EnforcementOrganization())){
                return organization;
            }
            organization = new EnforcementOrganization();
            organizationList.add(organization);
        }
        return organization;
    }
    
    public Integer getOrgCount(){
        Integer count = organizationList.size();
        return count;
    }

}