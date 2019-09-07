import java.util.Random;

class Dice{

	private final int numFaces;

	Dice(){
		this.numFaces = 6;
	}

	Dice(int faces){
		this.numFaces = faces;
	}

	int roll(){
		return RandomNumberGenerator.getANum(1,numFaces);
	}
}