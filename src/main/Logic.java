package main;

import java.io.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors


public class Logic implements java.io.Serializable {
    public static String path = "./people/simple/";

    public static void run() throws IOException {
        System.out.println("PRINTING EACH ITEM");
        PrintPeopleDetails(path);
        System.out.println("PRINTING EMPLOYEE DETAILS");
        PrintEmployees(path);
        System.out.println("RUNNING ADD EMPLOYEE");
        AddEmployee(11110, "test", "test", 1);
        System.out.println("RUNNING SERIALIZE EMPLOYEE");
        SerializeEmployee();
    }

    public static void AddEmployee(Integer id, String firstName, String lastName, Integer hireDate) throws IOException {
        String folder = "./people/long/";
        String fileName =  id + ".txt";

        //determining how many items are in folder
        path = "./people/long/";
        System.out.println(path);
        File file = new File("./people/long/");
        String[] path;
        path = file.list();

        try {
            if (id >= path.length){
                File f = new File(folder, fileName);
                f.createNewFile();
                FileWriter myWriter = new FileWriter(f);
                myWriter.write(id + ", " + firstName + ", " + lastName + ", " + hireDate);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            }else{
                System.out.println("ID given: " + id + "is invalid. ID cannot be less than 10000");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    public static void DeleteEmployee(Integer id){

    }

    public static void UpdateEmployee(Integer id, String firstName, String lastName, Integer hireDate){

    }

    public static void SerializeEmployee() throws IOException {
        String newPath = "./people/long/";
        System.out.println(newPath);
        File f = new File("./people/long/");
        String[] paths;
        paths = f.list();

        for (int i = 0; i < paths.length; i++) {
            var readString = readFromFile(newPath + paths[i]).split(",");
            System.out.println();
            var empID = Integer.parseInt(readString[0]);
            var empFNAME = readString[1].replaceAll("\\s", "");
            var empLNAME = readString[2].replaceAll("\\s", "");
            var empHIRE_YEAR = Integer.parseInt(readString[3].replaceAll("\\s", ""));

            var employee = new Employee(empID, empFNAME, empLNAME,empHIRE_YEAR);
            System.out.println(employee);

            String filename = empID + ".ser";

            try
            {
                File targetDir=new File("./people/long serialized/");
                File targetFile=new File(targetDir, filename);

                //Saving of object in a file in the right directory
                FileOutputStream file = new FileOutputStream(targetFile);
                ObjectOutputStream out = new ObjectOutputStream(file);

                // Method for serialization of object
                out.writeObject(employee);

                out.close();
                file.close();

                System.out.println("Object has been serialized");

            }

            catch(IOException ex)
            {
                System.out.println("IOException is caught");
                ex.printStackTrace();
            }
        }

    }

    public static Employee GetSerializedEmployee() {
        String newPath = "./people/long/";
        System.out.println(newPath);
        File f = new File("./people/long/");
        String[] paths;
        paths = f.list();

        for (int i = 0; i < paths.length; i++) {
            Employee employee = null;

            String filename = i + ".ser";

            try {
                // Reading the object from a file
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                employee = (Employee) in.readObject();

                in.close();
                file.close();

                System.out.println("Object has been deserialized ");
                System.out.println("ID = " + employee.id);
                System.out.println("First Name = " + employee.firstName);
                System.out.println("Last Name = " + employee.lastName);
                System.out.println("Hire Year = " + employee.hireYear);
            } catch (IOException ex) {
                System.out.println("IOException is caught");
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException is caught");
            }
            return employee;
        }
        return null;
    }

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
