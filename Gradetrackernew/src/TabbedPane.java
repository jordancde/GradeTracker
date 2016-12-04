
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicSplitPaneDivider;

 
public class TabbedPane extends JFrame {
    
    
    public TabbedPane() throws IOException {
         
        setTitle("Grade Tracker");
        
        
        JTabbedPane jtp = new JTabbedPane();
        
        
        JSplitPane splitPane;
        
       
        
        ArrayList<JSplitPane> classPanels = new ArrayList<JSplitPane>();
        ArrayList<JPanel> classes = new ArrayList<JPanel>();
        double[] weights = new double[] {.5,.2,.3};
        for(String className :Info.classes){
            Class currentClass = new Class(className, weights);
            splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
                true, currentClass, new Graph(className));
            splitPane.setResizeWeight(0.5);
            JPanel average = new Average(className);
            JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
                true, splitPane, average);
            splitPane2.setResizeWeight(0.7);
            classes.add(currentClass);
            classPanels.add(splitPane2);
            
        }
        
        
        
        /*
        JLabel label1 = new JLabel();
        label1.setText("You are in area of Tab1");
        JLabel label2 = new JLabel();
        label2.setText("You are in area of Tab2");
        jp1.add(label1);
        jp2.add(label2);*/
        
        for(JSplitPane classPanel :classPanels){
            jtp.addTab(classes.get(classPanels.indexOf(classPanel)).getName(), classPanel);
        }
        
        
        getContentPane().add(jtp);
    }
    public static void main(String[] args) throws IOException {
        
        Info.main(args);
        TabbedPane tp = new TabbedPane();
        tp.setPreferredSize(new Dimension(500, 400));
        tp.pack();
        tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.setVisible(true);
         
    }
    public static void addAssignment(){
        String inputValue = JOptionPane.showInputDialog("Please input a value");
    }
    
}