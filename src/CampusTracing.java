import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

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
    
    public void testStudents() {
    	
    	StringBuilder buffer = new StringBuilder();
    	for(int i = 0; i < population.size(); i++) {
    		Student student = population.get(i);
    		// check for infected students
        	checkIfInfected(student);
        	if(student.getIsInfected())
        		quarantine(student);
        	
        	// update quarantine dates
        	updateQuarantineDate(student);
        	
        	// quarantine entire class if student infected
        	if(student.getIsInfected()) {
        		quarantinePeopleInSameClasses(student);
        	}
        	// write to buffer
        	addIndividualStudentInformationToBuffer(buffer, student, i);
    	}
    	// write updates to buffer
    	writeBackToBuffer(buffer);
    }
    
    private void addIndividualStudentInformationToBuffer(StringBuilder buffer, Student student, int i) {
    	buffer.append(student.toString() + "\n");
    	ArrayList<Classroom> classes = student.getClassesAttending(); 
    	for(int j = 0; j < classes.size(); j++) {
    		buffer.append(classes.get(j).getName());
    		if(j != (classes.size() - 1))
    			buffer.append(" ");
    	}
    	// add new line
    	if(i != (population.size() - 1))
    		buffer.append("\n");
    }
    private void writeBackToBuffer(StringBuilder buffer) {
    	
    	try {
    		// write updates from buffer
        	FileWriter writer = new FileWriter("students.txt", false);
    		writer.write(buffer.toString());
    		writer.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    private void quarantinePeopleInSameClasses(Student student) {
    	for(Classroom classroom: student.getClassesAttending()) {
    		Teacher teacher = classroom.getTeacher();
    		ArrayList<Student> peers = classroom.getPeers();
    		// quarantine teacher
    		quarantine(teacher);
    		// quarantine classroom
    		for(Student peer: peers) {
    			quarantine(peer);
    		}
    	}
    }
    private void quarantine(Person person) {
    	if(!person.getIsQuarantined() && !person.getIsImmune()) {
			person.setDaysInQuarantine(0);
			person.setQuarantined(true);
		}
    }
    
    private void updateQuarantineDate(Student student) {
    
		if(student.getIsQuarantined()) {
			int daysSinceLastTested = Integer.valueOf(today) - Integer.valueOf(student.getDateLastTested());
			int daysSpent = student.getDaysInQuarantine();
			student.setDaysInQuarantine(daysSpent + daysSinceLastTested);
		}
    	
    }
    private void checkIfInfected(Student student) {
    
		boolean isImmune = student.getIsImmune();
		for(Person contact: student.getStrangerInteractions()) {
			if(contact instanceof Student)
				determineIfInfected(!isImmune && contact.getIsInfected(), student);
			else
				determineIfInfected(contact instanceof Stranger, student);
		}
		
		for(Person contact: student.getFriendInteractions()) {
			if(contact instanceof Student)
				determineIfInfected(!isImmune && contact.getIsInfected(), student);
			else
				determineIfInfected(contact instanceof Stranger, student);
		}
		
		student.setDateLastTested(today);
    	
    }
    private void determineIfInfected(boolean conditionForGettingInfected, Person person) {
    	if(conditionForGettingInfected) {
    		// if person isn't already infected
    		if(!person.getIsInfected()) {
    			boolean infected = Math.random() <= person.getInfectionProb();
    			person.setInfected(infected);
    		}
    	}
    }
    private String getRiskLevel(double percentInfected) {
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


    public void displayStatistics() 
    {
    	// Need to figure out what data structure to make lastChecked.
    	// Maybe an ArrayList of booleans for each day since semester started
        if(!lastChecked.equals(today))
        {
        	testStudents();
        }

        // Statistics data holder
        ArrayList<Student> infected = new ArrayList<>();;
        ArrayList<Student> quarantined = new ArrayList<>();
        ArrayList<Student> immune = new ArrayList<>();
        int daysInQuarantine = 0;
        int firstDayS = 0, lastDayS = 0;
        
        // Populate statistics
        for(Student s : population)
        {
            if(s.getIsInfected())
            {
                infected.add(s);
            }
            if(s.getIsQuarantined())
            {
                quarantined.add(s);
                daysInQuarantine += s.getDaysInQuarantine();
                // Need to make sure the cases are right
                if(s.getDaysInQuarantine() == 0)
                {
                    
                    firstDayS++;
                }
                else if(s.getDaysInQuarantine() == 14)
                {
                    lastDayS++;
                }
            }
            if(s.getIsImmune())
            {
                immune.add(s);
            }
        }

        // Process Statistics
        ArrayList<String> statistics = new ArrayList<>();                                   // Hold all the strings to be printed
        double percentInfected = ((infected.size() * 1.0) / population.size()) * 100;       // Percent of Infected People
        String riskLevel = getRiskLevel(percentInfected);               // Risk Level
        double avgQuarantine = quarantined.size() != 0 ? ((daysInQuarantine * 1.0) / quarantined.size()) : 0;      // Mean Days Spent in Quarantine
        // graph days spent in quarantine. calculate variance/std dev.

        statistics.add("There are " + infected.size() + " infected studies.");
        statistics.add("This makes up for " + percentInfected + "% of the Student Body.");
        statistics.add("There are " + quarantined.size() + " Students in quarantine, which have completed an average of " + Math.round(avgQuarantine) + " days already spent.");
        statistics.add( lastDayS + " students are doing their last day of quarantine, while " + firstDayS + " have been quarantined today");
        statistics.add("The campus is at a "+ riskLevel + " risk level.");
        for(String stat: statistics) {
        	System.out.println(stat);
        }
    }
}
