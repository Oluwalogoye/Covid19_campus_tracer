
public abstract class HasHealth {

	// data members
	private boolean isInfected;
	private boolean isQuarantined;
	private boolean isImmune;
	private int daysInQuarantine;
	private float infectionProb;
	private String dateLastTested;
	
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
	public boolean getIsInfected() {
		return isInfected;
	}

	public void setInfected(boolean isInfected) {
		this.isInfected = isInfected;
	}

	public boolean getIsQuarantined() {
		return isQuarantined;
	}

	public void setQuarantined(boolean isQuarantined) {
		this.isQuarantined = isQuarantined;
	}

	public boolean getIsImmune() {
		return isImmune;
	}

	public void setImmune(boolean isImmune) {
		this.isImmune = isImmune;
	}

	public int getDaysInQuarantine() {
		return daysInQuarantine;
	}

	public void setDaysInQuarantine(int daysInQuarantine) {
		if(daysInQuarantine >= 14) {
			isImmune = true;
			daysInQuarantine = 0;
		} else {
			this.daysInQuarantine = daysInQuarantine;
		}
	}

	public float getInfectionProb() {
		return infectionProb;
	}

	public void setInfectionProb(float infectionProb) {
		this.infectionProb = infectionProb;
	}

	public String getDateLastTested() {
		return dateLastTested;
	}

	public void setDateLastTested(String dateLastTested) {
		this.dateLastTested = dateLastTested;
	}
}
