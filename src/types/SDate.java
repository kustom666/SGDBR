package types;

public class SDate extends Types{
	private int year;
	private int month;
	private int day;

	public SDate(){

	}

	public SDate(int yI, int mI, int dI){
		this.year = yI;
		this.month = mI;
		this.day = dI;
	}

	public String toString(){
		String y = String.valueOf(year);
		String m = String.valueOf(month);
		String d = String.valueOf(day);

		return y+m+d;
	}

	public String typeToString(){
		return "Date";
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
