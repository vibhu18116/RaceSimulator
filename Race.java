import java.util.Scanner;

class Race{

	private final int trackLength;
	private String prompt = ">>";
	private static Scanner sc = new Scanner(System.in);

	Race(){
		System.out.println(prompt + "Enter total number of tiles on the race track (length)");
		int length = sc.nextInt();

		if (length < 100){
			System.out.println(prompt + "Too less chosen. Assigning default 100 tiles.");
			length = 100;
		}

		this.trackLength = length;
	}

	private void setUpRaceTrack(int numTiles){
		System.out.println("Setting up race track...");
	}

	void startRace(){
		

	}
}