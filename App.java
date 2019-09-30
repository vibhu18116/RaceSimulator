import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class App{

	public static Race loadSavedGame(String playerName) throws IOException, ClassNotFoundException{
		ObjectInputStream in = null;

		try{
			String filename = playerName + ".txt";
			File file = new File(filename);

			if (!file.exists()){
				System.out.println("No previous game record corresponding to given player found. Exiting...");
				return null;
			}		
			in = new ObjectInputStream(new FileInputStream(filename));
			Race r = (Race) in.readObject();
			return r;

		}finally{
			if (in != null){
				in.close();
			}			
		}

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException{

		System.out.println("Choose an option.");
		System.out.println("1) Start a new game.");
		System.out.println("2) Load an existing game.");
		System.out.println("3) Exit");

		Scanner sc = new Scanner(System.in);

		boolean valid = false;

		while (!valid){
			int ans = 0;
			try{
				ans = sc.nextInt();
			}catch(InputMismatchException e){
				System.out.println("Invalid input. Exiting");
				break;
			}
			

			if (ans == 1){
				valid = true;
				Race r = new Race();
				r.startRace();
			}else if (ans == 2){
				valid = true;
				System.out.println("Enter player name.");
				sc.nextLine();
				String name = sc.nextLine();

				Race r = loadSavedGame(name);

				if (r!=null){
					r.continueRace();
				}
				

			}else if (ans == 3) return;
			else{
				System.out.println("Invalid option. Try again.");
			}
		}	
	}
}