import java.util.ArrayList;

public class CampusTracing {
    
    // data members
    private ArrayList<Student> population;
    private String lastChecked;
    private String today;
    
  
    public CampusTracing() {
    	
    }
    


	public CampusTracing(ArrayList<Student> p, String c, String today) {
        population = p;
        lastChecked = c;
        this.today = today;
    }

    public ArrayList<Student> getPopulation() {
        return population;
    }

    public void setPopulation(ArrayList<Student> p) {
        population = p;
    }

    public String getLastChecked() {
        return lastChecked;
    }

    public void setLastChecked(String c) {
        lastChecked = c;
    }

    public void testStudents(ArrayList<Student> p) {

    }
    public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

    public void updateStudentImmediateContacts(Student student, ArrayList<Person> immediateContacts) {
        
    	for(Person person: immediateContacts) {
    		if (person instanceof Student)
    			student.getFriendInteractions().add((Student)person);
    		else 
    			student.getStrangerInteractions().add((Stranger)person);
    	} 
    }


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


    public void displayStatistics(ArrayList<Student> p) 
    {
    	// Need to figure out what data structure to make lastChecked.
        // Maybe an ArrayList of booleans for each day since semester started
        if(!lastChecked.equals(today))
        {
                testStudents(population);
        }

        // Statistics data holder
        ArrayList<Student> infected;
        ArrayList<Student> quarantined;
        ArrayList<Student> immune;
        int daysInQuarantine;
        int firstDayS, lastDayS;
        
        // Populate statistics
        for(Student s : population)
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
        int percentInfected = (infected.size() / population.size()) * 100;       // Percent of Infected People
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
