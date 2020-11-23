
import java.util.ArrayList;

public class Student {
	
	// data members
	private String name;
	private int id;
	private ArrayList<Student> immediateContacts;
	private boolean isInfected;
	private boolean isQuarantined;
	private int daysInQurantine;
	private boolean isImmune;
	
	// Constructors
	
	public Student(String name, int id, ArrayList<Student> immediateContacts, boolean isInfected, boolean isQuarantined,
			int daysInQurantine, boolean isImmune) {
		this.name = name;
		this.id = id;
		this.immediateContacts = immediateContacts;
		this.isInfected = isInfected;
		this.isQuarantined = isQuarantined;
		this.daysInQurantine = daysInQurantine;
		this.isImmune = isImmune;
	}

	// Methods
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Student> getImmediateContacts() {
		return immediateContacts;
	}

	public void setImmediateContacts(ArrayList<Student> immediateContacts) {
		this.immediateContacts = immediateContacts;
	}

	public boolean isInfected() {
		return isInfected;
	}

	public void setInfected(boolean isInfected) {
		this.isInfected = isInfected;
	}

	public boolean isQuarantined() {
		return isQuarantined;
	}

	public void setQuarantined(boolean isQuarantined) {
		this.isQuarantined = isQuarantined;
	}

	public int getDaysInQurantine() {
		return daysInQurantine;
	}

	public void setDaysInQurantine(int daysInQurantine) {
		this.daysInQurantine = daysInQurantine;
	}

	public boolean isImmune() {
		return isImmune;
	}

	public void setImmune(boolean isImmune) {
		this.isImmune = isImmune;
	}

}
