package types;

public class Date extends Types{
	private int year;
	private int month;
	private int day;
	
	public Date(int yI, int mI, int dI){
		this.year = yI;
		this.month = mI;
		this.day = dI;
	}
	
	public toString(){
		
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
}
