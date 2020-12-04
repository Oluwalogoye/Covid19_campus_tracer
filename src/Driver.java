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
  public static CampusTracing campusTracing;
  
	public static void main (String[] args) {
		
		campusTracing = new CampusTracing();
		//
    	createTeachers(new File("teachers.txt"));
    	createClasses(new File("classes.txt"));
    	createStudents(new File("students"));
    	createInteractions(new File("interactions.txt"));
    	
    }
	
	public static void createTeachers(File file) {
		
		teachers = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String[] tokens = scanner.nextLine().split(" ");
				String name = tokens[0] + tokens[1];
				int age = Integer.valueOf(tokens[2]);
				boolean isInfected = Boolean.valueOf(tokens[3]);
				boolean isQuarantined = Boolean.valueOf(tokens[4]);
				boolean isImmune = Boolean.valueOf(tokens[5]);
				int daysInQuarantine = Integer.valueOf(tokens[6]);
				float infectionProb = Float.valueOf(age/100);
				ArrayList<Classroom> classes = new ArrayList<>(); 
				Teacher teacher = new Teacher(isInfected, isQuarantined, isImmune, daysInQuarantine, infectionProb, name, age, classes);
				teachers.add(teacher);
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void createClasses(File file) {
		classrooms = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String[] tokens = scanner.nextLine().split(" ");
				String teacherName = tokens[0] + " " + tokens[1];
				Teacher teacher = (Teacher) findPerson(teacherName);
				// create classes
				for(int i = 2; i < tokens.length; i++) {
					String className = tokens[i];
					int num = 0;
					ArrayList<Student> peers = new ArrayList<>();
					classrooms.add(new Classroom(className, num, peers));
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void createStrangers(File file) {
		strangers = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String[] tokens = scanner.nextLine().split(" ");
				String name = tokens[0] + tokens[1];
				int age = Integer.valueOf(tokens[2]);
				boolean isInfected = Boolean.valueOf(tokens[3]);
				boolean isQuarantined = Boolean.valueOf(tokens[4]);
				boolean isImmune = Boolean.valueOf(tokens[5]);
				int daysInQuarantine = Integer.valueOf(tokens[6]);
				float infectionProb = Float.valueOf((100 - age)/100);
				Stranger stranger = new Stranger(isInfected, isQuarantined, isImmune, daysInQuarantine, infectionProb, name, age);
				strangers.add(stranger);
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void createStudents(File file) {
//		strangers = new ArrayList<>();
		students = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String[] tokens = scanner.nextLine().split(" ");
				String name = tokens[0] + tokens[1];
				int age = Integer.valueOf(tokens[2]);
				int id = Integer.valueOf(tokens[3]);
				boolean isInfected = Boolean.valueOf(tokens[4]);
				boolean isQuarantined = Boolean.valueOf(tokens[5]);
				boolean isImmune = Boolean.valueOf(tokens[6]);
				int daysInQuarantine = Integer.valueOf(tokens[7]);
				float infectionProb = Float.valueOf((age)/100);
				// map student to classes
				String[] classNames = scanner.nextLine().split(" ");
				ArrayList<Classroom> classes = new ArrayList<>();
				for(String className : classNames) {
					Classroom oneClass = findClass(className);
					classes.add(oneClass);
				}
				Student student = new Student(isInfected, isQuarantined, isImmune, daysInQuarantine, infectionProb, name, age, id, classes, new ArrayList<Stranger>(), new ArrayList<Student>());
				
				// map classes to students
				for(Classroom classroom: classes) {
					classroom.setNum(classroom.getNum() + 1);
					classroom.getPeers().add(student);
				}

			}
			scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void createInteractions(File file) {

		try {
			
			Scanner scanner = new Scanner(file);
			String lastDayChecked = scanner.nextLine().split(" ")[0];
			String today = scanner.nextLine().split(" ")[0];
			campusTracing.setLastChecked(lastDayChecked);
			campusTracing.setToday(today);
			campusTracing.setPopulation(students);
			
			while(scanner.hasNextLine()) {
				String[] tokens = scanner.nextLine().split(" ");
				Student student = (Student)findPerson(tokens[0] + " " + tokens[1]);
				ArrayList<Person> immediateContacts = new ArrayList<>();
				
				for(int i = 2; i < tokens.length; i+=2) {
					Person stranger = findPerson(tokens[i] + " " + tokens[i + 1]);
					immediateContacts.add(stranger);
				}
				campusTracing.updateStudentImmediateContacts(student, immediateContacts);
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public static Person findPerson(String fullName) {
		
		for(Teacher teacher: teachers) {
			if(teacher.getName().equals(fullName)) {
				return teacher;
			}
		}
		for(Student student: students) {
			if(student.getName().equals(fullName)) {
				return student;
			}
		}
		for(Stranger stranger: strangers) {
			if(stranger.getName().equals(fullName))
				return stranger;
		}
		System.out.println("Person not Found");
		return null;
	}
	public static Classroom findClass(String className) {
		for(Classroom classroom : classrooms) {
			if(classroom.getName().equals(className)) {
				return classroom;
			}
		}
		return null;
	}
}
