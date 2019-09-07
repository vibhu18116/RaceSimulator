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

	private final Tile board[];


	RaceTrack(int totalTiles){

		this.totalTiles = totalTiles;

		int fraction = RandomNumberGenerator.getANum(3,10);
		this.toPutTiles = (int) totalTiles/fraction;

		int remaining = this.toPutTiles;
		this.snakeTiles = RandomNumberGenerator.getANum(1,remaining);

		remaining -= this.snakeTiles;
		this.vultureTiles = RandomNumberGenerator.getANum(1,remaining);

		remaining -= this.vultureTiles;
		this.trampolineTiles = RandomNumberGenerator.getANum(1,remaining);

		remaining -= this.trampolineTiles;
		this.cricketTiles = remaining;

		board = new Tile[totalTiles];

		decidePositions(this.snakeTiles, "Snake");
		decidePositions(this.vultureTiles, "Vulture");
		decidePositions(this.cricketTiles, "Cricket");
		decidePositions(this.trampolineTiles, "Trampoline");

		for (int i = 0; i<totalTiles; i++){
			if (board[i] == null){
				board[i] = new WhiteTile();
			}

			// System.out.println(board[i]);
		}

		System.out.printf(">>Danger: There are %d, %d, %d Snake, Vultures and Crickets respectively on your track!!\n\n", this.snakeTiles, this.vultureTiles, this.cricketTiles);
		System.out.printf(">>Danger: Each Snake, Vulture and Cricket can throw you back by %d, %d and %d number of tiles respectively.\n\n", -1*new SnakeTile().move(), -1*new VultureTile().move(), -1*new CricketTile().move());
		System.out.printf(">>Good News: There are %d number of Trampolines on your Track!\n\n", this.trampolineTiles);
		System.out.printf(">>Good News: Each trampoline can help you advance by %d number of tiles.\n\n", new TrampolineTile().move());
	}


	void decidePositions(int numPos, String type){

		while (numPos != 0){
			int pos = RandomNumberGenerator.getANum(1,totalTiles)-1;

			if (pos == 0){
				continue;
			}

			if (board[pos] == null){
				if (type.equals("Cricket")){
					board[pos] = new CricketTile();

				}else if (type.equals("Vulture")){
					board[pos] = new VultureTile();

				}else if (type.equals("Snake")){
					board[pos] = new SnakeTile();

				}else if (type.equals("Trampoline")){
					board[pos] = new TrampolineTile();
				}

				numPos--;
			}
		}

	}

	Tile getTile(int tileNUM){
		return board[tileNUM-1];
	}
}