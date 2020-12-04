
public abstract class Person extends HasHealth {
	
	// data members
	private String name;
	private int age;
	
	// constructors
	public Person(boolean isInfected, boolean isQuarantined, boolean isImmune, int daysInQuarantine, String name, int age) {
		super(isInfected, isQuarantined, isImmune, daysInQuarantine);
		this.name = name;
		this.age = age;
	}

	// methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}	
}
