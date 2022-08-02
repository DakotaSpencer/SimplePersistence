package main;

import java.io.*;
import java.util.Scanner;

public class Logic {
    public static String path = "./people/simple/";

    public static void run(){
        System.out.println("Hi");
        PrintPeopleDetails(path);
    }

    public static String readFromFile(String filePath){
        String line = "";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            line = bufferedReader.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return line;
    }

    public static void PrintPeopleDetails(String path){
        System.out.println( path);
        File f = new File("./people/simple/");
        String[] paths;
        paths = f.list();

        for(int i = 0; i < paths.length; i++) {
            //System.out.println( paths[i]);
            System.out.println( readFromFile(path + paths[i]));
        }
    }
}
