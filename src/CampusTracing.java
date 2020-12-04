import java.util.ArrayList;
import java.util.Random;

public class CampusTracing {
    
    // data members
    private ArrayList<Student> population;
    private int lastDayTested;
    
    /**
     * constructor
     */
    public CampusTracing(ArrayList<Student> p, int d) {
        population = p;
        lastDayTested = d;
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
     * @return lastDayTested
     */
    public int getLastDayTested() {
        return lastDayTested;
    }

    /**
     * @param d
     */
    public void setLastDayTested(int d) {
        lastDayTested = d;
    }

    /**
     * @param p
     */
    public void testStudents(int day) 
    {
        boolean firstTestToday = false;
        // If this is the first time testing today
        if(day != lastDayTested)
        {
            // Update that
            lastDayTested = day;
            firstTestToday = true;
        }
        // For each student
        for(Student student : population)
        {
            // Update quarantine status
            if(firstTestToday && student.getIsQuarantined())
            {
                student.setDaysInQuarantine(student.getDaysInQuarantine() + 1);
            }
            // Check if a student is immune (if he is he cannot get infected)
            if(!student.getIsImmune())
            {
                ArrayList<Stranger> strangers = student.getStrangerInteractions();
                ArrayList<Student> friends = student.getFriendInteractions();
                // loop for stranger
                for (Stranger stranger : strangers)
                {
                    if(stranger.getIsInfected() && !student.getIsQuarantined())
                    {
                        // If the stranger is infected quarantine student and get a random change of student actually becoming infected
                        student.setIsQuarantined(true);
                        student.setDaysInQuarantine(0);
                        // Infect student based on the infection probability ([1 - age] /100)
                        Random random = new Random();
                        if(random.nextInt(100) < ((1 - student.getAge())/100))
                        {
                            student.setIsInfected(true);
                        }
                    }
                    
                }
                // loop for friend
                for (Student friend : friends)
                {
                    if(friend.getIsInfected() && !student.getIsQuarantined())
                    {
                        // If the stranger is infected quarantine student and get a random change of student actually becoming infected
                        student.setIsQuarantined(true);
                        student.setDaysInQuarantine(0);
                        // Infect student based on the infection probability ([1 - age] /100)
                        Random random = new Random();
                        if(random.nextInt(100) < ((1 - student.getAge())/100))
                        {
                            student.setIsInfected(true);
                        }
                    }
                }
            }
            // Immediate contacts for students are erased after each testing, as students at risk 
            // (1-step of separation from infected student) are immediately isolated/quarantined.
            student.setStrangerInteractions(new ArrayList<Stranger>());
            student.setFriendInteractions(new ArrayList<Student>());
        }
    }

    /**
     * @param id
     * @param s
     * @param newContacts
     */
    public void updateStudentImmediateContacts(int id, ArrayList<Person> immediateContacts) 
    {
        for (Student student : population)
        {
            if(id == student.getId())
            {
                for(Person contact : immediateContacts)
                {
                    if(contact instanceof Stranger)
                    {
                        ArrayList<Stranger> sI = student.getStrangerInteractions();
                        sI.add((Stranger)contact);
                        student.setStrangerInteractions(sI);
                    }
                    else if(contact instanceof Student)
                    {
                        ArrayList<Student> sI = student.getFriendInteractions();
                        sI.add((Student)contact);
                        student.setFriendInteractions(sI);
                    }
                }
            }
            else
            {
                System.out.println("This Student does not exist");
            }
        }
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
    public void displayStatistics() 
    {
        // Need to figure out what data structure to make lastChecked.
        // Maybe an ArrayList of booleans for each day since semester started
        if(!lastChecked(day))
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
