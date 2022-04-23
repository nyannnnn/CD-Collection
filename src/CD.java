import java.util.ArrayList;
import java.util.Collections;

public class CD {

	private String title;
	private int numSong;
	private Time time;
	private ArrayList<Song> SongList = new ArrayList<>();
	
	public String getTitle() {
		return this.title;
	}
	
	public int getNumSong() {
		return this.numSong;
	}
		
	public ArrayList<Song> getSongList() {
		return SongList;
	}

	public Time getTime() {
		return time;
	}

	public CD(String title, int numSong) {
		this.title = title;
		this.numSong = numSong;
		this.SongList = new ArrayList<Song>();
		this.time = new Time(0);
	}
	
	public CD(CD c) {
		this.title = "copy " + c.getTitle();
		this.numSong = c.getNumSong();
		this.SongList = new ArrayList<>(c.getSongList());
		this.time = c.getTime();
	}
	
	public String toString() {
		return String.format("CD Title: %s%nNumber of Songs: %d%nTotal Time: %s%n", this.title, this.numSong, this.time.toString());
	}
	
	public void addSong(Song s) {
		SongList.add(s);
		this.time.add(s.getTime());
		numSong++;
	}
	
	public void displayAllSongs() {
		System.out.println();
		for(int i = 0; i < this.SongList.size(); i++) {
			System.out.println("#" + (i+1) + " " + SongList.get(i).getTitle());
		}
		System.out.println();
	}
	
	public void removeSongNum(int index) {
		SongList.remove(index);
		//implement subtracting time
		numSong--;
	}
	
	public void sortTitle() {
		Collections.sort(SongList);
	}
	
	public void displaySong(int index) {
		System.out.println(this.SongList.get(index-1));
	}
	
}
