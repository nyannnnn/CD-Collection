
public class Song {

	private String title;
	private String artist;
	private String genre;
	private int rating;
	private Time time;

	public Song(String title, String artist, String genre, int rating, Time time) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.rating = rating;
		this.time = time;
	}

	public String toString() {
		return String.format("Song Title: %s%nArtist: %s%nGenre: %s%nRating: %d%nTime: %s%n", this.title, this.artist,
				this.genre, this.rating, this.time.toString());
	}
	
}
