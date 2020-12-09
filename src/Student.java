
import java.util.ArrayList;

public class Student extends Person {
	
	// data members
	private int id;
	private ArrayList<Classroom> classesAttending;
	private ArrayList<Stranger> strangerInteractions;
	private ArrayList<Student> friendInteractions;
	
	// constructors
	public Student(boolean isInfected, boolean isQuarantined, boolean isImmune, int daysInQuarantine,
			float infectionProb, String name, int age, int id, ArrayList<Classroom> classesAttending,
			ArrayList<Stranger> strangerInteractions, ArrayList<Student> friendInteractions) {
		super(isInfected, isQuarantined, isImmune, daysInQuarantine, infectionProb, name, age);
		this.id = id;
		this.classesAttending = classesAttending;
		this.strangerInteractions = strangerInteractions;
		this.friendInteractions = friendInteractions;
	}

	// methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Classroom> getClassesAttending() {
		return classesAttending;
	}

	public void setClassesAttending(ArrayList<Classroom> classesAttending) {
		this.classesAttending = classesAttending;
	}

	public ArrayList<Stranger> getStrangerInteractions() {
		return strangerInteractions;
	}

	public void setStrangerInteractions(ArrayList<Stranger> strangerInteractions) {
		this.strangerInteractions = strangerInteractions;
	}

	public ArrayList<Student> getFriendInteractions() {
		return friendInteractions;
	}

	public void setFriendInteractions(ArrayList<Student> friendInteractions) {
		this.friendInteractions = friendInteractions;
	}	
	@Override
	public String toString() {
		return this.getName() + " " + this.getAge() + " " + this.getId() + " " + this.getIsInfected() + " " + this.getIsQuarantined() + " " + this.getIsImmune() + " " + this.getDaysInQuarantine(); 
	}
}
