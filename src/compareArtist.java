import java.util.*;
public class compareArtist implements Comparator<Song>{

	public int compare(Song o1, Song o2) {

		return o1.getArtist().compareToIgnoreCase(o2.getArtist());
		
	}

}
