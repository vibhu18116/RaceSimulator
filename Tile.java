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


	abstract void shake();

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


final class SnakeTile extends Disastrous{

	private static final int throwBack = RandomNumberGenerator.getANum(1,10);

	SnakeTile(){
		super(throwBack);
	}

	@Override
	void shake(){
		throw new SnakeBiteException("Hiss...! I am a Snake, you go back " + throwBack + " tiles!");
	}

}

final class VultureTile extends Disastrous{

	private static final int throwBack = RandomNumberGenerator.getANum(1,10);

	VultureTile(){
		super(throwBack);
	}

	@Override
	void shake(){
		throw new VultureBiteException("Yapping...! I am a Vulture, you go back " + throwBack + " tiles!");
	}
}

final class CricketTile extends Disastrous{

	private static final int throwBack = RandomNumberGenerator.getANum(1,10);

	CricketTile(){
		super(throwBack);
	}

	@Override
	void shake(){
		throw new CricketBiteException("Chirp...! I am a Cricket, you go back " + throwBack + " tiles!");
	}

}

final class WhiteTile extends SupportingTiles{

	WhiteTile(){
		super(0);
	}

	@Override
	void shake(){
		throw new WhiteTileException("I am a white tile!");
	}

}

final class TrampolineTile extends SupportingTiles{

	private static final int moveForward = RandomNumberGenerator.getANum(1,10);

	TrampolineTile(){
		super(moveForward);
	}

	@Override
	void shake(){
		throw new TrampolineException("PingPong! I am a Trampoline, you advance" + moveForward + " tiles!");
	}
}