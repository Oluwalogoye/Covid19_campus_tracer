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
     * @param percentInfected
     * helper method that returns risk level String to make code tidy
     */
    private String getRiskLevel(int percentInfected)
    {
        if(percentInfected <= 5)
        {
            return "Low";
        }
        else if(percentInfected <= 10)
        {
            return "Low Moderate";
        }
        else if(percentInfected <= 20)
        {
            return "Moderate";
        }
        else
        {
            return "High";
        }
    }

    /**
     * @param p
     */
    public void displayStatistics(ArrayList<Student> p) 
    {
        // Need to figure out what data structure to make lastChecked.
        // Maybe an ArrayList of booleans for each day since semester started
        if(!lastChecked(day))
        {
                testStudents(p);
        }

        // Statistics data holder
        ArrayList<Student> infected;
        ArrayList<Student> quarantined;
        ArrayList<Student> immune;
        int daysInQuarantine;
        int firstDayS, lastDayS;
        
        // Populate statistics
        for(Student s : p)
        {
            if(s.isInfected())
            {
                infected.add(s);
            }
            if(s.isQuarantined())
            {
                quarantined.add(s);
                daysInQuarantine += s.getDaysInQuarantine();
                // Need to make sure the cases are right
                if(s.getDaysInQuarantine() == 1)
                {
                    
                    firstDayS++;
                }
                else if(s.getDaysInQuarantine() == 14)
                {
                    lastDayS++;
                }
            }
            if(s.isImmune())
            {
                immune.add(s);
            }
        }

        // Process Statistics
        ArrayList<String> statistics;                                   // Hold all the strings to be printed
        int percentInfected = (infected.size() / p.size()) * 100;       // Percent of Infected People
        String riskLevel = getRiskLevel(percentInfected);               // Risk Level
        int avgQuarantine = daysInQuarantine / quarantined.size();      // Mean Days Spent in Quarantine
        // graph days spent in quarantine. calculate variance/std dev.

        statistics.add("There are " + infected.size() + " infected studies.");
        statistics.add("This makes up for " + percentInfected + "% of the Student Body.");
        statistics.add("There are " + quarantined.size() + "Students in quarantine, which have completed an average of " + avgQuarantine + " days already spent.");
        statistics.add( lastDayS + " students are doing their last day of quarantine, while " + firstDayS + " have been quarantined today");
        statistics.add("The campus is at a "+ riskLevel + " risk level.");
    }
}