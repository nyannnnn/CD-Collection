import java.util.*;

public class compareTime implements Comparator<Song> {
	public int compare(Song o1, Song o2) {

		return o1.convert() - o2.convert();

	}
}
