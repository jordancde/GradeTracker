

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jordandearsley
 */
public final class Class extends JPanel {
    public String FILEPATHAssignments;
    public String className;
    public ArrayList<String[]> assignments;
    public ArrayList<JLabel> labels;
    public double[] weightings;
    
    boolean exam = true;
    
    double testWeight = .6;
    double assignmentWeight = .1;
    double examWeight = .3;
    double midtermWeight = .13;
    
    double average;
    
   
    
    
    
    public Class(String name, double[] weights) throws IOException{
        
        className = name;
        labels = new ArrayList<JLabel>();
        weightings = weights;
        URL location = Info.class.getProtectionDomain().getCodeSource().getLocation();
        FILEPATHAssignments = location.getFile()+className+"tests.csv";
        if(!(new File(FILEPATHAssignments).canRead())){

            (new File(FILEPATHAssignments)).createNewFile();
            
        }
        JButton addAssignment = new JButton("Add New Assessment");
       
        addAssignment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    addAssignment();
                } catch (IOException ex) {
                    Logger.getLogger(Class.class.getName()).log(Level.SEVERE, null, ex);
                }
            }          
        });
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(addAssignment);
        JLabel br = new JLabel();
                
        br.setText("<html><br></html>");
        this.add(br);
       
        readAssignments();
        
        
        
    }
    
    public void addAssignment() throws IOException{
        
        String name = JOptionPane.showInputDialog("Name of Assignment:");
        String type = JOptionPane.showInputDialog("Type of Assignment:");
        double mark = Double.parseDouble(JOptionPane.showInputDialog("Mark (%):"));
    
        
        writeAssignment(new String[] {name,type,Double.toString(mark)});
        readAssignments();
        
    }
    
    public void calcAverage(){
        double sum = 0;
        double averageSum = 0;
        int[] numEach = new int[4];
        
        /*for(String[] assignment : assignments){
            if(assignment[0]=="test")
                numEach[0]++;
            else if(assignment[0]=="assignment")
                numEach[1]++;
            else if(assignment[0]=="exam")
                numEach[2]++;
        }
        
        
        for(String[] assignment : assignments){
            double mark = Double.parseDouble(assignment[2]);
            String type = assignment[1];
            
            
        }*/
        for(String[] assignment : assignments){
            sum += Double.parseDouble(assignment[2]);
        
        }
        
        average = sum/assignments.size();
    }
    
    public String getName(){
        return className;
    }
    
    
    public void writeAssignment(String[] dataArray) throws IOException{
        try(
            
            FileWriter fw = new FileWriter(FILEPATHAssignments, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {       
            for(String data:dataArray){
                out.print(data+",");
            }
            
            out.println("");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Something Fucked up");
        }
    }
    
    public void readAssignments() throws IOException{
        
        String line = "";
        String cvsSplitBy = ",";
        assignments = new ArrayList<String[]>();
        if(labels.size()>1){
            for(JLabel label: labels){
                this.remove(label);
            }
        }
            
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATHAssignments))) {

            while ((line = br.readLine()) != null) {
                
                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                
                JLabel label = new JLabel();
                
                label.setText("<html>"+data[0]+": "+data[2]+"%"+"<br></html>");
                this.add(label);
                
                assignments.add(data);
                labels.add(label);
                
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
        
    }
    
}
