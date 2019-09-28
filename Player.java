import java.io.*;

final class Player implements Serializable{

	private final String name;

	Player(String name){
		this.name = name;
	}

	String getName(){
		return this.name;
	}
}


final class Computer{

	private final Dice dice = new Dice();

	public int rollDice(){
		int numDice = dice.roll();
		return numDice;
	}
}