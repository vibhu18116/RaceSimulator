import org.junit.Test;
import static org.junit.Assert.*;

public class TestCricketBite{

	@Test(expected = CricketBiteException.class)
	public void testVultureBite(){
		CricketTile c_tile = new CricketTile();
		c_tile.shake();
	}
}