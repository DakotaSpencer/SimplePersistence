package main;

import java.io.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Scanner;

public class Logic {
    public static String path = "./people/simple/";

    public static void run() throws IOException {
        System.out.println("PRINTING EACH ITEM");
        PrintPeopleDetails(path);
        System.out.println("PRINTING EMPLOYEE DETAILS");
        PrintEmployees(path);
        AddEmployee(12, "test", "test", 1);
    }

    public static void AddEmployee(Integer id, String firstName, String lastName, Integer hireDate) throws IOException {

        File myObj = new File("C:\\Users\\MyName\\filename.txt");

        String folder = "./people/long serialized/";
        String fileName = "filename.txt";

//        File f = new File(folder, fileName);
//        f.createNewFile();


        try {
            File f = new File(folder, fileName);
            f.createNewFile();

            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write("test");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public static void DeleteEmployee(Integer id){

    }

    public static void UpdateEmployee(Integer id, String firstName, String lastName, Integer hireDate){

    }

    public static void SerializeEmployee(){

        System.out.println(path);
        File f = new File("./people/simple/");
        String[] paths;
        paths = f.list();

        for (int i = 0; i < paths.length; i++) {
            var readString = readFromFile(path + paths[i]).split(",");
            System.out.println();
            var empID = Integer.parseInt(readString[0]);
            var empFNAME = readString[1].replaceAll("\\s", "");
            var empLNAME = readString[2].replaceAll("\\s", "");
            var empHIRE_YEAR = Integer.parseInt(readString[3].replaceAll("\\s", ""));

            var employee = new Employee(empID, empFNAME, empLNAME,empHIRE_YEAR);
            System.out.println(employee.toString());
        }
    }

//    public static Employee GetSerializedEmployee(Integer id){
//        return;
//    }

    public static String readFromFile(String filePath) {
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            line = bufferedReader.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return line;
    }

    public static void PrintPeopleDetails(String path) {
        System.out.println(path);
        //prints out the path
        File f = new File("./people/simple/");
        //sets the file path to an object
        String[] paths;
            //Both add file path to an iterable array
        paths = f.list();

        for (int i = 0; i < paths.length; i++) {
            //iterates though and prings every file
            //System.out.println( paths[i]);
            System.out.println(readFromFile(path + paths[i]));
        }
    }

    public static void PrintEmployees(String path) {
        System.out.println(path);
        File f = new File("./people/simple/");
        String[] paths;
        paths = f.list();

        for (int i = 0; i < paths.length; i++) {
            var readString = readFromFile(path + paths[i]).split(",");
            System.out.println();
            var empID = Integer.parseInt(readString[0]);
            var empFNAME = readString[1].replaceAll("\\s", "");
            var empLNAME = readString[2].replaceAll("\\s", "");
            var empHIRE_YEAR = Integer.parseInt(readString[3].replaceAll("\\s", ""));

            var employee = new Employee(empID, empFNAME, empLNAME,empHIRE_YEAR);
            System.out.println(employee.toString());
        }
    }
}
