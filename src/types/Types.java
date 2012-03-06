package types;

public abstract class Types {
	private boolean isPrimary;
	private boolean isCandidate;
	private Types foreign = null;
	
	//Accesseurs
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public boolean isCandidate() {
		return isCandidate;
	}
	public void setCandidate(boolean isCandidate) {
		this.isCandidate = isCandidate;
	}
	public Types getForeign() {
		return foreign;
	}
	public void setForeign(Types foreign) {
		this.foreign = foreign;
	}
	
}
