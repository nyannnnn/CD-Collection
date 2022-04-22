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

		int mainMenuChoice = 0, subMenuChoice = 0;

		while (true) {
			try {
				mainMenuChoice = displayMenu(0, stdIn);
			} catch (NumberFormatException e) {
				System.out.println("That choice was not a number");
				continue;
			}
			//MENU 1
			if (mainMenuChoice == 1) {
				while (true) {
					try {
						subMenuChoice = displayMenu(1, stdIn);
					} catch (NumberFormatException e) {
						System.out.println("That choice was not a number");
						continue;
					}
					if (subMenuChoice == 1) {
						listCD(CDList);
					} 
					else if (subMenuChoice == 2) {
						try {
							listCD(CDList);
							System.out.print("Enter the CD you want to access: ");
							int num = Integer.parseInt(sc.nextLine()) - 1;
							if(num > CDList.size()-1) {
								throw new NumberFormatException();
							}
							displayCD(num, CDList);
						}catch(NumberFormatException e) {
							System.out.println("This CD does not exist");
						}
					}
					else if (subMenuChoice == 3) {
						System.out.print("Enter the file name: ");
						String name = sc.nextLine();
						addCD(name, CDList);
					} 
					else if (subMenuChoice == 4) {
						listCD(CDList);
						System.out.print("Enter the CD index: ");
						int num = Integer.parseInt(sc.nextLine()) - 1;
						removeCD(num, CDList);
					}
					else if(subMenuChoice == 5) {
						listCD(CDList);
						System.out.println("Enter the CD index you want to copy");
						int num = Integer.parseInt(sc.nextLine()) - 1;
						copyCD(num, CDList);
					}
					else if(subMenuChoice == 6) {
						listCD(CDList);
						System.out.print("Enter the sub CD starting index: ");
						int num = Integer.parseInt(sc.nextLine()) - 1;
						System.out.print("Enter the sub CD starting index: ");
						int starting = Integer.parseInt(sc.nextLine());
						System.out.print("Enter the sub CD ending index: ");
						int ending = Integer.parseInt(sc.nextLine());
						
					}
					else if (subMenuChoice == 8) {
						break;
					} else {
						System.out.println("Choice does not exist, please re-enter");
					}
				}
			} 
			//MENU 2
			else if (mainMenuChoice == 2) {
				int choice = 0;
				listCD(CDList);
				if (CDList.size() == 0) {
					System.out.println("CDList is empty, please go to submenu 1");
					continue;
				}
				System.out.print("Enter the # of the CD you want to access: ");
				while (true) {
					choice = Integer.parseInt(sc.nextLine());
					if (choice > CDList.size()) {
						System.out.println("CD #" + choice + "does not exist");
					} else {
						choice--;
						break;
					}
				}
				while (true) {
					subMenuChoice = displayMenu(2, stdIn);
					System.out.println();
					if (subMenuChoice == 1) {
						CDList.get(choice).displayAllSongs();
					} else if (subMenuChoice == 2) {
						CDList.get(choice).displayAllSongs();
						System.out.print("Enter the song that you want to display: ");
						int song = Integer.parseInt(sc.nextLine());
						CDList.get(choice).displaySong(song);
						System.out.println();
					} else if (subMenuChoice == 6) {
						break;
					} else {
						System.out.println("Choice does not exist, please re-enter");
					}
				}
			} else if (mainMenuChoice == 3) {
				break;
			} else {
				System.out.println("Choice does not exist, please re-enter");
			}
		}

	}

	public static void subCD(int num, int starting, int ending, ArrayList<CD> CDList) {
		
		try {
			CD copy = new CD("sub " + CDList.get(ending), ending - starting);
			for(int i = starting-1; i <= ending; i++) {
				copy.addSong(CDList.get(i).getSongList().get(starting));
			}
			CDList.add(copy);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("The indexs are out of bound");
		}
		
	}
	
	public static void copyCD(int index, ArrayList<CD> CDList) {
		try {
			CD copy = new CD(CDList.get(index));
			CDList.add(copy);			
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("The CD does not exist");
		}
	}
	
	public static void removeCD(int index, ArrayList<CD> CDList) {
		try {
			
			CDList.remove(index);
			
		}catch(IndexOutOfBoundsException e) {
			System.out.println("The CD does not exist");
		}
	}

	public static void displayCD(int index, ArrayList<CD> CDList) {
		if (CDList.size() < 1) {
			System.out.println("CDList is empty");
		} else {
			System.out.println(CDList.get(index));
		}
	}

	public static void listCD(ArrayList<CD> CDList) {
		if (CDList.size() < 1) {
			System.out.println("CDList is empty");
		} else {
			int i = 1;
			for (CD c : CDList) {
				System.out.println("CD # " + i + c.getTitle());
				i++;
			}
		}
	}

	public static void addCD(String name, ArrayList<CD> CDList) {
		try {
			BufferedReader inFile = new BufferedReader(new FileReader(name.toLowerCase() + ".txt"));
			String title = inFile.readLine();
			int numSong = Integer.parseInt(inFile.readLine());
			CD cd = new CD(title, numSong);
			for (int i = 0; i < numSong; i++) {
				String songTitle = inFile.readLine();
				String artist = inFile.readLine();
				String genre = inFile.readLine();
				int rating = Integer.parseInt(inFile.readLine());
				String time = inFile.readLine();
				if (time.length() <= 2) {
					cd.addSong(new Song(songTitle, artist, genre, rating, new Time(Integer.parseInt(time))));
				}
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
