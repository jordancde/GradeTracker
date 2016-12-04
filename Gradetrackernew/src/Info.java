
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jordandearsley
 */
public class Info {
    public static String[] classes;

    public static String FILEPATHClasses;
    
    public static void main(String[] args) throws IOException{
        
        URL location = Info.class.getProtectionDomain().getCodeSource().getLocation();
        FILEPATHClasses = location.getFile()+"classes.csv";
        if(!(new File(FILEPATHClasses).canRead())){

            (new File(FILEPATHClasses)).createNewFile();
            
        }
        //writeClasses(new String[]{"History","Math","Functions","Computer Science"});
        classes = readClasses();
        
    }
    

    public static void writeClasses(String[] data) throws IOException{
        
        
        try(
            FileWriter fw = new FileWriter(FILEPATHClasses, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {       
            for(String className:classes){
                out.print(className+",");
            }
            
            out.println("");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Something Fucked up");
        }
    
    }
    public static String[] readClasses() throws IOException{
        
        String line = "";
        String cvsSplitBy = ",";
        String[] data = new String[0];
            
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATHClasses))) {

            while ((line = br.readLine()) != null) {
                
                // use comma as separator
                data = line.split(cvsSplitBy);
                
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
       
        
    }
}
