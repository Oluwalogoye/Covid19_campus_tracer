import java.util.ArrayList;

public class CampusTracing {
    
    // data members
    private ArrayList<Student> population;
    private String lastChecked;
    
    /**
     * constructor
     */
    public CampusTracing(ArrayList<Student> p, String c) {
        population = p;
        lastChecked = c;
    }

    /**
     * @return population
     */
    public ArrayList<Student> getPopulation() {
        return population;
    }

    /**
     * @param p
     */
    public void setPopulation(ArrayList<Student> p) {
        population = p;
    }

    /**
     * @return lastChecked
     */
    public String getLastChecked() {
        return lastChecked;
    }

    /**
     * @param c
     */
    public void setLastChecked(String c) {
        lastChecked = c;
    }

    /**
     * @param p
     */
    public void testStudents(ArrayList<Student> p) {

    }

    /**
     * @param id
     * @param s
     * @param newContacts
     */
    public void updateStudentImmediateContacts(int id, ArrayList<Student> s, ArrayList<Person> newContacts) {

    }

    /**
     * @param p
     */
    public void displayStatistics(ArrayList<Student> p) {
        
    }
}