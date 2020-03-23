/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization.OrganizationDirectory;
import java.util.ArrayList;

public class EnterpriseDirectory {
    private ArrayList<Enterprise> enterpriseList;
   
    public EnterpriseDirectory() {
        
        Enterprise governingBody = new GoverningBodyEnterprise("Governing Body");
        Enterprise enforcementBody = new EnforcementBodyEnterprise("Enforcement Body");
        
        enterpriseList = new ArrayList<Enterprise>();
        enterpriseList.add(governingBody);
        enterpriseList.add(enforcementBody);
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }

    public void setEnterpriseList(ArrayList<Enterprise> enterpriseList) {
        this.enterpriseList = enterpriseList;
    }
    
    //Create enterprise
    public Enterprise createAndAddEnterprise(String name,Enterprise.EnterpriseType type){
        Enterprise enterprise=null;
        if(type==Enterprise.EnterpriseType.GoverningBody){
            enterprise=new GoverningBodyEnterprise(name);
            enterpriseList.add(enterprise);
        }
        if(type==Enterprise.EnterpriseType.EnforcementBody){
            enterprise=new EnforcementBodyEnterprise(name);
            enterpriseList.add(enterprise);
        }
        return null;
    }
}
