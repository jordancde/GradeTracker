
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Graph extends JPanel {
    public int width;
    public int height;
    public String className;
    public String FILEPATHAssignments;
    public ArrayList<String[]> assignments;
    
    public Graph(String name) throws IOException{
        className = name;
        width = this.getWidth();
        height = this.getHeight();
        URL location = Info.class.getProtectionDomain().getCodeSource().getLocation();
        FILEPATHAssignments = location.getFile()+className+"tests.csv";
        if(!(new File(FILEPATHAssignments).canRead())){

            (new File(FILEPATHAssignments)).createNewFile();
            
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            readAssignments();
        } catch (IOException ex) {
            Logger.getLogger(Graph.class.getName()).log(Level.SEVERE, null, ex);
        }
        Graphics2D g2d = (Graphics2D) g;
        
        
        
        this.setOpaque(true);
        g2d.setColor(Color.blue); 
        for(int i = 0; i<assignments.size();i++){
            double mark =Double.parseDouble(assignments.get(i)[2]);
            g2d.fillOval((i+1)*this.getWidth()/(assignments.size()+1)-10, this.getHeight()-(int)Math.round(this.getHeight()*mark/100)-10, 20, 20);
            if(i<assignments.size()-1){
                double nextmark = Double.parseDouble(assignments.get(i+1)[2]);
                g2d.drawLine((i+1)*this.getWidth()/(assignments.size()+1), this.getHeight()-(int)Math.round(this.getHeight()*mark/100),(i+2)*this.getWidth()/(assignments.size()+1) , this.getHeight()-(int)Math.round(this.getHeight()*nextmark/100));
            }
        }
        
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
