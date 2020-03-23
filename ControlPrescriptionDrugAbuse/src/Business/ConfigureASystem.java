package Business;

import Business.Employee.Employee;
import Business.Role.SystemAdminRole;
import Business.UserAccount.UserAccount;

/**
 *
 * @author ashwin
 */
public class ConfigureASystem {
    
    public static EcoSystem configure(String username, String password, String secretCode){
        
        EcoSystem system = EcoSystem.getInstance();
        
        //Create a network
        //create an enterprise
        //initialize some organizations
        //have some employees 
        //create user account
        
        Employee employee = system.getEmployeeDirectory().createEmployee(username);
        
        UserAccount ua = system.getUserAccountDirectory().createUserAccount(username, password, employee, new SystemAdminRole(), secretCode);
        
        return system;
    }
    
}
