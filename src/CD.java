import java.util.ArrayList;

public class CD {

	private String title;
	private int numSong;
	private Time time;
	private ArrayList<Song> SongList = new ArrayList<>();
	
	public String getTitle() {
		return this.title;
	}
	
	public CD(String title, int numSong) {
		this.title = title;
		this.numSong = numSong;
		this.SongList = new ArrayList<Song>();
		this.time = new Time(0);
	}
	
	public String toString() {
		return String.format("CD Title: %s%nNumber of Songs: %d%nTotal Time: %s%n", this.title, this.numSong, this.time.toString());
	}
	
	public void addSong(Song s) {
		SongList.add(s);
		this.time.add(s.getTime());
	}
	
	public void displayAllSongs() {
		for(int i = 0; i < this.SongList.size(); i++) {
			System.out.println("#" + (i+1) + " " + SongList.get(i).getTitle());
		}
	}
	
	public void displaySong(int index) {
		System.out.println(this.SongList.get(index));
	}
	
}
