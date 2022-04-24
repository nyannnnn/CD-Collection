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
		return String.format("CD Title: %s%nNumber of Songs: %d%nTotal Time: %s%n", this.title, this.numSong,
				this.time.toString());
	}

	public void addSong(Song s) {
		SongList.add(s);
		this.time.add(s.getTime());
		numSong++;
	}

	public void displayAllSongs() {
		System.out.println();
		for (int i = 0; i < this.SongList.size(); i++) {
			System.out.println("#" + (i + 1) + " " + SongList.get(i).getTitle());
		}
		System.out.println();
	}

	public void removeSongNum(int index) {
		this.time.minus(SongList.get(index).getTime());
		numSong--;
		SongList.remove(index);
	}

	public void removeTitle(String title) {
		ArrayList <Song> copy = this.getSongList();
		Collections.sort(copy);
		int index = Collections.binarySearch(copy, new Song(title, null, null, 0, new Time(0)));
		if (index >= 0) {
			this.numSong--;
			this.time.minus(SongList.get(index).getTime());
			SongList.remove(index);
			
			int left = index - 1;
			int right = index + 1;
			while(left >= 0) {
				if(!this.SongList.get(index).getTitle().equalsIgnoreCase(title)) {
					break;
				}
				else {
					this.numSong--;
					this.time.minus(SongList.get(index).getTime());
					SongList.remove(index);
					left--;
				}
			}
			
			while(right < SongList.size()) {
				if(!this.SongList.get(index).getTitle().equalsIgnoreCase(title)) {
					break;
				}
				else {
					this.numSong--;
					this.time.minus(SongList.get(index).getTime());
					SongList.remove(index);
					right++;
				}
			}
			
		} else {
			System.out.println("Cannot find the song");
		}
	}
	
	public void sortTitle() {
		Collections.sort(SongList);
	}

	public void sortArtist() {
		Collections.sort(SongList, new compareArtist());
	}

	public void sortTime() {
		Collections.sort(SongList, new compareTime());
	}

	public void displaySong(int index) {
		System.out.println(this.SongList.get(index - 1));
	}

}
