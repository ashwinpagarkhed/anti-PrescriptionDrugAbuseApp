package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ashwin
 */
public class EnforcementBodyEnterprise extends Enterprise {

    public EnforcementBodyEnterprise(String name) {
        super(name, Enterprise.EnterpriseType.EnforcementBody);
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }

}
