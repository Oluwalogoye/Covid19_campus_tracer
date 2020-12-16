import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
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
        	// update quarantine dates
        	updateQuarantineDate(student);
        	
        	// check for infected students
        	checkIfInfected(student);
        	if(student.getIsInfected())
        		quarantine(student);
        	
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
    
    	student.setDateLastTested(lastChecked);
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
    public void histogram (ArrayList<Integer[]> percents) {
    	//print and underline text that indicates this method will print class histogram
    	System.out.println("Printing histogram\n" + "-".repeat(20) + "\n");
		//code to develop the histogram
		
		//some important variables to develop the histogram
		int histogramWidth = 5;
		int maxDigits = 5; //maximum score, 100, is 3 digits
		int topScore =  100;
		int elements = percents.size();
		//create a temporary arrayList and add the values in the the ArrayList of scores to it; it's stores all the scores that will be added to the y-axis of the histogram
		ArrayList<Integer> temporary = new ArrayList<>();
		for (int i = 0; i <= 10; i ++) {
			temporary.add(i * 10);
		}
		
		//treat the number of lines from the topScore to - 1 as a grid
		//beginning from the score of the top scorer to 0, loop through each row 
		for(int i = topScore; i >= -1; i -- ) {
			
			//set the first 3 (maximum Digits of a score) columns of every row to the index if the index is one of the scores in the scores ArrayList; otherwise, put 3 (maximum Digits of a score) spaces
			if(i > 0 && temporary.contains(i)) {
				//enter the digits of the score and the appropriate space, so that their sum is 3 (maximum Digits of a score)
				String num = Integer.toString(i);
				for(int p = 0; p < maxDigits; p ++ ) {
					if(p < num.length()) {
						System.out.print(num.charAt(p));
					}
					else {
						System.out.print(" ");
					}
				}
				if (i >= 0)
					System.out.print("|");
				else 
					System.out.print(" ");
				//delete the added score from the temporary duplicate of scores to avoid repetition
				if(temporary.contains(i)) {
					temporary.remove(temporary.indexOf(i));
				}
				
			}
			//enter 3 (maximum Digits of a score) spaces if the index isn't in the scores ArrayList
			else {
				for(int s= 0;s < maxDigits; s++) {
					System.out.print(" ");
				}
				if(i >= 0)
					System.out.print("|");
				else 
					System.out.print(" ");
			}
			//loop through every column to add the appropriate data 
			for(int j = 0; j < elements * histogramWidth; j += histogramWidth){
				System.out.print(" ");
				if(i == 0) {
					for(int q = 0; q < histogramWidth; q ++) {
						System.out.print("-");
					}
				}
				else if(i == -1) {
					String date = String.valueOf(percents.get(j/histogramWidth)[1]);
					for(int f = 0; f < histogramWidth; f++) {
						if(f < date.length()) {
							System.out.print(date.charAt(f));
						}
						else {
							System.out.print(" ");
						}
					}
				}
				else if(i == (percents.get(j/histogramWidth)[0])) {
					for(int q = 0; q < histogramWidth; q ++) {
						System.out.print("-");
					}
				}
				else if(percents.get(j/histogramWidth)[0] > i) {
					for(int q = 0; q < histogramWidth; q++) {
						if( q == 0 || q == histogramWidth - 1) {
							System.out.print("|");
						}
						else{
							System.out.print(" ");
						}
					}
				}
				else if(i > percents.get(j/histogramWidth)[0]) {
					for(int q = 0; q< histogramWidth; q++) {
						System.out.print(" ");
					}					
				}
			}
			System.out.println();
		}
    }
    
    public void addAllPrevStats(ArrayList<Integer[]> list) {
    	try {
    		Scanner scanner = new Scanner(new File("prevStats.txt"));
    		while(scanner.hasNextLine()) {
    			String[] tokens = scanner.nextLine().split(" ");
    			int perc = Integer.valueOf(tokens[0]);
    			int date = Integer.valueOf(tokens[1]);
    			list.add(new Integer[] {perc, date});
    		}
    		scanner.close();
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
    }
    public void addNewStat(Integer[] newPercInfo) {
    	try {
    		FileWriter writer = new FileWriter("prevStats.txt", true);
    		writer.write(newPercInfo[0] + " " + newPercInfo[1] + "\n");
    		writer.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    public void displayStatistics() 
    {
        // Statistics data holder
        ArrayList<Student> infected = new ArrayList<>();;
        ArrayList<Student> quarantined = new ArrayList<>();
        ArrayList<Student> immune = new ArrayList<>();
        int daysInQuarantine = 0;
        int firstDayS = 0, lastDayS = 0;
        
        // Populate statistics
        for(Student s : population) {
            if(s.getIsInfected()) {
                infected.add(s);
            }
            if(s.getIsQuarantined()) {
                quarantined.add(s);
                daysInQuarantine += s.getDaysInQuarantine();
                // Need to make sure the cases are right
                if(s.getDaysInQuarantine() == 0) {
                    firstDayS++;
                }
                else if (s.getDaysInQuarantine() == 14) {
                    lastDayS++;
                }
            }
            if (s.getIsImmune()) {
                immune.add(s);
            }
        }

        // Process Statistics
        ArrayList<String> statistics = new ArrayList<>();                                   // Hold all the strings to be printed
        double percentInfected = ((infected.size() * 1.0) / population.size()) * 100;       // Percent of Infected People
        int percAsInt = (int)Math.round(percentInfected);
        String riskLevel = getRiskLevel(percentInfected);               // Risk Level
        double avgQuarantine = quarantined.size() != 0 ? ((daysInQuarantine * 1.0) / quarantined.size()) : 0;      // Mean Days Spent in Quarantine
        // graph days spent in quarantine. calculate variance/std dev.
        
        System.out.println();
        
        statistics.add("There are " + infected.size() + " infected students.");
        statistics.add("This makes up for " + percAsInt + "% of the Student Body.");
        statistics.add("There are " + quarantined.size() + " Students in quarantine, which have completed an average of " + avgQuarantine + " days already.");
        statistics.add( lastDayS + " students are doing their last day of quarantine, while " + firstDayS + " have been quarantined today");
        statistics.add("The campus is at a "+ riskLevel + " risk level.");
        
        
        for(String stat: statistics) {
        	System.out.println(stat);
        }
        System.out.println("*".repeat(40) + "\n");
        // call histogram
        
        ArrayList<Integer[]> percents = new ArrayList<>();
        addAllPrevStats(percents);
        Integer[] percentInfo = new Integer[] {percAsInt, Integer.valueOf(today)};
        percents.add(percentInfo);
        addNewStat(percentInfo);
        histogram(percents);
    }
    
}
