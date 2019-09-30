import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;

final class Race implements Serializable{

	private final int trackLength;
	private static transient String prompt = ">>";
	private static Scanner sc = new Scanner(System.in);
	private RaceTrack track;
	private Player currentPlayer;
	private int currentTile = 1;
	private int numMoves = 0;
	private static transient Computer comp = new Computer();
	private static final long serialVersionUID = 2;

	private int snakeBites = 0;
	private int vultureBites = 0;
	private int cricketBites = 0;
	private int trampolines = 0;


	private boolean checkPoint1 = false;
	private boolean checkPoint2 = false;
	private boolean checkPoint3 = false;

	Race(){
		System.out.println(prompt + "Enter total number of tiles on the race track (length) (between 100 and 99999999 (both inclusive))");
		boolean input = false;
		int length = 0;

		while (!input){
			try{
				length = sc.nextInt();
				if (length>99999999){
					System.out.println(prompt + "Too much to handle, try with a smaller number.");
					continue;
				}if (length < 100){
					System.out.println(prompt + "Too less chosen, try with a greater number.");
					continue;
				}
				input = true;
			}catch(InputMismatchException e){
				System.out.println(prompt + "Invalid input. Try again.");
				sc.next();
			}
		}

		this.trackLength = length;

		setUpRaceTrack(this.trackLength);

		System.out.println(prompt + "Enter the player name.");
		String name = "";
		sc.nextLine();

		while (name.isBlank()){
			name = sc.nextLine();

			if (name.isBlank()){
				System.out.println(prompt + "Invalid name. Try again...");
			}
		}

		currentPlayer = new Player(name, this.trackLength);
	}

	Race(String name, int trackLength){
		currentPlayer = new Player(name, trackLength);
		this.trackLength = trackLength;
		setUpRaceTrack(this.trackLength);
	}


	@Override
	public boolean equals(Object o){

		if (o!=null && o.getClass() == getClass()){
			Race r = (Race) o;

			if (this.trackLength == r.trackLength &&
				this.currentPlayer.getName().equals(r.currentPlayer.getName()) &&
				this.currentTile == r.currentTile){
				return true;
			}


		}return false;
	}

	public int getTrackLength(){
		return this.trackLength;
	}

	public int getCurrentTile(){
		return this.currentTile;
	}

	public void setCurrentTile(int num){
		this.currentTile = num;
	}

	private void setUpRaceTrack(int numTiles){
		System.out.println(prompt + "Setting up race track...");
		track = new RaceTrack(numTiles);
	}

	void startRace() throws IOException, ClassNotFoundException{
		System.out.println(prompt + "Starting the game with " + currentPlayer.getName() + " at Tile-1.");
		System.out.println(prompt + "Control transferred to Computer for rolling the Dice for " + currentPlayer.getName() + ".");
		System.out.print(prompt + "Hit enter to start the game.");
		String keyPressed = sc.nextLine();

		if (!keyPressed.equals("")){
			System.out.println("Enter key not detected. Exiting...");
			return;
		}else{
			System.out.println(prompt + "Game Started================>");
			raceTOwin(true);
		}
	}


	private void raceProgress() throws IOException{

		if (currentTile >= trackLength*0.75 && !checkPoint3){
			checkPoint3 = true;
			saveGame(3);

		}else if (currentTile >= trackLength*0.5 && !checkPoint2){
			checkPoint2 = true;
			saveGame(2);

		}else if (currentTile >= trackLength*0.25 && !checkPoint1){
			checkPoint1 = true;
			saveGame(1);
		}
	}


	private void saveGame(int num) throws IOException{
		System.out.printf("Checkpoint %d reached. Would you like to save? (Yes/No)\n", num);

		String response = sc.nextLine();

		if (response.equals("Yes")){
			this.serialize();
			throw new GameSavedException("Game Saved.");
		}else{
			System.out.println("Continuing the race...");
		}

	}


	private void raceTOwin(boolean cagedAtstart) throws IOException{

		try{
			while (currentTile!=trackLength){

				try{
					this.raceProgress();
				}catch(GameSavedException s){
					System.out.println(s.getMessage());
					return;
				}
				

				int rolled = comp.rollDice();
				numMoves++;
				System.out.printf("%s[Roll-%d]: %s rolled %s at Tile-%d, ", prompt, numMoves, currentPlayer.getName(), rolled, currentTile);
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

					if (currentTile > trackLength){
						currentTile = trackLength;
						System.out.println("landed on Tile " + currentTile);
						checkWinner(numMoves);
					}
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

						if (currentTile<1){
							currentTile = 1;
							max_back = true;
						}

						if (currentTile>trackLength){
							max_forward = true;
							currentTile = trackLength;
							System.out.println("\t" + currentPlayer.getName() + " moved to Tile-" + trackLength);
							checkWinner(numMoves);
						}

						if (currentTile == 1){
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

			checkWinner(numMoves);

		}catch(GameWinnerException won){
			File temp = new File(this.currentPlayer.getName() + ".txt");
			if (temp.exists()){
				temp.delete();
			}
			System.out.println("\t\t\t" + won.getMessage());
			System.out.println("\t\t\t" + "Total Snake Bites = " + snakeBites);
			System.out.println("\t\t\t" + "Total Vulture Bites = " + vultureBites);
			System.out.println("\t\t\t" + "Total Cricket Bites = " + cricketBites);
			System.out.println("\t\t\t" + "Total trampolines = " + trampolines);
		}

	}

	void checkWinner(int numMoves){
		throw new GameWinnerException(currentPlayer.getName() + " won the race in " + numMoves + " rolls!");
	}

	public void continueRace() throws IOException{
		this.raceTOwin(false);
	}


	public void serialize() throws IOException{
		ObjectOutputStream out = null;
		String filename = currentPlayer.getName() + ".txt";

		try{
			out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(this);
		}finally{
			out.close();
		}

	}

}