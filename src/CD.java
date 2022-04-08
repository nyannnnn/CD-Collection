import java.util.ArrayList;

public class CD {

	private String title;
	private int numSong;
	private Time time;
	private ArrayList<Song> SongList = new ArrayList<>();
	
	public CD(String title, int numSong) {
		this.title = title;
		this.numSong = numSong;
	}
	
	public String toString() {
		return String.format("CD Title: %s%nNumber of Songs: %d%nTotal Time: %s%n", this.title, this.numSong, this.time.toString());
	}
	
	public void addSong(Song s) {
		SongList.add(s);
	}
	
	public int addTime(Time t) {
		
	}
}
