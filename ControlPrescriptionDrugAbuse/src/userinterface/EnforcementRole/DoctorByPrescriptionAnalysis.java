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
import Business.WorkQueue.PrescriptionRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartFrame;

import org.jfree.chart.JFreeChart;

import org.jfree.chart.plot.CategoryPlot;

import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author ashwin
 */
public class DoctorByPrescriptionAnalysis {
    private EcoSystem system;
    
    public DoctorByPrescriptionAnalysis( EcoSystem system){
        this.system = system;
        createGraph();
    }
    
    private void createGraph(){
        Map<String, Integer> doctorWithPrescriptionCount = new HashMap<String, Integer>();
        Integer prescriptionCount = 0;
        for(Network network: system.getNetworkList()){
            for(Enterprise enterprise: network.getEnterpriseDirectory().getEnterpriseList()) {
                if(enterprise.getEnterpriseType() == Enterprise.EnterpriseType.GoverningBody) {
                    for(Organization organization: enterprise.getOrganizationDirectory().getOrganizationList()) {
                        for(WorkRequest wr: organization.getWorkQueue().getWorkRequestList()){
                            PrescriptionRequest pr = (PrescriptionRequest) wr;
                            if(doctorWithPrescriptionCount.containsKey(pr.getPrescription().getDoctor().toString())){
                                doctorWithPrescriptionCount.put(pr.getPrescription().getDoctor().toString(), doctorWithPrescriptionCount.get(pr.getPrescription().getDoctor().toString()) + 1);
                            }
                            else{
                                doctorWithPrescriptionCount.put(pr.getPrescription().getDoctor().toString(), 1);
                            }
                        }
                    }
                }
            }
        }
        
        List<Map.Entry<String, Integer>> list; 
        list = new ArrayList<>(doctorWithPrescriptionCount.entrySet());
  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            } 
        }); 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int count = 0;
        if(list.size() > 5)
            count = 5;
        else 
            count = list.size();
        
        for(int i =0; i < count ; i++){
            dataset.setValue(list.get(i).getValue(), "Prescriptions", list.get(i).getKey());
            
        }
        JFreeChart chart = ChartFactory.createBarChart("Doctor Analysis by prescription", "Doctor’s Name","Prescriptions", dataset, PlotOrientation.VERTICAL,false,true,false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.black);
        ChartFrame frame = new ChartFrame("Bar Chart Report",chart);
        frame.setVisible(true);
        frame.setSize(650,550);
    }
}
