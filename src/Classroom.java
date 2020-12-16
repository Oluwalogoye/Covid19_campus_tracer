
import java.util.ArrayList;

public class Classroom {
	
	// data members
	private String name;
	private int num;
	private ArrayList<Student> peers;
	private Teacher teacher;
	
	// constructors
	public Classroom(String name, int num, ArrayList<Student> peers, Teacher teacher) {
		this.name = name;
		this.num = num;
		this.peers = peers;
		this.teacher = teacher;
	}
	
	// methods
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public ArrayList<Student> getPeers() {
		return peers;
	}
	public void setPeers(ArrayList<Student> peers) {
		this.peers = peers;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
