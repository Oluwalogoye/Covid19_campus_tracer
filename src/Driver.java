// no implementation yet
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
 
public class Driver {
  
  public static ArrayList<Student> students;
  public static ArrayList<Teacher> teachers;
  public static ArrayList<Classroom> classrooms;
  public static ArrayList<Stranger> strangers;
  
    // in main, we would read teachers
    // read
    // main
    public static void main(String[] args) {
      // initalise all objects
      // interactions after creating file objects 
      createClasses(new File("studentClasses"));

      createStudents(new File("student.txt"));
      createTeachers(new File("teachers.txt"));
     
      createStrangers(new File("strangers.txt"));
    }

    public static void createStudents(File file){
          try {
            Scanner input = new Scanner(file);

            int daysSinceSemStarted = Integer.parseInt(input.nextLine());
            
            while (input.hasNextLine()) {
                String eachLine = input.nextLine();
                String[] tokens = eachLine.split(" ");
                
                int id = Integer.valueOf(tokens[0]);
                Sttring name = tokens[0] + tokens[1];
                <first name> <last name> <age> <id> <isInfected> <isQurantined> <isImmune> <daysInQuarantine> <interactions>
                Student s = new Student(boolean isInfected, boolean isQuarantined, boolean isImmune, int daysInQuarantine,
                float infectionProb, String name, int age, int id, ArrayList<Classroom> classesAttending,
                ArrayList<Stranger> strangerInteractions, ArrayList<Student> friendInteractions)
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    // check if created already;
    public static void createClasses(File file){
        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String raw = input.nextLine();
                String[] temp = raw.split(" ");
            }

        } catch (FileNotFoundException e) {
			    System.out.println("File not found");
			    e.printStackTrace();
        }
    }
    
    public static void createTeachers(File file){
        try {
            Scanner input = new Scanner(file);
            
            while (input.hasNextLine()) {
                String raw = input.nextLine();
                String[] temp = raw.split(" ");
            }
            
            
        } catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
        }
    }
    
    public static void createStrangers(File file){
        try {
            Scanner input = new Scanner(file);
            
            while (input.hasNextLine()) {
                String eachLine = input.nextLine();
                String[] tokens = eachLine.split(" ");
                
                String name =  tokens[0] + tokens[1];
                int age =  Integer.parseInt(tokens[2]);
                
                // get infection probabiliity
                boolean isInfected = false;;
                if((Math.random()) <= (1 - age/100)){
                  isInfected = true;
                }
                
                Stranger stranger =  new Stranger();
        }
            
        } catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
        }
    
    }
}