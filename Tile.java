abstract class Tile{

	private final int numTilesToMove;

	Tile(int num){
		this.numTilesToMove = num;
	}

	int move(){
		return this.numTilesToMove;
	}

	@Override
	public String toString(){
		return "" + this.getClass().getName() + " " + this.numTilesToMove;
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

	private static final int throwBack = RandomNumberGenerator.getANum(1,10);

	SnakeTile(){
		super(throwBack);
	}

}

class VultureTile extends Disastrous{

	private static final int throwBack = RandomNumberGenerator.getANum(1,10);

	VultureTile(){
		super(throwBack);
	}
}

class CricketTile extends Disastrous{

	private static final int throwBack = RandomNumberGenerator.getANum(1,10);

	CricketTile(){
		super(throwBack);
	}

}

class WhiteTile extends SupportingTiles{

	WhiteTile(){
		super(0);
	}

}

class TrampolineTile extends SupportingTiles{

	private static final int moveForward = RandomNumberGenerator.getANum(1,10);

	TrampolineTile(){
		super(moveForward);
	}
}