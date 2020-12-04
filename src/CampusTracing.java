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
    public void updateStudentImmediateContacts(int id, ArrayList<Person> newContacts) {
        
        for (i = 0; i < population.size(); i++){
            if (id == population.get(i).getId()){
                for (n = 0; n < immediateContacts.size(); n++){
                    if (immediateContacts.get(n) instanceof Stranger){
                        strangerInteractions.add(n);
                    } else if (immediateContacts.get(n) instanceof Student){
                        friendInteractions.add(n);
                    }
                }
            } else {
                System.out.println("This Student does not exist");
            }
        }
    }

    /**
     * @param p
     */
    public void displayStatistics(ArrayList<Student> p) {
        
    }
}
