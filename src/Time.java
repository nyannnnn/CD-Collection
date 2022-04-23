
public class Time {

	private int minute;
	private int seconds;
	private int totalSecond;
	
	public int getMinute() {
		return minute;
	}

	public int getSeconds() {
		return seconds;
	}

	public Time(String s) {
		minute = minute + Integer.parseInt(s.substring(0, s.length()-3));
		seconds = seconds + Integer.parseInt(s.substring(s.length()-2));
	}
	
	public Time(int n) {
		this.minute = 0;
		this.seconds = n;
	}
	
	public void add(Time t) {
		int add = this.minute * 60 + this.seconds + t.minute * 60 + t.seconds;
		this.minute = add / 60;
		this.seconds = add % 60;
	}
	
	public String toString() {
		return String.format("%02d:%02d", minute, seconds);
	}
	
}
