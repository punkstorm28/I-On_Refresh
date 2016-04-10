/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Robocop
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {

    String fileName= "pref.pf";


    FileWrite(String inp, boolean append) {


        
      
        Writer(inp, append);

        //  System.out.println("in FileWriter");
    }

    public void Writer(String argToWrite, boolean APPEND) {
        try {

            FileWriter write = new FileWriter(fileName, APPEND);
            BufferedWriter Bwrite = new BufferedWriter(write);
            Bwrite.append(argToWrite);
            System.out.println("appending complete...");
            Bwrite.close();

        } catch (IOException e) {
            System.out.println("File Not Found");
        }
    }
}