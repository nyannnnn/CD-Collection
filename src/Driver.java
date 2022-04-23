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
			// MENU 1
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
					} else if (subMenuChoice == 2) {
						try {
							listCD(CDList);
							System.out.print("Enter the CD you want to access: ");
							int num = Integer.parseInt(sc.nextLine()) - 1;
							if (num > CDList.size() - 1) {
								throw new NumberFormatException();
							}
							displayCD(num, CDList);
						} catch (NumberFormatException e) {
							System.out.println("This CD does not exist");
						}
					} else if (subMenuChoice == 3) {
						System.out.print("Enter the file name: ");
						String name = sc.nextLine();
						addCD(name, CDList);
					} else if (subMenuChoice == 4) {
						listCD(CDList);
						System.out.print("Enter the CD index: ");
						int num = Integer.parseInt(sc.nextLine()) - 1;
						removeCD(num, CDList);
					} else if (subMenuChoice == 5) {
						listCD(CDList);
						System.out.print("Enter the CD index you want to copy: ");
						int num = Integer.parseInt(sc.nextLine()) - 1;
						copyCD(num, CDList);
					} else if (subMenuChoice == 6) {
						listCD(CDList);
						System.out.print("Enter the CD index you want to copy: ");
						int num = Integer.parseInt(sc.nextLine()) - 1;
						System.out.print("Enter the sub CD starting song: ");
						int starting = Integer.parseInt(sc.nextLine());
						System.out.print("Enter the sub CD ending song: ");
						int ending = Integer.parseInt(sc.nextLine());
						subCD(num, starting, ending, CDList);
					} else if (subMenuChoice == 7) {
						listCD(CDList);
						System.out.print("Enter the first CD index: ");
						int start = Integer.parseInt(sc.nextLine()) - 1;
						System.out.print("Enter the second CD index: ");
						int end = Integer.parseInt(sc.nextLine()) - 1;
						listComm(start, end, CDList);
					} else if (subMenuChoice == 8) {
						break;
					} else {
						System.out.println("Choice does not exist, please re-enter");
					}
				}
			}
			// MENU 2
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
					} else if (subMenuChoice == 3) {
						String title = "";
						String artist = "";
						String genre = "";
						int rating = 0;
						String time = "";

						while (true) {
							System.out.print("Enter the song title: ");
							title = sc.nextLine();
							System.out.print("Enter the song artist: ");
							artist = sc.nextLine();
							System.out.print("Enter the song genre: ");
							genre = sc.nextLine();
							System.out.print("Enter the song rating (out of 5): ");
							try {
								rating = Integer.parseInt(sc.nextLine());
								if (rating > 5) {
									throw new NumberFormatException();
								}
							} catch (NumberFormatException e) {
								System.out.println("The rating must be out of 5 and has to be an integer");
								continue;
							}
							System.out.print("Enter the time (mm:ss): ");
							try {
							time = sc.nextLine();
								if(time.indexOf(":") == -1) {
									throw new NumberFormatException();
								}
							}catch(NumberFormatException e) {
								System.out.println("The format you entered is invalid");
								continue;
							}
							System.out.println();
							break;
						}
						addASong(title, artist, genre, rating, time, CDList.get(choice));
					} else if (subMenuChoice == 4) {
						while (true) {
							System.out.print(
									"1: Remove by song # \n2:Remove by song title \n3:Remove first song \n4:Remove last song \nEnter your choice: ");
							int option = Integer.parseInt(sc.nextLine());
							if (option == 1) {
								CDList.get(choice).displayAllSongs();
								System.out.print("Enter the song number you want to remove: ");
								int index = Integer.parseInt(sc.nextLine()) - 1;
								CDList.get(choice).removeSongNum(index);
								System.out.println("Song removed");
								break;
							} else if (option == 2) {
								System.out.print("Enter the song title you want to remove: ");
								String title = sc.nextLine();
								CDList.get(choice).removeTitle(title);
							} else if (option == 3) {
								CDList.get(choice).removeSongNum(1);
								System.out.println("Song removed");
								break;
							} else if (option == 4) {
								CDList.get(choice).removeSongNum(CDList.get(choice).getSongList().size() - 1);
								System.out.println("Song removed");
								break;
							}
						}
					} else if (subMenuChoice == 5) {
						while(true) {
							System.out.print("1: Sort by Title\n2: Sort by artist\n3: Sort by time\n\nEnter your choice: ");
							int option = Integer.parseInt(sc.nextLine());
							if(option == 1) {
								CDList.get(choice).sortTitle();
								CDList.get(choice).displayAllSongs();
								break;
							}
							else if(option == 2) {
								CDList.get(choice).sortArtist();
								CDList.get(choice).displayAllSongs();
								break;
							}
							else if(option == 3) {
								CDList.get(choice).sortTime();
								CDList.get(choice).displayAllSongs();
								break;
							}
						}
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

	public static void addASong(String title, String artist, String genre, int rating, String time, CD cd) {
		cd.addSong(new Song(title, artist, genre, rating, new Time(time)));
	}

	public static void listComm(int index1, int index2, ArrayList<CD> CDList) {

		try {

			CD c1 = CDList.get(index1);
			CD c2 = CDList.get(index2);

			int index = Math.min(c1.getNumSong(), c2.getNumSong());

			for (int i = 0; i < index; i++) {
				// comparing address
				if (c2.getSongList().contains(c1.getSongList().get(i))) {
					System.out.println(c1.getSongList().get(i));
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("The indexs are out of bound");
		}

	}

	public static void subCD(int num, int starting, int ending, ArrayList<CD> CDList) {

		try {
			CD copy = new CD(("sub " + CDList.get(num).getTitle()), (ending - starting + 1));
			for (int i = starting - 1; i < ending; i++) {
				copy.addSong(CDList.get(num).getSongList().get(i));
			}
			CDList.add(copy);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("The indexs are out of bound");
		}

	}

	public static void copyCD(int index, ArrayList<CD> CDList) {
		try {
			CD copy = new CD(CDList.get(index));
			CDList.add(copy);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("The CD does not exist");
		}
	}

	public static void removeCD(int index, ArrayList<CD> CDList) {
		try {

			CDList.remove(index);

		} catch (IndexOutOfBoundsException e) {
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
		System.out.println();
		if (CDList.size() < 1) {
			System.out.println("CDList is empty");
		} else {
			int i = 1;
			for (CD c : CDList) {
				System.out.println("CD# " + i + " " + c.getTitle());
				i++;
			}
			System.out.println();
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
