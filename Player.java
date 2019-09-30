import java.io.*;

final class Player implements Serializable{

	private final String name;
	private final int trackLength;
	private int currentPos;

	Player(String name, int l){
		this.name = name;
		this.trackLength = l;
	}

	String getName(){
		return this.name;
	}

	void setCurrent(int val){
		this.currentPos = val; 
	}

	void checkWinner(){
		if (this.trackLength == this.currentPos){
			throw new GameWinnerException(this.getName() + " won the race.");
		}
	}

}


final class Computer{

	private final Dice dice = new Dice();

	public int rollDice(){
		int numDice = dice.roll();
		return numDice;
	}
}