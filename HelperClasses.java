import java.util.Random;

class RandomNumberGenerator{

	private static Random randGen = new Random();

	static int getANum(int start, int end){
		if (end < start)
			return 0;
		return randGen.nextInt(end-start+1) + start;
	}
}

class RaceTrack{

	private final int totalTiles;
	private final int toPutTiles;
	private final int snakeTiles;
	private final int vultureTiles;
	private final int cricketTiles;
	private final int trampolineTiles;


	RaceTrack(int totalTiles){

		this.totalTiles = totalTiles;

		int fraction = RandomNumberGenerator.getANum(3,10);
		this.toPutTiles = (int) totalTiles/fraction;

		int remaining = this.toPutTiles;
		this.snakeTiles = RandomNumberGenerator.getANum(1,remaining);

		remaining -= this.snakeTiles;
		this.vultureTiles = RandomNumberGenerator.getANum(1,remaining);

		remaining -= this.vultureTiles;
		this.cricketTiles = RandomNumberGenerator.getANum(1,remaining);

		remaining -= this.cricketTiles;
		this.trampolineTiles = remaining;
	}
}