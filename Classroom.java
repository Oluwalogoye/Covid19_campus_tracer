
import java.util.ArrayList;

public class Classroom {
	
	// data members
	private int num;
	private ArrayList<Student> peers;
	
	// constructors
	public Classroom(int num, ArrayList<Student> peers) {
		this.num = num;
		this.peers = peers;
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
}
