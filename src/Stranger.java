
public class Stranger extends Person{
	
	// data members
	private float riskFactor;

	// constructors
	public Stranger(boolean isInfected, boolean isQuarantined, boolean isImmune, int daysInQuarantine,
			float infectionProb, String name, int age, float riskFactor) {
		super(isInfected, isQuarantined, isImmune, daysInQuarantine, infectionProb, name, age);
		this.riskFactor = riskFactor;
	}

	// methods
	public float getRiskFactor() {
		return riskFactor;
	}

	public void setRiskFactor(float riskFactor) {
		this.riskFactor = riskFactor;
	}
	
	
}
