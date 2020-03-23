/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.EnforcementRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.DoctorOrganization;
import Business.Organization.Organization;
import Business.UserAccount.DoctorAccount;
import Business.UserAccount.UserAccount;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author ashwin
 */
public class GoverningBodyAnalysis extends JFrame{
    private EcoSystem system;
    public GoverningBodyAnalysis(String appTitle, String chartTitle, EcoSystem system){
        this.system = system;
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset, chartTitle);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);
    }

    private PieDataset createDataset() {
        Map<Boolean, Integer> doctorWithLicence = new HashMap<Boolean, Integer>();
        Integer licenceTotal = 0;
        for(Network network: system.getNetworkList()){
            for(Enterprise enterprise: network.getEnterpriseDirectory().getEnterpriseList()) {
                if(enterprise.getEnterpriseType() == Enterprise.EnterpriseType.GoverningBody) {
                    for(Organization organization: enterprise.getOrganizationDirectory().getOrganizationList()) {
                        if(organization instanceof DoctorOrganization){
                            for(UserAccount acc : organization.getUserAccountDirectory().getUserAccountList()){
                                if(acc.getRole().toString().equals("Business.Role.DoctorRole")){
                                    DoctorAccount dr = (DoctorAccount) acc;
                                    if(doctorWithLicence.containsKey(dr.isLicense())){
                                        doctorWithLicence.put(dr.isLicense(), doctorWithLicence.get(dr.isLicense()) + 1);
                                    }
                                    else{
                                        doctorWithLicence.put(dr.isLicense(), 1);
                                    }
                                    licenceTotal = licenceTotal + 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        DefaultPieDataset result = new DefaultPieDataset();
        if(doctorWithLicence.get(true) != null)
            result.setValue("Doctors with Licence", (doctorWithLicence.get(true)*100)/licenceTotal);
        if(doctorWithLicence.get(false) != null)
            result.setValue("Doctors without Licence", (doctorWithLicence.get(false)*100)/licenceTotal);
        return result;
    }

    private JFreeChart createChart(PieDataset dataset, String chartTitle) {
        JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, dataset, true, true, false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(0);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
    }
    
    
}
