import java.io.*;
import java.util.*;

public class Driver {

	public static int displayMenu(int menuNum, BufferedReader stdIn) throws IOException {

		if (menuNum == 0) {
			System.out.println("----------  MAIN MENU  -----------");
			System.out.println("1) Accessing your list of CDs");
			System.out.println("2) Accessing within a particular CD");
			System.out.println("3) Exit");
			System.out.println("----------------------------------");
		} else if (menuNum == 1) {
			System.out.println("\n---------  SUB-MENU #1  ----------");
			System.out.println("1) Display all of your CDs"); // Just the CD titles, numbered in order
			System.out.println("2) Display info on a particular CD");
			System.out.println("3) Add a CD"); // Adds a CD by reading from input file
			System.out.println("4) Remove a CD");
			System.out.println("5) Copy a CD");
			System.out.println("6) Create a sub-CD");
			System.out.println("7) List songs in common between two CDs");
			System.out.println("8) Return back to main menu.");
			System.out.println("----------------------------------");
		} else {
			System.out.println("\n---------  SUB-MENU #2  ----------");
			System.out.println("1) Display all songs (in the last sorted order) ");
			System.out.println("2) Display info on a particular song ");
			System.out.println("3) Add song");
			System.out.println("4) Remove Song (4 options)");
			System.out.println("5) Sort songs (3 options)");
			System.out.println("6) Return back to main menu");
			System.out.println("----------------------------------");
		}

		System.out.print("\n\nPlease enter your choice:  ");

		int choice = Integer.parseInt(stdIn.readLine());

		return choice;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		ArrayList<CD> CDList = new ArrayList<>();

		int mainMenuChoice, subMenuChoice;

		while (true) {
			mainMenuChoice = displayMenu(0, stdIn);
			if (mainMenuChoice == 1) {
				while (true) {
					subMenuChoice = displayMenu(1, stdIn);
					if (subMenuChoice == 1) {
						listCD(CDList);
					}
					if (subMenuChoice == 3) {
						String name = sc.nextLine();
						addCD(name, CDList);
					}
				}
			} else if (mainMenuChoice == 2) {
				subMenuChoice = displayMenu(2, stdIn);
			} else if (mainMenuChoice == 3) {
				break;
			}
		}

	}

	public static void displayCD(int index, ArrayList<CD> CDList) {
		System.out.println(CDList.get(index));
	}

	public static void listCD(ArrayList<CD> CDList) {
		for (CD c : CDList) {
			System.out.println(c);
		}
	}

	public static void addCD(String name, ArrayList<CD> CDList) {
		try {
			BufferedReader inFile = new BufferedReader(new FileReader(name + ".txt"));
			String title = inFile.readLine();
			int numSong = Integer.parseInt(inFile.readLine());
			CD cd = new CD(title, numSong);
			for(int i = 0; i < numSong; i++) {
				String songTitle = inFile.readLine();
				String artist = inFile.readLine();
				String genre = inFile.readLine();
				int rating = Integer.parseInt(inFile.readLine());
				String time = inFile.readLine();
				cd.addSong(new Song(songTitle, artist, genre, rating, new Time(time)));
			}
			CDList.add(cd);
			System.out.println("added");
			inFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception!!!");
		} catch (IOException e) {
			System.out.println("IOException!!!");
		}

	}
}
