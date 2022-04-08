
public class Time {

	private int minute;
	private int seconds;
	private int totalSecond;
	
	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public Time(String s) {
		minute = minute +Integer.parseInt(s.substring(0, s.length()-3));
		seconds = seconds + Integer.parseInt(s.substring(s.length()-2));
	}
	
	public Time(int n) {
		this.minute = 0;
		this.seconds = n;
	}
	
	public String toString() {
		return String.format("%d:%d", minute, seconds);
	}
	
}
