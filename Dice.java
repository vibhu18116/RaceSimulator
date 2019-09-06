import java.util.Random;

class Dice{

	private final int numFaces;
	private static RandomNumberGenerator rg = new RandomNumberGenerator();

	Dice(){
		this.numFaces = 6;
	}

	Dice(int faces){
		this.numFaces = faces;
	}

	int roll(){
		return rg.getANum(1,numFaces);
	}

}