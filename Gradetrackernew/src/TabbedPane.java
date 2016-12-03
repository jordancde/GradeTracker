import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

 
public class TabbedPane extends JFrame {
     
    public TabbedPane() {
         
        setTitle("Grade Tracker");
        
        
        JTabbedPane jtp = new JTabbedPane();
        JPanel average = new JPanel();
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
                true, jtp, average);
        
        splitPane.setResizeWeight(0.8);
        
        ArrayList<JPanel> classPanels = new ArrayList<JPanel>();
        for(String className :Info.classes){
            classPanels.add(new Class(className));
        }
        
        
        
        /*
        JLabel label1 = new JLabel();
        label1.setText("You are in area of Tab1");
        JLabel label2 = new JLabel();
        label2.setText("You are in area of Tab2");
        jp1.add(label1);
        jp2.add(label2);*/
        
        for(JPanel classPanel :classPanels){
            jtp.addTab(classPanel.getName(), classPanel);
        }
        
        
        getContentPane().add(splitPane);
    }
    public static void main(String[] args) throws IOException {
        Info.main(args);
        TabbedPane tp = new TabbedPane();
        tp.setPreferredSize(new Dimension(400, 600));
        tp.pack();
        tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.setVisible(true);
         
    }
}