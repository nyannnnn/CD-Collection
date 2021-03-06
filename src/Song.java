//******************************************************************
//Name: Max Luo
//Date: 4/25/2022
//Description: This is the song class which contains the song object
//******************************************************************
public class Song implements Comparable<Song>{

	//initalization
	private String title;
	private String artist;
	private String genre;
	private int rating;
	private Time time;
	
	//getters and setters
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public Time getTime() {
		return time;
	}

	//constructor
	public Song(String title, String artist, String genre, int rating, Time time) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.rating = rating;
		this.time = time;
	}

	//overriding for contains
	public boolean equals(Object o) {
		Song s = (Song) o;
		return this.title.equalsIgnoreCase(s.getTitle());
	}
	
	// Description: converts to seconds
	// parameters: nothing
	// return: nothing because the method is void
	public int convert()
	{
		return time.getMinute() * 60 + time.getSeconds();
	}
	
	public String toString() {
		return String.format("Song Title: %s%nArtist: %s%nGenre: %s%nRating: %d%nTime: %s%n", this.title, this.artist,
				this.genre, this.rating, this.time.toString());
	}

	//compare title
	public int compareTo(Song o) {

		return this.title.compareToIgnoreCase(o.getTitle());
	
	}
	
}
