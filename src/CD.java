//******************************************************************
//Name: Max Luo
//Date: 4/25/2022
//Description: This is the CD class which stores the CD objects and Song ArrayLists
//******************************************************************
import java.util.ArrayList;
import java.util.Collections;

public class CD {

	//initialization
	private String title;
	private int numSong;
	private Time time;
	private ArrayList<Song> SongList = new ArrayList<>();

	//getters and setters
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

	//constructors
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

	// Description: adds a song
	// parameters: object song
	// return: nothing because the method is void
	public void addSong(Song s) {
		SongList.add(s);
		this.time.add(s.getTime());
		numSong++;
	}

	// Description: displays all songs in a songlist
	// parameters: nothing
	// return: nothing because the method is void
	public void displayAllSongs() {
		System.out.println();
		for (int i = 0; i < this.SongList.size(); i++) {
			System.out.println("#" + (i + 1) + " " + SongList.get(i).getTitle());
		}
		System.out.println();
	}

	// Description: removes a song by its index
	// parameters: int index
	// return: nothing because the method is void
	public void removeSongNum(int index) {
		this.time.minus(SongList.get(index).getTime());
		numSong--;
		SongList.remove(index);
	}

	// Description: remove song by its title
	// parameters: String title
	// return: nothing because the method is void
	public void removeTitle(String title) {
		//creates a copy of the current songList that is still linked
		ArrayList <Song> copy = this.getSongList();
		Collections.sort(copy);
		int index = Collections.binarySearch(copy, new Song(title, null, null, 0, new Time(0)));
		//same algorithm for movies project, binary search one position and expand left and right
		if (index >= 0) {
			this.numSong--;
			this.time.minus(SongList.get(index).getTime());
			SongList.remove(index);
			
			int left = index - 1;
			int right = index + 1;
			//checking left
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
			//checking right
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
	
	// Description: sort by title
	// parameters: nothing
	// return: nothing because the method is void
	public void sortTitle() {
		Collections.sort(SongList);
	}

	// Description: sort by title
	// parameters: nothing
	// return: nothing because the method is void
	public void sortArtist() {
		Collections.sort(SongList, new compareArtist());
	}

	// Description: sort by time
	// parameters: nothing
	// return: nothing because the method is void
	public void sortTime() {
		Collections.sort(SongList, new compareTime());
	}

	// Description: displays the current song
	// parameters: nothing
	// return: nothing because the method is void
	public void displaySong(int index) {
		System.out.println(this.SongList.get(index - 1));
	}

}
