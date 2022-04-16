package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Log {

    public static void log(String message){
        File filepath= new File("Log.txt");

        try(PrintWriter dataOutput = new PrintWriter(new FileOutputStream(filepath, true))){
            dataOutput.println(message);

        }
        catch(FileNotFoundException ex){
            System.err.println(ex.getMessage());

        }
    }
}
