abstract class Tile{

	private final int numTilesToMove;

	Tile(int num){
		this.numTilesToMove = num;
	}

	int move(){
		return this.numTilesToMove;
	}

}

abstract class SupportingTiles extends Tile{

	SupportingTiles(int moves){
		super(moves);
	}

}


abstract class Disastrous extends Tile{

	Disastrous(int moves){
		super(-1*moves);
	}

}


class SnakeTile extends Disastrous{

	SnakeTile(){
		super(RandomNumberGenerator.getANum(1,10));
	}

}

class VultureTile extends Disastrous{

	VultureTile(){
		super(RandomNumberGenerator.getANum(1,10));
	}
}

class CricketTile extends Disastrous{

	CricketTile(){
		super(RandomNumberGenerator.getANum(1,10));
	}

}

class WhiteTile extends SupportingTiles{

	WhiteTile(){
		super(0);
	}

}

class TrampolineTile extends SupportingTiles{

	TrampolineTile(){
		super(RandomNumberGenerator.getANum(1,10));
	}

}