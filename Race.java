import java.util.Scanner;
import java.util.InputMismatchException;

class Race{

	private final int trackLength;
	private String prompt = ">>";
	private static Scanner sc = new Scanner(System.in);
	private RaceTrack track;
	private Player currentPlayer;
	private int currentTile = 1;
	private int numMoves = 0;

	private int snakeBites = 0;
	private int vultureBites = 0;
	private int cricketBites = 0;
	private int trampolines = 0;

	Race(){
		System.out.println(prompt + "Enter total number of tiles on the race track (length)");
		boolean input = false;
		int length = 0;

		while (!input){
			try{
				length = sc.nextInt();
				input = true;
			}catch(InputMismatchException e){
				System.out.println("Invalid input. Try again.");
				sc.next();
			}
		}
		

		if (length < 100){
			System.out.println(prompt + "Too less chosen. Assigning default 100 tiles.");
			length = 100;
		}

		this.trackLength = length;

		setUpRaceTrack(this.trackLength);

		System.out.println(prompt + "Enter the player name.");
		sc.nextLine();
		String name = sc.nextLine();
		currentPlayer = new Player(name);
	}

	private void setUpRaceTrack(int numTiles){
		System.out.println(prompt + "Setting up race track...");
		track = new RaceTrack(numTiles);
	}

	void startRace(){
		System.out.println(prompt + "Starting the game with " + currentPlayer.getName() + " at Tile-1.");
		System.out.println(prompt + "Control transferred to Computer for rolling the Dice for " + currentPlayer.getName() + ".");
		System.out.print(prompt + "Hit enter to start the game.");
		String keyPressed = sc.nextLine();

		if (!keyPressed.equals("")){
			System.out.println("Enter key not detected. Exiting...");
			return;
		}else{
			System.out.println(prompt + "Game Started================>");
			raceTOwin();
		}
	}


	private void raceTOwin(){

		Computer comp = new Computer();
		boolean cagedAtstart = true;

		try{
			while (currentTile!=trackLength){
				int rolled = comp.rollDice();
				numMoves++;
				System.out.printf("%s[Roll-%d]: %s rolled %s at Tile-%d. ", prompt, numMoves, currentPlayer.getName(), rolled, currentTile);
				if (currentTile == 1){
					if (rolled!=6 && cagedAtstart){
						System.out.println("OOPs you need 6 to start.");
					}else if (rolled == 6 && cagedAtstart){
							System.out.println("You are out of the cage! You get a free roll.");
							cagedAtstart = false;
					}else if (!cagedAtstart){
						currentTile += rolled;
						System.out.println("landed on Tile " + currentTile);
					}
				}else{
					currentTile += rolled;
					System.out.println("landed on Tile " + currentTile);
				}

				if (currentTile!=1){
					System.out.println(prompt + "\tTrying to shake the Tile-" + currentTile);
					
					Tile current = track.getTile(currentTile);

					try{
						current.shake();

					}catch (SnakeBiteException sbe){
						snakeBites++;
						System.out.println("\t" + sbe.getMessage());

					}catch (VultureBiteException vbe){
						vultureBites++;
						System.out.println("\t" + vbe.getMessage());

					}catch (CricketBiteException cbe){
						cricketBites++;
						System.out.println("\t" + cbe.getMessage());

					}catch (TrampolineException te){
						trampolines++;
						System.out.println("\t" + te.getMessage());
					
					}catch (WhiteTileException wte){
						System.out.println("\t" + wte.getMessage());

					}finally{
						currentTile += current.move();
						boolean max_back = false;
						boolean max_forward = false;

						if (currentTile<0){
							currentTile = 0;
							max_back = true;
						}

						if (currentTile>trackLength){
							max_forward = true;
							currentTile = trackLength;
							System.out.println("\t" + currentPlayer.getName() + " moved to Tile-" + trackLength);
							throw new GameWinnerException(currentPlayer.getName() + " won the race in " + numMoves + " rolls!");
						}

						if (currentTile == 0){
							cagedAtstart = true;
						}

						System.out.print("\t" + currentPlayer.getName() + " moved to Tile-" + currentTile);

						if (max_back){
							System.out.println(", as it can't go any back further.");
						}else{
							System.out.println();
						}
					}
				}
			}

			throw new GameWinnerException(currentPlayer.getName() + " won the race in " + numMoves + " rolls!");

		}catch(GameWinnerException won){
			System.out.println("\t\t\t" + won.getMessage());
			System.out.println("\t\t\t" + "Total Snake Bites = " + snakeBites);
			System.out.println("\t\t\t" + "Total Vulture Bites = " + vultureBites);
			System.out.println("\t\t\t" + "Total Cricket Bites = " + cricketBites);
			System.out.println("\t\t\t" + "Total trampolines = " + trampolines);
		}

	}

}