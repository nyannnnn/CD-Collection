//******************************************************************
//Name: Max Luo
//Date: 4/25/2022
//Description: This time class contains the time object
//******************************************************************
public class Time {

	//initalization
	private int minute;
	private int seconds;
	
	//getters and setters
	public int getMinute() {
		return minute;
	}

	public int getSeconds() {
		return seconds;
	}

	//constructors
	public Time(String s) {
		minute = minute + Integer.parseInt(s.substring(0, s.indexOf(":")));
		seconds = seconds + Integer.parseInt(s.substring(s.indexOf(":")+1));
	}
	
	public Time(int n) {
		this.minute = 0;
		this.seconds = n;
		
	}
	
	// Description: adds the time
	// parameters: Time t
	// return: nothing because the method is void
	public void add(Time t) {
		int add = this.minute * 60 + this.seconds + t.minute * 60 + t.seconds;
		this.minute = add / 60;
		this.seconds = add % 60;
	}
	
	// Description: minus the time
	// parameters: Time t
	// return: nothing because the method is void
	public void minus(Time t) {
		int minus = this.minute * 60 + this.seconds - t.minute * 60 - t.seconds;
		this.minute = minus / 60;
		this.seconds = minus % 60;
	}
	
	public String toString() {
		return String.format("%02d:%02d", minute, seconds);
	}
	
}
