package main;

import java.io.*;
import java.util.*;


public class Logic implements java.io.Serializable {
    public static String path = "./people/simple/";

    public static void run() throws IOException {
//        System.out.println("PRINTING EACH EMPLOYEE");
        
//        long startTime = System.nanoTime();
//        long endTime = System.nanoTime();
//        PrintPeopleDetails(path);
//        long duration = (endTime - startTime);
//        System.out.println("Method took " + duration / 1000000 + " ms to complete");

//        System.out.println("PRINTING EMPLOYEE DETAILS");
//        PrintEmployees(path);
//        System.out.println("RUNNING ADD EMPLOYEE");


//        AddEmployee(11110, "test", "test", 1);
//        System.out.println("RUNNING SERIALIZE EMPLOYEE");
//        SerializeEmployee();
//        System.out.println("DELETE EMPLOYEE");
//        DeleteEmployee(11110); //<-- enter id number there
//        System.out.println("UPDATE EMPLOYEE");
//        UpdateEmployee(347, "Nelly", "Morgan", 2007);
//        System.out.println("GET SERIALIZED EMPLOYEE");
//        GetSerializedEmployee(1);

//        System.out.println("FIND EMPLOYEE BY ID");
//        FindEmployeeById(4);
//        System.out.println();
//        System.out.println();
//        System.out.println("FIND EMPLOYEE BY LAST NAME");
//        FindEmployeeByLastName("FOSTER");
        //GetAllEmployees(new File("people\\simple"));
       // PrintAllEmployees();
        //PrintSerializedDetails(new File("people\\simple serialized"));
        GetSerializedEmployee(1);

    }


    public static Employee FindEmployeeById(int id) {
        String path = "./people/long/";
        System.out.println("ID given:" + id);
        System.out.println();
        File f = new File("./people/long/");
        String[] paths;
        paths = f.list();
        Employee employee = null;

        for (int i = 0; i < paths.length; i++) {

            var readString = readFromFile(path + paths[i]).split(",");
            var empID = Integer.parseInt(readString[0]);
            var empFNAME = readString[1].replaceAll("\\s", "");
            var empLNAME = readString[2].replaceAll("\\s", "");
            var empHIRE_YEAR = Integer.parseInt(readString[3].replaceAll("\\s", ""));
            if (empID == id) {
                // Reading the object from a file
                employee = new Employee(empID, empFNAME, empLNAME, empHIRE_YEAR);
            }
        }


        System.out.println(employee);
        return employee;
    }

    public static Employee FindEmployeeByLastName(String lastName){
        String path = "./people/long/";
        System.out.println("Last Name given: " + lastName);
        File f = new File("./people/long/");
        String[] paths;
        paths = f.list();
        Employee employee = null;

        for (int i = 0; i < paths.length; i++) {

            var readString = readFromFile(path + paths[i]).split(",");

            var empID = Integer.parseInt(readString[0]);
            var empFNAME = readString[1].replaceAll("\\s", "");
            var empLNAME = readString[2].replaceAll("\\s", "");
            var empHIRE_YEAR = Integer.parseInt(readString[3].replaceAll("\\s", ""));

            if (empLNAME.contains(lastName)) {
                // Reading the object from a file
                System.out.println();
                System.out.println("MATCH FOUND");
                System.out.println();

                employee = new Employee(empID, empFNAME, empLNAME, empHIRE_YEAR);
                System.out.println(employee);
                //IF BREAK IS REMOVED, THIS THEN RETURNS EVERY PERSON WITH THE SAME LAST NAME GIVEN
                break;
            }
        }
        return employee;
    }

    public static List<Employee> FindAllEmployeesByLastName(String lastName){

        String path = "./people/long/";
        System.out.println("Last Name given:" + lastName);
        System.out.println(path);
        File f = new File("./people/long/");
        String[] paths;
        paths = f.list();
        Employee employee = null;

        for (int i = 0; i < paths.length; i++) {

            //logic
//            Searches all employee records for the first record with the given lastName
//            Returns the first matching record as an employee object
        }


        System.out.println(employee);
        return (List<Employee>) employee;
    }

    public static HashMap<Integer, Employee> GetAllEmployees(File path){
        HashMap<Integer, Employee> empMap = new HashMap<>();
        Employee employee = null;

        File[] f = new File(path.toURI()).listFiles(); // assuming long serialized has the .ser files
        System.out.println(f);

        for (int i = 0; i < f.length; i++) {

            var readString = readFromFile(String.valueOf(f[i])).split(",");

            var empID = Integer.parseInt(readString[0]);
            var empFNAME = readString[1].replaceAll("\\s", "");
            var empLNAME = readString[2].replaceAll("\\s", "");
            var empHIRE_YEAR = Integer.parseInt(readString[3].replaceAll("\\s", ""));

            employee = new Employee(empID, empFNAME, empLNAME, empHIRE_YEAR);

            empMap.put(i, employee);
        }
        return empMap;
    }
    public static void PrintAllEmployees() {
        GetAllEmployees(new File("people\\simple")).forEach((key, value) -> {
            System.out.println(key + " | " + value);
        });
    }

    public static void PrintSerializedDetails(File path){

        System.out.println(path);
        File[] f = new File(path.toURI()).listFiles(); // assuming long serialized has the .ser files
        System.out.println(f.toString());

        Employee employee = null;

        for (int i = 0; i < f.length; i++) {

            File filename = f[i];
            System.out.println(filename);

            try {
                // Reading the object from a file
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                System.out.println(in.readObject());
              //  employee = (Employee) in.readObject();

                in.close();
                file.close();

//                System.out.println("Object has been deserialized ");
//                System.out.println("ID = " + employee.id);
//                System.out.println("First Name = " + employee.firstName);
//                System.out.println("Last Name = " + employee.lastName);
//                System.out.println("Hire Year = " + employee.hireYear);
            } catch (Exception ex) {
                System.out.println("IOException is caught");
                ex.printStackTrace();
            }
        }
    }

    //Part 2

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
                System.out.println("File " + id + ".txt has been successfully updated" );
            }else{
                System.out.println("ID given: " + id + "is invalid. ID cannot be less than 10000");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void DeleteEmployee(Integer id){
        File deletedFile = new File("people/long/" + id + ".txt"); //or the path of the file you want to delete
        //File idDeleteFile = new File(deletedFile.getName() + ".txt");
        if(deletedFile.delete()){
            System.out.println(deletedFile.getName() + " has been deleted.");
        } else{
            System.out.println("L bozo the file is still there");
        }
    }

    public static void UpdateEmployee(Integer id, String firstName, String lastName, Integer hireDate){
        String folder = "./people/long/";
        String fileName =  id + ".txt";

        //determining how many items are in folder
        path = "./people/long/";
        System.out.println(path);
        File file = new File("./people/long/");
        String[] paths;
        paths = file.list();

        for (int i = 0; i < paths.length; i++) {
            if (id == i) {
                try {
                    File f = new File(folder, fileName);
                    f.createNewFile();
                    FileWriter myWriter = new FileWriter(f);
                    myWriter.write(id + ", " + firstName + ", " + lastName + ", " + hireDate);
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            else{
                continue;
            }
        }
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

    public static Employee GetSerializedEmployee(Integer id) {
      //  String path = "./people/long serialized/";
      //  System.out.println(path);
       // File[] f = ; // assuming long serialized has the .ser files
        Employee employee = null;

       for(File entry : new File("people\\simple serialized").listFiles()) {

           try {
               // Reading the object from a file
               FileInputStream file = new FileInputStream(entry);
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
           } catch (Exception ex) {
               System.out.println("IOException is caught");
               ex.printStackTrace();
           }

        }
        return employee;
    }



    //Part 1

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

    public static String readFromFile(String filePath) {
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            line = bufferedReader.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return line;
    }
}
