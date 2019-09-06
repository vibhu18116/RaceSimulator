import java.util.Random;

class RandomNumberGenerator{

	private static Random randGen = new Random();

	int getANum(int start, int end){
		return randGen.nextInt(end-start+1) + start;
	}

}