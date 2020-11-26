
public abstract class HasHealth {

	// data members
	private boolean isInfected;
	private boolean isQuarantined;
	private boolean isImmune;
	private int daysInQuarantine;
	private float infectionProb;
	
	// constructor
	public HasHealth(boolean isInfected, boolean isQuarantined, boolean isImmune, int daysInQuarantine,
			float infectionProb) {
		this.isInfected = isInfected;
		this.isQuarantined = isQuarantined;
		this.isImmune = isImmune;
		this.daysInQuarantine = daysInQuarantine;
		this.infectionProb = infectionProb;
	}

	// methods
	public boolean isInfected() {
		return isInfected;
	}

	public void setInfected(boolean isInfected) {
		this.isInfected = isInfected;
	}

	public boolean isQuarantined() {
		return isQuarantined;
	}

	public void setQuarantined(boolean isQuarantined) {
		this.isQuarantined = isQuarantined;
	}

	public boolean isImmune() {
		return isImmune;
	}

	public void setImmune(boolean isImmune) {
		this.isImmune = isImmune;
	}

	public int getDaysInQuarantine() {
		return daysInQuarantine;
	}

	public void setDaysInQuarantine(int daysInQuarantine) {
		this.daysInQuarantine = daysInQuarantine;
	}

	public float getInfectionProb() {
		return infectionProb;
	}

	public void setInfectionProb(float infectionProb) {
		this.infectionProb = infectionProb;
	}
}
