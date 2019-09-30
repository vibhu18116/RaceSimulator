import org.junit.Test;
import static org.junit.Assert.*;

public class TestSnakeBite{

	@Test(expected = SnakeBiteException.class)
	public void testSnakeBite(){
		SnakeTile s_tile = new SnakeTile();
		s_tile.shake();
	}
}