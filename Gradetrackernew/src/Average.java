
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jordandearsley
 */
public class Average extends JPanel {
    
    
   
    public String className;
    public String FILEPATHAssignments;
    public ArrayList<String[]> assignments;
    
    
    public Average(String className) throws IOException{
        URL location = Info.class.getProtectionDomain().getCodeSource().getLocation();
        FILEPATHAssignments = location.getFile()+className+"tests.csv";
        if(!(new File(FILEPATHAssignments).canRead())){

            (new File(FILEPATHAssignments)).createNewFile();
            
        }
        JLabel label = new JLabel();
        label.setText(Double.toString(Math.round((calculateAverage())))+"%");
        label.setFont (label.getFont ().deriveFont (50.0f));
        this.add(label);
        //JLabel label2 = new JLabel();
        //label2.setText("You need "+Math.round(calculateNeededMark(70))+"% on your next test to achieve a 95%");
        //this.add(label2);
    }
    public double calculateNeededMark(int goal) throws IOException{
        readAssignments();
        double sum = 0;
        for(int i = 0; i<assignments.size();i++){
            sum+=Double.parseDouble(assignments.get(i)[2]);           
        }
        double average = sum/assignments.size();
      
        return goal/(0.1*0.9*average)*10;
        
        
    }
    
    public double calculateAverage() throws IOException{
        readAssignments();
        double sum = 0;
        for(int i = 0; i<assignments.size();i++){
            sum+=Double.parseDouble(assignments.get(i)[2]);
            
                    
        }
        return sum/assignments.size();
    
    }
    public void readAssignments() throws IOException{
        
        String line = "";
        String cvsSplitBy = ",";
        assignments = new ArrayList<String[]>();
        
            
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATHAssignments))) {

            while ((line = br.readLine()) != null) {
                
                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                
                
                
                assignments.add(data);
                
                
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }
    
    
}
