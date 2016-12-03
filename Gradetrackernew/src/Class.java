
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
public class Class extends JPanel {
    public String className;
    
    
    public Class(String name){
        className = name;


    }
    
    public String getName(){
        return className;
    }
}
