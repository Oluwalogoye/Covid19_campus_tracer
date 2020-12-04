
import java.util.ArrayList;

public class Teacher extends Person {

  private ArrayList<Classroom> classes;

  public Teacher(boolean isInfected, boolean isQuarantined, boolean isImmune, int daysInQuarantine, String name, int age, ArrayList<Classroom> classes){
      super(isInfected, isQuarantined, isImmune, daysInQuarantine, name, age);
      this.classes = classes;
  }

  public ArrayList<Classroom> getClasses(){
    return this.classes;
  }
  public void setClasses(ArrayList<Classroom> classes){
    this.classes = classes;
  }
}
