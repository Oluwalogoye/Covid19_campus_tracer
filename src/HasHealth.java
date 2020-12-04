
public abstract class HasHealth {

	// data members
	private boolean isInfected;
	private boolean isQuarantined;
	private boolean isImmune;
	private int daysInQuarantine;
	
	// constructor
	public HasHealth(boolean isInfected, boolean isQuarantined, boolean isImmune, int daysInQuarantine) {
		this.isInfected = isInfected;
		this.isQuarantined = isQuarantined;
		this.isImmune = isImmune;
		this.daysInQuarantine = daysInQuarantine;
	}

	// methods
	public boolean getIsInfected() {
		return isInfected;
	}

	public void setIsInfected(boolean isInfected) {
		this.isInfected = isInfected;
	}

	public boolean getIsQuarantined() {
		return isQuarantined;
	}

	public void setIsQuarantined(boolean isQuarantined) {
		this.isQuarantined = isQuarantined;
	}

	public boolean getIsImmune() {
		return isImmune;
	}

	public void setIsImmune(boolean isImmune) {
		this.isImmune = isImmune;
	}

	public int getDaysInQuarantine() {
		return daysInQuarantine;
	}

	public void setDaysInQuarantine(int daysInQuarantine) {
		this.daysInQuarantine = daysInQuarantine;
		if(daysInQuarantine > 14){
			this.daysInQuarantine = 0;
			this.isQuarantined = false;
			this.isInfected = false;
			this.isImmune = true;
		}
	}

	
}
